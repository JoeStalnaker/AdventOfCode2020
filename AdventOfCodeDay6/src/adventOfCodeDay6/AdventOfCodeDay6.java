package adventOfCodeDay6;

import java.util.HashSet;
import java.util.Scanner;

public class AdventOfCodeDay6 {

	public static void main(String[] args) {
		
		int total = 0;
		Scanner in = new Scanner(System.in);
		HashSet<Character> questions = new HashSet<Character>();
		while (in.hasNextLine()) {
			String line = in.nextLine().toLowerCase();
			if (line.equals("")) {
				total += questions.size();
				questions.clear();
			}
			else if (questions.isEmpty()) {
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
			System.out.println(questions+":"+total+":"+questions.size());
		}		
		in.close();
		
	}

}
