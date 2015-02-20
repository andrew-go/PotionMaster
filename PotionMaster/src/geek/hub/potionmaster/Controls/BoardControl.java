package geek.hub.potionmaster.Controls;

import android.view.MotionEvent;


public class BoardControl {
	
	private static BoardControl instance;

	public static BoardControl Instance() {
		return instance == null ? instance = new BoardControl() : instance;
	}

	private static int getColIndex(int x) {
		return ((int) x - GameControl.Instance().gameView.pouchesStartPoint.x - 10/*pouch margin*/) 
				/ (GameControl.Instance().gameView.getPouchNormalImage().getMinimumWidth() + 20/*x2 pouch margin*/);
	}
	
	private static int getRowIndex(int y) {
		return ((int) y - GameControl.Instance().gameView.pouchesStartPoint.y - 10/*pouch margin*/) 
				/ (GameControl.Instance().gameView.getPouchNormalImage().getMinimumHeight() + 20/*x2 pouch margin*/);
	}
	
	public static void removeSelectedPouch() {
		if (GameControl.Instance().currentRow == -1 || GameControl.Instance().currentCol == -1)
			return;
		GameControl.Instance().pouches[GameControl.Instance().currentCol][GameControl.Instance().currentRow] = -1;
	}
	
//	public static void markSelectedPouch(int x, int y) {
//		GameControl.Instance().currentCol = getColIndex(x);
//		GameControl.Instance().currentRow = getRowIndex(y);
//	}
	
	public static boolean isPouchExist(int x, int y) {
		GameControl.Instance().currentCol = getColIndex(x);
		GameControl.Instance().currentRow = getRowIndex(y);
		return GameControl.Instance().pouches[GameControl.Instance().currentCol][GameControl.Instance().currentRow] != -1;
	}
	
	public static boolean isTouchOn(MotionEvent event) {
		if ((event.getX() < GameControl.Instance().gameView.pouchesStartPoint.x - 10/*pouch merge*/)
				|| (event.getX() > GameControl.Instance().gameView.pouchesStartPoint.x + GameControl.Instance().gameView.getBoardImage().getMinimumWidth() + 10/*pouch margin*/ - 160 * 2/*board margin*/)
				|| (event.getY() < GameControl.Instance().gameView.pouchesStartPoint.y - 10/*pouch merge*/)
				|| (event.getY() > GameControl.Instance().gameView.pouchesStartPoint.y + (GameControl.Instance().gameView.getBoardImage().getMinimumHeight() + 10/*pouch margin*/ - 160 * 2/*board margin*/)))
			return false;
		return true;
	}

}
