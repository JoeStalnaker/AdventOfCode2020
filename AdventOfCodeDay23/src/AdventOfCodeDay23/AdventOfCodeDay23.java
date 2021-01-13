package AdventOfCodeDay23;

import java.util.Date;
import java.util.Scanner;

class Cup {
	public int value;
	public Cup next;
	
	public Cup ( int value ) {
		
		this.value = value;
		
	}
}

public class AdventOfCodeDay23 {

	private static void showCups ( Cup head ) {
		
		Cup current = head;
		do {
			System.out.print(current.value + " ");
			current = current.next;
		} while (current != head);
		System.out.println();
		
	}
	
	private static void showNodes ( Cup current, int count ) {

		for (int i = 0; i < count; i++) {
			System.out.print(current.value + " ");
			current = current.next;			
		}
		System.out.println();
		
	}
	
	private static Cup getPicks ( Cup current ) {
		
		Cup picks = current.next;
		current.next = current.next.next.next.next;
		return picks;
		
	}
	
	private static boolean inPicks ( int label, Cup picks ) {
		
		return (picks.value == label
				|| picks.next.value == label
				|| picks.next.next.value == label);
		
	}
	
	private static Cup findCup ( Cup current, int label ) {
		
		while (current.value != label) current = current.next;
		return current;
		
	}
	
	private static Cup getDest ( Cup current, Cup picks, int cups, Cup[] index ) {
		
		int label = current.value;
		label--;
		if (label == 0) label = cups;
		while (inPicks(label, picks)) {
			label--;
			if (label == 0) label = cups;
		}
		return index[label-1];
		
	}
	
	private static void putPicks ( Cup picks, Cup dest) {
		
		picks.next.next.next = dest.next;
		dest.next = picks;
		
	}
	
	public static void main(String[] args) {

		int cups = 1000000;
		int moves = 10000000;
		Cup[] index = new Cup[cups];

	    long start = System.currentTimeMillis();
	    System.out.println(start);
		//create ring structure
		int[] input = {5,8,9,1,7,4,2,6,3};
		Cup head = new Cup (input[0]);
		index[input[0]-1] = head;
		Cup current = head;
		current.next = current;
		for (int i = 1; i < input.length; i++) {
			int label = input[i];
			Cup next = new Cup (input[i]);
			index[label-1] = next;
			current.next = next;
			next.next = head;
			current = next;
		}
		for (int i = 10; i <= cups; i++) {
			Cup next = new Cup (i);
			index[i-1] = next;
			current.next = next;
			next.next = head;
			current = next;
		}
		current = head;
		//showIndex(index);
		//showNodes(head);
		long create = System.currentTimeMillis();
		System.out.println(create + " " + (create-start));
		
		//process moves
		while (moves > 0) {
			//get picks
			Cup picks = getPicks(current);
			//get destination
			Cup dest = getDest(current, picks, cups, index);
			//put picks
			putPicks(picks, dest);
			//iterate
			moves--;
			current = current.next;
		}
		//showNodes(head);
		showNodes(index[0], 3);
		long finish = System.currentTimeMillis();
		System.out.println(finish + " " + (finish-start));
		
	}

	private static void showIndex(Cup[] index) {
		
		for (Cup c : index) {
			System.out.print(c.value +" ");
		}
		System.out.println();
		
	}
	
}
