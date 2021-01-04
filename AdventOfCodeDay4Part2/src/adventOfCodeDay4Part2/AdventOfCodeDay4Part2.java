package adventOfCodeDay4Part2;

import java.util.HashMap;
import java.util.Scanner;

public class AdventOfCodeDay4Part2 {

	static boolean isValid (HashMap<String, String> passport) {

		System.out.println(passport);
		boolean valid = true;
		
		//check if all keys present
		String[] keys = {"byr","iyr","eyr","hgt","hcl","ecl","pid"};
		for (String key : keys) {
			if (! passport.containsKey(key)) {
				System.out.println(key);
				valid = false;
			}
		}
		
		//check each value for validity
		for (String key : passport.keySet()) {
			String value = "";
			switch (key) {
				case "byr":
					value = passport.get(key);
					if (! (value.length() == 4 
							&& Integer.valueOf(value) >= 1920
							&& Integer.valueOf(value) <= 2002)) {
						valid = false;
					}
					break;
				case "iyr":
					value = passport.get(key);
					if (! (value.length() == 4 
							&& Integer.valueOf(value) >= 2010
							&& Integer.valueOf(value) <= 2020)) {
						valid = false;
					}
					break;
				case "eyr":
					value = passport.get(key);
					if (! (value.length() == 4 
							&& Integer.valueOf(value) >= 2020
							&& Integer.valueOf(value) <= 2030)) {
						valid = false;
					}
					break;
				case "hgt":
					value = passport.get(key);
					if (value.matches("\\d+cm")) {
						value = value.replace("cm", "");
						if (! (Integer.valueOf(value) >= 150
								&& Integer.valueOf(value) <= 193)) {
							valid = false;
						}
					} else if (value.matches("\\d+in")) {
						value = value.replace("in", "");
						if (! (Integer.valueOf(value) >= 59
								&& Integer.valueOf(value) <= 76)) {
							valid = false;
						}
					} else {
						valid = false;
					}
					break;
				case "hcl":
					value = passport.get(key);
					if (! (value.matches("#[a-f,0-9]{6}"))) {
						valid = false;
					}
					break;
				case "ecl":
					value = passport.get(key);
					if (! (value.matches("^amb$")
							^ value.matches("^blu$")
							^ value.matches("^brn$")
							^ value.matches("^gry$")
							^ value.matches("^grn$")
							^ value.matches("^hzl$")
							^ value.matches("^oth$"))) {
						valid = false;
					}
					break;
				case "pid":
					value = passport.get(key);
					if (! value.matches("\\d{9}")) {
						valid = false;
					}
					break;
			}
			System.out.println(key+":"+value+" "+valid);
		}
		
		return valid;
	}
	
	public static void main(String[] args) {
				
		Scanner in = new Scanner(System.in);
		HashMap<String, String> passport = new HashMap<String, String>();
		int count = 0;
		while (in.hasNextLine()) {
			String line = in.nextLine();
			if (line.equals("")) {
				if (isValid(passport)) count++;
				System.out.println(count);
				passport.clear();
			} else {
				String[] fields = line.split(" ");
				for (String field : fields) {
					String[] kv = field.split(":");
					passport.put(kv[0], kv[1]);
				}
			}
		}
		in.close();
		
	}

}
