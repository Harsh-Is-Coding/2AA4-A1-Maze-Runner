package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Explorer implements Subject {
    private static final Logger log = LogManager.getLogger(Explorer.class);
    private int posX;
    private int posY;
    private char direction;
    Maze maze;
    Path path;
    private MazeSolver solver;
    private List<Observer> observers;
    //Command Constructors
    private MoveForwardCommand moveForwardCommand = new MoveForwardCommand(this);
    private TurnLeftCommand turnLeftCommand = new TurnLeftCommand(this);
    private TurnRightCommand turnRightCommand = new TurnRightCommand(this);

    public Explorer(int startX, int startY, char startDirection, Maze maze, Path path, MazeSolver solver) {
        this.posX = startX;
        this.posY = startY;
        this.direction = startDirection;
        this.maze = maze;
        this.path = path;
        this.solver = solver;


    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(char move) {
        if (observers == null) {
            return;
        }
        for (Observer observer : observers) {
            observer.update(move);
        }
    }

    public String getObserverPath() {
        if (observers == null || observers.isEmpty()) {
            return null;
        }
        String p = observers.getFirst().toString();
        Path path = new Path(p, false);
        return path.toFactorizedPath();
    }




    //run the solver based on interface, allows for adding diff in the future
    public String solveMaze(){
        this.observers = new ArrayList<>();
        observers.add(new Path("", false));
        return solver.solve(this);

    }

    // methods for right hand algo
    public boolean checkRightSideIsWall(){
        if(direction == 'U') return maze.isWall(posX+1, posY);
        else if(direction == 'D') return maze.isWall(posX-1, posY);
        else if(direction == 'L') return maze.isWall(posX, posY-1);
        else if(direction == 'R') return maze.isWall(posX, posY+1);
        else return false;
    }

    public boolean checkFrontSideIsWall(){
        if(direction == 'U') return maze.isWall(posX, posY-1);
        else if(direction == 'D') return maze.isWall(posX, posY+1);
        else if(direction == 'L') return maze.isWall(posX-1, posY);
        else if(direction == 'R') return maze.isWall(posX+1, posY);
        else return false;
    }

    //move through maze to check inputted path
    public boolean validatePath(){
        boolean valid = true;
        String pathString = path.toString();

        for(char c : pathString.toCharArray()){
            if(c == 'F'){
                valid = moveForwardCommand.execute();
            }else if(c == 'R'){
                turnRightCommand.execute();
            }else if(c == 'L'){
                turnLeftCommand.execute();
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



    //methods to move through maze
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

    //methods used for initialization and interaction
    private void setDirection(char direction) {
        this.direction = direction;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

}
