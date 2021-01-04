package AdventOfCodeDay17;

import java.util.ArrayList;
import java.util.Scanner;

public class AdventOfCodeDay17 {

	private static ArrayList<String> getInput () {
		ArrayList<String> input = new ArrayList<String>();
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		while (!line.equals("end")) {
			input.add(line);
			line = in.nextLine();
		}
		in.close();
		return input;
	}
	
	private static char[][][] initCube(ArrayList<String> input, char[][][] cube) {
		int[] start = new int[3];
		start[0] = cube.length/2 - input.size()/2;
		start[1] = cube[0].length/2 - input.get(0).length()/2;
		start[2] = cube[0][0].length/2;
		//init all with .
		for (int x = 0; x < cube.length; x++) {
			for (int y = 0; y < cube[0].length; y++) {
				for (int z = 0; z < cube[0][0].length; z++) {
					cube[x][y][z] = '.';
				}
			}
		}
		//add input to center
		for (int x = 0; x < input.get(0).length(); x++) {
			for (int y = 0; y < input.size(); y++) {
				cube[x+start[0]][y+start[1]][start[2]] = input.get(y).charAt(x);
			}
		}
		return cube;
	}
	
	private static void showCube (char[][][] cube) {
		for (int z = 0; z < cube[0][0].length; z++) {
			for (int x = 0; x < cube.length; x++) {
				for (int y = 0; y < cube[0].length; y++) {
					System.out.print(cube[x][y][z]);
				}
				System.out.println();
			}
			System.out.println();
			System.out.println();
		}		
	}
	
	private static void showInput (ArrayList<String> input) {
		for (String s : input) System.out.println(s);
	}
	
	private static boolean isActive (char[][][] cube, int x, int y, int z) {
		boolean active = false;
		if (x > 0 && x < cube.length
				&& y > 0 && y < cube[0].length
				&& z > 0 && z < cube[0][0].length
				&& cube[x][y][z] == '#') {
			active = true;
		}		
		return active;
	}
	
	private static int neighborsActive (char[][][] cube, int a, int b, int c) {
		int count = 0;
		for (int x = a-1; x <= a+1; x++) {
			for (int y = b-1; y <= b+1; y++) {
				for (int z = c-1; z <= c+1; z++) {
					if ( !(x == a && y == b && z == c)
							&& isActive(cube, x, y, z)) {
						//System.out.println(cube[x][y][z] + "" + count);
						count++;
					}
				}			
			}			
		}
		//System.out.println(count);
		return count;
	}
	
	private static char[][][] cycleCube (char[][][] cube) {
		char[][][] newCube =
				new char[cube.length][cube[0].length][cube[0][0].length];
		for (int x = 0; x < cube.length; x++) {
			for (int y = 0; y < cube[0].length; y++) {
				for (int z = 0; z < cube[0][0].length; z++) {
					newCube[x][y][z] = cube[x][y][z];
					int na = neighborsActive(cube, x, y, z);
					switch (cube[x][y][z]) {
					case '#':
						if (na < 2 || na > 3) {
							newCube[x][y][z] = '.';
						}
						break;
					case '.':
						if (na == 3) {
							newCube[x][y][z] = '#';
						}
						break;
					}
				}
			}
		}
		return newCube;
	}
	
	private static int countActive (char[][][] cube) {
		int count = 0;
		for (int z = 0; z < cube.length; z++) {
			for (int x = 0; x < cube[0].length; x++) {
				for (int y = 0; y < cube[0][0].length; y++) {
					if (isActive(cube, x, y, z)) count++;
				}
			}
		}
		return count;
	}
	
	private static int calcPart1 (char[][][] cube) {
		for (int i = 0; i < 6; i++) {
			cube = cycleCube(cube);
		}
		return countActive(cube);
	}
	
	public static void main(String[] args) {
		
		int s = 20;
		char[][][] cube = new char[s][s][s];
		ArrayList<String> input = getInput();
		cube = initCube(input, cube);
		/*
		System.out.println(countActive(cube));
		cube = cycleCube(cube);
		System.out.println(countActive(cube));
		showCube(cube);
		*/
		System.out.println(calcPart1(cube));
		
	}

}
