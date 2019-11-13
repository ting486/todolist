package ui;

import model.Observer;
import model.exceptions.*;
import model.*;
import network.ReadWebPage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


public class ToDoList implements Saveable, Loadable {

    public Map<String, Item> toDoMap;
    public Map<String, Item> doneMap;
    public SchoolList schoolList;
    private Observer subscriber;

    private Scanner scanner;
    private SimpleDateFormat formatter = new SimpleDateFormat(Item.FORMATTER_PATTERN);

    private static final String FILE_NAME = "./data/listData.txt";
    private static final String OPERATION_GREETING = "\r What would you like to do? \r\n 1: add a regular item \r\n "
            + "2: add an urgent item \r\n "
            + "3: cross off an item \r\n 4: show all the items \r\n 5: quit \r\n Enter the number: ";



    // EFFECTS: constructs a new ToDoList
    public ToDoList() throws FileNotFoundException, UnsupportedEncodingException {
        scanner = new Scanner(System.in);
        toDoMap = new HashMap<>();
        doneMap = new HashMap<>();
        schoolList = new SchoolList();
        subscriber = new Subscriber("user");
        schoolList.addObserver(subscriber);
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


    // MODIFIES: this
    // EFFECTS: adds an item to the to-do list
    private void operationAddRegular() throws ParseException, IOException {
        Item regularItem = new RegularItem();

        System.out.println("Type '1' to put it under School; type other letters to put it just under regular:");
        if (scanner.nextLine().equals("1")) {
            operationAddSchool();
        } else {
//            System.out.println("Enter the title:");
            String inputTitle = validInputTitle();
//            System.out.println("Enter the content: (do NOT use the symbol '==')");
//            String inputContent = validInputContent();
//            regularItem.setContent(inputContent);
//            System.out.println("When is it due? Enter the date in the form: dd/MM/yyyy");
//            String inputDateStr = validInputDateStr();
//            regularItem.setDue(formatter.parse(inputDateStr));
            //regularItem = validInputItem(regularItem);

            //if (isValidInputItem(validInputItem(regularItem))) {
            //System.out.println("is a valid input regular item");
            Item validRegularItem = validInputItem(regularItem);
            toDoMap.put(inputTitle, validRegularItem);
            saveToFile();
            //}
        }

    }

    // MODIFIES: this
    // EFFECTS: adds an item to the to-do list
    private void operationAddSchool() throws ParseException, IOException {
        Item regularItem = new RegularItem();

//        System.out.println("Enter the title:");
        String inputTitle = validInputTitle();
        System.out.println("Enter the content (do NOT use the symbol '=='):");
        String inputContent = validInputContent();
        regularItem.setContent(inputContent);
        System.out.println("When is it due? Enter the date in the form dd/MM/yyyy:");
        String inputDateStr = validInputDateStr();
        regularItem.setDue(formatter.parse(inputDateStr));

        //Item validRegularItem = validInputItem(regularItem);

//        if (regularItem.isInSchool()) {
//            System.out.println("schoolList not null!!!");
//        }
        //if (isValidInputItem(validRegularItem)) {
        regularItem.addSchoolList(schoolList);
        toDoMap.put(inputTitle, regularItem);
        saveToFile();
        //}
    }


    // MODIFIES: this
    // EFFECTS: adds an item to the to-do list
    private void operationAddUrgent() throws ParseException, IOException {
        Item urgentItem = new UrgentItem();

//        System.out.println("Enter the title:");
        String inputTitle = validInputTitle();
//        System.out.println("Enter the content: (do NOT use the symbol '==')");
//        String inputContent = validInputContent();
//        urgentItem.setContent(inputContent);
//        System.out.println("When is it due? Enter the date in the form: dd/MM/yyyy");
//        String inputDateStr = validInputDateStr();
//        urgentItem.setDue(formatter.parse(inputDateStr));

        toDoMap.put(inputTitle, validInputItem(urgentItem));
        saveToFile();
    }


    // EFFECTS: checks whether the item is equal to any item already in toDoMap
//    private boolean isValidInputItem(Item item) {
//        for (Map.Entry<String, Item> entry : toDoMap.entrySet()) {
//            //String k = entry.getKey();
//            Item v = entry.getValue();
//            if (v.equals(item)) {
//                return false;
//            }
//        }
//        return true;
//    }

    // EFFECTS: returns a valid item such that we can save it into toDoMap or doneMap w/out causing errors/exceptions
    private Item validInputItem(Item item) throws ParseException {
        System.out.println("Enter the content: (do NOT use the symbol '==')");
        String inputContent = validInputContent();
        item.setContent(inputContent);

        System.out.println("When is it due? Enter the date in the form: dd/MM/yyyy");
        String inputDateStr = validInputDateStr();
        item.setDue(formatter.parse(inputDateStr));

        return item;
    }

    // EFFECTS: returns a non-duplicate title
    private String validInputTitle() {
        System.out.println("Enter the title:");
        String inputTitle = scanner.nextLine();

        while (toDoMap.get(inputTitle) != null) {
            System.out.println("The title already exists. Retry.");
            inputTitle = scanner.nextLine();
        }

        return inputTitle;
    }

    // EFFECTS: returns a non-null, non-empty content which has no '==' symbol in it
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

    // EFFECTS: returns a date in dd/MM/yyyy String form (which can be parsed correctly)
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



    // MODIFIES: this
    // EFFECTS: deletes an item from the to-do list, and move it to the crossed-off list; if there
    //          is nothing to be deleted, return to the main menu (i.e.: returning to processOperations();)
    private void operationMarkCompleted() throws IOException {

        System.out.println("Here are all the to-do's: ");
        operationDisplayTodo();

        if (toDoMap.size() == 0) {
            System.out.println("You have no items to delete.");
        } else {
            System.out.println("Which entry would you like to delete? Enter the entry title: ");
            String entryTitle = getValidEntryTitle();

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
                if (!toDoMap.containsKey(entryTitle)) {
                    throw new InvalidCrossingOffException();
                }
                break;
            } catch (InvalidCrossingOffException e) {
                System.out.println("Cannot find the item with this title. Try again:");
                //scanner.next();
            } finally {
                System.out.println("This is just a finally clause... To satisfy deliverable 6 requirement lol");
            }
        }
        return entryTitle;
    }



