package quoridor;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a Quoridor board
 * 
 * @author Andi Chen
 * @date Jul 2016
 */

public class GameBoard {
	protected int row, col;
	protected boolean[][] vertical;
	protected boolean[][] horizontal;
	protected int[][] players = new int[2][2];
	protected int rBar, bBar;

	public GameBoard() {
		// TODO: doesn't work for unknown reason
		new GameBoard(9, 9);
	}

	public GameBoard(int r, int c) {
		row = r;
		col = c;
		vertical = new boolean[col - 1][row];
		horizontal = new boolean[col][row - 1];
		// red[0] = col / 2;
		// red[1] = 0;
		// blue[0] = col / 2;
		// blue[1] = row - 1;
		players[0][0] = col / 2;
		players[0][1] = 0;
		players[1][0] = col / 2;
		players[1][1] = row - 1;
		rBar = bBar = 10;
	}

	public List<int[]> getMoves(char o) {
		List<int[]> moves = new ArrayList<int[]>();
		// int[][] chessCons
		int playerNum = 0;
		if (o == 'b') {
			playerNum = 1;
		}
		// TODO:
		// DONE
		// directly upward
		if (!hasWallAbove(playerNum) && !hasOppAbove(o)) {
			int[] move = new int[2];
			move[0] = players[playerNum][0];
			move[1] = players[playerNum][1] - 1;
			moves.add(move);
		}
		// DONE
		// directly leftward
		if (!hasWallLeft(playerNum) && !hasOppLeft(o)) {
			int[] move = new int[2];
			move[0] = players[playerNum][0] - 1;
			move[1] = players[playerNum][1];
			moves.add(move);
		}
		// DONE
		// directly downward
		if (!hasWallBelow(playerNum) && !hasOppBelow(o)) {
			int[] move = new int[2];
			move[0] = players[playerNum][0];
			move[1] = players[playerNum][1] + 1;
			moves.add(move);
		}
		// DONE
		// directly rightward
		if (!hasWallRight(playerNum) && !hasOppRight(o)) {
			int[] move = new int[2];
			move[0] = players[playerNum][0] + 1;
			move[1] = players[playerNum][1];
			moves.add(move);
		}
		// DONE
		// jump over upward
		if (players[playerNum][1] >= 2 && hasOppAbove(o)
				&& !hasWallAbove(playerNum)
				&& !hasWallAbove((playerNum + 1) % 2)) {
			int[] move = new int[2];
			move[0] = players[playerNum][0];
			move[1] = players[playerNum][1] - 2;
			moves.add(move);
		}
		// jump over leftward
		if (players[playerNum][0] >= 2 && hasOppLeft(o)
				&& !hasWallLeft(playerNum) && !hasWallLeft((playerNum + 1) % 2)) {
			int[] move = new int[2];
			move[0] = players[playerNum][0] - 2;
			move[1] = players[playerNum][1];
			moves.add(move);
		}
		// jump over downward
		if (players[playerNum][1] < row - 2 && hasOppBelow(o)
				&& !hasWallBelow(playerNum)
				&& !hasWallBelow((playerNum + 1) % 2)) {
			int[] move = new int[2];
			move[0] = players[playerNum][0];
			move[1] = players[playerNum][1] + 2;
			moves.add(move);
		}
		// jump over rightward
		if (players[playerNum][0] < col - 2 && hasOppRight(o)
				&& !hasWallRight(playerNum)
				&& !hasWallRight((playerNum + 1) % 2)) {
			int[] move = new int[2];
			move[0] = players[playerNum][0] + 2;
			move[1] = players[playerNum][1];
			moves.add(move);
		}
		// left&right upward
		if (hasOppAbove(o) && hasWallAbove((playerNum + 1) % 2)
				&& !hasWallAbove(playerNum)
				&& !hasWallLeft((playerNum + 1) % 2) && !hasWallLeft(playerNum)) {
			int[] move = new int[2];
			move[0] = players[playerNum][0] - 1;
			move[1] = players[playerNum][1] - 1;
			moves.add(move);
		}
		if (hasOppAbove(o) && hasWallAbove((playerNum + 1) % 2)
				&& !hasWallAbove(playerNum)
				&& !hasWallRight((playerNum + 1) % 2)
				&& !hasWallRight(playerNum)) {
			int[] move = new int[2];
			move[0] = players[playerNum][0] + 1;
			move[1] = players[playerNum][1] - 1;
			moves.add(move);
		}
		// up&down on the left
		if (hasOppLeft(o) && hasWallLeft((playerNum + 1) % 2)
				&& !hasWallLeft(playerNum)
				&& !hasWallAbove((playerNum + 1) % 2)
				&& !hasWallAbove(playerNum)) {
			int[] move = new int[2];
			move[0] = players[playerNum][0] - 1;
			move[1] = players[playerNum][1] - 1;
			moves.add(move);
		}
		if (hasOppLeft(o) && hasWallLeft((playerNum + 1) % 2)
				&& !hasWallLeft(playerNum)
				&& !hasWallBelow((playerNum + 1) % 2)
				&& !hasWallBelow(playerNum)) {
			int[] move = new int[2];
			move[0] = players[playerNum][0] - 1;
			move[1] = players[playerNum][1] + 1;
			moves.add(move);
		}
		// left&right downward
		if (hasOppBelow(o) && hasWallBelow((playerNum + 1) % 2)
				&& !hasWallBelow(playerNum)
				&& !hasWallLeft((playerNum + 1) % 2) && !hasWallLeft(playerNum)) {
			int[] move = new int[2];
			move[0] = players[playerNum][0] - 1;
			move[1] = players[playerNum][1] + 1;
			moves.add(move);
		}
		if (hasOppBelow(o) && hasWallBelow((playerNum + 1) % 2)
				&& !hasWallBelow(playerNum)
				&& !hasWallRight((playerNum + 1) % 2)
				&& !hasWallRight(playerNum)) {
			int[] move = new int[2];
			move[0] = players[playerNum][0] + 1;
			move[1] = players[playerNum][1] + 1;
			moves.add(move);
		}
		// up&down on the right
		if (hasOppRight(o) && hasWallRight((playerNum + 1) % 2)
				&& !hasWallRight(playerNum)
				&& !hasWallAbove((playerNum + 1) % 2)
				&& !hasWallAbove(playerNum)) {
			int[] move = new int[2];
			move[0] = players[playerNum][0] + 1;
			move[1] = players[playerNum][1] - 1;
			moves.add(move);
		}
		if (hasOppRight(o) && hasWallRight((playerNum + 1) % 2)
				&& !hasWallLeft(playerNum)
				&& !hasWallBelow((playerNum + 1) % 2)
				&& !hasWallBelow(playerNum)) {
			int[] move = new int[2];
			move[0] = players[playerNum][0] + 1;
			move[1] = players[playerNum][1] + 1;
			moves.add(move);
		}
		return moves;
	}

