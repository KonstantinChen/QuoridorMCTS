package quoridor;

import java.util.List;
import java.util.Random;

public class GameNode {
	GameNode parent;
	GameBoard board;
	char player;
	List<GameNode> children;
	double uct; //upper confidence bound for trees
	double ucth; //uct blended with heuristic
	
	// MCTS values
	int wins = 0;
	int visits = 0;
	
	public GameNode(GameBoard b,char p,GameNode pa){
		board = b;
		player = p;
		parent = pa;
	}
	
	public void addChild(GameNode child){
		children.add(child);
		child.setParent(this);
	}
	
	public void setParent(GameNode pa){
		parent = pa;
	}
	
	public double UCT(){
		return 0;
	}
	
	public double UCTH(){
		
	}
}
