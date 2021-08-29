/**
 * Represents a command to mark a task as done.
 * 
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class MarkDoneCommand extends Command{
    private boolean isExitCommand;
    private int taskNumber;

    /**
     * Class constructor.
     * 
     * @param taskNumber the serial number of the task.
     */
    MarkDoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
        isExitCommand = false;
    }

    /**
     * Executes the command to mark a task as done.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.markDone(taskNumber - 1);
        ui.showMarkTaskDone(task);
    }
    
}