	public boolean win(char playercolor) {
		switch (playercolor) {
		case 'r':
			return players[0][0] == row - 1;
		case 'b':
			return players[1][0] == 0;
		default:
			throw new IllegalArgumentException();
		}
	}

	protected void moveTo(char color, int[] param) {
		if (color == 'r') {
			players[0][0] = param[0];
			players[0][1] = param[1];
		}
		if (color == 'b') {
			players[1][0] = param[0];
			players[1][1] = param[1];
		}
	}

	/**
	 * Do a BFS with the wall placed to see if wall is valid
	 */
	// TODO:
	// protected boolean stillWinnable(char o, int x, int y) {
	// if (o == 'v') {
	// vertical[x][y] = true;
	// vertical[x][y + 1] = true;
	// } else {
	// horizontal[x][y] = true;
	// horizontal[x + 1][y] = true;
	// }
	//
	// if (o == 'v') {
	// vertical[x][y] = false;
	// vertical[x][y + 1] = false;
	// } else {
	// horizontal[x][y] = false;
	// horizontal[x + 1][y] = false;
	// }
	// }

	protected boolean placeWall(char o, int x, int y) {
		// vertical bar
		if (o == 'v') {
			if (x < 0 || y < 0 || x > col - 2 || y > row - 2)
				return false;
			if (vertical[x][y] || vertical[x][y + 1])
				return false;
			if (horizontal[x][y] && horizontal[x + 1][y])
				return false;
			// TODO:
			// if (!stillWinnable(o, x, y))
			// return false;
			vertical[x][y] = true;
			vertical[x][y + 1] = true;
			return true;
		}
		// horizontal bar
		if (o == 'h') {
			if (x < 0 || y < 0 || x > col - 2 || y > row - 2)
				return false;
			if (vertical[x][y] || vertical[x + 1][y])
				return false;
			horizontal[x][y] = true;
			horizontal[x + 1][y] = true;
			return true;
		}
		return false;
	}

	// ///////////////////////////////////////////////////////////////
	// / ///
	// / Helper functions to see walls/opponent around ///
	// / ///
	// ///////////////////////////////////////////////////////////////

	private boolean hasOppAbove(char c) {
		switch (c) {
		case 'r':
			return players[1][0] == players[0][0] && players[1][1] == players[0][1] - 1;
		case 'b':
			return players[1][0] == players[0][0] && players[1][1] - 1 == players[0][1];
		}
		return false;
	}

	private boolean hasOppBelow(char c) {
		switch (c) {
		case 'r':
			return players[1][0] == players[0][0] && players[1][1] == players[0][1] + 1;
		case 'b':
			return players[1][0] == players[0][0] && players[1][1] + 1 == players[0][1];
		}
		return false;
	}

	private boolean hasOppLeft(char c) {
		switch (c) {
		case 'r':
			return players[1][1] == players[0][1] && players[1][0] == players[0][0] - 1;
		case 'b':
			return players[1][1] == players[0][1] && players[1][0] - 1 == players[0][0];
		}
		return false;
	}

	private boolean hasOppRight(char c) {
		switch (c) {
		case 'r':
			return players[1][1] == players[0][1] && players[1][0] == players[0][0] + 1;
		case 'b':
			return players[1][1] == players[0][1] && players[1][0] + 1 == players[0][0];
		}
		return false;
	}

	private boolean hasWallAbove(int playerNum) {
		return players[playerNum][1] == 0
				|| horizontal[players[playerNum][0]][players[playerNum][1] - 1];
	}

	private boolean hasWallBelow(int playerNum) {
		return players[playerNum][1] == row - 1
				|| horizontal[players[playerNum][0]][players[playerNum][1]];
	}

	private boolean hasWallLeft(int playerNum) {
		return players[playerNum][0] == 0
				|| vertical[players[playerNum][0] - 1][players[playerNum][1]];
	}

	private boolean hasWallRight(int playerNum) {
		return players[playerNum][0] == col - 1
				|| vertical[players[playerNum][0]][players[playerNum][1]];
	}
}
