package adventOfCodeDay8;

import java.util.Scanner;

class Node {
	
	boolean visited = false;
	String instruction;
	int value;
	Node next = null;
	Node prev = null;
	
	public Node (String instruction, int value) {
		this.instruction = instruction;
		this.value = value;
	}
	
	public String toString() {
		String out = String.valueOf(visited);
		out += " " + instruction;
		out += " " + value;
		out += " " + System.identityHashCode(this);
		if (next == null) {
			out += " null";
		} else {
			out += " " + System.identityHashCode(next);			
		}
		if (prev == null) {
			out += " null";
		} else {
			out += " " + System.identityHashCode(prev);			
		}
		return out;
	}
		
}

public class AdventOfCodeDay8 {
	
	static Node parseInput() {
		
		Node head = null;
		Node current = null;		
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		
		do {
			String[] tokens = line.split(" ");
			String instruction = tokens[0];
			int value = Integer.parseInt(tokens[1]);
			if (current == null) {
				head = new Node(instruction, value);
				current = head;
			} else {
				current.next = new Node(instruction, value);
				current.next.prev = current;
				current = current.next;				
			}
			line = in.nextLine();
		} while (!line.equals("end"));
		in.close();
		
		return head;
		
	}
	
	static boolean isInfinite (Node head) {
		boolean infinite = false;
		
		return 
	}
	
	static int execute (Node head) {
		Node current = head;
		int acc = 0;
		boolean infinite = false;
		while (current != null && !infinite) {
			if (current.visited) infinite = true; 
			current.visited = true;
			System.out.println(current);
			switch (current.instruction) {
				case "acc":
					acc += current.value;
					break;
				case "jmp":
					int jmp = current.value;
					if (jmp > 0) {
						for (int i = 0; i < jmp-1; i++) {
							current = current.next;
						}
					} else {
						for (int i = 0; i > jmp-1; i--) {
							current = current.prev;
						}						
					}
					break;
			}
			current = current.next;
			return acc;
		}
		
	}
	
	public static void main(String[] args) {

		Node head = parseInput();
		if (!isInfinite(head)) System.out.println(execute(head));
		else System.out.println("infinite");

	}

}
