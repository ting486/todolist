package main.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;



public class ToDoList implements Saveable, Loadable {

    public ArrayList<Entry> toDoList;
    public ArrayList<Entry> doneList;
    private Scanner scanner;


    // EFFECTS: constructs a new ToDoList
    public ToDoList() throws ParseException, IOException {
        toDoList = new ArrayList<Entry>();
        doneList = new ArrayList<Entry>();
        scanner = new Scanner(System.in);
        //run();
    }


    // EFFECTS: directs the user to corresponding to-do list operations (adding an item, crossing off an entry,
    //          displaying the list or quitting the program) depending on the user input
    public void run() throws ParseException, IOException {
        //thisToDoList = new ToDoList();
        while (true) {
            loadFile();

            System.out.println("What would you like to do today? \r\n 1: add a to-do list item \r\n "
                    + "2: cross off an item \r\n 3: show all the items \r\n 4: quit \r\n Enter the number: ");
            String operation = scanner.nextLine();

            if (operation.equals("4")) {
                break;
            } else if (operation.equals("1")) {
                operationAdd();
            } else if (operation.equals("2")) {
                operationMarkCompleted();
            } else if (operation.equals("3")) {
                operationDisplay();
            }

            //saveToFile();
        }
    }


    // REQUIRES: toDoList.size() < 1000
    // MODIFIES: this
    // EFFECTS: adds an item to the to-do list
    private void operationAdd() throws ParseException, IOException {
        Entry entry = new Entry();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Please enter the content: (do NOT use the symbol '==')");
        entry.setContent(scanner.nextLine());
        System.out.println("When is it due? Enter the date in the form: dd/MM/yyyy");
        entry.setDue(formatter.parse(scanner.nextLine()));

        //System.out.println("You just added a new to-do list: ");
        //entry.printEntry();
        toDoList.add(entry);
        saveToFile();
    }

    // MODIFIES: this and doneList
    // EFFECTS: deletes an item from the to-do list, and move it to the crossed-off list; if there
    //          is nothing to be deleted, return to the main menu (i.e.: returning to processOperations();)
    private void operationMarkCompleted() throws IOException {
        int entryNum;

        System.out.println("Here are all the to-do's: ");
        operationDisplayTodo();
        if (toDoList.size() == 0) {
            System.out.println("You have no items to delete.");
        } else {
            System.out.println("Which entry would you like to delete? Enter the entry number: ");
            entryNum = scanner.nextInt();

            while (entryNum >= toDoList.size() || entryNum <  0) {
                System.out.println("Please enter a valid number: ");
                entryNum = scanner.nextInt();
            }

            toDoList.get(entryNum).setStatus(true);
            doneList.add(toDoList.get(entryNum));
            toDoList.remove(entryNum);

            saveToFile();

            //System.out.println("Updated to-do list: ");
            //operationDisplayTodo();
        }
    }

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
        if (toDoList.size() == 0) {
            System.out.println("The to-do list is empty");
        } else {
            for (int i = 0; i < toDoList.size(); i++) {
                System.out.println(i + ": " + toDoList.get(i).printEntry());
            }
        }
    }

    // EFFECTS: displays the crossed-off list
    private void operationDisplayDone() {
        if (doneList.size() == 0) {
            System.out.println("The crossed-off list is empty");
        } else {
            for (int i = 0; i < doneList.size(); i++) {
                System.out.println(Integer.toString(i + 1000) + ": " + doneList.get(i).printEntry());
            }
        }
    }



    @Override
    public void saveToFile() throws IOException {
        PrintWriter writer = new PrintWriter("./data/listData.txt","UTF-8");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        if (toDoList.size() != 0) {
            for (int i = 0; i < toDoList.size(); i++) {
                Entry toDoEntry = toDoList.get(i);
                writer.println(i + "==" + toDoEntry.getContent() + "==" + formatter.format(toDoEntry.getDue())
                        + "==" + toDoEntry.getStatus());
            }
        }
        if (doneList.size() != 0) {
            for (int i = 0; i < doneList.size(); i++) {
                //int j = i + 1000;
                Entry doneEntry = doneList.get(i);
                writer.println(Integer.toString(i + 1000) + "==" + doneEntry.getContent() + "=="
                        + formatter.format(doneEntry.getDue()) + "==" + doneEntry.getStatus());
            }
        }
        writer.close();
    }



    @Override
    public void loadFile() throws IOException, ParseException {
        List<String> lines = Files.readAllLines(Paths.get("./data/listData.txt"));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        toDoList.clear();
        doneList.clear();

        for (String line : lines) {
            ArrayList<String> partsOfLine = splitByPart(line);
            Entry entry = new Entry();

            int indexNum = Integer.parseInt(partsOfLine.get(0));
            entry.setContent(partsOfLine.get(1));
            entry.setDue(formatter.parse(partsOfLine.get(2)));
            entry.setStatus(Boolean.parseBoolean(partsOfLine.get(3)));
            if (indexNum < 1000) {
                toDoList.add(entry);
            } else {
                doneList.add(entry);
            }
        }
    }



    public static ArrayList<String> splitByPart(String line) {
        String[] splits = line.split("==");
        return new ArrayList<>(Arrays.asList(splits));
    }


}
