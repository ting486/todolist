package modelTest;

import model.Item;
import model.RegularItem;
import model.Subscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;


public class SubscriberTest {
    Subscriber subscriber;

    @BeforeEach
    public void runBefore() {
        subscriber = new Subscriber("Jim");
    }

    @Test
    public void testConstructor() {
        assertEquals("Jim", subscriber.getSubscriberData().getName());
    }

    @Test
    public void testUpdate() throws ParseException {
        Item item = new RegularItem();
        SimpleDateFormat formatter = new SimpleDateFormat(Item.FORMATTER_PATTERN);

        item.setThis("content", formatter.parse("12/12/2019"), false);
        subscriber.update(item);

        assertEquals("Jim now knows this item has been added to SchoolList: " + item.printItem(),
                subscriber.getUpdateMessage());
    }
}
