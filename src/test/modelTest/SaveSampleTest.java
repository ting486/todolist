package modelTest;

import model.*;
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
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private SchoolList sl;

    @BeforeEach
    public void runBefore() throws IOException, ParseException {
        sampleInputList = new SaveSample();
        yooo = new SaveSample();
        sl = new SchoolList();
    }

    @Test
    public void testSaveThisSample() throws IOException, ParseException {
        //ToDoList expectedList = new ToDoList();
        ToDoList expectedList = sampleInputList.sampleToDoItems;

        Item toDoReg = new RegularItem();
        toDoReg.setContent("new 3");
        toDoReg.setDue(formatter.parse("12/12/2019"));

        Item toDoUrg1 = new UrgentItem();
        toDoUrg1.setContent("urgent 1");
        toDoUrg1.setDue(formatter.parse("20/10/2019"));

        Item toDoSchool = new RegularItem();
        toDoSchool.setContent("cpsc210");
        toDoSchool.setDue(formatter.parse("22/10/2019"));
        toDoSchool.addSchoolList(sl);

        Item doneReg1 = new RegularItem();
        doneReg1.setContent("done reg 1");
        doneReg1.setDue(formatter.parse("11/11/1111"));
        doneReg1.setStatus(true);

        Item doneReg2 = new RegularItem();
        doneReg2.setContent("done reg 2");
        doneReg2.setDue(formatter.parse("11/10/2019"));
        doneReg2.setStatus(true);

        Item doneUrg1 = new UrgentItem();
        doneUrg1.setContent("done urg");
        doneUrg1.setDue(formatter.parse("01/10/2019"));
        doneUrg1.setStatus(true);


        sampleInputList.sampleToDoItems.toDoMap.put("new 3", toDoReg);
        sampleInputList.sampleToDoItems.toDoMap.put("cpsc210", toDoSchool);
        sampleInputList.sampleToDoItems.toDoMap.put("urgent 1", toDoUrg1);
        sampleInputList.sampleToDoItems.doneMap.put("done reg 1", doneReg1);
        sampleInputList.sampleToDoItems.doneMap.put("done reg 2", doneReg2);
        sampleInputList.sampleToDoItems.doneMap.put("done urg", doneUrg1);


        sampleInputList.saveThisSample();
        ToDoList sampleOutputList = sampleInputList.loadFile();

        assertEquals(expectedList.toDoMap.size(), sampleOutputList.toDoMap.size());

        assertTrue(sampleOutputList.toDoMap.containsValue(toDoReg));
        assertTrue(sampleOutputList.toDoMap.containsValue(toDoSchool));
        assertTrue(sampleOutputList.toDoMap.containsValue(toDoUrg1));
        assertFalse(sampleOutputList.toDoMap.get("new 3").getUrg());
        assertFalse(sampleOutputList.toDoMap.get("cpsc210").getUrg());
        assertTrue(sampleOutputList.toDoMap.get("urgent 1").getUrg());
        assertFalse(sampleOutputList.toDoMap.get("new 3").isInSchool());
        assertTrue(sampleOutputList.toDoMap.get("cpsc210").isInSchool());
        assertFalse(sampleOutputList.toDoMap.get("urgent 1").isInSchool());



        assertEquals(expectedList.doneMap.size(), sampleOutputList.doneMap.size());

        assertTrue(sampleOutputList.doneMap.containsValue(doneReg1));
        assertTrue(sampleOutputList.doneMap.containsValue(doneReg2));
        assertTrue(sampleOutputList.doneMap.containsValue(doneUrg1));
        assertFalse(sampleOutputList.doneMap.get("done reg 1").getUrg());
        assertFalse(sampleOutputList.doneMap.get("done reg 2").getUrg());
        assertTrue(sampleOutputList.doneMap.get("done urg").getUrg());
    }

    @Test
    public void testSplitByPart() {
        ArrayList<String> listOfString = new ArrayList<>();
        listOfString.add("qwertyuio");
        listOfString.add("q12");
        assertEquals(listOfString, sampleInputList.splitByPart("qwertyuio==q12"));
    }
}
