package geek.hub.potionmaster.Controls;

import geek.hub.potionmaster.Views.GameView;

import java.util.Random;

import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

public class Game {
	
	private static Game instance;

	public static Game Instance() {
		return instance == null ? instance = new Game() : instance;
	}
	
	public GameView gameView;
	
	public int[][] pouches = new int[5][5];
	
	public int selCol = -1;
	public int selRow = -1;
	
	public Point selectedPouchPoint;
	
	public void initPouches() {
		Random random = new Random();
		for (int i = 0; i < pouches.length; i++)
			for (int j = 0; j < pouches[i].length; j++)
				pouches[i][j] = random.nextInt(20) + 1;
	}
	
	public void removeSelectedPouch(int x, int y) {
		selCol = getColIndex(x);
		selRow = getRowIndex(y);
		Game.Instance().pouches[selCol][selRow] = -1;
		selCol = -1;
		selRow = -1;
	}
	
	public void markSelectedPouch(int x, int y) {
		selCol = getColIndex(x);
		selRow = getRowIndex(y);
	}
	
	private int getColIndex(int x) {
		return ((int) x - Game.Instance().gameView.pouchesStartPoint.x - 10/*pouch margin*/) 
				/ (Game.Instance().gameView.getPouchNormalImage().getMinimumWidth() + 20/*x2 pouch margin*/);
	}
	
	private int getRowIndex(int y) {
		return ((int) y - Game.Instance().gameView.pouchesStartPoint.y - 10/*pouch margin*/) 
				/ (Game.Instance().gameView.getPouchNormalImage().getMinimumHeight() + 20/*x2 pouch margin*/);
	}
	
	public boolean isOnBoard(MotionEvent event) {
		if ((event.getX() < Game.Instance().gameView.pouchesStartPoint.x - 10/*pouch merge*/)
				|| (event.getX() > Game.Instance().gameView.pouchesStartPoint.x + Game.Instance().gameView.getBoardImage().getMinimumWidth() + 10/*pouch margin*/ - 160 * 2/*board margin*/)
				|| (event.getY() < Game.Instance().gameView.pouchesStartPoint.y - 10/*pouch merge*/)
				|| (event.getY() > Game.Instance().gameView.pouchesStartPoint.y + (Game.Instance().gameView.getBoardImage().getMinimumHeight() + 10/*pouch margin*/ - 160 * 2/*board margin*/)))
			return false;
		return true;
	}
	
	public DrawThread drawThread;

	public static class DrawThread extends Thread {

		View view;
		boolean run = true;

		public void Stop() {
			run = false;
		}
		
		public DrawThread(View view) {
			this.view = view;
		}

	    public void run() {
	    	while(run) {
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				view.postInvalidate();
	    	}
	    }

	}

}
