package AdventOfCodeDay10Part2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AdventOfCodeDay10Part2 {

	public static boolean canComplete (ArrayList<Integer> al, int start, int end) {
		boolean complete = false;
		if (al.size() >= 3
			&& start >= 0
			&& end < al.size()) {
			int diff = Math.abs(al.get(end) - al.get(start));
			complete = diff <= 3;
		}
		return complete;
	}
	
	public static int pathsFromHere (ArrayList<Integer> al, int index) {
		int paths = 0;
		if (index >= al.size()-1) paths = 1;
		if (canComplete(al, index, index+1)) {
			paths += pathsFromHere(al, index+1);
		}
		if (canComplete(al, index, index+2)) {
			paths += pathsFromHere(al, index+2);
		}
		if (canComplete(al, index, index+3)) {
			paths += pathsFromHere(al, index+3);
		}
		System.out.println(paths);
		return paths;
	}
	
	public static void main(String[] args) {
		
		ArrayList<Integer> al = new ArrayList<Integer>();
		al.add(new Integer(0));
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		while (!line.equals("end")) {
			al.add(new Integer(line));
			line = in.nextLine();
		}
		Integer[] a = new Integer[al.size()];
		a = al.toArray(a);
		Arrays.sort(a);
		al.clear();
		for (Integer i : a) {
			al.add(i);
		}
		al.add(new Integer(a[a.length-1])+3);
		System.out.println(pathsFromHere(al, 0));
		in.close();

	}

}
