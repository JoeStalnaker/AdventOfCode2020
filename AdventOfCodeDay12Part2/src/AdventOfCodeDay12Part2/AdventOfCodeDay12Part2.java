package AdventOfCodeDay12Part2;

import java.util.ArrayList;
import java.util.Scanner;

public class AdventOfCodeDay12Part2 {

	static int[] cur = {0, 0};
	static int[] way = {10, 1};
		
	public static ArrayList<String> getInstructions () {
		
		ArrayList<String> instructions = new ArrayList<String>();
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		while (!line.equals("end")) {
			instructions.add(line);
			line = in.nextLine();
		}
		in.close();
		return instructions;
		
	}
	
	public static void move (int value) {
		for (int i = 0; i < value; i++) {
			cur[0] += way[0];
			cur[1] += way[1];
		}
	}
	
	public static void translate (char dir, int value) {
		switch (dir) {
		case 'N':
			way[1] += value;
			break;
		case 'S':
			way[1] -= value;
			break;
		case 'E':
			way[0] += value;
			break;
		case 'W':
			way[0] -= value;
			break;
		}
	}
	
	public static void rotate (char dir, int value) {
		for ( int i = 0; i < value/90; i++) {
			int[] newWay = {0, 0};
			switch (dir) {
			case 'L':
				newWay[0] = -1 * way[1];
				newWay[1] = way[0];
				break;
			case 'R':
				newWay[0] = way[1];
				newWay[1] = -1 * way[0];
				break;
			}
			way = newWay;
			System.out.println("way: "+way[0]+" "+way[1]);
			
		}
	}
	
	public static int getManhattan() {
		return Math.abs(cur[0]) + Math.abs(cur[1]);
	}
	
	public static void followInstructions(ArrayList<String> instructions) {
		for (int i = 0; i < instructions.size(); i++) {
			String instruction = instructions.get(i);
			char dir = instruction.charAt(0);
			int value = Integer.parseInt(instruction.substring(1));
			if (dir == 'F') move(value);
			else if ("LR".contains(String.valueOf(dir))) rotate(dir, value);
			else translate(dir, value);
			System.out.println(instruction);
			System.out.println("way: "+way[0]+" "+way[1]);
			System.out.println("cur: "+cur[0]+" "+cur[1]);
		}
	}
	
	public static void main(String[] args) {
		
		ArrayList<String> instructions = getInstructions();
		followInstructions(instructions);
		System.out.println(getManhattan());
		
	}

}
