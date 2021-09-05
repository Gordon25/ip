package duke.task;

import IP.duke.main.DukeException;

/**
 * Represents a todo task.
 * 
 * @author Gordon Yit
 * @version C2103T, Semester 2
 */
public class Todo extends Task {
    
    private final String TASK_MARKER = "T";
    private String taskDescription;
    private final String TASK_KEYWORD = "todo ";
    
    /**
     * Class constructor.
     * 
     * @param description the task description.
     * @throws DukeException cause by exception handled in DukeException class.
     */
    public Todo(String description) throws DukeException {
        try {
            if (!description.contains("todo")) {
                throw new IllegalArgumentException();
            }
            int startingIndex = description.indexOf(TASK_KEYWORD);
            taskDescription = description.substring(startingIndex + TASK_KEYWORD.length());
            if (taskDescription == "") {
                throw new StringIndexOutOfBoundsException();
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(e);
        } catch (IllegalArgumentException e) {
            throw new DukeException(e);
        }
    }

    /**
     * Prints out the task.
     * 
     * @return string format of the todo task, 
     * consisting of the task marker "T" and task description.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s %s", TASK_MARKER, super.toString(), taskDescription);
    }

    /**
     * Formats the task in to the storage format.
     *
     * @return storage format of the task.
     */
    public String formatToStore() {
        return String.format("%s | %s | %s", TASK_MARKER, getStatusIcon() == " " ? 1 : 0,
                taskDescription);
    }

    /**
     * Class constructor for loading tasks from storage file.
     *
     * @param todoDescription description of todo task.
     * @param dateOfTask date of the todo task (unused).
     */
    public Todo(String todoDescription, String dateOfTask) {
        taskDescription = todoDescription;
    }
    
    /**
     * Returns task marker. 
     *
     * @return a one character string that is a marker for this task.
     */
    public String getTaskMarker() {
        return TASK_MARKER;
    }
}
