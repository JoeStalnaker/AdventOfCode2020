package adventOfCodeDay3;

import java.util.Scanner;

public class AdventOfCodeDay3 {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int mapHeight = 323;
		int xMove = 3;
		int yMove = 1;
		int numTrees = 0;
		String[] map = new String[mapHeight];
		
		for (int i = 0; i < mapHeight; i++) {
			map[i] = in.nextLine();
		}
		
		//run course
		for (int x = xMove, y = y = yMove; y < mapHeight; y += yMove, x += xMove) {
			if (map[y].charAt(x%map[y].length()) == '#') {
				numTrees++;
			}
			System.out.println(x + " " + y + " " + numTrees);
		}
		
		/*print map
		for (int i = 0; i < mapHeight; i++) {
			System.out.println(map[i]);
		}
		*/
		
		System.out.println(numTrees);
		in.close();
	}

}
