package geek.hub.potionmaster.Controls;

import geek.hub.potionmaster.Controls.GameItemsControls.BoardControl;
import geek.hub.potionmaster.Models.Character;
import geek.hub.potionmaster.Views.GameView;

import java.util.Random;

import android.view.View;

public class GameControl {
	
	/**Instance components**/
	
	private static GameControl instance;

	public static GameControl Instance() {
		return instance == null ? instance = new GameControl() : instance;
	}
	
	/**Enums**/
	
	public enum eGameStatus 
	{ 
		noAction,
		pouchSelecting,
		pouchSelected, 
		ingredientDisplaying, 
		actionOffer, 
		attacking, 
		inventoryDisplaying		
	};
	
	/**Members**/
	
	public GameView gameView;
	
	public DrawThread drawThread;
	
	public GameThread gameThread;
	
	public eGameStatus gameStatus = eGameStatus.noAction;
	
	/**Game components**/
	
	public int[][] pouches = new int[5][5];
	
	public Character player = new Character();
	public Character enemy = new Character();
	
	/**Board selections**/
	
	public int currentCol = -1;
	public int currentRow = -1;
	
	public int selCol = -1;
	public int selRow = -1;
	
	/**Game initialization**/
	
	public void initPouches() {
		Random random = new Random();
		for (int i = 0; i < pouches.length; i++)
			for (int j = 0; j < pouches[i].length; j++)
				pouches[i][j] = random.nextInt(20) + 1;
	}
	
	public void initDrawThread() {
		GameControl.Instance().drawThread = new DrawThread(GameControl.Instance().gameView);
		GameControl.Instance().drawThread.start();
	}
	
	public void initGameThread() {
		GameControl.Instance().gameThread = new GameThread();
		GameControl.Instance().gameThread.start();
	}
	
	public void initCharacters() {
		player = new Character();
		enemy = new Character();
	}
	
	/**Thread classes**/	

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
	
	public static class GameThread extends Thread {

		boolean run = true;

		public void Stop() {
			run = false;
		}

	    public void run() {
	    	while(run) {
	    		if (GameControl.Instance().gameStatus == eGameStatus.pouchSelected)
	    		{
					try {
						sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					BoardControl.removeSelectedPouch();
					GameControl.Instance().gameStatus = eGameStatus.noAction;
	    		}
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	    	}
	    }

	}

}
