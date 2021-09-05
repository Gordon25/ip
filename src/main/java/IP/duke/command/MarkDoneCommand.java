package IP.duke.command;

import IP.duke.main.Duke;
import IP.duke.main.DukeException;
import IP.duke.task.Task;
import IP.duke.main.Storage;
import IP.duke.main.TaskList;
import IP.duke.main.Ui;

/**
 * Represents a command to mark a task as done.
 * 
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class MarkDoneCommand extends Command {
    private boolean isExitCommand;
    private int taskNumber;

    /**
     * Class constructor.
     * 
     * @param taskNumber the serial number of the task.
     */
    public MarkDoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
        isExitCommand = false;
    }

    /**
     * Executes the command to mark a task as done.
     * @throws DukeException exception handled by DukeException class
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.markDone(taskNumber - 1);
            ui.showMarkTaskDone(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(e);
        }
    }
}