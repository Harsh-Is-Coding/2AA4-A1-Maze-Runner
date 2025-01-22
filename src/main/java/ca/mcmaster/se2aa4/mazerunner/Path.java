package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class Path {
    private ArrayList<String> path;
    public Path(String path, boolean factorized) {
        if (!factorized) this.path = new ArrayList<>();
        else this.path = new ArrayList<>();

    }

    public boolean checkPath(Maze maze) {
        return false;
    }

    public String getFactorizedPath() {
        return "";
    }

    public String toString() {
        return "";
    }

}
