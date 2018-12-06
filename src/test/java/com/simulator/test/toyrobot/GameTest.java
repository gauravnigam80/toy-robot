/**
 * 
 */
package com.simulator.test.toyrobot;

import org.junit.Assert;
import org.junit.Test;

import com.simulator.toyrobot.Direction;
import com.simulator.toyrobot.Game;
import com.simulator.toyrobot.Position;
import com.simulator.toyrobot.ToyRobot;
import com.simulator.toyrobot.exception.ToyRobotException;
import com.simulator.toyrobot.impl.SquareBoard;

/**
 * @author Gaurav Nigam
 *
 */
public class GameTest {

    final int BOARD_ROWS = 5;
    final int BOARD_COLUMNS = 5;
 
    @Test
    public void testPlacing() throws ToyRobotException {

        SquareBoard board = new SquareBoard(BOARD_COLUMNS, BOARD_ROWS);
        ToyRobot toyRobot = new ToyRobot();
        Game game = new Game(board, toyRobot);

        Assert.assertTrue(game.placeToyRobot(new Position(0, 1, Direction.NORTH)));
        Assert.assertTrue(game.placeToyRobot(new Position(2, 2, Direction.SOUTH)));
        Assert.assertFalse(game.placeToyRobot(new Position(6, 6, Direction.WEST)));
        Assert.assertFalse(game.placeToyRobot(new Position(-1, 5, Direction.EAST)));
    }

    @Test
    public void testReadCommand() throws ToyRobotException {

        SquareBoard board = new SquareBoard(BOARD_COLUMNS, BOARD_ROWS);
        ToyRobot toyRobot = new ToyRobot();
        Game game = new Game(board, toyRobot);

        game.executeCommand("PLACE 0,0,NORTH");
        Assert.assertEquals("0,0,NORTH", game.executeCommand("REPORT"));

        game.executeCommand("MOVE");
        game.executeCommand("RIGHT");
        game.executeCommand("MOVE");
        Assert.assertEquals("1,1,EAST", game.executeCommand("REPORT"));

        // if it goes out of the board it ignores the command
        for (int i = 0; i < 10; i++) {
        	game.executeCommand("MOVE");
        }
        Assert.assertEquals("5,1,EAST", game.executeCommand("REPORT"));

        //rotate on itself
        for (int i = 0; i < 4; i++) {
        	game.executeCommand("LEFT");
        }
        Assert.assertEquals("5,1,EAST", game.executeCommand("REPORT"));

        // invalid commands
        Assert.assertNull("Invalid command", game.executeCommand("PLACE12NORTH"));
        Assert.assertNull("Invalid command", game.executeCommand("LEFFT"));
        Assert.assertNull("Invalid command", game.executeCommand("RIGHTT"));
        Assert.assertNull("Invalid command", game.executeCommand("PLACE"));
        Assert.assertNull("Invalid command", game.executeCommand("PLACE 0,1,UP"));
        Assert.assertNull("Invalid command", game.executeCommand("PLACE 0,1"));
   
        
        toyRobot = new ToyRobot(null);
        game = new Game(board, toyRobot);
        Assert.assertNull("Invalid command", game.executeCommand("REPORT"));

    }
    
    @Test
    public void testMoveAroundYAxis() throws ToyRobotException {

        SquareBoard board = new SquareBoard(BOARD_COLUMNS, BOARD_ROWS);
        ToyRobot toyRobot = new ToyRobot();
        Game game = new Game(board, toyRobot);

        game.executeCommand("PLACE 0,1,NORTH");

        Assert.assertEquals("0,1,NORTH",game.report());
        game.executeCommand("MOVE");
        Assert.assertEquals("0,2,NORTH",game.report());
        game.executeCommand("MOVE");
        Assert.assertEquals("0,3,NORTH",game.report());
        game.executeCommand("MOVE");
        Assert.assertEquals("0,4,NORTH",game.report());
        game.executeCommand("MOVE");
        Assert.assertEquals("0,5,NORTH",game.report());
        game.executeCommand("MOVE");
        Assert.assertEquals("0,5,NORTH",game.report());
        game.executeCommand("LEFT");
        Assert.assertEquals("0,5,WEST",game.report());
        game.executeCommand("MOVE");
        Assert.assertEquals("0,5,WEST",game.report());
        
    }

    @Test
    public void testMoveAroundXAxis() throws ToyRobotException {

        SquareBoard board = new SquareBoard(BOARD_COLUMNS, BOARD_ROWS);
        ToyRobot toyRobot = new ToyRobot();
        Game game = new Game(board, toyRobot);

        game.executeCommand("PLACE 0,0,EAST");
        game.executeCommand("MOVE");
        Assert.assertEquals("1,0,EAST",game.report());
        game.executeCommand("MOVE");
        Assert.assertEquals("2,0,EAST",game.report());
        game.executeCommand("MOVE");
        Assert.assertEquals("3,0,EAST",game.report());
        game.executeCommand("MOVE");
        Assert.assertEquals("4,0,EAST",game.report());
        game.executeCommand("MOVE");
        Assert.assertEquals("5,0,EAST",game.report());
        game.executeCommand("MOVE");
        Assert.assertEquals("5,0,EAST",game.report());
        game.executeCommand("RIGHT");
        Assert.assertEquals("5,0,SOUTH",game.report());
        game.executeCommand("MOVE");
        Assert.assertEquals("5,0,SOUTH",game.report());
        
    }
}
