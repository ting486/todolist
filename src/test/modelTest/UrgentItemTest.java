package modelTest;

import model.Item;
import model.UrgentItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UrgentItemTest {

    private Item urgentItem;
    private final static String CONTENT = "sample content";
    private final static String DUE_STRING = "30/09/2019";
    private Date due;
    private final static Boolean STATUS = true;



    @BeforeEach
    public void runBefore() throws ParseException {
        urgentItem = new UrgentItem();
        due = urgentItem.formatter.parse(DUE_STRING);
    }


    @Test
    public void testGreeting() {
        assertEquals("This is urgent", urgentItem.greeting());
    }

    @Test
    public void testSetContent() {
        assertEquals(null, urgentItem.getContent());

        urgentItem.setContent(CONTENT);
        assertEquals(CONTENT, urgentItem.getContent());
    }

    @Test
    public void testSetDue() {
        assertEquals(null, urgentItem.getDue());

        urgentItem.setDue(due);
        assertEquals(due, urgentItem.getDue());
    }

    @Test
    public void testSetCompletion() {
        assertEquals(false, urgentItem.getStatus());

        urgentItem.setStatus(STATUS);
        assertEquals(true, urgentItem.getStatus());
    }

    @Test
    public void testSetThis() {
        urgentItem.setThis(CONTENT, due, STATUS);
        assertEquals(CONTENT, urgentItem.getContent());
        assertEquals(due, urgentItem.getDue());
        assertEquals(STATUS, urgentItem.getStatus());
    }

    @Test
    public void testUrgency() {
        assertTrue(urgentItem.getUrg());
    }


    @Test
    public void testPrintEntry() {
        urgentItem.setContent(CONTENT);
        urgentItem.setDue(due);
        assertEquals(CONTENT + " is due on " + urgentItem.formatter.format(due) + ". Not completed :(",
                urgentItem.printItem());

        urgentItem.setStatus(STATUS);
        assertEquals(CONTENT + " is due on " + urgentItem.formatter.format(due) + ". Completed!",
                urgentItem.printItem());
    }
}
