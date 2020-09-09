package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.ExceptionMessage;
import duke.exception.UnknownTaskTypeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * TaskStorage saves and loads task data.
 */
public class TaskStorage {

    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

    private Path folderPath;
    private File folderFile;
    private Path taskFilePath;
    private File taskFile;
    private Path isDoneFilePath;
    private File isDoneFile;

    private Scanner taskReader;
    private Scanner isDoneReader;

    /**
     * Constructs a TaskStorage object.
     * @throws IOException
     */
    public TaskStorage() throws IOException {
        folderPath = Paths.get(".", "saves");
        folderFile = folderPath.toFile();
        taskFilePath = Paths.get(folderPath.toString(), "taskSave.txt");
        taskFile = taskFilePath.toFile();
        isDoneFilePath = Paths.get(folderPath.toString(), "isDoneSave.txt");
        isDoneFile = isDoneFilePath.toFile();

        folderFile.mkdir();
        taskFile.createNewFile();
        isDoneFile.createNewFile();

        taskReader = new Scanner(taskFile);
        isDoneReader = new Scanner(isDoneFile);
    }

    private Task stringToTask(String taskString) {
        String[] taskStringParts = taskString.split(" ", 2);
        String taskType = taskStringParts[0];
        String taskInfo = taskStringParts[1];

        switch (taskType) {
        case TODO:
            return ToDo.createToDo(taskInfo);
        case DEADLINE:
            return Deadline.createDeadline(taskInfo);
        case EVENT:
            return Event.createEvent(taskInfo);
        default:
            throw new UnknownTaskTypeException(ExceptionMessage.getUnknownTaskMessage(taskType));
        }
    }

    private String taskToString(Task task) {
        return task.getTaskType() + " " + task.getTaskInfo();
    }

    /**
     * Gets all tasks saved in the task saving txt files.
     * @return the ArrayList of all tasks saved
     */
    public ArrayList<Task> getSavedTasks() {
        ArrayList<Task> tasks = new ArrayList<>();

        while (taskReader.hasNextLine() && isDoneReader.hasNextLine()) {
            String taskString = taskReader.nextLine();
            boolean isTaskDone = isDoneReader.nextBoolean();

            Task task = stringToTask(taskString);
            if (isTaskDone) {
                task.markAsDone();
            }

            tasks.add(task);
        }

        return tasks;
    }

    /**
     * Saves all tasks given to the task saving txt files.
     * @param tasks tasks to be saved
     * @throws IOException
     */
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        FileWriter taskWriter = new FileWriter(taskFile);
        FileWriter isDoneWriter = new FileWriter(isDoneFile);

        // Remove all current contents in the files
        taskWriter.write("");
        isDoneWriter.write("");

        for (Task task : tasks) {
            taskWriter.append(taskToString(task) + '\n');
            isDoneWriter.append(Boolean.toString(task.isTaskDone()) + '\n');
        }
        taskWriter.close();
        isDoneWriter.close();
    }
}
