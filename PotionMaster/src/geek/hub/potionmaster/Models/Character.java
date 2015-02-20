package geek.hub.potionmaster.Models;

import geek.hub.potionmaster.Interfaces.ICharactereble;

import java.util.Hashtable;

public class Character implements ICharactereble{

	int health = 100;
	int attack = 5;
	public Hashtable<Integer, Integer> bag = new Hashtable<>();

}
