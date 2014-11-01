import java.util.ArrayList;

import interaction.Painter;

public class Tile {

	private ArrayList<Tile> neighbors;
	private int value;
	private int row;
	private int column;
	private boolean colored;
	private Tile preTile;
	private int distance;

	public Tile() {
		this.value = 999;
	}

	public Tile(int r, int c) {
		this.row = r;
		this.column = c;
		this.colored = false;
		this.neighbors = new ArrayList<Tile>();
	}

	public boolean getColored() {
		return this.colored;
	}

	public int getValue() {
		return this.value;
	}

	public void addNeighbor(Tile tile) {
		if (this.neighbors.indexOf(tile) == -1) {
			this.neighbors.add(tile);
		}
		this.value = this.neighbors.size();

		if (tile.neighbors.indexOf(this) == -1) {
			tile.neighbors.add(this);
		}
		tile.value = tile.neighbors.size();
	}

	private void removeNeighbor(Tile tile) {
		this.neighbors.remove(tile);
		this.value = this.neighbors.size();
		tile.neighbors.remove(this);
		tile.value = tile.neighbors.size();
	}

	public Tile color(Painter painter) {
		this.colored = true;
		if (painter != null) {
			painter.setPixel(this.column, 6 - this.row, true);
		}
		Tile next = this.searchMinimum();
		for (int i = 0; this.neighbors.size() != 0;) {
			this.neighbors.get(i).removeNeighbor(this);
		}
		return next;
	}

	public Tile searchMinimum() {
		if (this.neighbors.size() == 0) {
			return null;
		}
		Tile minTile = this.neighbors.get(0);
		for (Tile t0 : this.neighbors) {
			if (t0.value < minTile.value) {
				minTile = t0;
			}
		}
		return minTile;
	}

	private void initSingleSource(Tile[][] tiles) {
		for (int i = 0; i < tiles.length; i++) { // row
			for (int j = 0; j < tiles[0].length; j++) { // column
				tiles[i][j].distance = 9999;
				tiles[i][j].preTile = null;
			}
		}
		this.distance = 0;
	}

	public void relax(int round) {
		if (this.neighbors.size() == 0 || round > 6) {
			return;
		}
		for (Tile t0 : this.neighbors) {
			if (t0.distance > this.distance + 1) {
				t0.distance = this.distance + 1;
				t0.preTile = this;
			}
			t0.relax(round + 1);
		}
	}

	public ArrayList<Tile> djikstra(Tile[][] tiles, ArrayList<Tile> destinations) {
		this.initSingleSource(tiles);
		this.relax(1);
		Tile destination = destinations.get(0);
		for (int i = 1; i < destinations.size(); i++) {
			if (destination.distance > destinations.get(i).distance) {
				destination = destinations.get(i);
			}
		}
		ArrayList<Tile> path = new ArrayList<Tile>();
		path.add(destination);
		while (!path.get(path.size() - 1).equals(this)) {
			if (path.get(path.size() - 1).preTile != null)
				path.add(path.get(path.size() - 1).preTile);
			else
				return null;
		}
		return path;
	}

	public Tile colorTileArray(ArrayList<Tile> tileArray, Painter painter) {
		if (tileArray == null) {
			return null;
		}
		for (int i = tileArray.size() - 1; i > 0; i--) {
			tileArray.get(i).color(painter);
		}
		return tileArray.get(0);
	}

	public String toString() {
		return "(" + this.row + "," + this.column + ")" + "-" + this.value
				+ "-" + this.colored;
	}

}
