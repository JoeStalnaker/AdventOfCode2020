package AdventOfCodeDay14Part2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AdventOfCodeDay14Part2 {

	private static HashMap<Long, Long> mem = new HashMap<Long, Long> ();
	
	public static ArrayList<String> getInput () {
		ArrayList<String> al = new ArrayList<String>();
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		while (!line.equals("end")) {
			al.add(line);
			line = in.nextLine();
		}
		in.close();
		return al;
	}

	public static void applyMask (Long value, String address, String mask) {
		for (int i = 0; i < mask.length(); i++) {
			switch (mask.charAt(i)) {
			case '1':
				address = address.substring(0,i) + '1' +address.substring(i+1);
				break;
			case 'X':
				address = address.substring(0,i) + 'X' +address.substring(i+1);
				break;
			}
		}
		permuteAddress(value, address);
	}
	
	public static void permuteAddress (Long value, String address) {
		int i = address.indexOf('X');
		if (i == -1) {
			mem.put(string2long(address), value);
		} else {
			address = address.substring(0,i) + '0' + address.substring(i+1);
			permuteAddress(value, address);
			address = address.substring(0,i) + '1' + address.substring(i+1);
			permuteAddress(value, address);
		}
	}
	
	public static Long string2long (String s) {
		return new Long(Long.parseLong(s,2));
	}
	
	public static String long2string ( Long value ) {
		String s = Long.toBinaryString(value);
		s = pad(s);
		return s;
	}
	
	public static String pad ( String s ) {
		String pad = "";
		for (int i = 0; i < 36 - s.length(); i++) {
			pad += "0";
		}
		return pad + s;
	}
	
	public static void procInput (ArrayList<String> input) {
		String mask = "";
		for (String s : input) {
			String[] sa = s.split(" = ");
			if (sa[0].equals("mask")) {
				mask = sa[1];
			} else {
				String address = sa[0].replace("mem[", "");
				address = address.replace("]", "");
				address = long2string(new Long(address));
				Long value = new Long(sa[1]);
				applyMask(value, address, mask);
			}
		}
	}
	
	public static BigInteger memSum () {
		BigInteger bi = new BigInteger("0");
		for (Long key : mem.keySet()) {
			BigInteger addend = new BigInteger(String.valueOf(mem.get(key)));
			//System.out.print(bi +" + "+ addend +" = ");
			bi = bi.add(addend);
			//System.out.println(bi);
		}
		return bi;
	}
	
	public static void main(String[] args) {

		ArrayList<String> input = getInput();
		procInput(input);
		System.out.println(memSum());		

	}

}
