package geek.hub.potionmaster.Controls.GameItemControls;

import geek.hub.potionmaster.Controls.GameControl;

public class CharacterControl {
	
	private static CharacterControl instance;
	
	public static CharacterControl Instance() {
		return instance == null ? instance = new CharacterControl() : instance;
	}

	public static void attack() {
		GameControl.Instance().oppositeCharacter.currentHealth -= GameControl.Instance().activeCharacter.attack;
		int a = GameControl.Instance().oppositeCharacter.currentHealth;
		int b = 0;
	}
	
	/**TODO Might be not the best solution**/
	public static void endTurn() { 
		GameControl.Instance().activeCharacter = GameControl.Instance().activeCharacter.equals(GameControl.Instance().player) 
				? GameControl.Instance().enemy 
				: GameControl.Instance().player;
		GameControl.Instance().oppositeCharacter = GameControl.Instance().activeCharacter.equals(GameControl.Instance().player) 
				? GameControl.Instance().enemy 
				: GameControl.Instance().player;
	}
	
}
