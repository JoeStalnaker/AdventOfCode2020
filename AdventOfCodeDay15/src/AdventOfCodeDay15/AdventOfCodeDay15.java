package AdventOfCodeDay15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdventOfCodeDay15 {

	public static void main(String[] args) {
		
		Integer[] a = {16,12,1,0,15,7,11};
		Integer n = new Integer(a[0]);
		HashMap<Integer, Integer> m = new HashMap<Integer, Integer>(a.length);
		for (int i = 1; i < a.length; i++) {
			m.put(n,  new Integer(i));
			n = a[i];
		}

		int last = 30000000;
		for (int i = a.length; i < last; i++) {
			Integer j = m.get(n);
			if (j == null) {
				m.put(n, i);
				n = new Integer(0);
			}
			else {
				m.put(n,  i);
				n = new Integer(i - j);
			}
			if(i%100000==0)System.out.println(n);
		}
		System.out.println(n);
	}

}
