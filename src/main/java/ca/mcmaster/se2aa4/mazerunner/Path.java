package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class Path {
    private ArrayList<Character> path;
    public Path(String path, boolean factorized) {
        if(!factorized) {
            ArrayList<Character> tempPath = new ArrayList<>();
            for(char c : path.toCharArray()) {
                tempPath.add(c);
            }

            this.path = tempPath;
        }else{
            ArrayList<Character> tempPath = new ArrayList<>();
            for(char c : path.toCharArray()) {
                tempPath.add(c);
            }

            this.path = tempPath;
        }

    }

    public void add(char c) {
        path.add(c);
    }
    public ArrayList<Character> getPath() {
        return path;
    }

    public boolean checkPath(Maze maze) {
        return false;
    }

    public String getFactorizedPath() {
        return "";
    }

    public String toString() {
        String res = "";
        for(Character c : path) {
            res += c;
        }
        return res;
    }

}
