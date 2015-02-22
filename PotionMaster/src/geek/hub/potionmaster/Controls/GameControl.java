package geek.hub.potionmaster.Controls;

import geek.hub.potionmaster.Controls.GameItemControls.BoardControl;
import geek.hub.potionmaster.Controls.GameItemControls.CharacterControl;
import geek.hub.potionmaster.Models.Character;
import geek.hub.potionmaster.Views.GameView;

import java.util.Random;
import java.util.ResourceBundle.Control;

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
		attackSelected,
		attacking,
		spellSelected,
		inventoryDisplaying,
		ingredientDragging
	};
	
	/**Members**/
	
	public GameView gameView;
	
	public DrawThread drawThread;
	
	public GameThread gameThread;
	
	public eGameStatus gameStatus = eGameStatus.noAction;
	
	/**Game components**/
	
	public int[][] pouches = new int[5][5];
	
	public Character player;
	public Character enemy;
	public Character activeCharacter;
	public Character oppositeCharacter;
	
	/**Board selections**/
	
	public int currentCol = -1;
	public int currentRow = -1;
	
	public int selCol = -1;
	public int selRow = -1;
	
	public int curX = -1;
	public int curY = -1;
	
	public int lastSelectedIngredient = -1;
	
	public int removedPouchesCount = 0;
	
	/**Game initialization**/
	
	public void initPouches() {
		Random random = new Random();
		for (int i = 0; i < pouches.length; i++)
			for (int j = 0; j < pouches[i].length; j++)
				pouches[i][j] = random.nextInt(5) + 1;
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
		player = new Character("player");
		enemy = new Character("enemy");
		activeCharacter = player; /**TODO For a while**/
		oppositeCharacter = enemy;
	}
	
	/**Public methods**/
	public void setLastSelectedIngedient() {
		lastSelectedIngredient = pouches[currentCol][currentRow];
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
					if (GameControl.Instance().gameStatus == eGameStatus.ingredientDragging)
						sleep(500);
					else
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
						actionOffer();
						break;
					case attackSelected:
						attackSelected();
						break;
					case attacking:
						attacking();
						break;
					case spellSelected:
						spellSelected();
						break;
					case inventoryDisplaying:
						inventoryDisplaying();
						break;
	    		}
	    	}
	    	/**TODO maybe it will decrease memory usage**/
			try {
				sleep(50);
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
	    
	    private void actionOffer() {
			/**actionOffer**/	    	
	    }
	    
	    private void attackSelected() {
	    	/**attackSelected**/
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			GameControl.Instance().gameStatus = eGameStatus.attacking;
	    }
	    
	    private void attacking() {
			/**TODO Maybe I should make this in Character class**/
			CharacterControl.attack();			
			CharacterControl.endTurn();
			if (BoardControl.isEmpty())
				BoardControl.fillUp();
	    	GameControl.Instance().gameStatus = eGameStatus.noAction;
	    }
	    
	    private void spellSelected() {
	    	/**spellSelected**/
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    	GameControl.Instance().gameStatus = eGameStatus.inventoryDisplaying;
	    }
	    
	    private void inventoryDisplaying() {
	    	/**inventoryDisplaying**/
	    }
	    
	}

}
