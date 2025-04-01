package ca.mcmaster.se2aa4.mazerunner;

public class RightHandSolver implements MazeSolver {


    @Override
    public String solve(Explorer explorer) {
        //Command Constructors
        MoveForwardCommand moveForwardCommand = new MoveForwardCommand(explorer);
        TurnLeftCommand turnLeftCommand = new TurnLeftCommand(explorer);
        TurnRightCommand turnRightCommand = new TurnRightCommand(explorer);
        Path mazePath = new Path("", false);
        boolean atEnd = false;


        //keep going till end of maze
        while (!atEnd){

            //keep hand on right wall
            if(explorer.checkRightSideIsWall()){
                if(explorer.checkFrontSideIsWall()){
                    turnLeftCommand.execute();
                    mazePath.add('L');
                }else{
                    moveForwardCommand.execute();
                    mazePath.add('F');
                }
            }else{
                //if not right wall then turn
                turnRightCommand.execute();
                mazePath.add('R');
                moveForwardCommand.execute();
                mazePath.add('F');
            }

            //check for either end position
            if(explorer.getPosX() == explorer.maze.getRightEntryPos()[0] && explorer.getPosY() == explorer.maze.getRightEntryPos()[1]){
                atEnd = true;
            }else if(explorer.getPosX() == explorer.maze.getLeftEntryPos()[0] && explorer.getPosY() == explorer.maze.getLeftEntryPos()[1]){
                atEnd = true;
            }



        }

        return mazePath.toFactorizedPath();

    }


}