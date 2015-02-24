package geek.hub.potionmaster;

import java.util.ArrayList;
import java.util.List;

import geek.hub.potionmaster.Models.Combination.DamageSpell;
import geek.hub.potionmaster.Models.Combination.HealSpell;
import geek.hub.potionmaster.Models.Combination.Spell;

public class CombinationManager {

	private static CombinationManager instance;

	public static CombinationManager Instance() {
		return instance == null ? instance = new CombinationManager() : instance;
	}
	
	List<Spell> spells = new ArrayList<Spell>();	
	
	public CombinationManager() {
		initCombinations();
	}
	
	public void initCombinations(){
		DamageSpell fireBall = new DamageSpell("Fireball", Combinations.getFireBallCombination(), 20);
		spells.add(fireBall);
		HealSpell softHeal = new HealSpell("Soft Heal", Combinations.getSoftHealCombination(), 20);
		spells.add(softHeal);
	}
	
	public Spell getSpell(int[][] selectedCombination){
		for (Spell spell : spells)
			if(spell.checkSpellCombination(selectedCombination))
				return spell;
		return null;
	}
	


}
