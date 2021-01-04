package AdventOfCodeDay16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AdventOfCodeDay16 {

	public static HashMap<String, int[]> getRules (Scanner in) {
		HashMap<String, int[]> rules = new HashMap<String, int[]>();
		String line = in.nextLine();
		while (!line.equals("")) {
			String name = line.split(":")[0];
			String[] specs = line.split(":")[1].split(" or ");
			String[] a = specs[0].split("-");
			String[] b = specs[1].split("-");
			int[] array = {Integer.parseInt(a[0].substring(1)),
					Integer.parseInt(a[1]),
					Integer.parseInt(b[0]),
					Integer.parseInt(b[1])};
			rules.put(name,  array);
			line = in.nextLine();
			}
		return rules;
	}
	
	public static int[] getMyTicket(Scanner in) {
		ArrayList<Integer> fields = new ArrayList<Integer>();
		String line = in.nextLine();
		line = in.nextLine();
		in.nextLine();
		Scanner ls = new Scanner(line);
		while (ls.hasNextInt()) {
			fields.add(new Integer(ls.nextInt()));
		}
		int[] a = new int[fields.size()];
		for(int i = 0; i < fields.size(); i++) {
			a[i] = fields.get(i);
		}
		ls.close();
		return a;
	}
	
	public static int[][] getOtherTickets (Scanner in) {
		ArrayList<String> lines = new ArrayList<String>();
		String line = in.nextLine();
		line = in.nextLine();
		while (!line.equals("") && !line.equals("end")) {
			lines.add(line);
			line = in.nextLine();
		}
		int[][] a = new int[lines.size()][lines.get(0).split(",").length];
		for (int i = 0; i < lines.size(); i++) {
			String[] fields = lines.get(i).split(",");
			for (int j = 0; j < fields.length; j++) {
				a[i][j] = Integer.parseInt(fields[j]);
			}
		}
		return a;
	}
	
	public static boolean isValid (int field, int[] spec) {
		return ((field >= spec[0] && field <= spec[1])
				|| (field >= spec[2] && field <= spec[3]));
	}
	
	public static int getTSER(HashMap<String, int[]> rules, int[] ticket) {
		int tser = 0;
		for (int i = 0; i < ticket.length; i++) {
			boolean valid = false;
			for (String rule : rules.keySet()) {
				valid = valid || isValid(ticket[i], rules.get(rule));
			}
			if (!valid) tser += ticket[i];
		}
		return tser;
	}
	
	public static int applyRules (HashMap<String, int[]> rules, int[][] tickets) {
		int tser = 0;
		for (int i = 0; i < tickets.length; i++) {
			tser += getTSER(rules, tickets[i]);
		}
		return tser;
	}
	
	public static HashMap<String, Integer> getFI (HashMap<String, int[]> rules, int[][] tickets) {
		HashMap<String, Integer> fi = new HashMap<String, Integer>();
		for (String name : rules.keySet()) {
			for (int j = 0; j < tickets[0].length; j++) {
				boolean valid = true;
				for (int i = 0; i < tickets.length; i++) {
					valid = valid && isValid(tickets[i][j], rules.get(name));
				}
				if (valid) fi.put(name,  new Integer(j));
			}
		}
		return fi;
	}
		
	public static int calcDept (HashMap<String, Integer> fi, int[] ticket) {
		int d = 0;
		for (String name : fi.keySet()) {
			if (name.contains("departure")) {
				d *= ticket[fi.get(name)];
			}
		}
		return d;
	}
	
	public static void showFI (HashMap<String, Integer> fi) {
		for (String name : fi.keySet()) {
			System.out.println(name + fi.get(name));
		}
	}
	
	public static int[][] purgeInvalid (HashMap<String, int[]> rules, int[][] tickets) {

		ArrayList<int[]> valids = new ArrayList<int[]>();		
		for (int i = 0; i < tickets.length; i++) {
			if(getTSER(rules, tickets[i]) > 0) {
				valids.add(tickets[i]);
			}
		//int[][] a = new int[valids.size()][tickets[0].length];
		}
		int[][] a = new int[valids.size()][tickets[0].length];
		a = valids.toArray(a);
		return a;
	}
	
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		HashMap<String, int[]> rules = getRules(in);
		int[] myTicket = getMyTicket(in);
		int[][] tickets = getOtherTickets(in);
		in.close();
		System.out.println(applyRules(rules, tickets));
		tickets = purgeInvalid(rules, tickets);
		HashMap<String, Integer> fi = getFI(rules, tickets);
		showFI(fi);
		System.out.println(calcDept(fi, myTicket));

	}

}
