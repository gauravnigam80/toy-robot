/**
 * 
 */
package com.simulator.test.toyrobot;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.simulator.toyrobot.Direction;
import com.simulator.toyrobot.Position;
import com.simulator.toyrobot.ToyRobot;
import com.simulator.toyrobot.exception.ToyRobotException;

/**
 * @author Gaurav Nigam
 *
 */

public class ToyRobotTest {

    @Rule
    public org.junit.rules.ExpectedException thrown = ExpectedException.none();

    @Test
    public void testMovement() throws ToyRobotException {

        ToyRobot robot = new ToyRobot(new Position(0, 0, Direction.NORTH));

        Assert.assertTrue(robot.move());
        Assert.assertEquals(0, robot.getPosition().getX());
        Assert.assertEquals(1, robot.getPosition().getY());
        Assert.assertEquals(Direction.NORTH, robot.getPosition().getDirection());


        robot.setPosition(new Position(1, 2, Direction.EAST));
        robot.move();
        robot.move();
        robot.rotateLeft();
        robot.move();

        Assert.assertEquals(3, robot.getPosition().getX());
        Assert.assertEquals(3, robot.getPosition().getY());
        Assert.assertEquals(Direction.NORTH, robot.getPosition().getDirection());

        robot.setPosition(new Position(0, 0, Direction.NORTH));
        robot.rotateRight();
        Assert.assertEquals(Direction.EAST, robot.getPosition().getDirection());
        robot.rotateRight();
        Assert.assertEquals(Direction.SOUTH, robot.getPosition().getDirection());
        robot.rotateRight();
        Assert.assertEquals(Direction.WEST, robot.getPosition().getDirection());
        robot.rotateRight();
        Assert.assertEquals(Direction.NORTH, robot.getPosition().getDirection());
        robot.rotateRight();
        Assert.assertEquals(Direction.EAST, robot.getPosition().getDirection());

        
        Assert.assertTrue("Expected false but returned true", false == robot.setPosition(null));
        Assert.assertTrue("Expected false but returned true", false == robot.move(null));
        
        robot.setPosition(new Position(1, 2, null));
        Assert.assertTrue("Expected false but returned true", false == robot.rotateLeft());
        Assert.assertTrue("Expected false but returned true", false == robot.rotateRight());
        
        

    }

}