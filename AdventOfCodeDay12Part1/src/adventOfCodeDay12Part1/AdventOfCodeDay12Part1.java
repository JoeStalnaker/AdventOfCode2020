package adventOfCodeDay12Part1;

import java.util.ArrayList;
import java.util.Scanner;

public class AdventOfCodeDay12Part1 {

	static int x = 0, y = 0, facing = 1;
	
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
	
	public static void turn (char dir, int ang) {
		switch (dir) {
		case 'L':
			facing -= ang/90;
			if (facing < 0) facing += 4;
			break;
		case 'R':
			facing += ang/90;
			if (facing > 3) facing -= 4;
			break;
		}
		System.out.println(facing);
	}
	
	public static void move (char dir, int value) {
		switch (dir) {
		case 'N':
			y += value;
			break;
		case 'S':
			y -= value;
			break;
		case 'E':
			x += value;
			break;
		case 'W':
			x -= value;
			break;
		case 'F':
			switch (facing) {
			case 0:
				move('N', value);
				break;
			case 1:
				move('E', value);
				break;
			case 2:
				move('S', value);
				break;
			case 3:
				move('W', value);
				break;
			}
			break;
		}
	}
	
	public static int followInstructions(ArrayList<String> instructions) {
		int dist = 0;
		for (int i = 0; i < instructions.size(); i++) {
			String instruction = instructions.get(i);
			char dir = instruction.charAt(0);
			int value = Integer.parseInt(instruction.substring(1));
			if ("LR".contains(String.valueOf(dir))) turn(dir, value); 
			else move(dir, value);
			System.out.println(instruction);
			System.out.printf("%d %d %d", x, y, facing);
			System.out.println();
		}
		dist = Math.abs(x) + Math.abs(y);
		return dist;
	}
	
	public static void main(String[] args) {
		
		ArrayList<String> instructions = getInstructions();
		System.out.println(followInstructions(instructions));
		
	}

}
