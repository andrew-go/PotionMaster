package geek.hub.potionmaster.Controls;

import geek.hub.potionmaster.Controls.GameItemControls.BoardControl;
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
	public Character activeCharacter = new Character();
	
	/**Board selections**/
	
	public int currentCol = -1;
	public int currentRow = -1;
	
	public int selCol = -1;
	public int selRow = -1;
	
	public int lastSelectedIngredient = -1;
	
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
		activeCharacter = player; /**For a while**/
	}
	
	/**Public methods**/
	public void setLastSelectedIngedient() {
		lastSelectedIngredient = pouches[currentCol][currentRow];
	}
	
	public void addIngredientToBag() {
		activeCharacter.bag.put(lastSelectedIngredient, 
				(activeCharacter.bag.containsKey(lastSelectedIngredient) ? activeCharacter.bag.get(lastSelectedIngredient) : 0) + 1);
	}
	
	public void emptySelectedPouch() {
		pouches[currentCol][currentRow] = -1;
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
		
		/**Members**/

		boolean run = true;
		
		/**Thread methods**/

		public void Stop() {
			run = false;
		}

	    public void run() {
	    	while(run) {
	    		switch(GameControl.Instance().gameStatus) {
					case noAction:
						break;
					case pouchSelecting:						
						break;
					case pouchSelected:
						pouchSelected();
						break;
					case ingredientDisplaying:
						ingredientDisplaying();
						break;
					case actionOffer:
						break;
					case attacking:
						break;
					case inventoryDisplaying:
						break;
	    		}
	    	}
	    	/**TODO maybe it will decrease memory usage**/
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

	    }
	    
	    /**Status methods**/
	    
	    private void pouchSelected() {
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			BoardControl.removeSelectedPouch();
			GameControl.Instance().gameStatus = eGameStatus.noAction;
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			GameControl.Instance().gameStatus = eGameStatus.ingredientDisplaying;
	    }
	    
	    private void ingredientDisplaying() {
			/**ingredientDisplaying**/	    	
	    }
	    
	}

}
