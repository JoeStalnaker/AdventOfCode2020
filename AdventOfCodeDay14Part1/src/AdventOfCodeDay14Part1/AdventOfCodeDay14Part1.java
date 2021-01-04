package AdventOfCodeDay14Part1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AdventOfCodeDay14Part1 {

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
	
	public static Long applyMask (Long value, String mask) {
		String vs = long2string(value);
		//System.out.println(mask +"\n"+ vs);
		for (int i = 0; i < mask.length(); i++) {
			switch (mask.charAt(i)) {
			case '0':
				vs = vs.substring(0,i) + '0' + vs.substring(i+1);
				break;
			case '1':
				vs = vs.substring(0,i) + '1' + vs.substring(i+1);
				break;
			}
		}
		return string2long(vs);
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
	
	public static HashMap<Long, Long> procInput (ArrayList<String> input) {
		HashMap<Long, Long> mem = new HashMap<Long, Long> ();
		String mask = "";
		Long ma = new Long("0");
		Long value = new Long("0");
		for (String s : input) {
			String[] a = s.split(" = ");
			String command = a[0];
			if (command.equals("mask")) {
				mask = a[1];
			} else {
				command = command.replace("mem[", "");
				command = command.replace("]", "");
				ma = new Long(command);
				value = new Long(a[1]);
				value = applyMask(value, mask);
				mem.put(ma,  value);
			}
		}
		return mem;
	}
	
	public static BigInteger memSum (HashMap<Long, Long> mem) {
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
		HashMap<Long, Long> mem = procInput(input);
		System.out.println(memSum(mem));
		
	}

}
