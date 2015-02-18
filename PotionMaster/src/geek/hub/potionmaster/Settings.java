package geek.hub.potionmaster;


public class Settings {

	private static Settings instance;

	public static Settings Instance() {
		return instance == null ? instance = new Settings() : instance;
	}
	
	public int screenWidth = 1920;
	public int screenHeight = 1080;

}
