package quoridor;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GameBoardTest {

	GameBoard gb;

	@Before
	public void setUp() {
		gb = new GameBoard(9, 9);
	}

	@Test
	public void testMoveTo() {
		Position red = new Position(4, 7);
		Position blue = new Position(3, 3);
		gb.moveTo('r', red);
		gb.moveTo('b', blue);
		assertEquals("red wrong", gb.redPos(), red);
		assertEquals("blue wrong", gb.bluePos(), blue);
	}

	@Test
	public void getMovesStartBlue() {
		List<Position> moves = gb.getMoves('b');
		assertEquals("Missing moves", moves.size(), 3);
		Position left = new Position(3, 8);
		Position right = new Position(5, 8);
		Position up = new Position(4, 7);
		Position test = new Position(4, 7);
		assertEquals("Wrong up", up, moves.get(0));
		assertEquals("Wrong left", left, moves.get(1));
		assertEquals("Wrong right", right, moves.get(2));
	}

	@Test
	public void getMovesStartRed() {
		List<Position> moves = gb.getMoves('r');
		assertEquals("Missing moves", moves.size(), 3);
		Position left = new Position(3, 0);
		Position right = new Position(5, 0);
		Position down = new Position(4, 1);
		assertEquals("Wrong left", left, moves.get(0));
		assertEquals("Wrong down", down, moves.get(1));
		assertEquals("Wrong right", right, moves.get(2));
	}

	@Test
	public void getMovesNormalBlue() {
		gb.player[1].setRow(7);
		List<Position> moves = gb.getMoves('b');
		assertEquals("Missing moves", moves.size(), 4);
		Position left = new Position(3, 7);
		Position right = new Position(5, 7);
		Position up = new Position(4, 6);
		Position down = new Position(4, 8);
		assertEquals("Wrong up", up, moves.get(0));
		assertEquals("Wrong left", left, moves.get(1));
		assertEquals("Wrong down", down, moves.get(2));
		assertEquals("Wrong right", right, moves.get(3));
	}

	@Test
	public void getMovesNormalRed() {
		gb.player[0].setRow(1);
		List<Position> moves = gb.getMoves('r');
		assertEquals("Missing moves", moves.size(), 4);
		Position left = new Position(3, 1);
		Position right = new Position(5, 1);
		Position up = new Position(4, 0);
		Position down = new Position(4, 2);
		assertEquals("Wrong up", up, moves.get(0));
		assertEquals("Wrong left", left, moves.get(1));
		assertEquals("Wrong down", down, moves.get(2));
		assertEquals("Wrong right", right, moves.get(3));
	}

	@Test
	public void getMovesJumpBlue() {
		Position blue = new Position(4, 7);
		Position red = new Position(4, 6);
		gb.moveTo('b', blue);
		gb.moveTo('r', red);
		List<Position> moves = gb.getMoves('b');
		Position left = new Position(3, 7);
		Position right = new Position(5, 7);
		Position up = new Position(4, 5);
		Position down = new Position(4, 8);
		assertEquals("Wrong up", up, moves.get(3));
		assertEquals("Wrong left", left, moves.get(0));
		assertEquals("Wrong down", down, moves.get(1));
		assertEquals("Wrong right", right, moves.get(2));
	}

	@Test
	public void getMovesSide() {
		Position blue = new Position(4, 7);
		Position red = new Position(4, 6);
		gb.horizontal[4][5] = true;
		gb.moveTo('b', blue);
		gb.moveTo('r', red);
		List<Position> moves = gb.getMoves('b');
		Position left = new Position(3, 7);
		Position right = new Position(5, 7);
		Position upleft = new Position(3, 6);
		Position upright = new Position(5, 6);
		Position down = new Position(4, 8);
		assertEquals("Wrong upleft", upleft, moves.get(3));
		assertEquals("Wrong upright", upright, moves.get(4));
		assertEquals("Wrong left", left, moves.get(0));
		assertEquals("Wrong down", down, moves.get(1));
		assertEquals("Wrong right", right, moves.get(2));
	}
}
