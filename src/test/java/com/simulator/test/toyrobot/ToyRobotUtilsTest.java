package com.simulator.test.toyrobot;

import org.junit.Assert;
import org.junit.Test;

import com.simulator.toyrobot.exception.ToyRobotException;
import com.simulator.toyrobot.utils.ToyRobotUtils;

/**
 * @author Gaurav Nigam
 *
 */
public class ToyRobotUtilsTest {
	
	@Test
    public void testIsInteger() throws ToyRobotException {
		
		Assert.assertFalse("Special Symbols are not considered as integer", ToyRobotUtils.isInteger("££££"));
		Assert.assertFalse("Decimal values are not acceptable", ToyRobotUtils.isInteger("1.11"));
		Assert.assertFalse("Decimal values are not acceptable", ToyRobotUtils.isInteger("helloWorld"));
		Assert.assertTrue("This is should be integer", ToyRobotUtils.isInteger("100"));
		
	}
	
	@Test
    public void testIsIntegerWithParameters() throws ToyRobotException {
		Assert.assertFalse("Empty String is not considered as integer", ToyRobotUtils.isInteger("",10));
		Assert.assertFalse("Special Symbols are not considered as integer", ToyRobotUtils.isInteger("££££",10));
		Assert.assertFalse("Minus Symbol is not considered as integer", ToyRobotUtils.isInteger("-",10));
		Assert.assertTrue("Number is out of range for Radix", ToyRobotUtils.isInteger("10",8));
		
	}

}
