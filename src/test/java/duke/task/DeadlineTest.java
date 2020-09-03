package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.ExceptionMessage;
import duke.exception.IncorrectFormatException;

public class DeadlineTest {

    @Test
    public void createDeadline_normalInput_success() {
        String input01 = "read book /by tmr";
        String input02 = "return book /by 18:00";
        String input03 = "have dinner /by 2020-08-26 12:00";

        try {
            Deadline.createDeadline(input01);
            Deadline.createDeadline(input02);
            Deadline.createDeadline(input03);
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void createDeadline_noDeadline_exceptionThrown() {
        Exception exception = assertThrows(
                IncorrectFormatException.class, () -> Deadline.createDeadline("read book"));

        String errMessage = ExceptionMessage.DEADLINE_INCORRECT_FORMAT_MESSAGE;

        assertEquals(errMessage, exception.getMessage());
    }

    @Test
    public void getTaskTypeTest() {
        assertEquals("deadline",
                Deadline.createDeadline("read book /by tmr").getTaskType());
    }
}
