package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {
    private char[][] grid;
    private int[] leftEntryPos;
    private int[] rightEntryPos;
    private final Logger logger = LogManager.getLogger();

    public Maze(String filePath) {
        initializeGrid(filePath);
        findEntryPos();
    }

    private void initializeGrid(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            int rows = 0;
            int cols = 0;

            //figure out size of maze to create array
            while ((line = reader.readLine()) != null) {
                cols = Math.max(cols, line.length());
                rows++;
            }

            grid = new char[rows][cols];

            //restart reader for grid input
            reader.close();
            reader = new BufferedReader(new FileReader(filePath));
            int currentRow = 0;

            //add all maze properties to the grid
            while ((line = reader.readLine()) != null) {
                for (int x = 0; x < line.length(); x++) {
                    if (line.charAt(x) == '#') {
                        logger.info("WALL ");
                        setGrid(x, currentRow, '#');
                    } else if (line.charAt(x) == ' ') {
                        logger.info("PASS ");
                        setGrid(x, currentRow, ' ');
                    }
                }
                currentRow++;
                logger.info(System.lineSeparator());
            }
        } catch (Exception e) {
            logger.error("/!\\ An error has occurred /!\\");
            logger.error(e.getMessage());
        }
    }

    public void findEntryPos() {
        logger.info("finding entry pos");

        //loop through left col to find left entry pos
        for (int y = 0; y < grid.length; y++) {
            if (grid[y][0] == ' ') {
                leftEntryPos = new int[]{0, y};
                break;
            }
        }

        //loop through rightmost col to find right entry pos
        for (int y = 0; y < grid.length; y++) {
            if (grid[y][grid[0].length - 1] == ' ') {
                rightEntryPos = new int[]{grid[0].length - 1, y};
                break;
            }
        }
    }


    public void setGrid(int x, int y, char c) {
        this.grid[y][x] = c;
    }

    public boolean isWall(int x, int y) {
        return grid[y][x] == '#';
    }

    public boolean isPass(int x, int y) {
        return grid[y][x] == ' ';
    }

    public int[] getLeftEntryPos() {
        return leftEntryPos;
    }

    public int[] getRightEntryPos() {
        return rightEntryPos;
    }
}
