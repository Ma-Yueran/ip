import exception.IncorrectFormatException;
import exception.NoDescriptionException;

public class EventCommand extends Command {

    public EventCommand() {
        names = new String[] { "event" };
    }

    @Override
    public void execute(String str) {
        String[] splitStr = str.split(" /at ", 2);

        if (splitStr.length != 2) {
            String line = UIPrint.getLine(UIPrint.star, 50);
            String errMessage =
                    line + "\nPlease follow the format of event <task description> /at <event time>\n" + line;
            throw new IncorrectFormatException(errMessage);
        }

        String description = splitStr[0];
        String time = splitStr[1];

        Event newEvent = new Event(UIPrint.eventIcon, description, time);

        Duke.tasks.add(newEvent);
        Duke.reportNewTask(newEvent);
    }
}