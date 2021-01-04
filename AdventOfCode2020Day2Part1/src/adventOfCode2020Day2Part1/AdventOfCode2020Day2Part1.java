package adventOfCode2020Day2Part1;

import java.util.Scanner;
import java.util.StringTokenizer;

public class AdventOfCode2020Day2Part1 {

	/*Part 1
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int count = 0;
		while (in.hasNextLine()) {
			StringTokenizer tokens = new StringTokenizer(in.nextLine(), "- :");
			int min = Integer.valueOf(tokens.nextToken());
			int max = Integer.valueOf(tokens.nextToken());
			String c = tokens.nextToken();
			String pass = tokens.nextToken();
			int before = pass.length();
			pass = pass.replace(c, "");
			int after = pass.length();
			int diff = before - after;
			if (diff >= min && diff <= max) {
				count++;
				System.out.println(count);
			}
		}
		in.close();
	}
	*/

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int count = 0;
		while (in.hasNextLine()) {
			StringTokenizer tokens = new StringTokenizer(in.nextLine(), "- :");
			int p1 = Integer.valueOf(tokens.nextToken()) - 1;
			int p2 = Integer.valueOf(tokens.nextToken()) - 1;
			String c = tokens.nextToken();
			String pass = tokens.nextToken();
			if (pass.substring(p1, p1+1).matches(c) ^ pass.substring(p2, p2+1).matches(c)) {
				count++;
				System.out.println(count);
			}
		}
		in.close();
	}
}
