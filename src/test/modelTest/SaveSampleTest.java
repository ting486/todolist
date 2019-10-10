package modelTest;

import model.RegularItem;
import model.SaveSample;
import model.UrgentItem;
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

public class SaveSampleTest {
    private SaveSample sampleInputList;
    private SaveSample yooo;
    //private SaveSample expectedList;

    @BeforeEach
    public void runBefore() throws IOException, ParseException {
        sampleInputList = new SaveSample();
        yooo = new SaveSample();
    }

    @Test
    public void testSaveThisSample() throws IOException, ParseException {
        //ToDoList expectedList = new ToDoList();
        ToDoList expectedList = sampleInputList.sampleToDoItems;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        RegularItem toDoReg1 = new RegularItem();
        toDoReg1.setContent("new 3");
        toDoReg1.setDue(formatter.parse("12/12/2019"));
        //toDoReg1.setStatus(false);

        UrgentItem toDoUrg1 = new UrgentItem();
        toDoUrg1.setContent("urgent 1");
        toDoUrg1.setDue(formatter.parse("20/10/2019"));
        //toDoUrg1.setStatus(false);

        RegularItem toDoReg2 = new RegularItem();
        toDoReg2.setContent("random random");
        toDoReg2.setDue(formatter.parse("22/10/2019"));
        //toDoReg2.setStatus(false);

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

        sampleInputList.sampleToDoItems.toDoItems.add(toDoReg1);
        sampleInputList.sampleToDoItems.toDoItems.add(toDoReg2);
        sampleInputList.sampleToDoItems.toDoItems.add(toDoUrg1);
        sampleInputList.sampleToDoItems.doneItems.add(doneReg1);
        sampleInputList.sampleToDoItems.doneItems.add(doneReg2);
        sampleInputList.sampleToDoItems.doneItems.add(doneUrg1);


        sampleInputList.saveThisSample();
        ToDoList sampleOutputList = yooo.loadFile();

        assertEquals(expectedList.toDoItems.size(), sampleOutputList.toDoItems.size());
        if (expectedList.toDoItems.size() != 0) {
            for (int i = 0; i < expectedList.toDoItems.size(); i++) {
                String expectedPrint = expectedList.toDoItems.get(i).printItem();
                String samplePrint = sampleOutputList.toDoItems.get(i).printItem();
                assertEquals(expectedPrint, samplePrint);
            }
        }
        assertFalse(sampleOutputList.toDoItems.get(0).getUrg());
        assertFalse(sampleOutputList.toDoItems.get(1).getUrg());
        assertTrue(sampleOutputList.toDoItems.get(2).getUrg());

        assertEquals(expectedList.doneItems.size(), sampleOutputList.doneItems.size());
        if (expectedList.doneItems.size() != 0) {
            for (int i = 0; i < expectedList.doneItems.size(); i++) {
                String expectedPrint = expectedList.doneItems.get(i).printItem();
                String samplePrint = sampleOutputList.doneItems.get(i).printItem();
                assertEquals(expectedPrint, samplePrint);
            }
        }
        assertFalse(sampleOutputList.doneItems.get(0).getUrg());
        assertFalse(sampleOutputList.doneItems.get(1).getUrg());
        assertTrue(sampleOutputList.doneItems.get(2).getUrg());
    }

    @Test
    public void testSplitByPart() {
        ArrayList<String> listOfString = new ArrayList<>();
        listOfString.add("qwertyuio");
        listOfString.add("q12");
        assertEquals(listOfString, sampleInputList.splitByPart("qwertyuio==q12"));
    }
}
