package quoridor;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class GameBoardTest {
	
	GameBoard gb;
	
	@Before
	public void setUp(){
		gb = new GameBoard();
	}

	@Test
	public void getMovesNormal() {
		ArrayList<int[]> redMoves = gb.getMoves('r');
		assertEquals("Missing moves", redMoves.size(),3);
	}

}
