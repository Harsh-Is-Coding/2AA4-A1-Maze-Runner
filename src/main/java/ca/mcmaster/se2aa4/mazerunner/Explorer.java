package ca.mcmaster.se2aa4.mazerunner;

public class Explorer {
    private int posX;
    private int posY;
    private char direction;

    public Explorer(int startX, int startY, char startDirection) {
        this.posX = startX;
        this.posY = startY;
        this.direction = startDirection;
    }

    public void moveForward() {
        if (direction == 'U') posY--;
        else if (direction == 'D') posY++;
        else if (direction == 'L') posX--;
        else if (direction == 'R') posX++;
    }

    public void turnLeft() {
        if (direction == 'U') direction = 'L';
        else if (direction == 'D') direction = 'R';
        else if (direction == 'L') direction = 'D';
        else if (direction == 'R') direction = 'U';
    }

    public void turnRight() {
        if (direction == 'U') direction = 'R';
        else if (direction == 'D') direction = 'L';
        else if (direction == 'L') direction = 'U';
        else if (direction == 'R') direction = 'D';
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
