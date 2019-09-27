package ui;

import model.Entry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {

    private ArrayList<Entry> toDoList;
    private ArrayList<Entry> doneList;
    private Scanner scanner;

    // constructor
    public ToDoList() throws ParseException {
        toDoList = new ArrayList<>();
        doneList = new ArrayList<>();
        scanner = new Scanner(System.in);
        processOperations();
    }


    // EFFECTS: directs the user to corresponding to-do list operations (adding an item, crossing off an entry,
    //          displaying the list or quitting the program) depending on the user input
    private void processOperations() throws ParseException {

        while (true) {
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

    // MODIFIES: this
    // EFFECTS: adds an item to the to-do list
    private void operationAdd() throws ParseException {
        Entry entry = new Entry();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Please enter the content: ");
        entry.setContent(scanner.nextLine());
        System.out.println("When is it due? Enter the date in the form: dd/MM/yyyy");
        entry.setDue(formatter.parse(scanner.nextLine()));

        System.out.println("You just added a new to-do list: ");
        entry.printEntry();

        toDoList.add(entry);
    }

    // MODIFIES: this and doneList
    // EFFECTS: deletes an item from the to-do list, and move it to the crossed-off list; if there
    //          is nothing to be deleted, return to the main menu (i.e.: returning to processOperations();)
    private void operationMarkCompleted() {
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

            System.out.println("Updated to-do list: ");
            operationDisplayTodo();
        }
    }

    // EFFECTS: displays both do-list list and crossed-off list
    private void operationDisplay() {
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
                System.out.println(i + ": " + doneList.get(i).printEntry());
            }
        }
    }


    public static void main(String[] args) throws ParseException {

        new ToDoList();
    }




}
