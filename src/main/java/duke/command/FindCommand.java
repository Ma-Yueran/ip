package duke.command;

import java.util.ArrayList;

import duke.Duke;
import duke.task.Task;

/**
 * FindCommand finds all tasks that contain the keyword entered by user,
 * then report to user.
 */
public class FindCommand extends Command {

    /**
     * Constructs a FindCommand.
     */
    public FindCommand() {
        names = new String[] { "find" };
    }

    /**
     * Finds all tasks containing the keyword using DukeTaskList,
     * then use Ui to report to the user.
     * @param str the keyword
     * @param duke the current Duke
     */
    @Override
    public void execute(String str, Duke duke) {
        ArrayList<Task> tasksFound = duke.getTaskList().findTasks(str);
        duke.getUi().reportGiveTasks(tasksFound);
    }
}
