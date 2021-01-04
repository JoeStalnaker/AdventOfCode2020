package AdventOfCodeDay20;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

class Tile {
	
	String id;
	char[][] data;
	
	public void flip () {
		
		char[][] newData = new char[data.length][data[0].length];
		for (int row = 0; row < data.length; row++) {
			for (int col = 0; row < data[0].length; col++) {
				newData[data.length-(row+1)][col] = data[row][col];
			}
		}
		this.data = newData;
		
	}
	
	public void rotate () {

		char[][] newData = new char[data.length][data[0].length];
		for (int row = 0; row < data.length; row++) {
			for (int col = 0; row < data[0].length; col++) {
				newData[col][(data.length-1)-row] = data[row][col];
			}
		}
		this.data = newData;		
		
	}
	
	public Tile ( String id, char[][] data ) {
		
		this.id = id;
		this.data = data;
		
	}
	
}

public class AdventOfCodeDay20 {
	
	private static ArrayList<String> getInput () {
		
		Scanner in = new Scanner(System.in);
		ArrayList<String> input = new ArrayList<String>();
		String line = in.nextLine();
		while (!line.equals("end")) {
			input.add(line);
			line = in.nextLine();
		}
		in.close();
		return input;
		
	}

	private static char[][] parseData ( ArrayList<String> data ) {
		
		char[][] ca = new char[data.get(0).length()][data.size()];
		for (int row = 0; row < data.size(); row++) {
			ca[row] = data.get(row).toCharArray();
		}
		return ca;
		
	}
	
	private static ArrayList<Tile> parseInput ( ArrayList<String> input ) {
		
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		ArrayList<String> data = new ArrayList<String>();
		String id = "";
		for (String line : input) {
			if (line.contains("Tile")) {
				id = line.replace("Title ",  "").replace(":",  "");
			} else if (line.equals("")) {
				tiles.add(new Tile(id, parseData(data)));
				data.clear();
			} else {
				data.add(line);
			}
		}
		return tiles;
		
	}
	
	private static void showTiles ( ArrayList<Tile> tiles ) {
		
		for (Tile tile : tiles) {
			System.out.println(tile.id);
			for (char[] ca : tile.data) {
				System.out.println(ca);
			}
		}
		
	}
	
	private BigInteger part1 ( ArrayList<Tile> tiles ) {
		
		int side  = (int)Math.sqrt((double)tiles.size());
		String[][] pic = null;
		
		//start with tile1 at pic[0][0]
		//try to solve the rest of the puzzle
		//permute thru each rotation and flip/rotation of tile1 at pic[0][0]
		//permute thru all tiles
		
		
		BigInteger bi = new BigInteger("1");bi.multiply(new BigInteger(pic[0][0]));
		bi.multiply(new BigInteger(pic[0][side-1]));
		bi.multiply(new BigInteger(pic[side-1][0]));
		bi.multiply(new BigInteger(pic[side-1][side-1]));
		return bi;
		
	}
	
	public static void main(String[] args) {
		
		ArrayList<String> input = getInput();
		ArrayList<Tile> tiles = parseInput(input);
		int[][] picture = part1(tiles);
		
		showTiles(tiles);

	}

}
