package quoridor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
	protected Position red, blue;
	protected Position[] player = new Position[2];
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
		red = new Position(col / 2, 0);
		blue = new Position(col / 2, row - 1);
		player[0] = red;
		player[1] = blue;
		rBar = bBar = 10;
	}

	public List<Position> getMoves(char o) {
		List<Position> moves = new ArrayList<Position>();
		int playerNum = 0;
		if (o == 'b') {
			playerNum = 1;
		}
		Position cur = player[playerNum];
		// TODO:
		// DONE
		// directly upward
		if (!hasWallAbove(playerNum) && !hasOppAbove(o)) {
			Position move = new Position(cur.getCol(), cur.getRow() - 1);
			moves.add(move);
		}
		// DONE
		// directly leftward
		if (!hasWallLeft(playerNum) && !hasOppLeft(o)) {
			Position move = new Position(cur.getCol() - 1, cur.getRow());
			moves.add(move);
		}
		// DONE
		// directly downward
		if (!hasWallBelow(playerNum) && !hasOppBelow(o)) {
			Position move = new Position(cur.getCol(), cur.getRow() + 1);
			moves.add(move);
		}
		// DONE
		// directly rightward
		if (!hasWallRight(playerNum) && !hasOppRight(o)) {
			Position move = new Position(cur.getCol() + 1, cur.getRow());
			moves.add(move);
		}
		// DONE
		// jump over upward
		if (cur.getRow() >= 2 && hasOppAbove(o) && !hasWallAbove(playerNum)
				&& !hasWallAbove((playerNum + 1) % 2)) {
			Position move = new Position(cur.getCol(), cur.getRow() - 2);
			moves.add(move);
		}
		// jump over leftward
		if (cur.getCol() >= 2 && hasOppLeft(o) && !hasWallLeft(playerNum)
				&& !hasWallLeft((playerNum + 1) % 2)) {
			Position move = new Position(cur.getCol() - 2, cur.getRow());
			moves.add(move);
		}
		// jump over downward
		if (cur.getRow() < row - 2 && hasOppBelow(o)
				&& !hasWallBelow(playerNum)
				&& !hasWallBelow((playerNum + 1) % 2)) {
			Position move = new Position(cur.getCol(), cur.getRow() + 2);
			moves.add(move);
		}
		// jump over rightward
		if (cur.getCol() < col - 2 && hasOppRight(o)
				&& !hasWallRight(playerNum)
				&& !hasWallRight((playerNum + 1) % 2)) {
			Position move = new Position(cur.getCol() + 2, cur.getRow());
			moves.add(move);
		}
		// left&right upward
		if (hasOppAbove(o) && hasWallAbove((playerNum + 1) % 2)
				&& !hasWallAbove(playerNum)
				&& !hasWallLeft((playerNum + 1) % 2) && !hasWallLeft(playerNum)) {
			Position move = new Position(cur.getCol() - 1, cur.getRow() - 1);
			moves.add(move);
		}
		if (hasOppAbove(o) && hasWallAbove((playerNum + 1) % 2)
				&& !hasWallAbove(playerNum)
				&& !hasWallRight((playerNum + 1) % 2)
				&& !hasWallRight(playerNum)) {
			Position move = new Position(cur.getCol() + 1, cur.getRow() - 1);
			moves.add(move);
		}
		// up&down on the left
		if (hasOppLeft(o) && hasWallLeft((playerNum + 1) % 2)
				&& !hasWallLeft(playerNum)
				&& !hasWallAbove((playerNum + 1) % 2)
				&& !hasWallAbove(playerNum)) {
			Position move = new Position(cur.getCol() - 1, cur.getRow() - 1);
			moves.add(move);
		}
		if (hasOppLeft(o) && hasWallLeft((playerNum + 1) % 2)
				&& !hasWallLeft(playerNum)
				&& !hasWallBelow((playerNum + 1) % 2)
				&& !hasWallBelow(playerNum)) {
			Position move = new Position(cur.getCol() - 1, cur.getRow() + 1);
			moves.add(move);
		}
		// left&right downward
		if (hasOppBelow(o) && hasWallBelow((playerNum + 1) % 2)
				&& !hasWallBelow(playerNum)
				&& !hasWallLeft((playerNum + 1) % 2) && !hasWallLeft(playerNum)) {
			Position move = new Position(cur.getCol() - 1, cur.getRow() + 1);
			moves.add(move);
		}
		if (hasOppBelow(o) && hasWallBelow((playerNum + 1) % 2)
				&& !hasWallBelow(playerNum)
				&& !hasWallRight((playerNum + 1) % 2)
				&& !hasWallRight(playerNum)) {
			Position move = new Position(cur.getCol() + 1, cur.getRow() + 1);
			moves.add(move);
		}
		// up&down on the right
		if (hasOppRight(o) && hasWallRight((playerNum + 1) % 2)
				&& !hasWallRight(playerNum)
				&& !hasWallAbove((playerNum + 1) % 2)
				&& !hasWallAbove(playerNum)) {
			Position move = new Position(cur.getCol() + 1, cur.getRow() - 1);
			moves.add(move);
		}
		if (hasOppRight(o) && hasWallRight((playerNum + 1) % 2)
				&& !hasWallLeft(playerNum)
				&& !hasWallBelow((playerNum + 1) % 2)
				&& !hasWallBelow(playerNum)) {
			Position move = new Position(cur.getCol() + 1, cur.getRow() + 1);
			moves.add(move);
		}
		return moves;
	}

	public void printMove(char o) {
		List<Position> moves = getMoves(o);
		System.out.print("Moves: { ");
		for (int i = 0; i < moves.size(); i++) {
			System.out.print(moves.get(i) + " ");
		}
		System.out.println("}");
	}

	public boolean hasWon(char playercolor) {
		switch (playercolor) {
		case 'r':
			return player[0].getRow() == row - 1;
		case 'b':
			return player[1].getRow() == 0;
		default:
			throw new IllegalArgumentException();
		}
	}

	protected void moveTo(char color, Position pos) {
		int c = pos.getCol();
		int r = pos.getRow();
		if (color == 'r') {
			player[0].setCol(c);
			player[0].setRow(r);
		}
		if (color == 'b') {
			player[1].setCol(c);
			player[1].setRow(r);
		}
	}

	protected void moveTo(char color, int c, int r) {
		if (color == 'r') {
			player[0].setCol(c);
			player[0].setRow(r);
		}
		if (color == 'b') {
			player[1].setCol(c);
			player[1].setRow(r);
		}
	}

	public Position redPos() {
		return player[0];
	}

	public Position bluePos() {
		return player[1];
	}

	/**
	 * Do a BFS to see if player c can still win, used to test wall validity
	 */
	// TODO:
	public boolean winnable(char c) {
		int num = 0;
		if (c == 'b') {
			num = 1;
		}
		// record old pawn position
		Position original = new Position(player[num].getCol(),
				player[num].getRow());

		Position start = player[num];
		boolean[][] visited = new boolean[9][9];
		Queue<Position> queue = new LinkedList<>();

		queue.offer(start);
		visited[start.col][start.row]=true;

		while (!queue.isEmpty()) {
			Position cur = queue.poll();
			moveTo(c, cur);
			if (hasWon(c)) {
				moveTo(c,original);
				return true;
			}
			List<Position> neighbors = getMoves(c);
			for (Position pos : neighbors) {
				if (!visited[pos.col][pos.row]) {
					visited[pos.col][pos.row]=true;
					queue.offer(pos);
				}
			}
		}
		moveTo(c,original);
		return false;
	}

	protected boolean placeWall(char o, int x, int y) {
		// vertical bar
		if (o == 'v') {
			if (x < 0 || y < 0 || x > col - 2 || y > row - 2)
				return false;
			if (vertical[x][y] || vertical[x][y + 1])
				return false;
			if (horizontal[x][y] && horizontal[x + 1][y])
				return false;
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

	private boolean hasOppAbove(char color) {
		int playerNum = 0;
		if (color == 'b') {
			playerNum = 1;
		}
		int myC = player[playerNum].getCol();
		int myR = player[playerNum].getRow();
		int opC = player[(1 + playerNum) % 2].getCol();
		int opR = player[(1 + playerNum) % 2].getRow();
		return myC == opC && myR == opR + 1;
	}

	private boolean hasOppBelow(char color) {
		int playerNum = 0;
		if (color == 'b') {
			playerNum = 1;
		}
		int myC = player[playerNum].getCol();
		int myR = player[playerNum].getRow();
		int opC = player[(1 + playerNum) % 2].getCol();
		int opR = player[(1 + playerNum) % 2].getRow();
		return myC == opC && myR == opR - 1;
	}

	private boolean hasOppLeft(char color) {
		int playerNum = 0;
		if (color == 'b') {
			playerNum = 1;
		}
		int myC = player[playerNum].getCol();
		int myR = player[playerNum].getRow();
		int opC = player[(1 + playerNum) % 2].getCol();
		int opR = player[(1 + playerNum) % 2].getRow();
		return myC - 1 == opC && myR == opR;
	}

	private boolean hasOppRight(char color) {
		int playerNum = 0;
		if (color == 'b') {
			playerNum = 1;
		}
		int myC = player[playerNum].getCol();
		int myR = player[playerNum].getRow();
		int opC = player[(1 + playerNum) % 2].getCol();
		int opR = player[(1 + playerNum) % 2].getRow();
		return myC == opC - 1 && myR == opR;
	}

	private boolean hasWallAbove(int playerNum) {
		int c = player[playerNum].getCol();
		int r = player[playerNum].getRow();
		return r == 0 || horizontal[c][r - 1];
	}

	private boolean hasWallBelow(int playerNum) {
		int c = player[playerNum].getCol();
		int r = player[playerNum].getRow();
		return r == row - 1 || horizontal[c][r];
	}

	private boolean hasWallLeft(int playerNum) {
		int c = player[playerNum].getCol();
		int r = player[playerNum].getRow();
		return c == 0 || vertical[c - 1][r];
	}

	private boolean hasWallRight(int playerNum) {
		int c = player[playerNum].getCol();
		int r = player[playerNum].getRow();
		return c == col - 1 || vertical[c][r];
	}
}
