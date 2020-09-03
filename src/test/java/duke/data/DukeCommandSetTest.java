package duke.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.UnknownCommandException;
import duke.ui.UiPrint;

public class DukeCommandSetTest {

    private final DukeCommandSet commandSet = new DukeCommandSet();

    @Test
    public void getCommand_validCommandName_success() {
        String commandName01 = "list";
        String commandName02 = "todo";
        String commandName03 = "done";

        try {
            commandSet.getCommand(commandName01);
            commandSet.getCommand(commandName02);
            commandSet.getCommand(commandName03);
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void getCommand_invalidCommandName_exceptionThrown() {
        String invalidCommandName = "hahaha";

        Exception exception = assertThrows(
                UnknownCommandException.class, () -> commandSet.getCommand(invalidCommandName));

        String line = UiPrint.getLine(UiPrint.STAR, 50);
        String errMessage = "\nOOPS!!! I'm sorry, but I don't know what that means :-(\n";

        assertEquals(errMessage, exception.getMessage());
    }
}
