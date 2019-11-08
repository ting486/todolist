package exceptionsTest;

import model.exceptions.InvalidCrossingOffException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.ToDoList;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.fail;

public class InvalidCrossingOffExceptionTest {
    ToDoList toDoList;

    @BeforeEach
    public void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        toDoList = new ToDoList();
    }

    @Test
    public void testValidateEntryNumTooBig() {
        try {
            validateEntryNum(100);
            fail("Exception was not thrown");
        } catch (InvalidCrossingOffException e) {
            // expected
        }
    }

    @Test
    public void testValidateEntryNumTooSmall() {
        try {
            validateEntryNum(-100);
            fail("Exception was not thrown");
        } catch (InvalidCrossingOffException e) {
            // expected
        }
    }

    @Test
    public void testValidateEntryNumUpperBound() {
        try {
            validateEntryNum(10);
            fail("Exception was not thrown");
        } catch (InvalidCrossingOffException e) {
            // expected
        }
    }

    @Test
    public void testValidateEntryNumLowerBound() {
        try {
            validateEntryNum(-1);
            fail("Exception was not thrown");
        } catch (InvalidCrossingOffException e) {
            // expected
        }
    }

    @Test
    public void testValidateEntryNumUpperPass() {
        try {
            validateEntryNum(9);
        } catch (InvalidCrossingOffException e) {
            fail("InvalidCrossingOffException should not have been thrown");
        }
    }

    @Test
    public void testValidateEntryNumLowerPass() {
        try {
            validateEntryNum(0);
        } catch (InvalidCrossingOffException e) {
            fail("InvalidCrossingOffException should not have been thrown");
        }
    }



    public void validateEntryNum(int entryNum) throws InvalidCrossingOffException {
        if (entryNum >= 10 || entryNum <  0) {
            throw new InvalidCrossingOffException();
        }
    }
}
