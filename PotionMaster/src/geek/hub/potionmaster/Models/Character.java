package geek.hub.potionmaster.Models;

import java.util.HashMap;

public class Character {

	public int health = 100;
	public int currentHealth = 100;
	public int attack = 5;
	String name;
	public HashMap<Integer, Integer> inventory = new HashMap<Integer, Integer>();
	public int[][] ingredients = new int[5][5];
	
	public Character(String name) {
		this.name = name;
	}

}
