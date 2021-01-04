package adventOfCodeDay9Part2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class AdventOfCodeDay9Part2 {

	public static BigDecimal[] parseInput () {

		Scanner in = new Scanner(System.in);
		ArrayList<BigDecimal> al = new ArrayList<BigDecimal>();
		String line = in.nextLine();
		while (!line.equals("end")) {
			al.add(new BigDecimal(line));
			line = in.nextLine();
		}
		in.close();
		BigDecimal[] a = new BigDecimal[al.size()];
		return al.toArray(a);
	}
	
	public static BigDecimal sum(BigDecimal[] a, int start, int end) {
		BigDecimal sum = new BigDecimal("0");
		for (int i = start; i <= end; i++) {
			sum = sum.add(a[i]);
		}
		return sum;
	}
	
	public static BigDecimal smallest (BigDecimal[] a, int start, int end) {
		BigDecimal smallest = a[start];
		for (int i = start+1; i <= end; i++) {
			if (a[i].compareTo(smallest) < 0) {
				smallest = a[i];
			}
		}
		return smallest;
	}

	public static BigDecimal largest (BigDecimal[] a, int start, int end) {
		BigDecimal largest = a[start];
		for (int i = start+1; i <= end; i++) {
			if (a[i].compareTo(largest) > 0) {
				largest = a[i];
			}
		}
		return largest;		
	}
	
	public static void main(String[] args) {
		
		BigDecimal magicNumber = new BigDecimal(31161678);
		BigDecimal[] a = parseInput();
		boolean found = false;
		int start = 0;
		int end = 1;
		while (!found) {
			BigDecimal sum = sum(a, start, end);
			BigDecimal diff = magicNumber.subtract(sum);
			System.out.println(start +" "+ end +" "+ diff);
			if (diff.compareTo(new BigDecimal(0)) == 0) {
				found = true;
			} else if (diff.compareTo(new BigDecimal(0)) > 0) {
				end++;
			} else {
				start++;
				end = start + 1;
			}
		}
		BigDecimal smallest = smallest(a, start, end);
		BigDecimal largest = largest(a, start, end);
		System.out.println(smallest.add(largest));
	}

}
