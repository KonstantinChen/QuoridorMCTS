package quoridor;

public class Position extends Object{
	
	int row;
	int col;
	
	public Position(int x, int y){
		col = x;
		row = y;
	}
	
	public int getRow(){
		return row;
	}
	
	public int getCol(){
		return col;
	}
	
	public void setRow(int r){
		row = r;
	}
	
	public void setCol(int c){
		col = c;
	}
	
	@Override
	public boolean equals(Object other){
		Position o = (Position)other;
		return row==o.row&&col==o.col;
	}
	
	public String toString(){
		return "("+col+","+row+")";
	}
}
