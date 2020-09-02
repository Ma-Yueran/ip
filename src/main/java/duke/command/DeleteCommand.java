package duke.command;

import duke.Duke;
import duke.exception.InvalidIndexException;
import duke.task.Task;
import duke.ui.UiPrint;

/**
 * DeleteCommand asks DukeTaskList to remove the task with the input index.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a DeleteCommand.
     */
    public DeleteCommand() {
        names = new String[] { "delete" };
    }

    /**
     * Asks DukeTaskList to remove the task with the input index.
     * @param str the index of the task
     * @param duke the current Duke
     * @throws InvalidIndexException thrown when the index is invalid
     */
    @Override
    public void execute(String str, Duke duke) throws InvalidIndexException {
        boolean canParseInt = tryParseInt(str);
        int taskIndex = canParseInt ? Integer.parseInt(str) - 1 : -1;

        checkException(taskIndex, str, duke);

        Task task = duke.taskList.deleteTask(taskIndex);

        duke.ui.reportDeleteTask(task);
    }

    private boolean tryParseInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    private void checkException(int taskIndex, String str, Duke duke) {
        if (duke.taskList.getSize() <= taskIndex || taskIndex < 0) {
            String line = UiPrint.getLine(UiPrint.star, 50);
            String errMessage =
                    line + "\nSorry " + str + " is not a valid index\n" + line;
            throw new InvalidIndexException(errMessage);
        }
    }
}
