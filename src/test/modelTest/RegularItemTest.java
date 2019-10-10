package modelTest;

import model.RegularItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegularItemTest {
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    private RegularItem regularItem;
    private final static String CONTENT = "sample content";
    private final static String DUE_STRING = "30/09/2019";
    private final Date DUE = formatter.parse(DUE_STRING);
    private final static Boolean STATUS = true;


    public RegularItemTest() throws ParseException {
    }


    @BeforeEach
    public void runBefore() {
        regularItem = new RegularItem();
    }


    @Test
    public void testGreeting() {
        assertEquals("This is regular", regularItem.greeting());
    }

    @Test
    public void testSetContent() {
        assertEquals(null, regularItem.getContent());

        regularItem.setContent(CONTENT);
        assertEquals(CONTENT, regularItem.getContent());
    }

    @Test
    public void testSetDue() {
        assertEquals(null, regularItem.getDue());

        regularItem.setDue(DUE);
        assertEquals(DUE, regularItem.getDue());
    }

    @Test
    public void testSetCompletion() {
        assertEquals(false, regularItem.getStatus());

        regularItem.setStatus(STATUS);
        assertEquals(true, regularItem.getStatus());
    }

    @Test
    public void testSetThis() {
        regularItem.setThis(CONTENT, DUE, STATUS);
        assertEquals(CONTENT, regularItem.getContent());
        assertEquals(DUE, regularItem.getDue());
        assertEquals(STATUS, regularItem.getStatus());
    }

    @Test
    public void testUrgency() {
        assertFalse(regularItem.getUrg());
    }


    @Test
    public void testPrintEntry() {
        regularItem.setContent(CONTENT);
        regularItem.setDue(DUE);
        assertEquals(CONTENT + " is due on " + formatter.format(DUE) + ". Not completed :(",
                regularItem.printItem());

        regularItem.setStatus(STATUS);
        assertEquals(CONTENT + " is due on " + formatter.format(DUE) + ". Completed!",
                regularItem.printItem());
    }
}
