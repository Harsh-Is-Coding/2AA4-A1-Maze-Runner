package ca.mcmaster.se2aa4.mazerunner;

public class TurnLeftCommand extends Command {
    public TurnLeftCommand(Explorer explorer) {
        super(explorer);
    }

    public void execute() {
        backup();
        explorer.turnLeft();
    }

}
