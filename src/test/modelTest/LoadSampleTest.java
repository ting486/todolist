package modelTest;

import model.LoadSample;
import ui.ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;




public class LoadSampleTest {
    private LoadSample sampleToDoList;

    @BeforeEach
    public void runBefore() throws IOException, ParseException {
        sampleToDoList = new LoadSample();
    }


    @Test
    public void testSplitByPart() {
        ArrayList<String> listOfString = new ArrayList<>();
        listOfString.add("qwertyuio");
        listOfString.add("q12");
        assertEquals(listOfString, sampleToDoList.splitByPart("qwertyuio==q12"));
    }

    @Test
    public void testLoad() throws IOException, ParseException {
        ToDoList expectedList; // = new ToDoList();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        /*
        RegularItem toDoReg1 = new RegularItem();
        toDoReg1.setContent("new 2");
        toDoReg1.setDue(formatter.parse("12/12/2019"));
        toDoReg1.setStatus(false);

        UrgentItem toDoUrg1 = new UrgentItem();
        toDoUrg1.setContent("urgent 1");
        toDoUrg1.setDue(formatter.parse("20/10/2019"));
        toDoUrg1.setStatus(false);

        RegularItem doneReg1 = new RegularItem();
        doneReg1.setContent("try1");
        doneReg1.setDue(formatter.parse("11/11/1111"));
        doneReg1.setStatus(true);

        RegularItem doneReg2 = new RegularItem();
        doneReg2.setContent("try3 - to be deleted");
        doneReg2.setDue(formatter.parse("11/10/2019"));
        doneReg2.setStatus(true);

        UrgentItem doneUrg1 = new UrgentItem();
        doneUrg1.setContent("this urgent item is done");
        doneUrg1.setDue(formatter.parse("01/10/2019"));
        doneUrg1.setStatus(true);

        expectedList.toDoList.add(toDoReg1);
        expectedList.toDoList.add(toDoUrg1);
        expectedList.doneList.add(doneReg1);
        expectedList.doneList.add(doneReg2);
        expectedList.doneList.add(doneUrg1);

        ToDoList sampleList = sampleToDoList.load();
        */

        expectedList = sampleToDoList.loadFile();


        /*
        List<String> lines = Files.readAllLines(Paths.get("inputfile.txt"));
        for (String line : lines) {
            //ArrayList<String> partsOfLine = splitByPart(line);
        }
         */



        //assertEquals(expectedList.toDoList.size(), sampleList.toDoList.size());
        /*
        if (expectedList.toDoList.size() != 0) {
            for (int i = 0; i < expectedList.toDoList.size(); i++) {
                String expectedPrint = expectedList.toDoList.get(i).printItem();
                String samplePrint = sampleList.toDoList.get(i).printItem();
                assertEquals(expectedPrint, samplePrint);
            }
        }
         */
        //String expectedToDo0 = expectedList.toDoList.get(0).printItem();
        //String expectedToDo1 = expectedList.toDoList.get(1).printItem();
        assertEquals("new 2 is due on 12/12/2019. Not completed :(",
                expectedList.toDoItems.get(0).printItem());
        assertFalse(expectedList.toDoItems.get(0).getUrg());
        assertEquals("urgent 1 is due on 20/10/2019. Not completed :(",
                expectedList.toDoItems.get(1).printItem());
        assertTrue(expectedList.toDoItems.get(1).getUrg());



        //assertEquals(expectedList.doneList.size(), sampleList.doneList.size());
        /*
        if (expectedList.doneList.size() != 0) {
            for (int i = 0; i < expectedList.doneList.size(); i++) {
                String expectedPrint = expectedList.doneList.get(i).printItem();
                String samplePrint = sampleList.doneList.get(i).printItem();
                assertEquals(expectedPrint, samplePrint);
            }
        }
         */

        //String expectedDone0 = expectedList.doneList.get(0).printItem();
        //String expectedDone1 = expectedList.doneList.get(1).printItem();
        //String expectedDone2 = expectedList.doneList.get(2).printItem();
        assertEquals("try1 is due on 11/11/1111. Completed!",
                expectedList.doneItems.get(0).printItem());
        assertFalse(expectedList.doneItems.get(0).getUrg());
        assertEquals("try3 - to be deleted is due on 11/10/2019. Completed!",
                expectedList.doneItems.get(1).printItem());
        assertFalse(expectedList.doneItems.get(1).getUrg());
        assertEquals("this urgent item is done is due on 01/10/2019. Completed!",
                expectedList.doneItems.get(2).printItem());
        assertTrue(expectedList.doneItems.get(2).getUrg());
    }
}
