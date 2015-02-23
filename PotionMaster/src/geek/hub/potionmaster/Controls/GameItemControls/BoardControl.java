package geek.hub.potionmaster.Controls.GameItemControls;

import geek.hub.potionmaster.Ingredients;
import geek.hub.potionmaster.Controls.GameControl;
import geek.hub.potionmaster.Controls.GameControl.eGameStatus;
import geek.hub.potionmaster.Settings.GameSettings;
import android.view.MotionEvent;


public class BoardControl {
	
	private static BoardControl instance;

	public static BoardControl Instance() {
		return instance == null ? instance = new BoardControl() : instance;
	}
	
	public int activePouchSize;
	public int activeBoardLeftBound;
	public int activeBoardTopBound;
	public int activeBoardSize;
	
	public BoardControl() {
		activePouchSize = GameControl.Instance().gameView.getPouchNormalImage().getMinimumWidth() - 8 + 20;
		activeBoardLeftBound = GameControl.Instance().gameView.pouchesStartPoint.x - 10;
		activeBoardTopBound = GameControl.Instance().gameView.pouchesStartPoint.y - 10;
		activeBoardSize = activePouchSize * 5;
	}

	private int getColIndex(int x) {
		return (x - activeBoardLeftBound) / activePouchSize;
	}
	
	private int getRowIndex(int y) {
		return (y - activeBoardTopBound) / activePouchSize;
	}
	
	public static void removeSelectedPouch() {
		if (GameControl.Instance().currentRow == -1 || GameControl.Instance().currentCol == -1)
			return;		
		GameControl.Instance().setLastSelectedIngedient();
		InventoryControl.addIngredient();
		GameControl.Instance().emptySelectedPouch();
		GameControl.Instance().removedPouchesCount++;
	}
	
	public boolean isPouchExist(int x, int y) {
		GameControl.Instance().currentCol = getColIndex(x);
		GameControl.Instance().currentRow = getRowIndex(y);
		return GameControl.Instance().pouches[GameControl.Instance().currentCol][GameControl.Instance().currentRow] != -1;
	}
	
	/**TODO check this. Maybe it would be better to use bounds - GameControl.Instance().gameView.getBoardImage().getBounds()**/
	public boolean isOn(MotionEvent event) {  
		if ((event.getX() < activeBoardLeftBound)
				|| (event.getX() > activeBoardSize + activeBoardLeftBound)
				|| (event.getY() < activeBoardTopBound)
				|| (event.getY() > activeBoardSize + activeBoardTopBound))
			return false;
		if (GameControl.Instance().gameStatus != eGameStatus.pouchSelecting)
			GameControl.Instance().gameStatus = eGameStatus.pouchSelecting;
		return true;
	}
	
	public static boolean isEmpty() {
		return GameControl.Instance().removedPouchesCount == GameSettings.Instance().pouchCount;
	}
	
	public static void fillUp() {
		GameControl.Instance().initPouches();
	}

}
