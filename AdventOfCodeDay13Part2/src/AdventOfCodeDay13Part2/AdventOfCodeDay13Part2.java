package AdventOfCodeDay13Part2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class AdventOfCodeDay13Part2 {

	private static BigInteger findLCM (String[] busses) {
		BigInteger lcm = new BigInteger(busses[0]);
		for (int i = 1; i < busses.length; i++) {
			if (!busses[i].equals("x")) {
				BigInteger bi = new BigInteger(String.valueOf(i));
				lcm = lcm(lcm, bi.add(new BigInteger(busses[i])));
			}
		}
		return lcm;
	}
	
	private static BigInteger lcm (BigInteger a, BigInteger b) {
		BigInteger gcd = a.gcd(b);
		return a.multiply(b).divide(gcd);
	}
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		in.close();
		String[] busses = line.split(",");
		boolean found = true;
		BigInteger time = new BigInteger("0");
		BigInteger addend = findLCM(busses);
		BigInteger zero = new BigInteger("0");
		long count = 0;
		do {
			found = true;
			time = time.add(addend);
			for (int i = 0; i < busses.length; i++) {
				if (!busses[i].equals("x")) {
					BigInteger bus = new BigInteger(busses[i]);
					BigInteger bi = new BigInteger(String.valueOf(i));
					found = found && (time.add(bi).mod(bus).equals(zero));
					/*
					System.out.println(time+"+"+bi+"="
							+time.add(bi)+"%"+bus+"="
							+time.add(bi).mod(bus)
							+"==0"+time.add(bi).mod(bus).equals(zero));
					System.out.println(found);
					*/
				}
			}
			if (count % 1000000 == 0) System.out.println(time);
		} while (false);
		System.out.println(time);
	}

}
