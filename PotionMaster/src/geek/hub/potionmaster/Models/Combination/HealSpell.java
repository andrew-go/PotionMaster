package geek.hub.potionmaster.Models.Combination;

public class HealSpell extends Spell {

	public int heal;
	
	public HealSpell(String name, int[][] combination, int heal) {
		super(name, combination);
		this.heal = heal;
	}
	
}
