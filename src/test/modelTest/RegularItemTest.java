package modelTest;

import model.RegularItem;
import model.SchoolList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class RegularItemTest {

    private RegularItem regularItem;
    private final static String CONTENT = "sample content";
    private final static String DUE_STRING = "30/09/2019";
    private Date due;
    private final static Boolean STATUS = true;
    private RegularItem regularItem2;

    private SchoolList sl;



    @BeforeEach
    public void runBefore() throws ParseException {
        regularItem = new RegularItem();
        sl = new SchoolList();
        due = regularItem.formatter.parse(DUE_STRING);
    }


    @Test
    public void testGreeting() {
        assertEquals("This is regular", regularItem.greeting());
    }

    @Test
    public void testSetContent() {
        assertNull(regularItem.getContent());

        regularItem.setContent(CONTENT);
        assertEquals(CONTENT, regularItem.getContent());
    }

    @Test
    public void testSetDue() {
        assertNull(regularItem.getDue());

        regularItem.setDue(due);
        assertEquals(due, regularItem.getDue());
    }

    @Test
    public void testSetCompletion() {
        assertEquals(false, regularItem.getStatus());

        regularItem.setStatus(STATUS);
        assertEquals(true, regularItem.getStatus());
    }

    @Test
    public void testSetThis() {
        regularItem.setThis(CONTENT, due, STATUS);
        assertEquals(CONTENT, regularItem.getContent());
        assertEquals(due, regularItem.getDue());
        assertEquals(STATUS, regularItem.getStatus());
    }

    @Test
    public void testUrgency() {
        assertFalse(regularItem.getUrg());
    }


    @Test
    public void testPrintEntry() {
        regularItem.setContent(CONTENT);
        regularItem.setDue(due);
        assertEquals(CONTENT + " is due on " + regularItem.formatter.format(due) + ". Not completed :(",
                regularItem.printItem());

        regularItem.setStatus(STATUS);
        assertEquals(CONTENT + " is due on " + regularItem.formatter.format(due) + ". Completed!",
                regularItem.printItem());
    }



    @Test
    public void testAddAndRemoveSchoolList() {
        assertNull(regularItem.getSchoolList());

        regularItem.addSchoolList(sl);
        assertEquals(sl, regularItem.getSchoolList());
        assertTrue(sl.getSchoolItems().contains(regularItem));
        assertTrue(regularItem.isInSchool());

        regularItem.removeFromSchoolList();
        assertNull(regularItem.getSchoolList());
        assertFalse(sl.getSchoolItems().contains(regularItem));
        assertFalse(regularItem.isInSchool());
    }

    @Test
    public void testOverridingEqualsAndHashCode() {
        regularItem2 = new RegularItem();
        regularItem.setThis(CONTENT, due, STATUS);
        regularItem2.setThis(CONTENT, due, STATUS);
        assertEquals(regularItem.hashCode(), regularItem2.hashCode());
        assertTrue(regularItem.equals(regularItem2));

        assertTrue(regularItem.equals(regularItem));
        assertFalse(regularItem.equals(1));
    }
}
