package AdventOfCodeDay21;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

class Food {
	
	public HashSet<String> ingredients;
	public HashSet<String> allergens;
	
	public Food ( HashSet<String> ingredients, HashSet<String> allergens ) {
		this.ingredients = ingredients;
		this.allergens = allergens;
	}
	
}

public class AdventOfCodeDay21 {

	private static ArrayList<Food> getInput () {
		
		ArrayList<Food> foods = new ArrayList<Food>();
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		while (!line.equals("end")) {
			//System.out.println(line);
			String[] parts = line.split("\\(");
			//get ingredients
			HashSet<String> ingredients = new HashSet<String>();
			for (String ingredient : parts[0].split(" "))
				ingredients.add(ingredient);
			//get allergens
			HashSet<String> allergens = new HashSet<String>();
			String al = parts[1].replaceAll("\\)",  "").replaceAll("contains",  "");
			if (al.contains(",")) {
				for (String allergen : al.split(","))
					allergens.add(allergen);
			} else {
				allergens.add(al);
			}
			for (String allergen : allergens) {
				allergen = allergen.replaceAll("\s",  "");
			}
			foods.add(new Food(ingredients, allergens));
			line = in.nextLine();
		}
		in.close();
		return foods;
		
	}
	
	private static void showFoods ( ArrayList<Food> foods) {
		
		for (Food food : foods) {
			for (String ingredient : food.ingredients) {
				System.out.print(ingredient + " ");
			}
			System.out.print(": ");
			for (String allergen : food.allergens) {
				System.out.print(allergen + " ");
			}
			System.out.println();
		}
		
	}
	
	private static HashMap<String, String> getAllergens ( ArrayList<Food> allFoods ) {
		
		HashMap<String, String> aiMap = new HashMap<String, String>();
		ArrayList<String> aList = new ArrayList<String>(); 
		ArrayList<String> iList = new ArrayList<String>();
		ArrayList<Food> fList = new ArrayList<Food>();
		
		for (Food food : allFoods) {
			for ( String allergen : food.allergens) {
				aList.add(allergen);
			}
		}
		//what ingredient is in all of the foods that contain dairy?
		for (String allergen : aList) {
			//build list of all foods containing an allergen
			for (Food food : allFoods) { 
				if (food.allergens.contains(allergen)) fList.add(food);
			} 
			Food food = fList.remove(0);
			for (String ingredient : food.ingredients) {
				if (!iList.contains(ingredient)) iList.add(ingredient);
				for (String i : iList) System.out.println(i);
			}
			String ingredient = iList.remove(0);
			while (iList.size() > 1 && !fList.isEmpty()) {
				while (!fList.isEmpty()) {
					Food next = fList.remove(0);
					if (!next.ingredients.contains(ingredient))
						iList.remove(ingredient);
				}
				System.out.println(iList);
			}
			aiMap.put(allergen,  ingredient);
			fList.clear();
		}
		return aiMap;
		
	}
	
	public static int part1 ( HashMap<String, String> aiMap, ArrayList<Food> allFoods ) {
		
		int sum = 0;
		HashSet<String> ingredients = new HashSet<String>();
		for (Food food : allFoods) {
			for (String ingredient : food.ingredients) {
				ingredients.add(ingredient);
				System.out.println(ingredient);
			}
		}
		for (String allergen : aiMap.keySet()) {
			ingredients.remove(aiMap.get(allergen));
		}
		for (String ingredient : ingredients) {
			for (Food food : allFoods) {
				if (food.ingredients.contains(ingredient)) {
					sum++;
					System.out.println(sum);
				}
			}
		}
		return sum;
	}
	
	private static void showAiMap ( HashMap<String, String> aiMap ) {
		
		for (String allergen : aiMap.keySet()) {
			System.out.println(allergen +" "+ aiMap.get(allergen));
		}
		
	}
	
	public static void main(String[] args) {
		
		ArrayList<Food> foods = getInput();
		HashMap<String, String> aiMap = getAllergens(foods);
		showAiMap(aiMap);
		//System.out.println(part1(aiMap, foods));
		//showFoods(foods);

	}

}
