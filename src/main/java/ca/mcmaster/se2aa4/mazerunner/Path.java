package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class Path {
    private ArrayList<Character> path;
    public Path(String path, boolean factorized) {

        //TODO check if factorized and store accordingly
        if(factorized) {
            ArrayList<Character> tempPath = new ArrayList<>();
            for(char c : path.toCharArray()) {
                tempPath.add(c);
            }

            this.path = tempPath;
            toCannonicalPath();
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

    public String toFactorizedPath() {
        System.out.println("converting to factorized Path");
        if(path.size() == 1 || path.isEmpty()) return path.toString();

        StringBuilder factorizedPath = new StringBuilder();
        char prev = path.getFirst();
        int count = 1;

        for(int i = 1; i < path.size(); i++) {
            char curr = path.get(i);
            if(Character.isWhitespace(curr)) {
                continue;
            } else if(curr == prev) {
                count++;
            }else{
                if(count > 1){
                    factorizedPath.append(count).append(prev).append(" ");
                }else {
                    factorizedPath.append(prev).append(" ");
                }
                prev = curr;
                count = 1;
            }
        }

        if(count > 1){
            factorizedPath.append(count).append(prev);
        }else {
            factorizedPath.append(prev);
        }
        return factorizedPath.toString();
    }

    public void toCannonicalPath(){
        System.out.println("Converting to Canonical Path...");
        ArrayList<Character> tempPath = new ArrayList<>();

        for (int i = 0; i < path.size(); i++) {
            char curr = path.get(i);

            if (Character.isDigit(curr)) {
                int count = Character.getNumericValue(curr);
                if (i + 1 < path.size()) {
                    char move = path.get(i + 1);
                    for (int j = 0; j < count; j++) {
                        tempPath.add(move);
                    }

                    i++;
                }
            } else {
                tempPath.add(curr);
            }
        }

        path = tempPath;
    }

    public String toString() {
        String res = "";
        for(Character c : path) {
            res += c;
        }
        return res;
    }

}
