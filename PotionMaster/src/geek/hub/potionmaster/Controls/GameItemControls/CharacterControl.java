package geek.hub.potionmaster.Controls.GameItemControls;

import geek.hub.potionmaster.Controls.GameControl;
import geek.hub.potionmaster.Models.Combination.DamageSpell;
import geek.hub.potionmaster.Models.Combination.HealSpell;
import geek.hub.potionmaster.Models.Combination.Spell;

public class CharacterControl {
	
	private static CharacterControl instance;
	
	public static CharacterControl Instance() {
		return instance == null ? instance = new CharacterControl() : instance;
	}

	public static void attack() {
		GameControl.Instance().oppositeCharacter.currentHealth -= GameControl.Instance().activeCharacter.attack;
	}
	
	/**TODO Might be not the best solution**/
	public static void endTurn() { 
//		GameControl.Instance().activeCharacter = GameControl.Instance().activeCharacter.equals(GameControl.Instance().player) 
//				? GameControl.Instance().enemy 
//				: GameControl.Instance().player;
//		GameControl.Instance().oppositeCharacter = GameControl.Instance().activeCharacter.equals(GameControl.Instance().player) 
//				? GameControl.Instance().enemy 
//				: GameControl.Instance().player;
	}
	
	public static void useSpell(Spell spell) {
		if (spell instanceof DamageSpell) {
			GameControl.Instance().oppositeCharacter.currentHealth -= ((DamageSpell) spell).damage;
			return;
		}
		if (spell instanceof HealSpell) {
			GameControl.Instance().activeCharacter.currentHealth += ((HealSpell) spell).heal;
			return;
		}
	}
	
}
