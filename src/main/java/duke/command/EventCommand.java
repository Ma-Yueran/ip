package duke.command;

import duke.Duke;
import duke.task.Event;

/**
 * EventCommand creates a Event task, stores it in task list, reports to the user.
 */
public class EventCommand extends Command {

    /**
     * Constructs an EventCommand.
     */
    public EventCommand() {
        names = new String[] { "event" };
    }

    /**
     * Creates a Event task, stores it in task list, reports to the user.
     * @param str the task info
     * @param duke the currentDuke
     */
    @Override
    public void execute(String str, Duke duke) {
        Event newEvent = Event.createEvent(str);
        duke.getTaskList().addTask(newEvent);
        duke.getUi().reportNewTask(newEvent);
    }

}
