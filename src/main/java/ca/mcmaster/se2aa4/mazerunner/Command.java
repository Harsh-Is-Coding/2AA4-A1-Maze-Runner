package ca.mcmaster.se2aa4.mazerunner;

public abstract class Command {
    public Explorer explorer;
    private String backupPath;

    public Command(Explorer explorer) {
        this.explorer = explorer;
    }

    void backup() {
        backupPath = explorer.getPath().toString();
    }

    public void restore() {
        explorer.setPath(new Path(backupPath, false));
    }
}
