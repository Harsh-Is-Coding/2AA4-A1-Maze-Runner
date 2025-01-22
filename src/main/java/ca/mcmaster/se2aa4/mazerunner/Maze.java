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
            // First pass: read lines to determine grid size
            while ((line = reader.readLine()) != null) {
                cols = Math.max(cols, line.length());
                rows++;
            }

            // Now initialize the grid with the correct dimensions
            grid = new char[cols][rows];

            // Second pass: populate the grid
            reader.close(); // Close and reopen the file for the second pass
            reader = new BufferedReader(new FileReader(filePath));
            int currentRow = 0;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        logger.info("WALL ");
                        setGrid(idx, currentRow, '#');
                    } else if (line.charAt(idx) == ' ') {
                        logger.info("PASS ");
                        setGrid(idx, currentRow, ' ');
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
        for (int i = 0; i < grid.length; i++) {
            if (grid[0][i] == ' ') {
                leftEntryPos = new int[2];
                leftEntryPos[0] = i;
                leftEntryPos[1] = 0;
                break;
            }

        }
        for (int i = 0; i < grid.length; i++) {
            if (grid[grid[0].length - 1][i] == ' ') {
                rightEntryPos = new int[2];
                rightEntryPos[0] = i;
                rightEntryPos[1] = grid[0].length - 1;
                break;
            }
        }
    }

    public char[][] getGrid() {
        return grid;
    }

    public void setGrid(int x, int y, char c) {
        this.grid[x][y] = c;
    }

    public boolean isWall(int x, int y) {
        return grid[x][y] == '#';
    }

    public boolean isPass(int x, int y) {
        return grid[x][y] == ' ';
    }

    public int[] getLeftEntryPos() {
        return leftEntryPos;
    }

    public int[] getRightEntryPos() {
        return rightEntryPos;
    }
}
