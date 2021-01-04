package adventOfCodeDay6Part2;

import java.util.HashSet;
import java.util.Scanner;

public class AdventOfCodeDay6Part2 {

	public static void main(String[] args) {
		
		int total = 0;
		Scanner in = new Scanner(System.in);
		int groupSize = 0;
		HashSet<Character> questions = new HashSet<Character>();
		String line = "";
		while (in.hasNextLine()) {
			line = in.nextLine().toLowerCase();
			if (line.equals("")) {
				total += questions.size();
				questions.clear();
				groupSize = 0;
			} else if (groupSize == 1) {
				for (Character c : line.toCharArray()) {
					questions.add(c);
				}
			} else {
				HashSet<Character> newQuestions = new HashSet<Character>();
				for (Character c : line.toCharArray()) {
					newQuestions.add(c);
				}
				questions.retainAll(newQuestions);
			}
			groupSize++;
			System.out.println(questions+":"+total+":"+questions.size()+":"+groupSize);
		}
		in.close();
		
	}

}
