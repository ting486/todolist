package modelTest;

import model.Entry;
import model.SaveSample;
import model.ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaveSampleTest {
    private SaveSample sampleInputList;
    //private SaveSample expectedList;

    @BeforeEach
    public void runBefore() throws IOException, ParseException {
        sampleInputList = new SaveSample();
    }

    @Test
    public void testSaveAndLoad() throws IOException, ParseException {
        //ToDoList expectedList = new ToDoList();
        ToDoList expectedList = sampleInputList.sampleToDoList;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Entry toDo1 = new Entry();
        toDo1.setContent("new 3");
        toDo1.setDue(formatter.parse("12/12/2019"));
        toDo1.setStatus(false);

        Entry toDo2 = new Entry();
        toDo2.setContent("random");
        toDo2.setDue(formatter.parse("22/10/2019"));
        toDo2.setStatus(false);

        Entry done1 = new Entry();
        done1.setContent("try1");
        done1.setDue(formatter.parse("11/11/1111"));
        done1.setStatus(true);

        Entry done2 = new Entry();
        done2.setContent("try3 - to be deleted");
        done2.setDue(formatter.parse("11/10/2019"));
        done2.setStatus(true);

        expectedList.toDoList.add(toDo1);
        expectedList.toDoList.add(toDo2);
        expectedList.doneList.add(done1);
        expectedList.doneList.add(done2);


        sampleInputList.saveThisSample();
        ToDoList sampleOutputList = sampleInputList.loadSavedSample();

        assertEquals(expectedList.toDoList.size(), sampleOutputList.toDoList.size());
        if (expectedList.toDoList.size() != 0) {
            for (int i = 0; i < expectedList.toDoList.size(); i++) {
                String checkExp = expectedList.toDoList.get(i).printEntry();
                String checkSamp = sampleOutputList.toDoList.get(i).printEntry();
                assertEquals(checkExp, checkSamp);
            }
        }

        assertEquals(expectedList.doneList.size(), sampleOutputList.doneList.size());
        if (expectedList.doneList.size() != 0) {
            for (int i = 0; i < expectedList.doneList.size(); i++) {
                String checkExp = expectedList.doneList.get(i).printEntry();
                String checkSamp = sampleOutputList.doneList.get(i).printEntry();
                assertEquals(checkExp, checkSamp);
            }
        }
    }
}
