package adventOfCodeDay8Part2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class AdventOfCodeDay8Part2 {

	public static HashMap parseInput() {
		
		HashMap<String, String> program = new HashMap<String, String>();
		int lineNum = 0;		
		Scanner in = new Scanner(System.in);
		String line = "";
		if (in.hasNextLine()) line = in.nextLine();
		
		while (!line.equals("end")) {
			program.put(String.valueOf(lineNum), line);
			line = in.nextLine();
			lineNum++;
		}
		in.close();
		return program;
		
	}
	
	public static boolean isInfinite (HashMap<String, String> program) {
		boolean infinite = false;
		HashSet<String> visited = new HashSet<String>();
		String lineNum = "0";
		while (program.containsKey(lineNum) && !infinite){
			infinite = visited.contains(lineNum);
			visited.add(lineNum);
			String[] tokens = program.get(lineNum).split(" ");
			String instruction = tokens[0];
			if (instruction.equals("jmp")) {
				int value = Integer.parseInt(tokens[1]);
				lineNum = String.valueOf(Integer.parseInt(lineNum) + value);
			} else {
				lineNum = String.valueOf(Integer.parseInt(lineNum) + 1);
			}
		}
		return infinite;
	}
	
	public static HashMap<String, String> fixProgram (HashMap<String, String> program) {
		boolean infinite = true;
		int lineNum = 0;
		int size = program.size();
		while (infinite && lineNum < size) {
			String key = String.valueOf(lineNum);
			String instruction = program.get(key);
			String newInstruction = "";
			if (instruction.contains("nop")) newInstruction = instruction.replace("nop", "jmp");
			else if (instruction.contains("jmp")) newInstruction = instruction.replace("jmp", "nop");
			program.put(key, newInstruction);
			infinite = isInfinite(program);
			if (infinite) {
				program.put(key,  instruction);
				lineNum++;
			}
		}
		if (infinite) program.clear();
		return program;
	}
	
	public static int executeProgram (HashMap<String, String> program) {
		int acc = 0;
		int lineNum = 0;
		while (lineNum < program.size()) {
			System.out.println(lineNum);
			String[] tokens = program.get(String.valueOf(lineNum)).split(" ");
			switch (tokens[0]) {
				case "jmp":
					lineNum += Integer.parseInt(tokens[1]);
					break;
				case "acc":
					acc += Integer.parseInt(tokens[1]);
					lineNum++;
					break;
				case "nop":
					lineNum++;
					break;
			}
		}
		return acc;
	}
	
	public static void main(String[] args) {
		
		HashMap<String, String> program = parseInput();
		System.out.println(program);
		System.out.println(isInfinite(program));
		program = fixProgram(program);
		System.out.println(program);
		System.out.println(isInfinite(program));
		System.out.println(executeProgram(program));
		
	}

}
