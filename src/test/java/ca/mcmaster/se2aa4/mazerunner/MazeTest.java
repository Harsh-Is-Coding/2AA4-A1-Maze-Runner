package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {
    private Maze maze;

    @BeforeEach
    public void setUp() {
        // Initialize the Maze object before each test
        maze = new Maze("./src/test/resources/tiny.maz.txt");
    }

    @Test
    public void testGetLeftEntryPos() {
        int[] expected = {0, 5};
        int[] actual = maze.getLeftEntryPos();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testGetRightEntryPos() {
        int[] expected = {6, 1};
        int[] actual = maze.getRightEntryPos();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testisWall() {
        boolean isWall = maze.isWall(0, 0);
        assertTrue(isWall);
    }

    @Test
    public void testisPass() {
        boolean isPass = maze.isPass(0, 1);
        assertFalse(isPass);
    }


}
