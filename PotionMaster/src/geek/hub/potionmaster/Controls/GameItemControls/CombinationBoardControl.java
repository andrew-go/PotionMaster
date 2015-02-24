package geek.hub.potionmaster.Controls.GameItemControls;

import geek.hub.potionmaster.IngredientManager;
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
	
	public int selCol = -1;
	public int selRow = -1;
	
	public boolean isContainingCombination = false;
	
	public int[][] combinationBoard = new int[3][3];
	
	public CombinationBoardControl() {
		activeIngredientSize = IngredientManager.Instance().getIngredientImage(1).getMinimumWidth() + 20;
		activeCombinationBoardSize = activeIngredientSize * 3;
		activeCombinationBoardLeftBound = GameControl.Instance().gameView.getCombinationBoardImage().getBounds().left + 10;
		activeCombinationBoardTopBound = GameControl.Instance().gameView.getCombinationBoardImage().getBounds().top + 10;

	}
	
	public void clearCombinationBoard() {
		for (int i = 0; i < combinationBoard.length; i++)
			for (int j = 0; j < combinationBoard[i].length; j++)
				combinationBoard[i][j] = 0;
		isContainingCombination = false;
	}
	
	public void putIngredient(MotionEvent event) {
		selCol = getColIndex((int)event.getX());
		selRow = getRowIndex((int)event.getY());
		combinationBoard[selCol][selRow] = InventoryControl.Instance().getDraggingIngredient();
	}
	
	private int getColIndex(int x) {
		return (x - activeCombinationBoardLeftBound) / activeIngredientSize;
	}
	
	private int getRowIndex(int y) {
		return (y - activeCombinationBoardTopBound) / activeIngredientSize;
	}

	public boolean isOn(MotionEvent event) {
		if ((event.getX() < activeCombinationBoardLeftBound)
				|| (event.getX() >= activeCombinationBoardSize + activeCombinationBoardLeftBound)
				|| (event.getY() < activeCombinationBoardTopBound)
				|| (event.getY() >= activeCombinationBoardSize + activeCombinationBoardTopBound))
			return false;
		return true;
	}
	
	public boolean isContainingCombination() {
		return isContainingCombination;
	}

}
