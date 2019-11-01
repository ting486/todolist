package model;

import ui.ToDoList;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaveSample {
    //ArrayList<String> sampleString = new ArrayList<>();
    public ToDoList sampleToDoItems = new ToDoList();
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public SaveSample() throws IOException, ParseException {
    }




    public void saveThisSample() throws IOException {
        PrintWriter writer = new PrintWriter("./data/testFileSave.txt","UTF-8");

//        if (sampleToDoItems.toDoItems.size() != 0) {
//            for (int i = 0; i < sampleToDoItems.toDoItems.size(); i++) {
//                Item toDoItem = sampleToDoItems.toDoItems.get(i);
//                writer.println(i + "==" + toDoItem.getContent() + "==" + formatter.format(toDoItem.getDue())
//                        + "==" + toDoItem.getStatus() + "==" + toDoItem.getUrg());
//            }
//        }
//        if (sampleToDoItems.doneItems.size() != 0) {
//            for (int i = 0; i < sampleToDoItems.doneItems.size(); i++) {
//                Item doneItem = sampleToDoItems.doneItems.get(i);
//                writer.println((i + 1000) + "==" + doneItem.getContent() + "=="
//                        + formatter.format(doneItem.getDue())
//                        + "==" + doneItem.getStatus() + "==" + doneItem.getUrg());
//            }
//        }
//        writer.close();


        sampleToDoItems.toDoMap.forEach((k,i) -> {
            writer.println(k + "==" + i.getContent() + "==" + formatter.format(i.getDue()) + "=="
                    + i.getStatus() + "==" + i.getUrg() + "==" + i.isInSchool());
        }
        );

        sampleToDoItems.doneMap.forEach((k,i) -> {
            writer.println(k + "==" + i.getContent()
                    + "==" + formatter.format(i.getDue()) + "=="
                    + i.getStatus() + "==" + i.getUrg() + "==" + i.isInSchool());
        }
        );
        writer.close();
    }


    /*
    public ToDoList loadSavedSample() throws IOException, ParseException {
        List<String> lines = Files.readAllLines(Paths.get("./data/testFileSave.txt"));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        sampleToDoItems.toDoList.clear();
        sampleToDoItems.doneList.clear();

        for (String line : lines) {
            ArrayList<String> partsOfLine = splitByPart(line);
            RegularItem regularItem = new RegularItem();

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
        List<String> lines = Files.readAllLines(Paths.get("./data/testFileSave.txt"));
//        sampleToDoItems.toDoItems.clear();
//        sampleToDoItems.doneItems.clear();
        sampleToDoItems.toDoMap.clear();
        sampleToDoItems.doneMap.clear();
        sampleToDoItems.schoolList.clearSchoolItems();

//        for (String line : lines) {
//            ArrayList<String> partsOfLine = splitByPart(line);
//            Item regularItem = new RegularItem();
//            Item urgentItem = new UrgentItem();
//
//            if (Integer.parseInt(partsOfLine.get(0)) < 1000) {
//                loadToToDo(partsOfLine);
//            } else {
//                loadToDone(partsOfLine);
//            }
//        }
//        return sampleToDoItems;

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
        RegularItem regularItem = new RegularItem();
        UrgentItem urgentItem = new UrgentItem();
//        Boolean status = Boolean.parseBoolean(partsOfLine.get(3));
//        Boolean urgency = Boolean.parseBoolean(partsOfLine.get(4));
//
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
        Item regularItem = new RegularItem();
        Item urgentItem = new UrgentItem();
//        Boolean status = Boolean.parseBoolean(partsOfLine.get(3));
//        Boolean urgency = Boolean.parseBoolean(partsOfLine.get(4));
//
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
