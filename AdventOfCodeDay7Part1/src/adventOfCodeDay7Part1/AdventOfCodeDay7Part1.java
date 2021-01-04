package adventOfCodeDay7Part1;

import java.util.HashMap;
import java.util.Scanner;

class Bag {
	
	String name = "";
	HashMap<String, Integer> contents = new HashMap<String, Integer>();
	
	public Bag (String name) {
		this.name = name;
	}
	
	public void add(String name, int count) {
		contents.put(name, new Integer(count));
	}
	
}

public class AdventOfCodeDay7Part1 {
	
	private static HashMap<String, Bag> parseInput() {
		HashMap<String, Bag> bags = new HashMap<String, Bag>();
		Scanner in = new Scanner(System.in);
		String line = "end";
		if (in.hasNextLine()) line = in.nextLine(); 
		while (!line.equals("end")) {
			String[] ruleParts = line.split("contain");
			String name = ruleParts[0].split("bags")[0];
			Bag bag = new Bag(name);			
			if (ruleParts[1].contains(",")) {
				String[] contents = ruleParts[1].split(",");
				for (String content : contents) {
					Scanner s = new Scanner(content);
					Integer count = new Integer(s.nextInt());
					String subName = s.next();
					while(s.hasNext()) {
						String next = s.next();
						if (!next.contains("bag")) subName += " " + next;
					}
					bag.contents.put(subName, count);
				}
			} else if (!ruleParts[1].contains("no other bags")){
				Scanner s = new Scanner(ruleParts[1]);
				Integer count = new Integer(s.nextInt());
				String subName = s.next();
				while(s.hasNext()) {
					String next = s.next();
					if (!next.contains("bag")) subName += " " + next;
				}
				bag.contents.put(subName, count);
			}
			line = in.nextLine();
		}
		return bags;
	}
	
	private static boolean canContain(HashMap<String, Bag> bags, String container, String containee) {
		boolean can = false;
		can = bags.get(container).contents.containsKey(containee);
		for (String bag : bags.keySet()) {
			can = can || canContain(bags, bag)
		}
		return can;
	}
	
	public static void main(String[] args) {
		
		HashMap<String, Bag> bags = parseInput();
		int count = 0;
		for (String bag : bags.keySet()) {
			if (canContain(bags, bag, "shiny gold")) count++;
		}
	}

}
