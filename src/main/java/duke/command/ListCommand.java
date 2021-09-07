package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * Represents a command to list out the tasks Duke.Duke is keeping track of.
 * 
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class ListCommand extends Command {
    private boolean isExitCommand;

    /**
     * Class constructor.
     */
    public ListCommand() {
        isExitCommand = false;
    }

    /**
     * Executes the command to list out all tasks Duke.Duke is keeping track of.
     * 
     * @param tasks taskList of tasks
     * @param ui the user interface.
     * @param storage the storage file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showListOfTasks(tasks);
    }
}
