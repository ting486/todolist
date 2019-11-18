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
    public ToDoList sampleToDoItems = new ToDoList();
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public SaveSample() throws IOException {
    }


    // MODIFIES: writer, ./data/testFileSave.txt
    // EFFECTS: saves items to ./data/testFileSave.txt
    public void saveThisSample() throws IOException {
        PrintWriter writer = new PrintWriter("./data/testFileSave.txt","UTF-8");

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

    // MODIFIES: sampleToDoItems
    // EFFECTS: loads items to ./data/testFileSave.txt
    public ToDoList loadFile() throws IOException, ParseException {
        List<String> lines = Files.readAllLines(Paths.get("./data/testFileSave.txt"));
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