    // EFFECTS: displays both do-list list and crossed-off list
    private void operationDisplay() {
        System.out.println("To-do list:");
        operationDisplayTodo();
        System.out.println("\r\r" + "School to-do's:");
        operationDisplaySchoolItems();
        System.out.println("\r\r" + "Crossed-off list: ");
        operationDisplayDone();
    }


    // EFFECTS: displays the to-do list
    private void operationDisplayTodo() {
        //toDoMap.forEach((k,i) -> System.out.println(k + ": " + i.printItem()));
        operationDisplayMap(toDoMap);
    }

    // EFFECTS: displays the school to-do's
    private void operationDisplaySchoolItems() {
        toDoMap.forEach((k,i) -> {
            if (i.isInSchool()) {
                System.out.println(k + ": " + i.printItem());
            }
        });
    }

    // EFFECTS: displays the crossed-off list
    private void operationDisplayDone() {
        //doneMap.forEach((k,i) -> System.out.println(k + ": " + i.printItem()));
        operationDisplayMap(doneMap);
    }

    private void operationDisplayMap(Map<String, Item> map) {
        map.forEach((k,i) -> System.out.println(k + ": " + i.printItem()));
    }


    // EFFECTS: saves ToDoList to /data/listData.txt
    @Override
    public void saveToFile() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(FILE_NAME,"UTF-8");

        toDoMap.forEach((k,i) -> {
//            System.out.println("saving to todoMap...");
//            System.out.println(k + "==" + i.getContent() + "==" + formatter.format(i.getDue()) + "=="
//                    + i.getStatus() + "==" + i.getUrg() + "==" + i.isInSchool());
            writer.println(k + "==" + i.getContent() + "==" + formatter.format(i.getDue()) + "=="
                    + i.getStatus() + "==" + i.getUrg() + "==" + i.isInSchool());
        }
        );

        doneMap.forEach((k,i) -> writer.println(k + "==" + i.getContent() + "==" + formatter.format(i.getDue()) + "=="
                + i.getStatus() + "==" + i.getUrg() + "==" + i.isInSchool()));
        writer.close();
    }

//    private void saveMap(Map<String, Item> map) {
//        map.forEach((k,i) -> writer.println(k + "==" + i.getContent() + "==" + formatter.format(i.getDue()) + "=="
//                + i.getStatus() + "==" + i.getUrg() + "==" + i.isInSchool()));
//    }


    // EFFECTS: loads info in /data/listData.txt to ToDoList
    @Override
    public void loadFile() throws IOException, ParseException {
        List<String> lines = Files.readAllLines(Paths.get(FILE_NAME));
        toDoMap.clear();
        doneMap.clear();
        schoolList.clearSchoolItems();

        for (String line : lines) {
            ArrayList<String> pol = splitByPart(line);

            if (!Boolean.parseBoolean(pol.get(3))) {
                loadToToDo(pol);
            } else {
                loadToDone(pol);
            }
        }
    }


    // EFFECTS: loads part of info in /data/listData.txt to toDoMap
    private void loadToToDo(ArrayList<String> pol) throws ParseException {
        Item regularItem = new RegularItem();
        Item urgentItem = new UrgentItem();
        String title = pol.get(0);

        if (Boolean.parseBoolean(pol.get(4))) {
            urgentItem.setThis(pol.get(1), formatter.parse(pol.get(2)), Boolean.parseBoolean(pol.get(3)));
            if (Boolean.parseBoolean(pol.get(5))) {
                schoolList.addItem(urgentItem);
            }
            toDoMap.put(title, urgentItem);

        } else {
            regularItem.setThis(pol.get(1), formatter.parse(pol.get(2)), Boolean.parseBoolean(pol.get(3)));
            if (Boolean.parseBoolean(pol.get(5))) {
                schoolList.addItem(regularItem);
            }
            toDoMap.put(title, regularItem);
        }
    }

    // EFFECTS: loads part of info in /data/listData.txt to doneMap
    private void loadToDone(ArrayList<String> pol) throws ParseException {
        Item regularItem = new RegularItem();
        Item urgentItem = new UrgentItem();

        if (Boolean.parseBoolean(pol.get(4))) {
            urgentItem.setThis(pol.get(1), formatter.parse(pol.get(2)), Boolean.parseBoolean(pol.get(3)));
            doneMap.put(pol.get(0), urgentItem);
            //putIntoMap(doneMap, pol, urgentItem);
        } else {
            regularItem.setThis(pol.get(1), formatter.parse(pol.get(2)), Boolean.parseBoolean(pol.get(3)));
            doneMap.put(pol.get(0), regularItem);
            //putIntoMap(doneMap, pol, regularItem);
        }
    }



    private void putIntoMap(Map<String, Item> map, ArrayList<String> pol, Item item) throws ParseException {
        item.setThis(pol.get(1), formatter.parse(pol.get(2)), Boolean.parseBoolean(pol.get(3)));
        map.put(pol.get(0), item);
    }


    // EFFECTS: splits line into a list of Strings, separated by "=="
    private ArrayList<String> splitByPart(String line) {
        String[] splits = line.split("==");
        return new ArrayList<>(Arrays.asList(splits));
    }

}
