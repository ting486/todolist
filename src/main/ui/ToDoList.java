package ui;

import model.exceptions.EmptyContentException;
import model.exceptions.InvalidCrossingOffException;
import model.exceptions.SpecialSymbolException;
import model.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;



public class ToDoList implements Saveable, Loadable {

    //public List<RegularItem> regularToDoItems;
    //public List<UrgentItem> urgentToDoItems;
    public List<Item> toDoItems;
    //public ArrayList<RegularItem> doneList;
    public ArrayList<Item> doneItems;
    private Scanner scanner;
    private static final String OPERATION_GREETING = "What would you like to do? \r\n 1: add a regular item \r\n "
            + "2: add an urgent item \r\n "
            + "3: cross off an item \r\n 4: show all the items \r\n 5: quit \r\n Enter the number: ";


    // EFFECTS: constructs a new ToDoList
    public ToDoList() {
        //regularToDoItems = new ArrayList<>();
        //urgentToDoItems = new ArrayList<>();
        toDoItems = new ArrayList<>();
        doneItems = new ArrayList<>();
        scanner = new Scanner(System.in);
    }


    // EFFECTS: directs the user to corresponding to-do list operations (adding an item, crossing off an entry,
    //          displaying the list or quitting the program) depending on the user input
    public void run() throws ParseException, IOException {
        while (true) {
            loadFile();
            System.out.println(OPERATION_GREETING);
            String operation = scanner.nextLine();

            if (operation.equals("5")) {
                break;
            } else if (operation.equals("1")) {
                operationAddRegular();
            } else if (operation.equals("2")) {
                operationAddUrgent();
            } else if (operation.equals("3")) {
                operationMarkCompleted();
            } else if (operation.equals("4")) {
                operationDisplay();
            } else {
                System.out.println("Invalid choice of operation. Try again.");
            }
        }
    }




    // REQUIRES: toDoList.size() < 1000;
    //           the first input (ie: the content) does not contain '==' or 'URGENT: '
    // MODIFIES: this
    // EFFECTS: adds an item to the to-do list
    private void operationAddRegular() throws ParseException, IOException {
        RegularItem regularItem = new RegularItem();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        //Boolean isItValid = false;

        System.out.println("Please enter the content: (do NOT use the symbol '==')");
        String inputContent = validInputContent();
        regularItem.setContent(inputContent);
        System.out.println("When is it due? Enter the date in the form: dd/MM/yyyy");
        /*
        String inputDateStr = scanner.nextLine();
        while (validateInputDateStr(inputDateStr) == false) {
            //isItValid = validateDateStr(dateStr);
            inputDateStr = scanner.nextLine();
            validateInputDateStr(inputDateStr);
        }
         */
        String inputDateStr = validInputDateStr();
        regularItem.setDue(formatter.parse(inputDateStr));

        toDoItems.add(regularItem);
        saveToFile();
    }

