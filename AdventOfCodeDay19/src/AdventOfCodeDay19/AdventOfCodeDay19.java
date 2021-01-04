package AdventOfCodeDay19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AdventOfCodeDay19 {

	private static ArrayList<String> getInput () {
		
		ArrayList<String> input = new ArrayList<String>();
		Scanner in = new Scanner(System.in);
		String line = "";
		if (in.hasNextLine()) line = in.nextLine();
		while (!line.equals("end")) {
			input.add(line);
			line = in.nextLine();
		}
		in.close();
		return input;
		
	}
	
	private static HashMap<Integer, String> getRules (ArrayList<String> input) {
		
		HashMap<Integer, String> rules = new HashMap<Integer, String>();
		for (String line : input) {
			if (line.equals("")) break;
			rules.put(new Integer(line.split(":")[0]),
					line.split(":")[1]);
		}
		return rules;
		
	}
	
	private static ArrayList<String> getMessages (ArrayList<String> input) {
		
		ArrayList<String> messages = new ArrayList<String>();
		boolean messageStart = false;
		for (String line : input) {
			if (line.equals("")) messageStart = true;
			if (messageStart) messages.add(line);
		}
		return messages;
		
	}
	
	private static String buildRule (String rule, HashMap<Integer, String> rules) {
		
		//System.out.println(rule);
		if (rule.charAt(0) == ' ') rule = rule.substring(1);
		if (rule.contains("\"")) rule = rule.substring(1,2);
		else {
			String[] subRules = rule.split("\s");
			for (String subRule : subRules) {
				rule += buildRule(rules.get(new Integer(rule)), rules);
			}
		}
		return rule;
		
	}
	
	private static boolean obeys (String message, String rule, HashMap<Integer, String> rules) {
		
		System.out.println(rule);
		if (rule.charAt(0) == ' ') rule = rule.substring(1);
		boolean obeys = false;
		if (rule.contains("|")) {
			//System.out.println(rule);
			obeys = obeys(message, rule.split("\\|")[0], rules)
					|| obeys(message, rule.split("\\|")[1], rules);
		} else {
			String[] subRules = rule.split("\s");
			for (String subRule : subRules) {
				obeys = message.equals(buildRule(subRule, rules));
			}
		}
		return obeys;
		
	}
	
	private static int calcPart1(ArrayList<String> messages, HashMap<Integer, String> rules) {
		int sum = 0;
		for (String message : messages) {
			if (obeys(message, rules.get(new Integer(0)), rules)) sum++;
		}
		return sum;
	}
	
	public static void main(String[] args) {
		
		ArrayList<String> input = getInput();
		for (String line : input) {
			System.out.println(line);
		}
		HashMap<Integer, String> rules = getRules(input);
		ArrayList<String> messages = getMessages(input);
		System.out.println(calcPart1(messages, rules));
	}

}
