package adventOfCodeDay9Part1;

import java.util.ArrayDeque;
import java.util.Scanner;

public class AdventOfCodeDay9Part1 {

	public static boolean sum2(ArrayDeque<Integer> preamble, int t) {
		Integer[] temp = new Integer[preamble.size()];
		temp = preamble.toArray(temp);
		boolean found = false;
		for (int i = 0; i < temp.length; i++) {
			for (int j = i; j < temp.length; j++) {
				Integer sum = temp[i] + temp[j];
				found = found || sum.equals(t);
				System.out.println(sum +"="+ t + found);
			}
		}
		return found;
	}
	
	public static void main(String[] args) {

		boolean found = true;
		int e = 0;
		int preambleLength = 25;
		ArrayDeque<Integer> preamble = new ArrayDeque<Integer>(preambleLength);
		Scanner in = new Scanner(System.in);
		for (int i = 0; i < preambleLength; i++) {
			preamble.add(new Integer(in.nextInt()));
		}
		while (in.hasNextInt() && found) {
			int t = in.nextInt();
			found = sum2(preamble, t);
			if (!found) e = t;
			else {
				preamble.add(new Integer(t));
				preamble.pop();
			}
			System.out.println(e);
		}
		in.close();
	}

}
