package geek.hub.potionmaster.Settings;


public class DeviceSettings {

	private static DeviceSettings instance;

	public static DeviceSettings Instance() {
		return instance == null ? instance = new DeviceSettings() : instance;
	}
	
	public int screenWidth = 1920;
	public int screenHeight = 1080;

}
