package AdventOfCodeDay10Part1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AdventOfCodeDay10Part1 {

	public static int numDiff (Integer[] a, int diff) {
		int num = 0;
		for (int i = 0; i < a.length-1; i++) {
			int d = Math.abs(a[i] - a[i+1]);
			if (d == diff) num++;
			System.out.println(num);
		}
		return num;
	}
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		ArrayList<Integer> al = new ArrayList<Integer>();
		String line = in.nextLine();
		while (!line.equals("end")) {
			al.add(new Integer(line));
			line = in.nextLine();
		}
		Integer[] a = new Integer[al.size()];
		a = al.toArray(a);
		Arrays.sort(a);
		int d = a[a.length-1] + 3;
		for (Integer i : a) {
		}
		System.out.println(d);
		System.out.println((numDiff(a, 1)+1) * (numDiff(a, 3)+1));
		in.close();

	}

}
