package exceptionsTest;

import model.exceptions.EmptyContentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.ToDoList;

import static org.junit.jupiter.api.Assertions.fail;

public class EmptyContentExceptionTest {
    ToDoList testToDoList;

    @BeforeEach
    public void runBefore() {
        testToDoList = new ToDoList();
    }

    @Test
    public void testEmptyContentExceptionNull() {
        try {
            validateInputContent(null);
            fail("exceptions were not thrown");
        } catch (EmptyContentException e) {
            // expected
        }
    }

    @Test
    public void testEmptyContentExceptionEmpty() {
        try {
            validateInputContent("");
            fail("InvalidContentException was not thrown");
        } catch (EmptyContentException e) {
            // expected
        }
    }

    @Test
    public void testInvalidContentExceptionPass() {
        try {
            validateInputContent("some content...");
        } catch (EmptyContentException e) {
            fail("EmptyContentException should not have been thrown");
        }
    }


    private void validateInputContent(String inputContent) throws EmptyContentException {
        if (inputContent == null || inputContent.isEmpty()) {
            throw new EmptyContentException();
        }
    }

}
