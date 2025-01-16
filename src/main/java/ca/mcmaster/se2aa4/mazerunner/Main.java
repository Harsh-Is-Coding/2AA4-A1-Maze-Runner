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

            if (cmd.hasOption("i")) {
                try {
                    logger.info("**** Reading the maze from file " + cmd.getOptionValue("i"));
                    BufferedReader reader = new BufferedReader(new FileReader(cmd.getOptionValue("i")));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        for (int idx = 0; idx < line.length(); idx++) {
                            if (line.charAt(idx) == '#') {
                                logger.info("WALL ");
                            } else if (line.charAt(idx) == ' ') {
                                logger.info("PASS ");
                            }
                        }
                        logger.info(System.lineSeparator());
                    }
                } catch (Exception e) {
                    logger.error("/!\\ An error has occured /!\\");
                }
            } else if (cmd.hasOption("p")) {
                logger.info("**** Computing path");
                logger.warn("PATH NOT COMPUTED");
            }
        }catch( ParseException exp){
           logger.error("/!\\ An error has occured with parser/!\\");
        }

        logger.info("** End of MazeRunner");
    }
}
