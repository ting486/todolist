package modelTest;

import model.ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ToDoListTest {
    ToDoList sampleList;

    @BeforeEach
    public void runBefore() throws IOException, ParseException {
        sampleList = new ToDoList();
    }

    @Test
    public void testSplitByPart() {
        ArrayList<String> listOfString = new ArrayList<>();
        listOfString.add("qwertyuio");
        listOfString.add("q12");
        assertEquals(listOfString, sampleList.splitByPart("qwertyuio==q12"));
    }
}
