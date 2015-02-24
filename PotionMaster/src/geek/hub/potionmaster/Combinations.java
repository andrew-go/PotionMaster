package geek.hub.potionmaster;

public class Combinations {

	/**List of ingredients:
	 * 1 - Fire
	 * 2 - Cone
	 * 3 - Blood
	 * 4 - Slime
	 * 5 - Mushrooms
	 */
	
	public static int[][] getFireBallCombination() {
		return new int[][]{
				{0,0,0}, 
				{1,2,0}, 
				{0,0,0}};
	}
	
	public static int[][] getSoftHealCombination() {
		return new int[][]{
				{0,0,0},
				{0,3,0},
				{0,0,0}};
	}

}
