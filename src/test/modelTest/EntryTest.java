package modelTest;

import model.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntryTest {
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    private Entry entry;
    private final static String CONTENT = "sample content";
    private final static String DUE_STRING = "30/09/2019";
    private final Date DUE = formatter.parse(DUE_STRING);
    private final static Boolean COMPLETION = true;


    public EntryTest() throws ParseException {
    }


    @BeforeEach
    public void runBefore() {
        entry = new Entry();
    }


    @Test
    public void testSetContent() {
        assertEquals(null, entry.getContent());

        entry.setContent(CONTENT);
        assertEquals(CONTENT, entry.getContent());
    }

    @Test
    public void testSetDue() {
        assertEquals(null, entry.getDue());

        entry.setDue(DUE);
        assertEquals(DUE, entry.getDue());
    }

    @Test
    public void testSetCompletion() {
        assertEquals(false, entry.getStatus());

        entry.setStatus(COMPLETION);
        assertEquals(true, entry.getStatus());
    }


    @Test
    public void testPrintEntry() {
        entry.setContent(CONTENT);
        entry.setDue(DUE);
        assertEquals(CONTENT + " is due on " + formatter.format(DUE) + ". Not completed :(",
                entry.printEntry());

        entry.setStatus(COMPLETION);
        assertEquals(CONTENT + " is due on " + formatter.format(DUE) + ". Completed!",
                entry.printEntry());
    }
}
