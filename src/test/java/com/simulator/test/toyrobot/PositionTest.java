package com.simulator.test.toyrobot;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.simulator.toyrobot.Direction;
import com.simulator.toyrobot.Position;
import com.simulator.toyrobot.exception.ToyRobotException;

/**
 * @author Gaurav Nigam
 *
 */
public class PositionTest {

    @Rule
    public org.junit.rules.ExpectedException thrown = ExpectedException.none();

    @Test
    public void testGetNextPosition() throws Exception {
        Position position = new Position(0, 0, Direction.EAST);

        Position newPosition = position.getNextPosition();
        Assert.assertEquals(newPosition.getX(), 1);
        Assert.assertEquals(newPosition.getY(), 0);
        Assert.assertEquals(newPosition.getDirection(), Direction.EAST);

        newPosition = newPosition.getNextPosition();
        Assert.assertNotEquals(newPosition.getX(), 1);
        Assert.assertEquals(newPosition.getY(), 0);
        Assert.assertEquals(newPosition.getDirection(), Direction.EAST);

        newPosition.setDirection(Direction.NORTH);
        newPosition = newPosition.getNextPosition();
        Assert.assertNotEquals(newPosition.getX(), 1);
        Assert.assertEquals(newPosition.getY(), 1);
        Assert.assertNotEquals(newPosition.getDirection(), Direction.EAST);


    }
    
    @Test
    public void testPositionExceptions() throws ToyRobotException {

        Position position2 = new Position(0, 0, null);
        thrown.expect(ToyRobotException.class);
        position2.getNextPosition();
        
    }

}