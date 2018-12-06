package com.simulator.toyrobot;

public class CommandPositionWrapper {

	Command command;
	Position position;
	public CommandPositionWrapper(Command command, Position position) {
		super();
		this.command = command;
		this.position = position;
	}
	public Command getCommand() {
		return command;
	}
	public Position getPosition() {
		return position;
	}
	
	
	
}
