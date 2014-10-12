import interaction.Painter;

public class GameRun {

	final static int length = 7;
	
	public static void initTiles() {
		Tile [][] tiles = new Tile [length][length];
		for (int i = 0; i < tiles.length; i ++) { // row
			for (int j = 0; j < tiles[0].length; j ++) { // column
				tiles[i][j] = new Tile(i, j);
			}
		}
		for (int i = 0; i < tiles.length; i ++) { // row
			for (int j = 0; j < tiles[0].length; j ++) { // column
				// add neighbors
			}
		}
	}
	
	public static void main(String[] args) {
		Painter painter = new Painter(length, length);
		painter.setPixel(4, 4, true);

	}

}
