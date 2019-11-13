package modelTest;

import model.RegularItem;
import model.SchoolList;
import model.Subscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;


public class SchoolListTest {
    private SchoolList sl;
    private RegularItem ri1;
    private RegularItem ri2;
    private RegularItem ri11;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    private Subscriber subscriber;

    @BeforeEach
    public void runBefore() throws ParseException {
        sl = new SchoolList();
        ri1 = new RegularItem();
        ri1.setContent("content1");
        ri1.setDue(formatter.parse("20/11/2019"));
        ri2 = new RegularItem();
        ri2.setContent("content2");
        ri2.setDue(formatter.parse("22/11/2019"));
    }

    @Test
    public void testDisplayEmptySchoolList() {
        assertEquals(0, sl.schoolItems.size());
        assertNull(ri1.getSchoolList());
    }

    @Test
    public void testAddAndRemoveItem() {
        sl.addItem(ri1);
        assertEquals(1, sl.getSchoolItems().size());
        assertTrue(sl.getSchoolItems().contains(ri1));
        assertEquals(sl, ri1.getSchoolList());


        sl.addItem(ri2);
        assertEquals(2, sl.getSchoolItems().size());
        assertTrue(sl.schoolItems.contains(ri2));
        assertEquals(sl, ri2.getSchoolList());

        sl.removeItem(ri1);
        assertEquals(1, sl.schoolItems.size());
        assertTrue(sl.schoolItems.contains(ri2));
        assertNull(ri1.getSchoolList());
        assertEquals(sl, ri2.getSchoolList());
    }

    @Test
    public void testClearSchoolItems() {
        sl.addItem(ri1);
        sl.addItem(ri2);
        assertNotEquals(0, sl.getSchoolItems().size());
        sl.clearSchoolItems();
        assertEquals(0, sl.getSchoolItems().size());
    }

//    @Test
//    public void testAddItemWithDuplicateInfo() throws ParseException {
//        ri11 = new RegularItem();
//        ri11.setContent("content1");
//        ri11.setDue(formatter.parse("20/11/2019"));
//
//        sl.addItem(ri1);
//        assertEquals(1, sl.getSchoolItems().size());
//        assertTrue(sl.getSchoolItems().contains(ri1));
//        assertEquals(sl, ri1.getSchoolList());
//
//        sl.addItem(ri11);
//        assertTrue(sl.getSchoolItems().contains(ri1));
//        assertFalse(sl.getSchoolItems().contains(ri11));
//        assertEquals(sl, ri1.getSchoolList());
//        assertNull(ri11.getSchoolList());
//    }


    @Test
    public void testAddObserver() {
        subscriber = new Subscriber("Tom");

        assertEquals(0, sl.getObservers().size());

        sl.addObserver(subscriber);
        assertEquals(1, sl.getObservers().size());
    }

}
