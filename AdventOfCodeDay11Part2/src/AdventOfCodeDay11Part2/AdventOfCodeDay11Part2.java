package AdventOfCodeDay11Part2;

import java.util.ArrayList;
import java.util.Scanner;

public class AdventOfCodeDay11Part2 {

	private static char[][] getRoom () {
		
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		ArrayList<String> rows = new ArrayList<String>();
		while (!line.equals("end")) {
			rows.add(line);
			line = in.nextLine();
		}
		char[][] room = new char[rows.size()][rows.get(0).length()];
		for (int i = 0; i < rows.size(); i ++) {
			room[i] = rows.get(i).toCharArray();
		}
		in.close();
		return room;
	}
	
	private static void showRoom (char[][] room) {
		for (int row = 0; row < room.length; row++) {
			for (int col = 0; col < room[0].length; col++) {
				System.out.print(room[row][col]);
			}
			System.out.println();
		}
	}
	
	private static boolean inRoom(char[][] room, int[] pos) {
		return (pos[0] >= 0 && pos[0] < room.length
				&& pos[1] >= 0 && pos[1] < room[0].length);
	}
	
	private static char peek(char[][] room, int[] pos) {
		if (inRoom(room, pos)) return room[pos[0]][pos[1]];
		else return 'X';
	}
	
	private static boolean occ (char[][] room, int[] v, int[] pos) {
		boolean occ = false;
		int[] look = {pos[0], pos[1]};
		do {
			look[0] += v[0];
			look[1] += v[1];
		} while (!"#LX".contains(String.valueOf(peek(room, look))));
		occ = peek(room, look) == '#';
		return occ;
	}
	
	private static int adjOcc (char[][] room, int[]pos) {
		int occ = 0;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				int[] v = {i, j};
				if ( !(i == 0 && j == 0) && occ(room, v, pos)) occ++;
			}
		}
		return occ;
	}
	
	private static char[][] processRoom (char[][] room) {
		char[][] newRoom = new char[room.length][room[0].length];
		for (int row = 0; row < room.length; row++) {
			for (int col = 0; col < room[0].length; col++) {
				int[] pos = {row, col};
				switch (room[row][col]) {
					case 'L':
						if (adjOcc(room, pos) == 0) {
							newRoom[row][col] = '#';
						} else {
							newRoom[row][col] = 'L';
						}
						break;
					case '#':
						if (adjOcc(room, pos) >= 5) {
							newRoom[row][col] = 'L';
						} else {
							newRoom[row][col] = '#';
						}
						break;
					case '.':
						newRoom[row][col] = '.';
						break;
				}
			}
		}
		return newRoom;
	}
	
	private static boolean stable (char[][] roomA, char[][] roomB) {
		boolean stable = true;
		for (int row = 0; row < roomA.length; row++) {
			for (int col = 0; col < roomA[0].length; col++) {
				stable = stable && roomA[row][col] == roomB[row][col]; 
			}
		}
		return stable;
	}
	
	private static int numOccupied (char[][] room) {
		int num = 0;
		for (int row = 0; row < room.length; row++) {
			for (int col = 0; col < room[0].length; col++) {
				if (room[row][col] == '#') num++; 
			}
		}
		return num;
	}
	
	public static void main(String[] args) {
	
		char[][] room = getRoom();
		showRoom(room);
		char[][] newRoom = processRoom(room);
		showRoom(newRoom);
		while (!stable(room, newRoom)) {
			room = newRoom;
			newRoom = processRoom(room);
			System.out.println();
			showRoom(newRoom);
		}
		System.out.println(numOccupied(room));
	}

}
