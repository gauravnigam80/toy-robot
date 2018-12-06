/**
 * 
 */
package com.simulator;

import java.util.Scanner;

import com.simulator.toyrobot.Game;
import com.simulator.toyrobot.ToyRobot;
import com.simulator.toyrobot.exception.ToyRobotException;
import com.simulator.toyrobot.impl.SquareBoard;

/**
 * @author Gaurav Nigam
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        SquareBoard squareBoard = new SquareBoard(4, 4);
        ToyRobot robot = new ToyRobot();
        Game game = new Game(squareBoard, robot);

        System.out.println("Toy Robot Simulator");
        System.out.println("Enter a command, Valid commands are:");
        System.out.println("\'PLACE X,Y,NORTH|SOUTH|EAST|WEST\', MOVE, LEFT, RIGHT, REPORT or EXIT");

        boolean keepRunning = true;
        while (keepRunning) {
            String inputString = in.nextLine();
            if ("EXIT".equals(inputString)) {
                keepRunning = false;
            } else {
                try {
                		String outputVal = game.executeCommand(inputString);
                		if(outputVal != null) {
                			System.out.println(outputVal);
                		}
                } catch (ToyRobotException e) {
                	;
                }
            }
        }
        in.close();
    }
}