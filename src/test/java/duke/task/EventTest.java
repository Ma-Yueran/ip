package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import duke.exception.ExceptionMessage;
import org.junit.jupiter.api.Test;

import duke.exception.IncorrectFormatException;
import duke.ui.UiPrint;

public class EventTest {

    @Test
    public void createEvent_normalInput_success() {
        String input01 = "read book /at tmr";
        String input02 = "return book /at 18:00";
        String input03 = "have dinner /at 2020-08-26 12:00";

        try {
            Event.createEvent(input01);
            Event.createEvent(input02);
            Event.createEvent(input03);
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void createEvent_noTime_exceptionThrown() {
        Exception exception = assertThrows(
                IncorrectFormatException.class, () -> Event.createEvent("read book"));

        String errMessage = ExceptionMessage.EVENT_INCORRECT_FORMAT_MESSAGE;

        assertEquals(errMessage, exception.getMessage());
    }

    @Test
    public void getTaskTypeTest() {
        assertEquals("event",
                Event.createEvent("read book /at tmr").getTaskType());
    }
}
