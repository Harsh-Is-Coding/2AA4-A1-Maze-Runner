package ca.mcmaster.se2aa4.mazerunner;

public class MoveForwardCommand extends Command {
    public MoveForwardCommand(Explorer explorer) {
        super(explorer);
    }

    public boolean execute() {
        backup();
        return explorer.moveForward();
    }

}