    // EFFECTS: check whether inputDateStr is valid (in dd/MM/yyyy form, which can be parsed correctly)
    public String validInputDateStr() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String inputDateStr;
        while (true) {
            try {
                inputDateStr = scanner.nextLine();
                formatter.parse(inputDateStr);
                break;
            } catch (ParseException e) {
                System.out.println("Incorrect date format. Try again:");
                //scanner.next();
            }
        }
        return inputDateStr;
    }

    // EFFECTS: check whether inputContent is valid (not null or empty)
    public String validInputContent() {
        String inputContent;
        while (true) {
            try {
                //System.out.println("Please enter the content: (do NOT use the symbol '==')");
                inputContent = scanner.nextLine();
                if (inputContent == null || inputContent.isEmpty()) {
                    throw new EmptyContentException();
                } else if (inputContent.contains("==")) {
                    throw new SpecialSymbolException();
                }
                break;
            } catch (EmptyContentException e) {
                System.out.println("Content cannot be empty. Try again: ");
                scanner.next();
            } catch (SpecialSymbolException e) {
                System.out.println("Content cannot contain the symbol '=='. Try again: ");
            }
        }
        //System.out.println("The returned input content is: " + inputContent);
        return inputContent;
    }


    // REQUIRES: toDoList.size() < 1000
    // MODIFIES: this
    // EFFECTS: adds an item to the to-do list
    private void operationAddUrgent() throws ParseException, IOException {
        UrgentItem urgentItem = new UrgentItem();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Please enter the content: (do NOT use the symbol '==')");
        /*
        try {
            validInputContent(inputContent);
        } catch (InvalidContentException e) {
            System.out.println("Content cannot be empty. Re-enter:");
            inputContent = scanner.nextLine();
        }
         */
        String inputContent = validInputContent();
        urgentItem.setContent(inputContent);

        System.out.println("When is it due? Enter the date in the form: dd/MM/yyyy");
        String inputDateStr = validInputDateStr();
        urgentItem.setDue(formatter.parse(inputDateStr));

        toDoItems.add(urgentItem);
        saveToFile();
    }

    // MODIFIES: this and doneList
    // EFFECTS: deletes an item from the to-do list, and move it to the crossed-off list; if there
    //          is nothing to be deleted, return to the main menu (i.e.: returning to processOperations();)
    private void operationMarkCompleted() throws IOException {
        int entryNum;

        System.out.println("Here are all the to-do's: ");
        operationDisplayTodo();
        if (toDoItems.size() == 0) {
            System.out.println("You have no items to delete.");
        } else {
            System.out.println("Which entry would you like to delete? Enter the entry number: ");
            //entryNum = scanner.nextInt();
            /* while (entryNum >= toDoItems.size() || entryNum <  0) {
                System.out.println("Please enter a valid number: ");
                entryNum = scanner.nextInt();
            } */
            entryNum = getValidEntryNum();

            toDoItems.get(entryNum).setStatus(true);
            doneItems.add(toDoItems.get(entryNum));
            toDoItems.remove(entryNum);

            saveToFile();
        }
    }

    public int getValidEntryNum() {
        int entryNum;
        while (true) {
            try {
                //System.out.println("Which entry would you like to delete? Enter the entry number: ");
                entryNum = scanner.nextInt();
                //validateCrossingOffOperation(entryNum);
                if (entryNum >= toDoItems.size() || entryNum <  0) {
                    throw new InvalidCrossingOffException();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("This is not a number. Try again:");
                scanner.next();
            } catch (InvalidCrossingOffException e) {
                System.out.println("Entered number is out of range. Try again:");
                //scanner.next();
            } finally {
                System.out.println("This is a finally clause... To satisfy deliv. 6 requirement lol");
            }
        }
        return entryNum;
    }

    /*
    public void validateCrossingOffOperation(int entryNum) throws InvalidCrossingOffException {
        if (entryNum >= toDoItems.size() || entryNum <  0) {
            throw new InvalidCrossingOffException();
        }
    }
     */

    // EFFECTS: displays both do-list list and crossed-off list
    private void operationDisplay() throws IOException, ParseException {
        //loadFile();
        System.out.println("To-do list: ");
        operationDisplayTodo();
        System.out.println("Crossed-off list: ");
        operationDisplayDone();
    }


    // EFFECTS: displays the to-do list
    private void operationDisplayTodo() {
        if (toDoItems.size() == 0) {
            System.out.println("The to-do list is empty");
        } else {
            for (int i = 0; i < toDoItems.size(); i++) {
                System.out.println(i + ": " + toDoItems.get(i).printItem());
            }
        }
    }

    // EFFECTS: displays the crossed-off list
    private void operationDisplayDone() {
        if (doneItems.size() == 0) {
            System.out.println("The crossed-off list is empty");
        } else {
            for (int i = 0; i < doneItems.size(); i++) {
                System.out.println(Integer.toString(i + 1000) + ": " + doneItems.get(i).printItem());
            }
        }
    }



    @Override
    public void saveToFile() throws IOException {
        PrintWriter writer = new PrintWriter("./data/listData.txt","UTF-8");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        if (toDoItems.size() != 0) {
            for (int i = 0; i < toDoItems.size(); i++) {
                Item toDoItem = toDoItems.get(i);
                writer.println(i + "==" + toDoItem.getContent() + "==" + formatter.format(toDoItem.getDue()) + "=="
                        + toDoItem.getStatus() + "==" + toDoItem.getUrg());
            }
        }
        if (doneItems.size() != 0) {
            for (int i = 0; i < doneItems.size(); i++) {
                Item doneItem = doneItems.get(i);
                writer.println((i + 1000) + "==" + doneItem.getContent() + "==" + formatter.format(doneItem.getDue())
                        + "==" + doneItem.getStatus() + "==" + doneItem.getUrg());
            }
        }
        writer.close();
    }



    @Override
    public void loadFile() throws IOException, ParseException {
        List<String> lines = Files.readAllLines(Paths.get("./data/listData.txt"));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        toDoItems.clear();
        doneItems.clear();

        for (String line : lines) {
            ArrayList<String> partsOfLine = splitByPart(line);
            //Item regularItem = new RegularItem();
            //Item urgentItem = new UrgentItem();

            if (Integer.parseInt(partsOfLine.get(0)) < 1000) {
                loadToToDo(partsOfLine);
            } else {
                loadToDone(partsOfLine);
            }
            //loadItem(partsOfLine);
        }
    }

    /*
    public void loadItem(ArrayList<String> partsOfLine) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Item regularItem = new RegularItem();
        Item urgentItem = new UrgentItem();
        Boolean status = Boolean.parseBoolean(partsOfLine.get(3));

        if (Integer.parseInt(partsOfLine.get(0)) < 1000) {
            if (partsOfLine.get(1).contains("URGENT: ")) { //Boolean.parseBoolean(partsOfLine.get(4)) == false
                urgentItem.setThis(partsOfLine.get(1), formatter.parse(partsOfLine.get(2)), status);
                toDoList.add(urgentItem);
            } else {
                regularItem.setThis(partsOfLine.get(1), formatter.parse(partsOfLine.get(2)), status);
                toDoList.add(regularItem);
            }
        } else {
            if (partsOfLine.get(1).contains("URGENT: ")) { //Boolean.parseBoolean(partsOfLine.get(4)) == false
                urgentItem.setThis(partsOfLine.get(1), formatter.parse(partsOfLine.get(2)), status);
                doneList.add(urgentItem);
            } else {
                urgentItem.setThis(partsOfLine.get(1), formatter.parse(partsOfLine.get(2)), status);
                doneList.add(regularItem);
            }
        }
    }
     */

    public void loadToToDo(ArrayList<String> partsOfLine) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Item regularItem = new RegularItem();
        Item urgentItem = new UrgentItem();
        Boolean status = Boolean.parseBoolean(partsOfLine.get(3));
        Boolean urgency = Boolean.parseBoolean(partsOfLine.get(4));

        if (urgency == true) { //Boolean.parseBoolean(partsOfLine.get(4)) == false
            urgentItem.setThis(partsOfLine.get(1), formatter.parse(partsOfLine.get(2)), status);
            toDoItems.add(urgentItem);
        } else {
            regularItem.setThis(partsOfLine.get(1), formatter.parse(partsOfLine.get(2)), status);
            toDoItems.add(regularItem);
        }
    }

    public void loadToDone(ArrayList<String> partsOfLine) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Item regularItem = new RegularItem();
        Item urgentItem = new UrgentItem();
        Boolean status = Boolean.parseBoolean(partsOfLine.get(3));
        Boolean urgency = Boolean.parseBoolean(partsOfLine.get(4));

        if (urgency == true) {
            urgentItem.setThis(partsOfLine.get(1), formatter.parse(partsOfLine.get(2)), status);
            doneItems.add(urgentItem);
        } else {
            regularItem.setThis(partsOfLine.get(1), formatter.parse(partsOfLine.get(2)), status);
            doneItems.add(regularItem);
        }
    }



    public static ArrayList<String> splitByPart(String line) {
        String[] splits = line.split("==");
        return new ArrayList<>(Arrays.asList(splits));
    }


}
