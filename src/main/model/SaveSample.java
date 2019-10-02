package model;

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
    public ToDoList sampleToDoList = new ToDoList();

    public SaveSample() throws IOException, ParseException {
        //sampleToDoList = new ToDoList();
    }




    public void saveThisSample() throws IOException {
        PrintWriter writer = new PrintWriter("./data/testFileSave.txt","UTF-8");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        if (sampleToDoList.toDoList.size() != 0) {
            for (int i = 0; i < sampleToDoList.toDoList.size(); i++) {
                Entry toDoEntry = sampleToDoList.toDoList.get(i);
                writer.println(i + "==" + toDoEntry.getContent() + "==" + formatter.format(toDoEntry.getDue())
                        + "==" + toDoEntry.getStatus());
            }
        }
        if (sampleToDoList.doneList.size() != 0) {
            for (int i = 0; i < sampleToDoList.doneList.size(); i++) {
                //int j = i + 1000;
                Entry doneEntry = sampleToDoList.doneList.get(i);
                writer.println(Integer.toString(i + 1000) + "==" + doneEntry.getContent() + "=="
                        + formatter.format(doneEntry.getDue()) + "==" + doneEntry.getStatus());
            }
        }
        writer.close();
    }


    public ToDoList loadSavedSample() throws IOException, ParseException {
        List<String> lines = Files.readAllLines(Paths.get("./data/testFileSave.txt"));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        sampleToDoList.toDoList.clear();
        sampleToDoList.doneList.clear();

        for (String line : lines) {
            ArrayList<String> partsOfLine = splitByPart(line);
            Entry entry = new Entry();

            int indexNum = Integer.parseInt(partsOfLine.get(0));
            entry.setContent(partsOfLine.get(1));
            entry.setDue(formatter.parse(partsOfLine.get(2)));
            entry.setStatus(Boolean.parseBoolean(partsOfLine.get(3)));
            if (indexNum < 1000) {
                sampleToDoList.toDoList.add(entry);
            } else {
                sampleToDoList.doneList.add(entry);
            }
        }
        return sampleToDoList;
    }


    public static ArrayList<String> splitByPart(String line) {
        String[] splits = line.split("==");
        return new ArrayList<>(Arrays.asList(splits));
    }
}
