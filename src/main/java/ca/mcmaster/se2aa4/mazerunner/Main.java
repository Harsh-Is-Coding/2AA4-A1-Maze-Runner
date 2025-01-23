package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Objects;

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

            if (cmd.hasOption("p")) {
                try {
                    Maze maze = new Maze(cmd.getOptionValue("i"));
                    Explorer explorerLeft = new Explorer(maze.getLeftEntryPos()[0], maze.getLeftEntryPos()[1], 'R', maze);
                    Path path = new Path(cmd.getOptionValue("p"), false);
                    logger.info("**** Reading the maze from file " + cmd.getOptionValue("i") + " with path " + cmd.getOptionValue("p"));

                    char [] mazePath = cmd.getOptionValue("p").toCharArray();
                    boolean valid = true;

                    for (char ch : mazePath) {
                        if(ch ==  'R'){
                            explorerLeft.turnRight();
                        }else if(ch == 'L'){
                            explorerLeft.turnLeft();
                        }else if(ch == 'F'){
                            valid  = explorerLeft.moveForward();
                        }else{
                            logger.warn("Unrecognized character " + ch);
                            break;
                        }

                        if(!valid){
                            logger.warn("invalid move ");
                            break;
                        }

                        logger.info("**** Move " + ch + " completed");
                    }

                } catch (Exception e) {
                    logger.error("/!\\ An error has occured /!\\");
                }
                logger.info("**** Computing path");
                logger.warn("PATH NOT COMPUTED");
            } else if (cmd.hasOption("i")) {
                try {
                    Maze maze = new Maze(cmd.getOptionValue("i"));
                    Explorer explorer = new Explorer(0, 0, 'R', maze);
                    Path path = new Path("", false);
                    logger.info("**** Reading the maze from file " + cmd.getOptionValue("i"));

                } catch (Exception e) {
                    logger.error("/!\\ An error has occured /!\\");
                }
            }
        }catch( ParseException exp){
           logger.error("/!\\ An error has occured with parser/!\\");
        }

        logger.info("** End of MazeRunner");
    }
}
