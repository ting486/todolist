package model;

import ui.ToDoList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoadSample {
    //ArrayList<String> listOfString = new ArrayList<>();
    ToDoList sampleToDoItems = new ToDoList();
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


    public LoadSample() throws IOException, ParseException {
    }

    /*
    public ToDoList load() throws IOException, ParseException {
        List<String> lines = Files.readAllLines(Paths.get("./data/testFileLoad.txt"));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        sampleToDoItems.toDoList.clear();
        sampleToDoItems.doneList.clear();

        for (String line : lines) {
            ArrayList<String> partsOfLine = splitByPart(line);
            RegularItem regularItem = new RegularItem();
            //UrgentItem urgentItem = new UrgentItem();

            int indexNum = Integer.parseInt(partsOfLine.get(0));
            regularItem.setContent(partsOfLine.get(1));
            regularItem.setDue(formatter.parse(partsOfLine.get(2)));
            regularItem.setStatus(Boolean.parseBoolean(partsOfLine.get(3)));
            if (indexNum < 1000) {
                sampleToDoItems.toDoList.add(regularItem);
            } else {
                sampleToDoItems.doneList.add(regularItem);
            }
        }
        return sampleToDoItems;
    }
     */

    public ToDoList loadFile() throws IOException, ParseException {
        List<String> lines = Files.readAllLines(Paths.get("./data/testFileLoad.txt"));
        //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        sampleToDoItems.toDoItems.clear();
        sampleToDoItems.doneItems.clear();

        for (String line : lines) {
            ArrayList<String> partsOfLine = splitByPart(line);
//            Item regularItem = new RegularItem();
//            Item urgentItem = new UrgentItem();

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
        }
        return sampleToDoItems;
    }

    public void loadToToDo(ArrayList<String> pol) throws ParseException {
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        RegularItem regularItem = new RegularItem();
        UrgentItem urgentItem = new UrgentItem();
//        Boolean status = Boolean.parseBoolean(partsOfLine.get(3));
//        Boolean urgency = Boolean.parseBoolean(partsOfLine.get(4));

//        if (urgency == true) {
//            urgentItem.setThis(partsOfLine.get(1), formatter.parse(partsOfLine.get(2)), status);
//            sampleToDoItems.toDoItems.add(urgentItem);
//        } else {
//            regularItem.setThis(partsOfLine.get(1), formatter.parse(partsOfLine.get(2)), status);
//            sampleToDoItems.toDoItems.add(regularItem);
//        }

        if (Boolean.parseBoolean(pol.get(4))) {
            urgentItem.setThis(pol.get(1), formatter.parse(pol.get(2)), Boolean.parseBoolean(pol.get(3)));
            if (Boolean.parseBoolean(pol.get(5))) {
                sampleToDoItems.schoolList.addItem(urgentItem);
            }

            sampleToDoItems.toDoMap.put(pol.get(0), urgentItem);
        } else {
            regularItem.setThis(pol.get(1), formatter.parse(pol.get(2)), Boolean.parseBoolean(pol.get(3)));
            if (Boolean.parseBoolean(pol.get(5))) {
                sampleToDoItems.schoolList.addItem(regularItem);
            }

            sampleToDoItems.toDoMap.put(pol.get(0), regularItem);
        }
    }

    public void loadToDone(ArrayList<String> pol) throws ParseException {
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Item regularItem = new RegularItem();
        Item urgentItem = new UrgentItem();
//        Boolean status = Boolean.parseBoolean(partsOfLine.get(3));
//        Boolean urgency = Boolean.parseBoolean(partsOfLine.get(4));

//        if (urgency == true) {
//            urgentItem.setThis(partsOfLine.get(1), formatter.parse(partsOfLine.get(2)), status);
//            sampleToDoItems.doneItems.add(urgentItem);
//        } else {
//            regularItem.setThis(partsOfLine.get(1), formatter.parse(partsOfLine.get(2)), status);
//            sampleToDoItems.doneItems.add(regularItem);
//        }

        if (Boolean.parseBoolean(pol.get(4))) {
            urgentItem.setThis(pol.get(1), formatter.parse(pol.get(2)), Boolean.parseBoolean(pol.get(3)));
            sampleToDoItems.doneMap.put(pol.get(0), urgentItem);
        } else {
            regularItem.setThis(pol.get(1), formatter.parse(pol.get(2)), Boolean.parseBoolean(pol.get(3)));
            sampleToDoItems.doneMap.put(pol.get(0), regularItem);
        }
    }

    public static ArrayList<String> splitByPart(String line) {
        String[] splits = line.split("==");
        return new ArrayList<>(Arrays.asList(splits));
    }
}
