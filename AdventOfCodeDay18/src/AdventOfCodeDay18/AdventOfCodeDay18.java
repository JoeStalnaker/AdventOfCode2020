package AdventOfCodeDay18;

import java.util.ArrayList;
import java.util.Scanner;

public class AdventOfCodeDay18 {

	private static long solvePart1 ( String exp ) {
		long value = 0;
		if (exp.contains(")")) {
			//System.out.prlongln(exp);
			//System.out.prlongln(exp.split("\\)")[0]);
			int start = exp.split("\\)")[0].lastIndexOf("(")+1;
			int end = exp.indexOf(")");
			//System.out.prlongln(start + " " + end);
			String before = exp.substring(0, start-1);
			String paren = exp.substring(start, end);
			String after = exp.substring(end+1);
			//System.out.prlongln(before);
			//System.out.prlongln(paren);
			//System.out.prlongln(after);
			//System.out.prlongln(before + "(" + paren + ")" + after);
			value = solvePart1(before +  String.valueOf(solvePart1(paren)) + after);
		} else {
			String[] terms = exp.split("\s");
			/*
			for (String term : terms) {
				System.out.prlongln(term);
			}
			*/
			value = Integer.valueOf(terms[0]);
			for (int i = 1; i < terms.length; i += 2) {
				switch (terms[i].charAt(0)) {
				case '+':
					value += Integer.valueOf(terms[i+1]);
					break;
				case '-':
					value -= Integer.valueOf(terms[i+1]);
					break;
				case '*':
					value *= Integer.valueOf(terms[i+1]);
					break;
				case '/':
					value /= Integer.valueOf(terms[i+1]);
					break;
				}
			}
		}
		return value;
	}
	
	private static long solvePart2 ( String exp ) {
		System.out.println(exp);
		long value = 0;
		if (exp.contains(")")) {
			//System.out.prlongln(exp);
			//System.out.prlongln(exp.split("\\)")[0]);
			int start = exp.split("\\)")[0].lastIndexOf("(")+1;
			int end = exp.indexOf(")");
			//System.out.prlongln(start + " " + end);
			String before = exp.substring(0, start-1);
			String paren = exp.substring(start, end);
			String after = exp.substring(end+1);
			//System.out.prlongln(before);
			//System.out.prlongln(paren);
			//System.out.prlongln(after);
			//System.out.prlongln(before + "(" + paren + ")" + after);
			value = solvePart2(before +  String.valueOf(solvePart2(paren)) + after);
		} else if (exp.contains("*")) {
			int i = exp.indexOf("*");
			value = solvePart2(exp.substring(0,i)) * solvePart2(exp.substring(i+1));
		} else if (exp.contains("+")){
			int i = exp.indexOf("+");
			value = solvePart2(exp.substring(0,i)) + solvePart2(exp.substring(i+1));
		} else {
			exp = exp.replaceAll("\s", "");
			return Long.parseLong(exp);
		}
		return value;
	}
	
	private static long calc ( ArrayList<String> exps ) {
		long sum = 0;
		for (String s : exps) {
			sum += solvePart2(s);
		}
		return sum;
	}
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		ArrayList<String> exps = new ArrayList<String>();
		while (!line.equals("end")) {
			exps.add(line);
			line = in.nextLine();
		}
		in.close();
		System.out.println(calc(exps));
	}

}
