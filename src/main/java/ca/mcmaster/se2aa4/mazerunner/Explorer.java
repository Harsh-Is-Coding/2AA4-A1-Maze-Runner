package ca.mcmaster.se2aa4.mazerunner;

public class Explorer {
    private int posX;
    private int posY;
    private char direction;
    Maze maze;

    public Explorer(int startX, int startY, char startDirection, Maze maze) {
        this.posX = startX;
        this.posY = startY;
        this.direction = startDirection;
        this.maze = maze;
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
