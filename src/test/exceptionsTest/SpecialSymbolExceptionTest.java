package exceptionsTest;

import exceptions.EmptyContentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.ToDoList;

import static org.junit.jupiter.api.Assertions.fail;

public class SpecialSymbolExceptionTest {
    ToDoList testToDoList;

    @BeforeEach
    public void runBefore() {
        testToDoList = new ToDoList();
    }

    @Test
    public void testSpecialSymbolExceptionFail() {
        try {
            validInputContent("fjiasfja==fsfs");
            fail("InvalidContentException was not thrown");
        } catch (exceptions.SpecialSymbolException e) {
            // expected
        }
    }

    @Test
    public void testInvalidContentExceptionPass() {
        try {
            validInputContent("some content...");
        } catch (exceptions.SpecialSymbolException e) {
            fail("SpecialSymbolException should not have been thrown");
        }
    }


    private void validInputContent(String inputContent) throws exceptions.SpecialSymbolException {
        if (inputContent.contains("==")) {
            throw new exceptions.SpecialSymbolException();
        }

    }
}
