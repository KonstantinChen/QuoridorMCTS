package quoridor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class HumanPlayer {
	private char color;
	private Scanner s;

	public HumanPlayer(char c) {
		color = c;
		s = new Scanner(System.in);
	}

	public HumanPlayer play(GameBoard board, HumanPlayer opponent) {
		// if the opponent has won, return opponent
		if (board.hasWon(opponent.getColor())) {
			return opponent;
		}

		// print the board

		// print moves
		List<Position> moves = board.getMoves(color);

		int nextMove = s.nextInt();

		// if the input is not a valid move, ask for another one
		while (!(nextMove >= 0 && nextMove < moves.size())) {
			System.out.println("Illegal input. Please choose again");
			nextMove = s.nextInt();
		}

		// ask for the orientation and the start point of the bar
		// if user chooses to put down a bar
		if (nextMove == moves.size() - 1) {
			String instr = s.nextLine();
			String[] instrs = instr.split(" ");
			while (instrs.length < 3) {
				System.out.println("Illegal input. Please type again");
				instr = s.nextLine();
				instrs = instr.split(" ");
			}
			char orientation = instr.charAt(0);
			int x = Integer.parseInt(instrs[1]);
			int y = Integer.parseInt(instrs[2]);
			while (!board.placeWall(orientation, x, y)) {
				System.out.println("Illegal input. Please type again");
				instr = s.nextLine();
				instrs = instr.split(" ");
				orientation = instr.charAt(0);
				x = Integer.parseInt(instrs[1]);
				y = Integer.parseInt(instrs[2]);
			}
		}

		else {
			board.moveTo(color, moves.get(nextMove));
		}

		return opponent.play(board, this);
	}

	public char getColor() {
		return color;
	}
}
