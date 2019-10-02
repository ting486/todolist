package ui;

import model.Entry;
import model.ToDoList;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Main {
/*
    private ArrayList<Entry> toDoList;
    private ArrayList<Entry> doneList;
    private Scanner scanner;

    // constructor
    public ToDoList() throws ParseException, IOException {
        toDoList = new ArrayList<>();
        doneList = new ArrayList<>();
        scanner = new Scanner(System.in);
        processOperations();
    }


    // EFFECTS: directs the user to corresponding to-do list operations (adding an item, crossing off an entry,
    //          displaying the list or quitting the program) depending on the user input
    private void processOperations() throws ParseException, IOException {

        while (true) {
            saveToFile();

            System.out.println("What would you like to do today?");
            System.out.println(" 1: add a to-do list item \r\n 2: cross off an item \r\n "
                    + "3: show all the items \r\n 4: quit");
            System.out.println("Enter the number: ");
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
        }
    }


    // REQUIRES: toDoList.size() < 1000
    // MODIFIES: this
    // EFFECTS: adds an item to the to-do list
    private void operationAdd() throws ParseException, IOException {
        Entry entry = new Entry();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Please enter the content: (do NOT use the symbol '|')");
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

            toDoList.get(entryNum).setCompletion(true);
            doneList.add(toDoList.get(entryNum));
            toDoList.remove(entryNum);

            saveToFile();

            //System.out.println("Updated to-do list: ");
            //operationDisplayTodo();
        }
    }

    // EFFECTS: displays both do-list list and crossed-off list
    private void operationDisplay() throws IOException, ParseException {
        loadFile();
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
*/

    public static void main(String[] args) throws ParseException, IOException {
        ToDoList tryThisList = new ToDoList();
        tryThisList.run();
    }



/*

    @Override
    public void saveToFile() throws IOException {
        List<String> lines = new ArrayList<>();
        PrintWriter writer = new PrintWriter("file.txt","UTF-8");

        //writer.println("To-do list:");
        for (int i = 0; i < toDoList.size(); i++) {
            //lines.add(i + ": " + toDoList.get(i).getContent() + " is due on " + toDoList.get(i).getDue()
            //        + ". " + toDoList.get(i).completionStatus(toDoList.get(i).getCompletion()) + "\r\n");
            writer.println(i + "|" + toDoList.get(i).getContent() + "|" + toDoList.get(i).getDue()
                    + "|" + toDoList.get(i).completionStatus(toDoList.get(i).getCompletion()));
        }
        //writer.println("Crossed-off list:");
        for (int i = 0; i < doneList.size(); i++) {
            //lines.add(i + ": " + doneList.get(i).getContent() + " is due on " + doneList.get(i).getDue()
             //       + ". " + doneList.get(i).completionStatus(doneList.get(i).getCompletion()) + "\r\n");
            int j = i + 1000;
            writer.println(j + "|" + doneList.get(i).getContent() + "|" + doneList.get(i).getDue()
                    + "|" + doneList.get(i).completionStatus(doneList.get(i).getCompletion()));
        }

        writer.close();
    }


    @Override
    public void loadFile() throws IOException, ParseException {
        List<String> lines = Files.readAllLines(Paths.get("file.text"));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        for (String line : lines) {
            //if (!(line == "To-do list:") && !(line == "Crossed-off list:")) {
            int indexNum = Integer.parseInt(splitByPart(line).get(0));
            //String content = splitByPart(line).get(1);
            //Date due = formatter.parse(splitByPart(line).get(2));
            //Boolean completion = Boolean.parseBoolean(splitByPart(line).get(3));
            Entry entry = new Entry();
            entry.setContent(splitByPart(line).get(1));
            entry.setDue(formatter.parse(splitByPart(line).get(2)));
            entry.setCompletion(Boolean.parseBoolean(splitByPart(line).get(3)));
            if (indexNum < 1000) {
                toDoList.add(entry);
            } else {
                doneList.add(entry);
            }
            //}
        }
    }


    public static ArrayList<String> splitByPart(String line) {
        String[] splits = line.split("|");
        return new ArrayList<>(Arrays.asList(splits));
    }

 */
}
