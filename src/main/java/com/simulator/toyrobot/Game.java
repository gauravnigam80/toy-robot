/**
 * 
 */
package com.simulator.toyrobot;

import com.simulator.toyrobot.exception.ToyRobotException;
import com.simulator.toyrobot.utils.ToyRobotUtils;

/**
 * @author Gaurav Nigam
 *
 */
public class Game {

    Board squareBoard;
    ToyRobot robot;

    public Game(Board squareBoard, ToyRobot robot) {
        this.squareBoard = squareBoard;
        this.robot = robot;
    }

    /**
     * Places the robot on the squareBoard  in position X,Y and facing NORTH, SOUTH, EAST or WEST
     *
     * @param position Robot position
     * @return true if placed successfully
     * @throws ToyRobotException
     */
    public boolean placeToyRobot(Position position){

        if (squareBoard == null || position == null || position.getDirection() == null || !squareBoard.isValidPosition(position)) {
        	return false;
        }

        // if position is valid then assign values to fields
        robot.setPosition(position);
        return true;
    }

    private boolean validate(String inputString) {
        	String[] args = inputString.split(" ");

	        // validate command
	        Command command;
	        try {
	            command = Command.valueOf(args[0]);
	        } catch (IllegalArgumentException e) {
	            return false;
	        }
	        if (command == Command.PLACE && args.length < 2) {
	            return false;
	        }
	
	        
	        	if (command == Command.PLACE) {
	                String[] params = args[1].split(",");
	        	
	        			if(params.length == 3 && ToyRobotUtils.isInteger(params[0])&&ToyRobotUtils.isInteger((params[1]))){
	        				try {
	        					Direction.valueOf(params[2]);
	        		        } catch (IllegalArgumentException e) {
	        		            return false;
	        		        }
	        				return true;
	        			}
	        			return false;
	        	}
	        	return true;
    }
    
    public String executeCommand(String inputString) throws ToyRobotException {
    	if(this.validate(inputString)) {
    		CommandPositionWrapper commandPositionWrapper = this.parseInputString(inputString);
    		return this.executeCommand(commandPositionWrapper);
    	}
		return null;
    }

    private String executeCommand(CommandPositionWrapper commandPositionWrapper) throws ToyRobotException {
		return this.executeCommand(commandPositionWrapper.getCommand(), commandPositionWrapper.getPosition());
	}

    /**
     * read, evaluate and executes a string command.
     *
     * @param inputString command string
     * @return string value of the executed command
     * @throws com.simulator.toyrobot.exception.ToyRobotException
     *
     */
    private String executeCommand(Command command, Position position) throws ToyRobotException {

        String output = null;

        switch (command) {
            case PLACE:
                placeToyRobot(position);
                break;
            case MOVE:
                Position newPosition = robot.getPosition().getNextPosition();
                if (squareBoard.isValidPosition(newPosition)) {
                	robot.move(newPosition);
                }
                break;
            case LEFT:
                robot.rotateLeft();
                break;
            case RIGHT:
                robot.rotateRight();
                break;
            case REPORT:
                output = report();
                break;
        }

        return output;
    }
    
	/**
     * Returns the X,Y and Direction of the robot
     */
    public String report() {
        if (robot.getPosition() != null) {
        	return robot.getPosition().getX() + "," + robot.getPosition().getY() + "," + robot.getPosition().getDirection().toString();
        }
        return null;
    }

	private CommandPositionWrapper parseInputString(String inputString) {
		String[] params;
        int x = 0;
        int y = 0;
        Direction commandDirection = null;
        Position position = null ;
        String[] args = inputString.split(" ");
        Command command = Command.valueOf(args[0]);
    	if (command == Command.PLACE) {
    		params = args[1].split(",");
    			x = Integer.parseInt(params[0]);
    			y = Integer.parseInt(params[1]);
    			commandDirection = Direction.valueOf(params[2]);
    			position = new Position(x, y, commandDirection);
    		
    	}
		return new CommandPositionWrapper(command,position);
	}
}
