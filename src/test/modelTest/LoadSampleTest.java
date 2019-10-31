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
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


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

        expectedList = sampleToDoList.loadFile();


        assertTrue(expectedList.toDoMap.containsKey("0"));
        assertFalse(expectedList.toDoMap.get("0").getUrg());
        assertTrue(expectedList.toDoMap.get("0").isInSchool());

        assertTrue(expectedList.toDoMap.containsKey("1"));
        assertTrue(expectedList.toDoMap.get("1").getUrg());
        assertFalse(expectedList.toDoMap.get("1").isInSchool());

        assertTrue(expectedList.doneMap.containsKey("yo"));
        assertFalse(expectedList.doneMap.get("yo").getUrg());
        assertFalse(expectedList.doneMap.get("yo").isInSchool());

        assertTrue(expectedList.doneMap.containsKey("1001"));
        assertFalse(expectedList.doneMap.get("1001").getUrg());
        assertFalse(expectedList.doneMap.get("1001").isInSchool());

        assertTrue(expectedList.doneMap.containsKey("1002"));
        assertTrue(expectedList.doneMap.get("1002").getUrg());
        assertFalse(expectedList.doneMap.get("1002").isInSchool());

        assertEquals(1, expectedList.schoolList.getSchoolItems().size());
    }
}
