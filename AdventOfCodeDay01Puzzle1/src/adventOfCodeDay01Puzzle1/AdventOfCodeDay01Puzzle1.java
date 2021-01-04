package adventOfCodeDay01Puzzle1;

import java.util.ArrayList;
import java.util.Scanner;

public class AdventOfCodeDay01Puzzle1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		ArrayList<Integer> al = new ArrayList<Integer>();
		while (in.hasNextInt()) {
			Integer n = new Integer(in.nextInt());
			al.add(n);
		}
		for (Integer x : al) {
			for (Integer y : al) {
				for (Integer z : al) {
					if ( x + y + z == 2020)	System.out.println(x*y*z);					
				}
			}
		}
		System.out.println(al);
		in.close();
	}

}
