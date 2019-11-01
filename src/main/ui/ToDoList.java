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
//    public List<Item> toDoItems;
    //public ArrayList<RegularItem> doneList;
//    public ArrayList<Item> doneItems;
    private Scanner scanner;
    private static final String OPERATION_GREETING = "What would you like to do? \r\n 1: add a regular item \r\n "
            + "2: add an urgent item \r\n "
            + "3: cross off an item \r\n 4: show all the items \r\n 5: quit \r\n Enter the number: ";
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public Map<String, Item> toDoMap;
    public Map<String, Item> doneMap;
    public SchoolList schoolList;


    // EFFECTS: constructs a new ToDoList
    public ToDoList() {
        //regularToDoItems = new ArrayList<>();
        //urgentToDoItems = new ArrayList<>();
//        toDoItems = new ArrayList<>();
//        doneItems = new ArrayList<>();
        scanner = new Scanner(System.in);

        toDoMap = new HashMap<>();
        doneMap = new HashMap<>();
        schoolList = new SchoolList();
    }


    // EFFECTS: directs the user to corresponding to-do list operations (adding a regular/an urgent item, crossing off
    //          an entry, displaying the list or quitting the program) depending on the user input
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

        System.out.println("Type '1' to put it under School; type other letters to put it just under regular:");
        if (scanner.nextLine().equals("1")) {
            operationAddSchool();
        } else {
//            System.out.println("THIS IS JUST REGULAR");
            System.out.println("Enter the title:");
            String inputTitle = validInputTitle();
//            System.out.println("Enter the content (do NOT use the symbol '=='):");
//            String inputContent = validInputContent();
//            regularItem.setContent(inputContent);
//            System.out.println("When is it due? Enter the date in the form dd/MM/yyyy:");
//            String inputDateStr = validInputDateStr();
//            regularItem.setDue(formatter.parse(inputDateStr));

//            System.out.println("Do you want to put it under School category? Type 'yes' or 'no': ");
//            String isSchool = scanner.nextLine();       // isSchool = true: put it under this category

//        if (isSchool.equals("yes")) {
//            System.out.println("adding regularItem to schoolList");
//            regularItem.addSchoolList(schoolList);
//
//            if (regularItem.isInSchool()) {
//                System.out.println("schoolList not null!!!");
//            }
//        }

            if (isValidInputItem(validInputItem(regularItem))) {
                toDoMap.put(inputTitle, validInputItem(regularItem));
                saveToFile();
            }
        }
    }

    private boolean isValidInputItem(Item item) {
        for (Map.Entry<String, Item> entry : toDoMap.entrySet()) {
            //String k = entry.getKey();
            Item v = entry.getValue();
            if (v.equals(item)) {
                return false;
            }
        }
        return true;
    }

    private void operationAddSchool() throws ParseException, IOException {
        RegularItem regularItem = new RegularItem();

//        System.out.println("THIS IS SCHOOL");
        System.out.println("Enter the title:");
        String inputTitle = validInputTitle();
        System.out.println("Enter the content (do NOT use the symbol '=='):");
        String inputContent = validInputContent();
        regularItem.setContent(inputContent);
        System.out.println("When is it due? Enter the date in the form dd/MM/yyyy:");
        String inputDateStr = validInputDateStr();
        regularItem.setDue(formatter.parse(inputDateStr));


        regularItem.addSchoolList(schoolList);

        if (regularItem.isInSchool()) {
            System.out.println("schoolList not null!!!");
        }

        if (isValidInputItem(regularItem)) {
            toDoMap.put(inputTitle, regularItem);
            saveToFile();
        }

    }


    // REQUIRES: toDoList.size() < 1000
    // MODIFIES: this
    // EFFECTS: adds an item to the to-do list
    private void operationAddUrgent() throws ParseException, IOException {
        UrgentItem urgentItem = new UrgentItem();

        System.out.println("Enter the title:");
        String inputTitle = validInputTitle();
//        System.out.println("Enter the content: (do NOT use the symbol '==')");
//        String inputContent = validInputContent();
//        urgentItem.setContent(inputContent);
//        System.out.println("When is it due? Enter the date in the form: dd/MM/yyyy");
//        String inputDateStr = validInputDateStr();
//        urgentItem.setDue(formatter.parse(inputDateStr));


//        toDoItems.add(urgentItem);
        toDoMap.put(inputTitle, validInputItem(urgentItem));
        saveToFile();
    }

    private Item validInputItem(Item item) throws ParseException {
        System.out.println("Enter the content: (do NOT use the symbol '==')");
        String inputContent = validInputContent();
        item.setContent(inputContent);
        System.out.println("When is it due? Enter the date in the form: dd/MM/yyyy");
        String inputDateStr = validInputDateStr();
        item.setDue(formatter.parse(inputDateStr));
        return item;
    }


    private String validInputTitle() {
        String inputTitle = scanner.nextLine();
        while (toDoMap.get(inputTitle) != null) {
            System.out.println("The title already exists. Retry.");
            inputTitle = scanner.nextLine();
        }
        return inputTitle;
    }

    // EFFECTS: check whether inputContent is valid (not null or empty)
    private String validInputContent() {
        String inputContent;
        while (true) {
            try {
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
        return inputContent;
    }

    // EFFECTS: check whether inputDateStr is valid (in dd/MM/yyyy form, which can be parsed correctly)
    private String validInputDateStr() {
        String inputDateStr;
        while (true) {
            try {
                inputDateStr = scanner.nextLine();
                formatter.parse(inputDateStr);
                break;
            } catch (ParseException e) {
                System.out.println("Incorrect date format. Try again:");
                scanner.next();
            }
        }
        return inputDateStr;
    }

//    private boolean validInputBool() {
//        boolean inputBool;
//        while (true) {
//            try {
//                inputBool = scanner.nextBoolean();
//                break;
//            } catch (InputMismatchException e) {
//                System.out.println("Enter either 'true' or 'false'. Try again:");
//                scanner.nextBoolean();
//            }
//        }
//        return inputBool;
//    }




    // MODIFIES: this and doneList
    // EFFECTS: deletes an item from the to-do list, and move it to the crossed-off list; if there
    //          is nothing to be deleted, return to the main menu (i.e.: returning to processOperations();)
    private void operationMarkCompleted() throws IOException {
//        int entryNum;

        System.out.println("Here are all the to-do's: ");
        operationDisplayTodo();

//        if (toDoItems.size() == 0) {
//            System.out.println("You have no items to delete.");
//        } else {
//            System.out.println("Which entry would you like to delete? Enter the entry number: ");
//            entryNum = getValidEntryNum();
//
//            toDoItems.get(entryNum).setStatus(true);
//            doneItems.add(toDoItems.get(entryNum));
//            toDoItems.remove(entryNum);
//
//            saveToFile();
//        }
        if (toDoMap.size() == 0) {
            System.out.println("You have no items to delete.");
        } else {
            System.out.println("Which entry would you like to delete? Enter the entry title: ");
            String entryTitle = getValidEntryTitle();

//            toDoItems.get(entryNum).setStatus(true);
//            doneItems.add(toDoItems.get(entryNum));
//            toDoItems.remove(entryNum);
            toDoMap.get(entryTitle).setStatus(true);
            doneMap.put(entryTitle, toDoMap.get(entryTitle));
            toDoMap.remove(entryTitle);

            saveToFile();
        }
    }

    private String getValidEntryTitle() {
        String entryTitle;
        while (true) {
            try {
                entryTitle = scanner.nextLine();
                if (toDoMap.get(entryTitle) == null) {
                    throw new InvalidCrossingOffException();
                }
                break;
            } catch (InvalidCrossingOffException e) {
                System.out.println("Entered number is out of range. Try again:");
                //scanner.next();
            } finally {
                System.out.println("This is just a finally clause... To satisfy deliverable 6 requirement lol");
            }
        }
        return entryTitle;
    }



    // EFFECTS: displays both do-list list and crossed-off list
    private void operationDisplay() {
        //loadFile();
        System.out.println("To-do list: ");
        operationDisplayTodo();
        System.out.println("School to-do's: ");
        operationDisplaySchoolItems();
        System.out.println("Crossed-off list: ");
        operationDisplayDone();
    }


    // EFFECTS: displays the to-do list
    private void operationDisplayTodo() {
//        if (toDoItems.size() == 0) {
//            System.out.println("The to-do list is empty");
//        } else {
//            for (int i = 0; i < toDoItems.size(); i++) {
//                System.out.println(i + ": " + toDoItems.get(i).printItem());
//            }
//        }
        toDoMap.forEach((k,i) -> System.out.println(k + ": " + i.printItem()));
    }

    private void operationDisplaySchoolItems() {
        toDoMap.forEach((k,i) -> {
            if (i.isInSchool()) {
                System.out.println(k + ": " + i.printItem());
            }
        });
    }

    // EFFECTS: displays the crossed-off list
    private void operationDisplayDone() {
//        if (doneItems.size() == 0) {
//            System.out.println("The crossed-off list is empty");
//        } else {
//            for (int i = 0; i < doneItems.size(); i++) {
//                System.out.println(Integer.toString(i + 1000) + ": " + doneItems.get(i).printItem());
//            }
//        }
        doneMap.forEach((k,i) -> System.out.println(k + ": " + i.printItem()));
    }





    @Override
    public void saveToFile() throws IOException {
        PrintWriter writer = new PrintWriter("./data/listData.txt","UTF-8");

//        if (toDoItems.size() != 0) {
//            for (int i = 0; i < toDoItems.size(); i++) {
//                Item toDoItem = toDoItems.get(i);
//                writer.println(i + "==" + toDoItem.getContent() + "==" + formatter.format(toDoItem.getDue()) + "=="
//                        + toDoItem.getStatus() + "==" + toDoItem.getUrg());
//            }
//        }
//        if (doneItems.size() != 0) {
//            for (int i = 0; i < doneItems.size(); i++) {
//                Item doneItem = doneItems.get(i);
//                writer.println((i + 1000) + "==" + doneItem.getContent() + "==" + formatter.format(doneItem.getDue())
//                        + "==" + doneItem.getStatus() + "==" + doneItem.getUrg());
//            }
//        }
//        writer.close();

        toDoMap.forEach((k,i) -> {
            //System.out.println(k + ".isInSchool(): " + i.isInSchool());
            writer.println(k + "==" + i.getContent() + "==" + formatter.format(i.getDue()) + "=="
                    + i.getStatus() + "==" + i.getUrg() + "==" + i.isInSchool());
        }
        );

        doneMap.forEach((k,i) -> writer.println(k + "==" + i.getContent() + "==" + formatter.format(i.getDue()) + "=="
                + i.getStatus() + "==" + i.getUrg() + "==" + i.isInSchool()));
        writer.close();
    }



    @Override
    public void loadFile() throws IOException, ParseException {
        List<String> lines = Files.readAllLines(Paths.get("./data/listData.txt"));
//        toDoItems.clear();
//        doneItems.clear();
        toDoMap.clear();
        doneMap.clear();
        schoolList.clearSchoolItems();

        for (String line : lines) {
            ArrayList<String> partsOfLine = splitByPart(line);

//            if (Integer.parseInt(partsOfLine.get(0)) < 1000) {
//                loadToToDo(partsOfLine);
//            } else {
//                loadToDone(partsOfLine);
//            }

            if (!Boolean.parseBoolean(partsOfLine.get(3))) {
                loadToToDo(partsOfLine);
            } else {
                loadToDone(partsOfLine);
            }

            //loadItem(partsOfLine);
        }
    }

    /*
    public void loadItem(ArrayList<String> partsOfLine) throws ParseException {
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

    private void loadToToDo(ArrayList<String> pol) throws ParseException {
        Item regularItem = new RegularItem();
        Item urgentItem = new UrgentItem();
        //String title = partsOfLine.get(0);
        //boolean status = Boolean.parseBoolean(pol.get(3));

        if (Boolean.parseBoolean(pol.get(4))) {
            urgentItem.setThis(pol.get(1), formatter.parse(pol.get(2)), Boolean.parseBoolean(pol.get(3)));
//            toDoItems.add(urgentItem);
            if (Boolean.parseBoolean(pol.get(5))) {
                schoolList.addItem(urgentItem);
            }

            toDoMap.put(pol.get(0), urgentItem);
        } else {
            regularItem.setThis(pol.get(1), formatter.parse(pol.get(2)), Boolean.parseBoolean(pol.get(3)));
//            toDoItems.add(regularItem);
            if (Boolean.parseBoolean(pol.get(5))) {
                schoolList.addItem(regularItem);
            }

            toDoMap.put(pol.get(0), regularItem);
        }
    }

    private void loadToDone(ArrayList<String> pol) throws ParseException {
        Item regularItem = new RegularItem();
        Item urgentItem = new UrgentItem();
        //String title = pol.get(0);
        //Boolean status = Boolean.parseBoolean(pol.get(3));
        //Boolean urgency = Boolean.parseBoolean(pol.get(4));

        if (Boolean.parseBoolean(pol.get(4))) {
            urgentItem.setThis(pol.get(1), formatter.parse(pol.get(2)), Boolean.parseBoolean(pol.get(3)));
//            doneItems.add(urgentItem);
            doneMap.put(pol.get(0), urgentItem);
        } else {
            regularItem.setThis(pol.get(1), formatter.parse(pol.get(2)), Boolean.parseBoolean(pol.get(3)));
//            doneItems.add(regularItem);
            doneMap.put(pol.get(0), regularItem);
        }
    }



    public static ArrayList<String> splitByPart(String line) {
        String[] splits = line.split("==");
        return new ArrayList<>(Arrays.asList(splits));
    }


}
