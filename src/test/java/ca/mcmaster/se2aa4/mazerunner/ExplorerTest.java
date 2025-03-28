package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExplorerTest {
    private Explorer explorer;
    private Explorer factorizedExplorer;
    private Maze maze;
    private Path unFactorizedPath;
    private Path factorizedPath;

    private MazeSolver solver;

    @BeforeEach
    public void setUp() {
        // Initialize the Explorer object before each test
        maze = new Maze("./src/test/resources/straight.maz.txt");
        unFactorizedPath = new Path("FFFFF", false);
        factorizedPath = new Path("5F", true);
        solver = new RightHandSolver();
        explorer = new Explorer(maze.getLeftEntryPos()[0], maze.getLeftEntryPos()[1], 'R', maze, unFactorizedPath, solver);
        factorizedExplorer = new Explorer(maze.getLeftEntryPos()[0], maze.getLeftEntryPos()[1], 'R', maze, factorizedPath, solver);
    }

    @Test
    public void testCheckRightSideIsWall() {
        System.out.println("rightWall");
        boolean rightWall = explorer.checkRightSideIsWall();

        assertTrue(rightWall);
    }

    @Test
    public void testCheckFrontSideIsWall() {
        boolean frontWall = explorer.checkFrontSideIsWall();
        assertFalse(frontWall);
    }

    @Test
    public void testMoveForwardSuccess() {
        boolean succcess = explorer.moveForward();
        assertTrue(succcess);
        assertEquals(maze.getLeftEntryPos()[0] + 1, explorer.getPosX());
        assertEquals(maze.getLeftEntryPos()[1], explorer.getPosY());

    }

    @Test
    public void testMoveForwardFailure() {
        explorer.turnLeft();
        boolean succcess = explorer.moveForward();
        assertFalse(succcess);
        assertEquals(maze.getLeftEntryPos()[0], explorer.getPosX());
        assertEquals(maze.getLeftEntryPos()[1], explorer.getPosY());
    }

    @Test
    public void solveTester() {
        String result = explorer.solveMaze();
        assertEquals("5F", result);
    }

    @Test
    public void validatePathTest() {
        boolean valid = explorer.validatePath();
        assertTrue(valid);
    }

    @Test
    public void validateFactorizedPathTest() {
        boolean valid = factorizedExplorer.validatePath();
        assertTrue(valid);
    }
}
