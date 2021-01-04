package adventOfCodeDay11Part1;

import java.util.ArrayList;
import java.util.Scanner;

public class AdventOfCodeDay11Part1 {

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
	
	private static int adjOcc (char[][] room, int row, int col) {
		int occ = 0;
		for (int i = row-1; i <= row+1; i++) {
			for (int j = col-1; j <= col+1; j++) {
				if (i >= 0 && i < room.length
					&& j >=0 && j < room[0].length
					&!(i == row && j == col) && room[i][j] == '#') 
					occ++;
			}
		}
		return occ;
	}
	
	private static char[][] processRoom (char[][] room) {
		char[][] newRoom = new char[room.length][room[0].length];
		for (int row = 0; row < room.length; row++) {
			for (int col = 0; col < room[0].length; col++) {
				switch (room[row][col]) {
					case 'L':
						if (adjOcc(room, row, col) == 0) {
							newRoom[row][col] = '#';
						} else {
							newRoom[row][col] = 'L';
						}
						break;
					case '#':
						if (adjOcc(room, row, col) >= 4) {
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
			showRoom(newRoom);
		}
		System.out.println(numOccupied(room));
	}

}
