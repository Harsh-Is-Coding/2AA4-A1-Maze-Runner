package ca.mcmaster.se2aa4.mazerunner;

public class Explorer {
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
        Path mazePath = new Path("", false);
        boolean valid = true;

        while(valid){
            valid = moveForward();

            if(valid){
                path.add('F');
            }else{
                System.out.println("maze failed at position " + posX + ", " + posY);
                return false;
            }

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
