package IP.duke.command;
import IP.duke.main.DukeException;
import IP.duke.main.Storage;
import IP.duke.main.TaskList;
import IP.duke.main.Ui;
import IP.duke.task.Deadline;
import IP.duke.task.Event;
import IP.duke.task.Task;
import IP.duke.task.Todo;

import java.time.format.DateTimeParseException;

/**
 * Represents a command to add a task.
 * 
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class AddCommand extends Command {
    private String addCommand;
    private boolean isExitCommand;

    /**
     * Class constructor.
     * 
     * @param addCommand the user inputed string to add a task.
     */
    public AddCommand(String addCommand) {
        this.addCommand = addCommand;
        isExitCommand = false;
    }

    /**
     * Executes the command to add a task for Duke.Duke to keep track of.
     * 
     * @param tasks lists of tasks
     * @param ui the user interface.
     * @param storage the storage file.
     * @throws DukeException exception handled by DukeException class
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task;
            if (addCommand.contains("deadline")) {
                task = new Deadline(addCommand);
            } else if (addCommand.contains("event")) {
                task = new Event(addCommand);
            } else {
                task = new Todo(addCommand);
            }
            Task taskAdded = tasks.add(task);
            ui.showTaskAdded(taskAdded, tasks.getNumTasks());
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(e);
        } catch (DateTimeParseException e) {
            throw new DukeException(e);
        }
    }
    
}
