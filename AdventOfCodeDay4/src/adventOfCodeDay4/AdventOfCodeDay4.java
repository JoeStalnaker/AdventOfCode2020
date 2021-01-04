package adventOfCodeDay4;

import java.util.Scanner;

public class AdventOfCodeDay4 {

	static boolean isValid (String passport) {
		
		String[] fields = {
				"byr",
				"iyr",
				"eyr",
				"hgt",
				"hcl",
				"ecl",
				"pid",
		};
		boolean valid = true;
		for (int i = 0; i < fields.length; i++) {
			String match = fields[i] + ":";
			if (!passport.contains(match)) {
				valid = false;
			}
		}
		return valid;
	}
	
	public static void main(String[] args) {
				
		Scanner in = new Scanner(System.in);
		String passport = "";
		int count = 0;
		while (in.hasNextLine()) {
			String line = in.nextLine();
			if (line.equals("")) {
				if (isValid(passport)) count++;
				System.out.println(count);
				passport = "";
			} else {
				passport += line;
			}
		}
		in.close();
		
	}

}
