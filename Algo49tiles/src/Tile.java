import java.util.ArrayList;

public class Tile {
	
	private ArrayList<Tile> neighbors;
	private int value;
	private int row;
	private int column;
	
	public Tile(int r, int c) {
		this.row = r;
		this.column = c;
		this.neighbors = new ArrayList<Tile>();
	}
	
	public void addNeighbor(Tile tile) {
		this.neighbors.add(tile);
	}
	
}