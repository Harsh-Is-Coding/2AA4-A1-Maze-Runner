package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Explorer {
    private static final Logger log = LogManager.getLogger(Explorer.class);
    private int posX;
    private int posY;
    private char direction;
    Maze maze;
    Path path;

    public Explorer(int startX, int startY, char startDirection, Maze maze, Path path) {
        this.posX = startX;
        this.posY = startY;
        this.direction = startDirection;
        this.maze = maze;
        this.path = path;
    }

    public boolean solveMaze(){
        //TODO add rightHand algo
        Path mazePath = new Path("", false);
        boolean valid = true;

        //keep moving forward till failure
        while(valid){
            valid = moveForward();

            if(valid){
                path.add('F');
            }else{
                System.out.println("maze failed at position " + posX + ", " + posY);
                return false;
            }

            //check if at exit point
            if(direction == 'R' && getPosX() == maze.getRightEntryPos()[0] && getPosY() == maze.getRightEntryPos()[1]){
                System.out.println("success");
                System.out.println("path is " + path.toString());
                return true;
            } else if (direction == 'L'  && getPosX() == maze.getLeftEntryPos()[0] && getPosY() == maze.getLeftEntryPos()[1]) {
                System.out.println("success");
                System.out.println("path is " + path.toString());
                return true;
            }
        }

        int[] currentPos = new int[2];
        currentPos[0] = posX;
        currentPos[1] = posY;

        //check if at exit point
        if(direction == 'R' && currentPos[0] == maze.getRightEntryPos()[0] && currentPos[1] == maze.getRightEntryPos()[1]){
            System.out.println("successful ended on Right at "+currentPos[0]+" "+currentPos[1]);
            return true;
        }else if(direction == 'L' && currentPos[0] == maze.getLeftEntryPos()[0] && currentPos[1] == maze.getLeftEntryPos()[1]){
            System.out.println("successful ended on Left at "+currentPos[0]+" "+currentPos[1]);
            return true;
        }else{
            return false;
        }

    }
    public boolean validatePath(){
        boolean valid = true;
        String pathString = path.toString();

        for(char c : pathString.toCharArray()){
            if(c == 'F'){
                valid = moveForward();
            }else if(c == 'R'){
                turnRight();
            }else if(c == 'L'){
                turnLeft();
            }else if(Character.isWhitespace(c)){
                continue;
            }else{
                log.error(c + " is not a valid path");
                return false;
            }

            if(!valid){
                log.error("not a valid forward move at position " + posX + ", " + posY);
                return false;
            }
        }

        if(posX == maze.getRightEntryPos()[0] && posY == maze.getRightEntryPos()[1]){
            log.info("successful ended on Right at "+posX+" "+posY);
            return true;
        }else if(posX == maze.getLeftEntryPos()[0] && posY == maze.getLeftEntryPos()[1]){
            log.info("successful ended on Left at "+posX+" "+posY);
            return true;
        }else{
            log.info("failed maze at position " + posX + ", " + posY);
            return false;
        }

    }

    public Path getPath() {
        return path;
    }

    public boolean moveForward() {
        if (direction == 'U' && maze.isPass(posX, posY - 1)) {
            posY--;
            return true;
        }else if (direction == 'D' && maze.isPass(posX, posY + 1)) {
            posY++;
            return true;
        }else if (direction == 'L' && maze.isPass(posX-1, posY)) {
            posX--;
            return true;
        }else if (direction == 'R' && maze.isPass(posX+1, posY)) {
            posX++;
            return true;
        }else{
            return false;
        }
    }

    public void turnLeft() {
        if (direction == 'U') setDirection('L');
        else if (direction == 'D') setDirection('R');
        else if (direction == 'L') setDirection('D');
        else if (direction == 'R') setDirection('U');
    }

    public void turnRight() {
        if (direction == 'U') setDirection('R');
        else if (direction == 'D') setDirection('L');
        else if (direction == 'L') setDirection('U');
        else if (direction == 'R') setDirection('D');
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public char getDirection() {
        return direction;
    }
}
