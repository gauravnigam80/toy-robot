package com.simulator.toyrobot.impl;

import com.simulator.toyrobot.Board;
import com.simulator.toyrobot.Position;

/**
 * @author Gaurav Nigam
 *
 */
public class SquareBoard implements Board {

    int rows;
    int columns;

    public SquareBoard(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

	public boolean isValidPosition(Position position) {
		return !(
                position.getX() > this.columns || position.getX() < 0 ||
                        position.getY() > this.rows || position.getY() < 0
        );
	}

}
