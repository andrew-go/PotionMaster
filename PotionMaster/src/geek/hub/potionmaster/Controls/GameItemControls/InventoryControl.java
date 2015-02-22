package geek.hub.potionmaster.Controls.GameItemControls;

import geek.hub.potionmaster.Ingredients;
import geek.hub.potionmaster.Controls.GameControl;
import android.view.MotionEvent;

public class InventoryControl {

	private static InventoryControl instance;

	public static InventoryControl Instance() {
		return instance == null ? instance = new InventoryControl() : instance;
	}
	
	public int selCol = -1;
	public int selRow = -1;
	
	public boolean isIngredientSelected(MotionEvent event) {
		selCol = getColIndex((int)event.getX());
		selRow = getRowIndex((int)event.getY());
		if (GameControl.Instance().activeCharacter.ingredients[selCol][selRow] != 0)
			return true;
		return false;
	}
	
	private static int getColIndex(int x) {
		return (x - GameControl.Instance().gameView.getInventoryImage().getBounds().left/*margin*/) 
				/ (Ingredients.Instance().getIngredientImage(1).getMinimumWidth() + 40/*margin*/);
	}
	
	private static int getRowIndex(int y) {
		return (y - GameControl.Instance().gameView.getInventoryImage().getBounds().top /*margin*/) 
				/ (Ingredients.Instance().getIngredientImage(1).getMinimumHeight() + 40/*x2 pouch margin*/);
	}
	
	public int getDraggingIngredient() {
		return GameControl.Instance().activeCharacter.ingredients[selCol][selRow];
	}
	
	public static void addIngredient() {
		GameControl.Instance().activeCharacter.inventory.put(GameControl.Instance().lastSelectedIngredient, 
				(GameControl.Instance().activeCharacter.inventory.containsKey(GameControl.Instance().lastSelectedIngredient) ? GameControl.Instance().activeCharacter.inventory.get(GameControl.Instance().lastSelectedIngredient) : 0) + 1);
		
		for (int i = 0; i < GameControl.Instance().activeCharacter.ingredients.length; i++)
			for (int j = 0; j < GameControl.Instance().activeCharacter.ingredients[i].length; j++)
			{
				if (GameControl.Instance().activeCharacter.ingredients[i][j] == GameControl.Instance().lastSelectedIngredient)
					return;
				if (GameControl.Instance().activeCharacter.ingredients[i][j] == 0)
				{
					GameControl.Instance().activeCharacter.ingredients[i][j] = GameControl.Instance().lastSelectedIngredient;
					return;
				}
			}
	}
	
}
