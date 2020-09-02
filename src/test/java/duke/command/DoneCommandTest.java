package duke.command;

import duke.DukeStub;
import duke.exception.InvalidIndexException;
import duke.task.Task;
import duke.ui.UiPrint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoneCommandTest {

    private final DoneCommand command = new DoneCommand();

    private final DukeStub dukeStub = new DukeStub();

    @Test
    public void constructorTest() {
        try {
            new DoneCommand();
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void execute_normalInput_taskMarkedAsDone() {
        String normalInput = "1";
        Task taskToBeMarked = dukeStub.getTaskList().getTask(0);

        try {
            command.execute(normalInput, dukeStub);

            assertEquals(true, taskToBeMarked.isTaskDone());
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void execute_invalidInput_exceptionThrown() {
        String invalidInput = "-12";

        Exception exception = assertThrows(InvalidIndexException.class,
                () -> command.execute(invalidInput, dukeStub));

        String line = UiPrint.getLine(UiPrint.STAR, 50);
        String errMessage =
                line + "\nSorry " + invalidInput + " is not a valid index\n" + line;

        assertEquals(errMessage, exception.getMessage());
    }
}
