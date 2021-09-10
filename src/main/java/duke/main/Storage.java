package duke.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a file to store data, operations to write on the file.
 *
 * @author Gordon
 * @version CS2103T, Semester 2
 */
public class Storage {
    private String filePath;
    private String fileName;
    private File file;
    private FileWriter fileWriter;

    /**
     * Class constructor.
     *
     * @param filePath the path from the project directory to the storage file.
     * @param fileName the name of the file.
     */
    public Storage(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
        assert filePath != null && fileName != null : "filepath and filename cannot be null";
    }

    /**
     * Loads all the tasks saved in the storage folder into a tasks arraylist.
     *
     * @return an arraylist of tasks.
     * @throws DukeException exception handled by DukeException class.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File fileDirectory = new File(filePath);
            if (!fileDirectory.exists()) {
                fileDirectory.mkdir();
            }
            file = new File(filePath + "/" + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            if (file.length() == 0) {
                return tasks;
            }
            assert file.length() > 0 : "file must not be empty";
            Scanner sc = new Scanner(file);
            String line;
            while (sc.hasNext()) {
                line = sc.nextLine();
                Task t;
                String divider = " | ";
                int startingIndex = line.indexOf(divider) + divider.length() + 4;
                if (line.contains("D |")) {
                    String taskDescriptionAndTime = line.substring(startingIndex);
                    int startOfTimeIndex = taskDescriptionAndTime.indexOf(divider);
                    String task = taskDescriptionAndTime.substring(0, startOfTimeIndex);
                    String time = taskDescriptionAndTime.substring(startOfTimeIndex + divider.length());
                    t = new Deadline(task, time);
                } else if (line.contains("E |")) {
                    String taskDescriptionAndTime = line.substring(startingIndex);
                    int startOfTimeIndex = taskDescriptionAndTime.indexOf(divider);
                    String task = taskDescriptionAndTime.substring(0, startOfTimeIndex);
                    String time = taskDescriptionAndTime.substring(startOfTimeIndex + divider.length());
                    t = new Event(task, time);
                } else {
                    String taskDescription = line.substring(startingIndex);
                    t = new Todo(taskDescription);
                }
                if (line.contains("| 0 |")) {
                    t.markAsDone();
                }
                tasks.add(t);
            }
            sc.close();
        } catch (IOException e) {
            file = new File(filePath);
            throw new DukeException(e);
        }
        return tasks;
    }

    /**
     * Stores all the tasks from the tasks array into the storage file.
     *
     * @param tasks an arraylist of tasks.
     * @throws IOException exception caused in creating new file.
     */
    public void store(TaskList tasks) throws IOException {
        fileWriter = new FileWriter(file, false);
        String data = "";
        for (int i = 0; i < tasks.getNumTasks(); i++) {
            data = data.concat(tasks.getTask(i).formatToStore() + "\n");
        }
        fileWriter.write(data);
        fileWriter.close();
    }

}
