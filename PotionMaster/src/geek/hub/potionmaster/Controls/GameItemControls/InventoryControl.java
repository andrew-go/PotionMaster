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
	
	public int activeIngredientSize;
	public int activeInventoryLeftBound;
	public int activeInventoryTopBound;
	public int activeInventorySize;
	
	public InventoryControl() {
		activeIngredientSize = Ingredients.Instance().getIngredientImage(1).getMinimumWidth() + 20;
		activeInventoryLeftBound = GameControl.Instance().gameView.getInventoryImage().getBounds().left + 10;
		activeInventoryTopBound = GameControl.Instance().gameView.getInventoryImage().getBounds().top + 10;
		activeInventorySize = activeIngredientSize * 5;
	}
	
	public boolean isIngredientSelected(MotionEvent event) {
		selCol = getColIndex((int)event.getX());
		selRow = getRowIndex((int)event.getY());
		if (GameControl.Instance().activeCharacter.ingredients[selRow][selCol] != 0)
			return true;
		return false;
	}
	
	private int getColIndex(int x) {
		return (x - activeInventoryLeftBound) / activeIngredientSize;
	}
	
	private int getRowIndex(int y) {
		return (y - activeInventoryTopBound) / activeIngredientSize;
	}
	
	public int getDraggingIngredient() {
		return GameControl.Instance().activeCharacter.ingredients[selRow][selCol];
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
	
	public boolean isOn(MotionEvent event) {  
		if ((event.getX() < activeInventoryLeftBound)
				|| (event.getX() > activeInventoryLeftBound + activeInventorySize)
				|| (event.getY() < activeInventoryTopBound)
				|| (event.getY() > activeInventoryTopBound + activeInventorySize))
			return false;
		return true;
	}
	
}
