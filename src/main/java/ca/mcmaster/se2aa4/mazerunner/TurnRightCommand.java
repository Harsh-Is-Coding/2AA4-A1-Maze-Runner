package ca.mcmaster.se2aa4.mazerunner;

public class TurnRightCommand extends Command {
    public TurnRightCommand(Explorer explorer) {
        super(explorer);
    }

    public void execute() {
        backup();
        explorer.turnRight();
    }

}
