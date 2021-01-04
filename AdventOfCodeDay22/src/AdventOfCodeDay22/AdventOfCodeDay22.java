package AdventOfCodeDay22;

import java.util.ArrayDeque;
import java.util.Scanner;

public class AdventOfCodeDay22 {

	private static ArrayDeque[] getInput () {
		
		ArrayDeque[] decks = {new ArrayDeque<Integer>(), new ArrayDeque<Integer>()};
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		int deck = 0;
		while (!line.equals("end")) {
			if (line.equals("")) deck++;
			else if (!line.contains("Player")) {
				decks[deck].add(new Integer(line));
			}
			line = in.nextLine();
		}
		in.close();
		return decks;
		
	}
	
	private static void showDecks (ArrayDeque[] decks) {
		for (int i = 0; i < 2; i++) {
			for (Object card : decks[i]) {
				System.out.println(card);
			}
			System.out.println("");
		}
	}
	
	private static ArrayDeque[] play ( ArrayDeque[] decks ) {
		
		int count = 0;
		while (!(decks[0].size() == 0 || decks[1].size() == 0)) {
			Integer a = (Integer)decks[0].pop();
			Integer b = (Integer)decks[1].pop();
			if ( a > b ) {
				decks[0].offer(a);
				decks[0].offer(b);
			} else {
				decks[1].offer(b);
				decks[1].offer(a);
			}
			//System.out.println(a +" "+ b);
			//showDecks(decks);
			count++;
		}
		return decks;
		
	}
	
	private static int calcScore ( ArrayDeque[] decks ) {
		
		showDecks(decks);
		int score = 0;
		if (decks[0].size() == 0) {
			for (int i = decks[1].size() - 1; i >= 0; i--) {
				score += (Integer)decks[1].pop() * (i+1);
				System.out.println(score);
			}
		} else if (decks[1].size() == 0) {
			for (int i = decks[0].size() - 1; i >= 0; i--) {
				score += (Integer)decks[0].pop() * (i+1);
				System.out.println(score);
			}
		}
		return score;
		
	}
	
	private static int part1 ( ArrayDeque[] decks ) {
		
		decks = play(decks);
		return calcScore(decks);
		
	}
	
	public static void main(String[] args) {
		
		ArrayDeque[] decks = {new ArrayDeque<Integer>(), new ArrayDeque<Integer>()};
		decks = getInput();
		System.out.println(part1(decks));
		
	}

}
