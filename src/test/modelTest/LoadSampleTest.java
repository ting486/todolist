package modelTest;

import main.model.Entry;
import main.model.LoadSample;
import main.model.ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoadSampleTest {
    private LoadSample sampleToDoList;

    @BeforeEach
    public void runBefore() throws IOException, ParseException {
        sampleToDoList = new LoadSample();
    }

    /*
    @Test
    public void testLoadTest() throws IOException {
        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add("123456789 0987654");
        expectedList.add("fjsdalkfja'fafj 324 fsalkr32jfsdofjap fsd");
        expectedList.add("");
        expectedList.add("43hwrejlkr894uihj rio4rw\\refwer3fewf fw4rfef");
        assertEquals(expectedList, sampleList.loadTest());
    }

     */

    @Test
    public void testSplitByPart() {
        ArrayList<String> listOfString = new ArrayList<>();
        listOfString.add("qwertyuio");
        listOfString.add("q12");
        assertEquals(listOfString, sampleToDoList.splitByPart("qwertyuio==q12"));
    }

    @Test
    public void testLoad() throws IOException, ParseException {
        ToDoList expectedList = new ToDoList();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Entry toDo1 = new Entry();
        toDo1.setContent("new 2");
        toDo1.setDue(formatter.parse("12/12/2019"));
        toDo1.setStatus(false);

        Entry done1 = new Entry();
        done1.setContent("try1");
        done1.setDue(formatter.parse("11/11/1111"));
        done1.setStatus(true);

        Entry done2 = new Entry();
        done2.setContent("try3 - to be deleted");
        done2.setDue(formatter.parse("11/10/2019"));
        done2.setStatus(true);

        expectedList.toDoList.add(toDo1);
        expectedList.doneList.add(done1);
        expectedList.doneList.add(done2);

        ToDoList sampleList = sampleToDoList.load();


        assertEquals(expectedList.toDoList.size(), sampleList.toDoList.size());
        if (expectedList.toDoList.size() != 0) {
            for (int i = 0; i < expectedList.toDoList.size(); i++) {
                String checkExp = expectedList.toDoList.get(i).printEntry();
                String checkSamp = sampleList.toDoList.get(i).printEntry();
                assertEquals(checkExp, checkSamp);
            }
        }

        assertEquals(expectedList.doneList.size(), sampleList.doneList.size());
        if (expectedList.doneList.size() != 0) {
            for (int i = 0; i < expectedList.doneList.size(); i++) {
                String checkExp = expectedList.doneList.get(i).printEntry();
                String checkSamp = sampleList.doneList.get(i).printEntry();
                assertEquals(checkExp, checkSamp);
            }
        }
    }
}
