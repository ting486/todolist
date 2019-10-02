package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoadSample {
    ArrayList<String> listOfString = new ArrayList<>();
    ToDoList sampleToDoList = new ToDoList();

    public LoadSample() throws IOException, ParseException {
    }

    public ToDoList load() throws IOException, ParseException {
        List<String> lines = Files.readAllLines(Paths.get("./data/testFileLoad.txt"));
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
