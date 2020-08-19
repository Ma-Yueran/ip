package function;

import ui.UIPrint;
import task.Task;
import command.Command;
import data.DukeData;
import data.DukeCommandSet;

import exception.IncorrectFormatException;
import exception.InvalidIndexException;
import exception.NoDescriptionException;
import exception.UnknownCommandException;

public class DukeFunction {
    public static void greet() {
        System.out.println(UIPrint.logo);

        UIPrint.drawLine(UIPrint.star, 50);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        UIPrint.drawLine(UIPrint.star, 50);
    }

    public static void echo(String str) {
        UIPrint.drawLine(UIPrint.star, 50);
        System.out.println(str);
        UIPrint.drawLine(UIPrint.star, 50);
    }

    public static void reportNewTask(Task task) {
        UIPrint.drawLine(UIPrint.star, 50);

        System.out.println("Got it. I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + DukeData.tasks.size() + " tasks in the list.");

        UIPrint.drawLine(UIPrint.star, 50);
    }

    public static void reportDeleteTask(Task task) {
        UIPrint.drawLine(UIPrint.star, 50);

        System.out.println("Noted. I've removed this task: ");
        System.out.println(task);
        System.out.println("Now you have " + DukeData.tasks.size() + " tasks in the list");

        UIPrint.drawLine(UIPrint.star, 50);
    }

    public static void checkCommand(String str) {
        String[] inputParts = str.split(" ", 2);
        String possibleCommand = inputParts[0];
        String rest = inputParts.length == 2 ? inputParts[1] : "";
        Command command = null;

        try {
            command = DukeCommandSet.getInstance().getCommand(possibleCommand);
        } catch (UnknownCommandException exception) {
            System.out.println(exception.getMessage());
        }

        if (command != null) {
            try {
                command.execute(rest);
            } catch (NoDescriptionException | IncorrectFormatException | InvalidIndexException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
