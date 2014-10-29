import interaction.Input;
import interaction.Painter;

public class GameRun {

	final static int length = 7;

	public static Tile[][] initTiles() {
		Tile[][] tiles = new Tile[length][length];
		for (int i = 0; i < tiles.length; i++) { // row
			for (int j = 0; j < tiles[0].length; j++) { // column
				tiles[i][j] = new Tile(i, j);
			}
		}
		for (int i = 0; i < tiles.length; i++) { // row
			for (int j = 0; j < tiles[0].length; j++) { // column
				if (i - 2 >= 0 && i - 2 < 7 && j - 1 >= 0 && j - 1 < 7) {
					tiles[i][j].addNeighbor(tiles[i - 2][j - 1]);
				}
				if (i - 2 >= 0 && i - 2 < 7 && j + 1 >= 0 && j + 1 < 7) {
					tiles[i][j].addNeighbor(tiles[i - 2][j + 1]);
				}
				if (i - 1 >= 0 && i - 1 < 7 && j - 2 >= 0 && j - 2 < 7) {
					tiles[i][j].addNeighbor(tiles[i - 1][j - 2]);
				}
				if (i - 1 >= 0 && i - 1 < 7 && j + 2 >= 0 && j + 2 < 7) {
					tiles[i][j].addNeighbor(tiles[i - 1][j + 2]);
				}
				if (i + 1 >= 0 && i + 1 < 7 && j - 2 >= 0 && j - 2 < 7) {
					tiles[i][j].addNeighbor(tiles[i + 1][j - 2]);
				}
				if (i + 1 >= 0 && i + 1 < 7 && j + 2 >= 0 && j + 2 < 7) {
					tiles[i][j].addNeighbor(tiles[i + 1][j + 2]);
				}
				if (i + 2 >= 0 && i + 2 < 7 && j - 1 >= 0 && j - 1 < 7) {
					tiles[i][j].addNeighbor(tiles[i + 2][j - 1]);
				}
				if (i + 2 >= 0 && i + 2 < 7 && j + 1 >= 0 && j + 1 < 7) {
					tiles[i][j].addNeighbor(tiles[i + 2][j + 1]);
				}
			}
		}
		return tiles;
	}

	public static int runGraphical(int startRow, int startColumn) {
		Tile[][] tiles = initTiles();
		Painter painter = new Painter(tiles.length, tiles[0].length);
		Tile t = tiles[startRow][startColumn].color(painter);

		while (t != null) {
			painter.delay(3000);
			t = t.color(painter);
		}

		int number = 0;
		for (int i1 = 0; i1 < tiles.length; i1++) { // row
			for (int j1 = 0; j1 < tiles[0].length; j1++) { // column
				if (tiles[i1][j1].getColored())
					number++;
				// System.out.println(tiles[i][j]);
			}
		}
		System.out.println("[" + startRow + ", " + startColumn + "]");
		System.out.println(number);
		return number;
	}

	public static int run(int startRow, int startColumn) {
		Tile[][] tiles = initTiles();
		Tile t = tiles[startRow][startColumn].color(null);
		while (t != null) {
			t = t.color(null);
		}

		int number = 0;
		for (int i1 = 0; i1 < tiles.length; i1++) { // row
			for (int j1 = 0; j1 < tiles[0].length; j1++) { // column
				if (tiles[i1][j1].getColored())
					number++;
				// System.out.println(tiles[i][j]);
			}
		}
		System.out.println("[" + startRow + ", " + startColumn + "]");
		System.out.println(number);
		return number;
	}

	public static int goodTiles() {
		Painter painter = new Painter(length, length);
		int good = 0;
		for (int i = 0; i < length; i++) { // row
			for (int j = 0; j < length; j++) { // column
				int startRow = i;
				int startColumn = j;
				if (run(startRow, startColumn) == 49) {
					painter.setPixel(startColumn, 6 - startRow, true);
					good++;
				}
			}
		}
		return good;
	}

	public static void main(String[] args) {
		// int startRow = (int) (7 * Math.random());
		// int startColumn = (int) (7 * Math.random());
		// int startRow = Input.readInt("startRow");
		// int startColumn = Input.readInt("startColumn");
		// runGraphical(startRow, startColumn);
		// run(startRow, startColumn)
		System.out.println(goodTiles());

	}

}
