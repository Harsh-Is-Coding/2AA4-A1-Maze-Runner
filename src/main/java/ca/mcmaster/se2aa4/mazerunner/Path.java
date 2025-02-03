package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class Path {
    //always stored as canonical
    private ArrayList<Character> path;
    public Path(String path, boolean factorized) {

        //check if factorized and store accordingly
        if(!path.matches("[0-9FRL\\s]*")){
            System.out.println("/!\\ An error has occured /!\\");
            System.out.println("Invalid path");
            return;
        }

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

    // path changing methods
    public void add(char c) {
        path.add(c);
    }


    //conversion methods
    public String toFactorizedPath() {
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

        //add leftover count
        if(count > 1){
            factorizedPath.append(count).append(prev);
        }else {
            factorizedPath.append(prev);
        }
        return factorizedPath.toString();
    }

    public void toCannonicalPath(){
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

    //specific tostring for path
    //used for debugging
    public String toString() {
        String res = "";
        for(Character c : path) {
            res += c;
        }
        return res;
    }

}
