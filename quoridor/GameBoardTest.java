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
		gb.moveTo('r', new int[] { 4, 7 });
		gb.moveTo('b', new int[] { 3, 3 });
		assertArrayEquals("red wrong", gb.players[0], new int[] { 4, 7 });
		assertArrayEquals("blue wrong", gb.players[1], new int[] { 3, 3 });
	}

	@Test
	public void getMovesStartBlue() {
		List<int[]> moves = gb.getMoves('b');
		assertEquals("Missing moves", moves.size(), 3);
		int[] left = new int[] { 3, 8 };
		int[] right = new int[] { 5, 8 };
		int[] up = new int[] { 4, 7 };
		assertTrue(
				"Wrong moves",
				Arrays.equals(up, moves.get(0))
						&& Arrays.equals(left, moves.get(1))
						&& Arrays.equals(right, moves.get(2)));
	}

	@Test
	public void getMovesStartRed() {
		List<int[]> moves = gb.getMoves('r');
		assertEquals("Missing moves", moves.size(), 3);
		int[] left = new int[] { 3, 0 };
		int[] right = new int[] { 5, 0 };
		int[] down = new int[] { 4, 1 };
		assertTrue(
				"Wrong moves",
				Arrays.equals(down, moves.get(1))
						&& Arrays.equals(left, moves.get(0))
						&& Arrays.equals(right, moves.get(2)));
	}

	@Test
	public void getMovesNormalBlue() {
		gb.players[1][1] = 7;
		List<int[]> moves = gb.getMoves('b');
		assertEquals("Missing moves", moves.size(), 4);
		int[] left = new int[] { 3, 7 };
		int[] right = new int[] { 5, 7 };
		int[] up = new int[] { 4, 6 };
		int[] down = new int[] { 4, 8 };
		assertTrue("0", Arrays.equals(up, moves.get(0)));
		assertTrue("1", Arrays.equals(left, moves.get(1)));
		assertTrue("3", Arrays.equals(down, moves.get(2)));
		assertTrue("4", Arrays.equals(right, moves.get(3)));
	}

	@Test
	public void getMovesNormalRed() {
		gb.players[0][1] = 1;
		List<int[]> moves = gb.getMoves('r');
		assertEquals("Missing moves", moves.size(), 4);
		int[] left = new int[] { 3, 1 };
		int[] right = new int[] { 5, 1 };
		int[] up = new int[] { 4, 0 };
		int[] down = new int[] { 4, 2 };
		assertTrue("0", Arrays.equals(up, moves.get(0)));
		assertTrue("1", Arrays.equals(left, moves.get(1)));
		assertTrue("3", Arrays.equals(down, moves.get(2)));
		assertTrue("4", Arrays.equals(right, moves.get(3)));
	}

	@Test
	public void getMovesJumpBlue() {
		int[] blue = new int[] { 4, 7 };
		int[] red = new int[] { 4, 6 };
		gb.moveTo('b', blue);
		gb.moveTo('r', red);
		List<int[]> moves = gb.getMoves('b');
		int[] left = new int[] { 3, 7 };
		int[] right = new int[] { 5, 7 };
		int[] up = new int[] { 4, 5 };
		int[] down = new int[] { 4, 8 };
		assertTrue("0", Arrays.equals(up, moves.get(3)));
		assertTrue("1", Arrays.equals(left, moves.get(0)));
		assertTrue("3", Arrays.equals(down, moves.get(1)));
		assertTrue("4", Arrays.equals(right, moves.get(2)));
	}

	@Test
	public void getMovesSide() {
		int[] blue = new int[] { 4, 7 };
		int[] red = new int[] { 4, 6 };
		gb.horizontal[4][5] = true;
		gb.moveTo('b', blue);
		gb.moveTo('r', red);
		List<int[]> moves = gb.getMoves('b');
		int[] left = new int[] { 3, 7 };
		int[] right = new int[] { 5, 7 };
		int[] upleft = new int[] { 3, 6 };
		int[] upright = new int[] { 5, 6 };
		int[] down = new int[] { 4, 8 };
		assertTrue("0", Arrays.equals(upleft, moves.get(3)));
		assertTrue("5", Arrays.equals(upright, moves.get(4)));
		assertTrue("1", Arrays.equals(left, moves.get(0)));
		assertTrue("3", Arrays.equals(down, moves.get(1)));
		assertTrue("4", Arrays.equals(right, moves.get(2)));
	}
}
