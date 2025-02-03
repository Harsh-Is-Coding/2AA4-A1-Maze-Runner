package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class Main {

    private static final Logger logger = LogManager.getLogger();
    private static final Options options = new Options();
    private static final CommandLineParser parser = new DefaultParser();




    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        options.addOption("i", true, "input file path");
        options.addOption("p", true, "compute path");
       try {
           CommandLine cmd =  parser.parse(options, args);


           //check for path input
            if (cmd.hasOption("p") && cmd.hasOption("i")) {
                try {

                    Maze maze = new Maze(cmd.getOptionValue("i"));

                    //check if path is factorized
                    boolean factorized = false;
                    for (char c : cmd.getOptionValue("p").toCharArray()) {
                        if (Character.isDigit(c)) {
                            factorized = true;
                        }
                    }


                    //init objects
                    Path path = new Path(cmd.getOptionValue("p"), factorized);
                    MazeSolver solver = new RightHandSolver();
                    Explorer explorerLeft = new Explorer(maze.getLeftEntryPos()[0], maze.getLeftEntryPos()[1], 'R', maze,  path, solver);
                    Explorer explorerRight = new Explorer(maze.getRightEntryPos()[0], maze.getRightEntryPos()[1], 'L', maze,  path, solver);
                    logger.info("**** Reading the maze from file " + cmd.getOptionValue("i") + " with path " + cmd.getOptionValue("p"));

                    //validate path from both left and right side
                    logger.info("**** Computing path");
                    boolean rightPath = explorerRight.validatePath();
                    boolean leftPath = explorerLeft.validatePath();
                    if (rightPath || leftPath) {
                        System.out.println("correct path");
                    }else{
                        System.out.println("incorrect path");
                    }



                } catch (Exception e) {
                    logger.error("/!\\ An error has occured /!\\");
                    logger.info(e.getMessage());
                }


            } else if (cmd.hasOption("i")) {
                try {
                    //init objects
                    Maze maze = new Maze(cmd.getOptionValue("i"));
                    Path path = new Path("", false);
                    MazeSolver solver = new RightHandSolver();
                    Explorer explorer = new Explorer(maze.getLeftEntryPos()[0], maze.getLeftEntryPos()[1], 'R', maze,path, solver);
                    logger.info("**** Reading the maze from file " + cmd.getOptionValue("i"));

                    //solve maze and output path
                    System.out.println(explorer.solveMaze());

                } catch (Exception e) {
                    logger.error("/!\\ An error has occured /!\\");
                    logger.error(e.getMessage());
                }
            }else{
                System.out.println("**** No options specified or Bad options");
            }
        }catch( ParseException exp){
           System.out.println("**** No options specified or Bad options");
           logger.error("/!\\ An error has occured with parser/!\\");
           logger.error(exp.getMessage());
        }

        logger.info("** End of MazeRunner");
    }
}
