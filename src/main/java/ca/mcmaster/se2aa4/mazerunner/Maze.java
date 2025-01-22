package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class Maze {
    private char[][] grid;

    public Maze(String filePath) {
       final Logger logger = LogManager.getLogger();
       try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            int rows = 0;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        logger.info("WALL ");
                        grid[idx][rows] = '#';
                    } else if (line.charAt(idx) == ' ') {
                        logger.info("PASS ");
                        grid[idx][rows] = ' ';
                    }
                }
                rows++;
                logger.info(System.lineSeparator());
            }
        } catch (Exception e) {
           logger.error("/!\\ An error has occured /!\\");
           logger.error(e.getMessage());
        }

    }

    public char[][] getGrid() {
        return grid;
    }

    public boolean isWall(int x, int y) {
        return grid[x][y] == '#';
    }

    public boolean isPass(int x, int y) {
        return grid[x][y] == ' ';
    }
}
