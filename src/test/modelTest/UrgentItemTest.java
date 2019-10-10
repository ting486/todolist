package modelTest;

import model.Item;
import model.UrgentItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UrgentItemTest {
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    private Item urgentItem;
    private final static String CONTENT = "sample content";
    private final static String DUE_STRING = "30/09/2019";
    private final Date DUE = formatter.parse(DUE_STRING);
    private final static Boolean STATUS = true;


    public UrgentItemTest() throws ParseException {
    }


    @BeforeEach
    public void runBefore() {
        urgentItem = new UrgentItem();
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

        urgentItem.setDue(DUE);
        assertEquals(DUE, urgentItem.getDue());
    }

    @Test
    public void testSetCompletion() {
        assertEquals(false, urgentItem.getStatus());

        urgentItem.setStatus(STATUS);
        assertEquals(true, urgentItem.getStatus());
    }

    @Test
    public void testSetThis() {
        urgentItem.setThis(CONTENT, DUE, STATUS);
        assertEquals(CONTENT, urgentItem.getContent());
        assertEquals(DUE, urgentItem.getDue());
        assertEquals(STATUS, urgentItem.getStatus());
    }

    @Test
    public void testUrgency() {
        assertTrue(urgentItem.getUrg());
    }


    @Test
    public void testPrintEntry() {
        urgentItem.setContent(CONTENT);
        urgentItem.setDue(DUE);
        assertEquals(CONTENT + " is due on " + formatter.format(DUE) + ". Not completed :(",
                urgentItem.printItem());

        urgentItem.setStatus(STATUS);
        assertEquals(CONTENT + " is due on " + formatter.format(DUE) + ". Completed!",
                urgentItem.printItem());
    }
}
