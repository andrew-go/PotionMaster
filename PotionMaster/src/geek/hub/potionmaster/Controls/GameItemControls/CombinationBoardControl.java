package geek.hub.potionmaster.Controls.GameItemControls;

import geek.hub.potionmaster.Ingredients;
import geek.hub.potionmaster.Controls.GameControl;
import android.view.MotionEvent;

public class CombinationBoardControl {
	
	private static CombinationBoardControl instance;

	public static CombinationBoardControl Instance() {
		return instance == null ? instance = new CombinationBoardControl() : instance;
	}
	
	public int activeIngredientSize;
	public int activeCombinationBoardLeftBound;
	public int activeCombinationBoardTopBound;
	public int activeCombinationBoardSize;
	
	public CombinationBoardControl() {
		activeIngredientSize = Ingredients.Instance().getIngredientImage(1).getMinimumWidth() + 20;
		activeCombinationBoardSize = activeIngredientSize * 5;
		activeCombinationBoardLeftBound = GameControl.Instance().gameView.getSpellPanelImage().getBounds().right - (30 + activeCombinationBoardSize);
		activeCombinationBoardTopBound = GameControl.Instance().gameView.getSpellPanelImage().getBounds().top + 30 + activeIngredientSize;

	}

	public boolean isOn(MotionEvent event) {  
		if ((event.getX() < activeCombinationBoardLeftBound)
				|| (event.getX() > activeCombinationBoardSize + activeCombinationBoardLeftBound)
				|| (event.getY() < activeCombinationBoardTopBound)
				|| (event.getY() > activeCombinationBoardSize + activeCombinationBoardTopBound))
			return false;
		int a = 0;
		return true;
	}

}
