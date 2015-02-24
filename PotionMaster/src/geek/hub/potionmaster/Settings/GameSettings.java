package geek.hub.potionmaster.Settings;

public class GameSettings {

	private static GameSettings instance;

	public static GameSettings Instance() {
		return instance == null ? instance = new GameSettings() : instance;
	}
	
	public boolean isMusicOn = true;
	public boolean isSoundsOn = true;
	
	public int pouchCount = 25;

}
