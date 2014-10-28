import java.util.ArrayList;
import interaction.Painter;

public class Tile {
	
	private ArrayList<Tile> neighbors;
	private int value;
	private int row;
	private int column;
	private boolean colored;
	
	public Tile(int r, int c) {
		this.row = r;
		this.column = c;
		this.colored = false;
		this.neighbors = new ArrayList<Tile>();
	}
	
	public boolean getColored() {
		return this.colored;
	}
	
	public void addNeighbor(Tile tile) {
		this.neighbors.add(tile);
		this.value = this.neighbors.size();
	}
	
	public void removeNeighbor(Tile tile) {
		this.neighbors.remove(tile);
		this.value = this.neighbors.size();
	}
	
	public Tile color(Painter painter) {
		this.colored = true;
		if (painter != null) painter.setPixel(this.column, 6 - this.row, true);
		for (int i = 0; i < this.neighbors.size(); i ++) {
			((Tile)this.neighbors.get(i)).removeNeighbor(this);
		}
		return this.searchMinimum();
	}
	
	public Tile searchMinimum() {
		if (this.neighbors.size() == 0) return null;
		Tile minTile = this.neighbors.get(0);
		for (Tile t0 : this.neighbors) {
			if (t0.value < minTile.value) minTile = t0;
		}
		return minTile;
	}
	
	public String toString() {
		return "(" + this.row + "," + this.column + ")" + "-" + this.value + "-" + this.colored;
	}
	
}