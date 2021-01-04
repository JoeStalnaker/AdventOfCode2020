package adventOfCodeDay5;

import java.util.Arrays;
import java.util.Scanner;

public class AdventOfCodeDay5 {
	
	static int seatID (String pass) {
		int row = 0;
		int seat = 0;
		for (int i = 0; i < 7; i++) {
			if (pass.charAt(i) == 'B') {
				row += 64/(int)(Math.pow(2.0, (double)i));
			}
		}
		for (int i = 7; i < 10; i++) {
			if (pass.charAt(i) == 'R') {
				seat += (int)(Math.pow(2.0, (double)9-i));
			}
		}
		return (row * 8) + seat;
	}

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int highest = 0;
		int numPasses = 932;
		int myPass = 0;
		int[] passes = new int[numPasses];
		for(int i = 0; i < numPasses; i++) {
			String pass = in.nextLine();
			passes[i] = seatID(pass);
			if (passes[i] > highest) highest = passes[i];
		}
		Arrays.sort(passes);
		for (int i = 0; i < numPasses; i++) {
			System.out.println(passes[i]);			
		}
		for (int i = 0; i < numPasses-1; i++) {
			if (passes[i+1] != passes[i]+1) {
				myPass = passes[i]+1;
			}
		}
		System.out.println(myPass);
		in.close();
				
		//System.out.println(seatID("FBFBBFFRLR"));
		
	}

}
