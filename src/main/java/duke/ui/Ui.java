package duke.ui;

import duke.Duke;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Ui interacts with users by printing out messages in console.
 * e.g. When the user enters a command, the command function would be
 * executed, and methods in Ui is usually called in by the execute function
 * to respond the user.
 */
public class Ui {

    private Duke duke;

    /**
     * Constructs a Ui.
     * @param duke the Duke that will use this Ui
     */
    public Ui(Duke duke) {
        this.duke = duke;
    }

    /**
     * Greets the user.
     */
    public void greet() {
        System.out.println(UiPrint.logo);

        UiPrint.drawLine(UiPrint.star, 50);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        UiPrint.drawLine(UiPrint.star, 50);
    }

    /**
     * Repeats the input string once.
     * @param str string to be repeated
     */
    public void echo(String str) {
        UiPrint.drawLine(UiPrint.star, 50);
        System.out.println(str);
        UiPrint.drawLine(UiPrint.star, 50);
    }

    /**
     * Prints out the current tasks saved in task list.
     */
    public void reportCurrentTasks() {
        UiPrint.drawLine(UiPrint.star, 50);

        System.out.println("Current tasks:\n");

        for (int i = 0; i < duke.taskList.getSize(); i++) {
            System.out.println(i + 1 + ". " + duke.taskList.getTask(i));
        }

        if (duke.taskList.getSize() == 0) {
            System.out.println("None");
        }

        UiPrint.drawLine(UiPrint.star, 50);
    }

    /**
     * Tells the user a new task is added to the task list.
     * @param task the new task added
     */
    public void reportNewTask(Task task) {
        UiPrint.drawLine(UiPrint.star, 50);

        System.out.println("Got it. I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + duke.taskList.getSize() + " tasks in the list.");

        UiPrint.drawLine(UiPrint.star, 50);
    }

    /**
     * Tells the user a task has been marked as done.
     * @param task the task marked as done
     */
    public void reportDoneTask(Task task) {
        UiPrint.drawLine(UiPrint.star, 50);

        System.out.println("Nice, I've marked this task as done:");
        System.out.println(task);

        UiPrint.drawLine(UiPrint.star, 50);
    }

    /**
     * Tells the user a task has been deleted.
     * @param task the task deleted
     */
    public void reportDeleteTask(Task task) {
        UiPrint.drawLine(UiPrint.star, 50);

        System.out.println("Noted. I've removed this task: ");
        System.out.println(task);
        System.out.println("Now you have " + duke.taskList.getSize() + " tasks in the list");

        UiPrint.drawLine(UiPrint.star, 50);
    }

    /**
     * Says good bye to the user.
     */
    public void reportExit() {
        UiPrint.drawLine(UiPrint.star, 50);

        String exitWords = "Bye, hope to see you again soon!";
        System.out.println(exitWords);

        UiPrint.drawLine(UiPrint.star, 50);
    }

    public void reportGiveTasks(ArrayList<Task> tasks) {
        UiPrint.drawLine(UiPrint.star, 50);

        System.out.println("Here are the matching tasks in your list:");

        if (tasks.size() == 0) {
            System.out.println("None");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + ". " + tasks.get(i));
            }
        }

        UiPrint.drawLine(UiPrint.star, 50);
    }
}
