package AdventOfCodeDay13Part1;

import java.util.ArrayList;
import java.util.Scanner;

public class AdventOfCodeDay13Part1 {

	private static int arr = 0;
	private static ArrayList<Integer> busses = new ArrayList<Integer>();
	
	private static void parseInput () {
		Scanner in = new Scanner(System.in);
		arr = in.nextInt();
		in.nextLine();
		String line = in.nextLine();
		String[] a = line.split(",");
		for (String s : a) {
			if (!s.equals("x")) {
				busses.add(new Integer(s));
			}
		}
		in.close();
	}
	
	public static int[] nextBus () {
		int[] nextBus = {Integer.MAX_VALUE, 0};
		for (int i = 0; i < busses.size(); i++) {
			int prev = busses.get(i) * (arr / busses.get(i));
			if (prev + busses.get(i) - arr < nextBus[0] - arr) {
				nextBus[0] = prev + busses.get(i);
				nextBus[1] = busses.get(i);
			}
		}
		return nextBus;
	}
	
	public static void main(String[] args) {
		parseInput();
		int[] nextBus = nextBus();
		System.out.println((nextBus[0] - arr) * nextBus[1]);
	}

}
