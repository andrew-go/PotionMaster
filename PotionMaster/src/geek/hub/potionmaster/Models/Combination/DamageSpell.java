package geek.hub.potionmaster.Models.Combination;


public class DamageSpell extends Spell {

	public int damage;
	
	public DamageSpell(String name, int[][] combination, int damage) {
		super(name, combination);
		this.damage = damage;
	}
		
}
