package geek.hub.potionmaster;

import geek.hub.potionmaster.Controls.GameControl;
import geek.hub.potionmaster.Views.GameView;
import android.graphics.drawable.Drawable;


public class Ingredients {
	
	private static Ingredients instance;

	public static Ingredients Instance() {
		return instance == null ? instance = new Ingredients() : instance;
	}
	
	public Drawable fireImage;
	public Drawable coneImage;
	public Drawable bloodImage;
	public Drawable slimeImage;
	public Drawable mushroomImage;


	/**List of ingredients:
	 * 1 - Fire
	 * 2 - Cone
	 * 3 - Blood
	 * 4 - Slime
	 * 5 - Mushrooms
	 */

	/**TODO maybe I need to move this into GameView or create specific class for images**/
	public Drawable getIgredientImage(int index) {
		switch(index) {
			case 1:
				return fireImage == null 
					? fireImage = GameControl.Instance().gameView.getResources().getDrawable(R.drawable.fire_ingredient) 
					: fireImage;
			case 2:
				return coneImage == null 
					? coneImage = GameControl.Instance().gameView.getResources().getDrawable(R.drawable.cone_ingredient) 
					: coneImage;
			case 3:
				return bloodImage == null 
					? bloodImage = GameControl.Instance().gameView.getResources().getDrawable(R.drawable.blood_ingredient) 
					: bloodImage;
			case 4:
				return slimeImage == null 
					? slimeImage = GameControl.Instance().gameView.getResources().getDrawable(R.drawable.slime_ingredient) 
					: slimeImage;
			case 5:
				return mushroomImage == null 
					? mushroomImage = GameControl.Instance().gameView.getResources().getDrawable(R.drawable.mushroom_ingredient) 
					: mushroomImage;
			default:
				return null;
		}
	}
	
}
