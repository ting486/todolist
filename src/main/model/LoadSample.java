package model;

import ui.ToDoList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoadSample {
    ToDoList sampleToDoItems = new ToDoList();
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public LoadSample() throws FileNotFoundException, UnsupportedEncodingException {
    }


    // MODIFIES: sampleToDoItems
    // EFFECTS: loads items to ./data/testFileLoad.txt
    public ToDoList loadFile() throws IOException, ParseException {
        List<String> lines = Files.readAllLines(Paths.get("./data/testFileLoad.txt"));
        sampleToDoItems.toDoMap.clear();
        sampleToDoItems.doneMap.clear();
        sampleToDoItems.schoolList.clearSchoolItems();


        for (String line : lines) {
            ArrayList<String> partsOfLine = splitByPart(line);

            if (!Boolean.parseBoolean(partsOfLine.get(3))) {
                loadToToDo(partsOfLine);
            } else {
                loadToDone(partsOfLine);
            }
        }
        return sampleToDoItems;
    }

    private void loadToToDo(ArrayList<String> pol) throws ParseException {
        RegularItem regularItem = new RegularItem();
        UrgentItem urgentItem = new UrgentItem();

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

    private void loadToDone(ArrayList<String> pol) throws ParseException {
        Item regularItem = new RegularItem();
        Item urgentItem = new UrgentItem();

        if (Boolean.parseBoolean(pol.get(4))) {
            urgentItem.setThis(pol.get(1), formatter.parse(pol.get(2)), Boolean.parseBoolean(pol.get(3)));
            sampleToDoItems.doneMap.put(pol.get(0), urgentItem);
        } else {
            regularItem.setThis(pol.get(1), formatter.parse(pol.get(2)), Boolean.parseBoolean(pol.get(3)));
            sampleToDoItems.doneMap.put(pol.get(0), regularItem);
        }
    }

    // EFFECTS: splits line by '==' into an arraylist of strings
    public static ArrayList<String> splitByPart(String line) {
        String[] splits = line.split("==");
        return new ArrayList<>(Arrays.asList(splits));
    }
}
