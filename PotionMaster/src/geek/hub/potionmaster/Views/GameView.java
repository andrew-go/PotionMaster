package geek.hub.potionmaster.Views;

import geek.hub.potionmaster.Ingredients;
import geek.hub.potionmaster.R;
import geek.hub.potionmaster.Controls.GameControl;
import geek.hub.potionmaster.Controls.GameControl.eGameStatus;
import geek.hub.potionmaster.Controls.GameItemControls.CombinationBoardControl;
import geek.hub.potionmaster.Controls.GameItemControls.InventoryControl;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class GameView extends View {

	Context context;
	
	Paint paint = new Paint();
	
	private Drawable backgroundImage;
	private Drawable boardImage;
	
	private Drawable pouchImage;
	
	private Drawable pouchImage1;
	private Drawable pouchActiveImage;
	
	private Drawable pouchOpenedImage;
	
	private Drawable playerImage;
	private Drawable enemyImage;
	
	private Drawable actionPanelImage;
	
	private Drawable btAttackImage;
	private Drawable btAttackClickedImage;
	private Drawable btSpellImage;
	private Drawable btSpellClickedImage;
	
	private Drawable spellPanelImage;
	
	private Drawable inventoryImage;
	
	private Drawable combinationBoardImage;
	
	private Drawable ingredientImage;
	
	private Drawable btCastImage;
	private Drawable btCastClickedImage;
	private Drawable btCancelImage;
	private Drawable btCancelClickedImage;
	
	public Drawable getBoardImage() {
		return boardImage == null 
				? boardImage = context.getResources().getDrawable(R.drawable.board) 
				: boardImage;
	}
	
	public Drawable getPouchNormalImage() {
		return pouchImage1 == null 
				? pouchImage1 = context.getResources().getDrawable(R.drawable.pouch) 
				: pouchImage1;
	}
	
	public Drawable getPouchImage() {
		return pouchImage1 == null 
					? pouchImage1 = context.getResources().getDrawable(R.drawable.pouch) 
					: pouchImage1;
	}
	
	public Drawable getActivePouchImage() {
			return pouchActiveImage == null 
					? pouchActiveImage = context.getResources().getDrawable(R.drawable.pouch_active) 
					: pouchActiveImage;
	}
	
	public Drawable getOpenedPouchImage() {
		return pouchOpenedImage == null 
				? pouchOpenedImage = context.getResources().getDrawable(R.drawable.pouch_opened) 
				: pouchOpenedImage;
	}
	
	public Drawable getPlayerImage() {
		return playerImage == null 
				? playerImage = context.getResources().getDrawable(R.drawable.player) 
				: playerImage;
	}
	
	public Drawable getEnemyImage() {
		return enemyImage == null 
				? enemyImage = context.getResources().getDrawable(R.drawable.enemy) 
				: enemyImage;
	}
	
	public Drawable getActionPanelImage() {
		return actionPanelImage == null 
				? actionPanelImage = context.getResources().getDrawable(R.drawable.action_panel) 
				: actionPanelImage;
	}
	
	public Drawable getBtAttackImage() {
		return btAttackImage == null 
					? btAttackImage = context.getResources().getDrawable(R.drawable.bt_attack) 
					: btAttackImage;
	}
	
	public Drawable getBtAttackClickedImage() {
			return btAttackClickedImage == null 
					? btAttackClickedImage = context.getResources().getDrawable(R.drawable.bt_attack_clicked) 
					: btAttackClickedImage;
	}
	
	public Drawable getBtSpellImage() {
		return btSpellImage == null 
				? btSpellImage = context.getResources().getDrawable(R.drawable.bt_spell) 
				: btSpellImage;
	}
	
	public Drawable getBtSpellClickedImage() {
		return btSpellClickedImage == null 
				? btSpellClickedImage = context.getResources().getDrawable(R.drawable.bt_spell_clicked) 
				: btSpellClickedImage;
	}
	
	public Drawable getSpellPanelImage() {
		return spellPanelImage == null 
				? spellPanelImage = context.getResources().getDrawable(R.drawable.spell_panel) 
				: spellPanelImage;
	}
	
	public Drawable getInventoryImage() {
		return inventoryImage == null 
				? inventoryImage = context.getResources().getDrawable(R.drawable.inventory) 
				: inventoryImage;
	}
	
	public Drawable getCombinationBoardImage() {
		return combinationBoardImage == null 
				? combinationBoardImage = context.getResources().getDrawable(R.drawable.spell_board) 
				: combinationBoardImage;
	}
	
	public Drawable getBtCastImage() {
		return btCastImage == null 
				? btCastImage = context.getResources().getDrawable(R.drawable.bt_cast) 
				: btCastImage;
	}
	
	public Drawable getBtCastClickedImage() {
		return btCastClickedImage == null 
				? btCastClickedImage = context.getResources().getDrawable(R.drawable.bt_cast_clicked) 
				: btCastClickedImage;
	}
	
	public Drawable getBtCancelImage() {
		return btCancelImage == null 
				? btCancelImage = context.getResources().getDrawable(R.drawable.bt_cancel) 
				: btCancelImage;
	}
	
	public Drawable getBtCancelClickedImage() {
		return btCancelClickedImage == null 
				? btCancelClickedImage = context.getResources().getDrawable(R.drawable.bt_cancel_clicked) 
				: btCancelClickedImage;
	}	
	
	public Point pouchesStartPoint; 
	
	public GameView(Context context) {
		super(context);
		this.context = context;  
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context; 
	}

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context; 
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawStandardItems(canvas);
		switch(GameControl.Instance().gameStatus) {
			case noAction:  /**TODO make drawing standard items only once**/
				break;
			case pouchSelecting:
				drawActivePouch(canvas);
				break;
			case pouchSelected:
				drawSelectedPouch(canvas);
				break;
			case ingredientDisplaying:
				drawBigPouch(canvas);
				drawSelectedIngredient(canvas);
				break;
			case actionOffer:
				drawActivePanel(canvas);
				drawBtAttack(canvas);
				drawBtSpell(canvas);
				break;
			case attackSelected:
				drawActivePanel(canvas);
				drawBtAttackClicked(canvas);
				drawBtSpell(canvas);
				break;
			case attacking:
				break;
			case spellSelected:				
				drawActivePanel(canvas);
				drawBtAttack(canvas);
				drawBtSpellClicked(canvas);
				break;
			case spellPanelDisplaying:
				drawSpellPanelItems(canvas);
				break;
			case ingredientDragging:
				drawSpellPanelItems(canvas);
				drawDraggingIngredient(canvas);
				break;
			case ingredientPutting:
				drawSpellPanelItems(canvas);
				break;
			case cancelSelected:
				drawSpellPanelItems(canvas);
				break;
			case castSelected:
				drawSpellPanelItems(canvas);
				break;
			case casting:
				break;
			default:
				break;
		}

	}
	
	private void drawStandardItems(Canvas canvas) {
			drawBackGround(canvas);
			drawBoard(canvas);
			drawPouches(canvas);
			drawCharacters(canvas);
			drawHealthPoints(canvas);
	}
	
	private void drawBackGround(Canvas canvas) {
		if (backgroundImage == null) {
			backgroundImage = context.getResources().getDrawable(R.drawable.background);
	        backgroundImage.setBounds(canvas.getClipBounds());        
		}
		backgroundImage.draw(canvas);
	}
	
	private void drawBoard(Canvas canvas) {
		if (boardImage == null) {
			boardImage = context.getResources().getDrawable(R.drawable.board);
			Rect clipBounds = canvas.getClipBounds();
			int x = (clipBounds.width() / 2) - (boardImage.getMinimumWidth() / 2);
			int y = (clipBounds.height() / 2) - (boardImage.getMinimumHeight() / 2);
			boardImage.setBounds(x, y, x + boardImage.getMinimumWidth(), y + boardImage.getMinimumHeight());
		}
		boardImage.draw(canvas);
	}
	
	private void drawPouches(Canvas canvas) {
		if (pouchesStartPoint == null)
			getPouchesStartPoint();
		int[][] pouches = GameControl.Instance().pouches;
		for (int i = 0; i < pouches.length; i++)
			for (int j = 0; j < pouches[i].length; j++)
			{
				pouchImage = getPouchImage();
				if (pouches[i][j] == -1) /*pouch has been removed*/
					continue;				
				pouchImage.setBounds(pouchesStartPoint.x + i * 140, 
						pouchesStartPoint.y + j * 140, 
						pouchesStartPoint.x + pouchImage.getMinimumWidth() + i * 140, 
						pouchesStartPoint.y + pouchImage.getMinimumHeight() + j * 140);
				pouchImage.draw(canvas);
			}
	}
	
	private void getPouchesStartPoint() {
		int startMargin = 160;
		int x = boardImage.getBounds().left + startMargin;
		int y = boardImage.getBounds().top + startMargin;
		pouchesStartPoint = new Point(x, y);
	}
	
	/**TODO Remove this bullshit numbers**/
	
	private void drawCharacters(Canvas canvas) {
		drawPlayer(canvas);
		drawEnemy(canvas);
	}
	
	private void drawPlayer(Canvas canvas) {
		getPlayerImage();
		playerImage.setBounds(160, 440, playerImage.getMinimumWidth() + 160,  playerImage.getMinimumHeight() + 440);
		playerImage.draw(canvas);
	}
	
	private void drawEnemy(Canvas canvas) {
		getEnemyImage();
		enemyImage.setBounds(1520, 440, enemyImage.getMinimumWidth() + 1520,  enemyImage.getMinimumHeight() + 440);
		enemyImage.draw(canvas);
	}
	
	private void drawHealthPoints(Canvas canvas) {
		paint.setColor(Color.YELLOW);
		paint.setTextSize(50);
		canvas.drawText(String.valueOf(GameControl.Instance().player.currentHealth), 0, 40, paint);
		paint.setColor(Color.RED);
		canvas.drawRect(0, 40, (400 * ((float) GameControl.Instance().player.currentHealth / (float) GameControl.Instance().player.health)), 80, paint);
		
		paint.setColor(Color.YELLOW);
		paint.setTextSize(50);
		canvas.drawText(String.valueOf(GameControl.Instance().enemy.currentHealth), 1840, 40, paint);
		paint.setColor(Color.RED);
		canvas.drawRect(1920 - (400 * ((float) GameControl.Instance().enemy.currentHealth / (float) GameControl.Instance().enemy.health)), 40, 1920, 80, paint);
	}
	
	private void drawActivePouch(Canvas canvas) {
		if (GameControl.Instance().currentRow == -1 || GameControl.Instance().currentCol == -1)
			return;
		if (GameControl.Instance().pouches[GameControl.Instance().currentCol][GameControl.Instance().currentRow] == -1)
			return;
		getActivePouchImage();
		pouchActiveImage.setBounds(pouchesStartPoint.x + GameControl.Instance().currentCol * 140, 
				pouchesStartPoint.y + GameControl.Instance().currentRow * 140, 
				pouchesStartPoint.x + pouchImage.getMinimumWidth() + GameControl.Instance().currentCol * 140, 
				pouchesStartPoint.y + pouchImage.getMinimumHeight() + GameControl.Instance().currentRow * 140);
		pouchActiveImage.draw(canvas);
	}
	
	private void drawSelectedPouch(Canvas canvas) {
		getActivePouchImage(); /**TODO Check this, maybe it would better to check member on null**/
		pouchActiveImage.setBounds(pouchesStartPoint.x + GameControl.Instance().currentCol * 140, /**TODO Also maybe it should be better to set bounds in init image method**/
				pouchesStartPoint.y + GameControl.Instance().currentRow * 140, 
				pouchesStartPoint.x + pouchImage.getMinimumWidth() + GameControl.Instance().currentCol * 140, 
				pouchesStartPoint.y + pouchImage.getMinimumHeight() + GameControl.Instance().currentRow * 140);
		pouchActiveImage.draw(canvas);
	}
	
	private void drawBigPouch(Canvas canvas) {
		getOpenedPouchImage();
		pouchOpenedImage.setBounds(pouchesStartPoint.x + 40, 
				pouchesStartPoint.y + 40, 
				pouchesStartPoint.x + pouchOpenedImage.getMinimumWidth() + 40, 
				pouchesStartPoint.y + pouchOpenedImage.getMinimumHeight() + 40);
		pouchOpenedImage.draw(canvas);
	}
	
	private void drawSelectedIngredient(Canvas canvas) {
		paint.setTextSize(50);
		paint.setColor(Color.RED);
		canvas.drawText(String.format("%d", GameControl.Instance().lastSelectedIngredient), 
				pouchesStartPoint.x + 400, pouchesStartPoint.y + 400, paint);
	}
	
	private void drawActivePanel(Canvas canvas) {
		getActionPanelImage();
		actionPanelImage.setBounds(520, 
				400, 
				actionPanelImage.getMinimumWidth() + 520, 
				actionPanelImage.getMinimumHeight() + 400);
		actionPanelImage.draw(canvas);
	}
	
	private void drawBtAttack(Canvas canvas) {
		getBtAttackImage();
		btAttackImage.setBounds(580, 
				460, 
				btAttackImage.getMinimumWidth() + 580, 
				btAttackImage.getMinimumHeight() + 460);
		btAttackImage.draw(canvas);
	}
	private void drawBtAttackClicked(Canvas canvas) {
		getBtAttackClickedImage();
		btAttackClickedImage.setBounds(580, 
				460, 
				btAttackClickedImage.getMinimumWidth() + 580, 
				btAttackClickedImage.getMinimumHeight() + 460);
		btAttackClickedImage.draw(canvas);
	}
	
	private void drawBtSpell(Canvas canvas) {
		getBtSpellImage();
		btSpellImage.setBounds(1180, 
				460, 
				btSpellImage.getMinimumWidth() + 1180, 
				btSpellImage.getMinimumHeight() + 460);
		btSpellImage.draw(canvas);
	}
	
	private void drawBtSpellClicked(Canvas canvas) {
		getBtSpellClickedImage();
		btSpellClickedImage.setBounds(1180,
				460, 
				btSpellClickedImage.getMinimumWidth() + 1180, 
				btSpellClickedImage.getMinimumHeight() + 460);
		btSpellClickedImage.draw(canvas);
	}
	
	private void drawSpellPanelItems(Canvas canvas) {
		drawSpellPanel(canvas);
		drawInventory(canvas);
		drawCombinationBoard(canvas);
		drawIngredients(canvas);
		drawCombinatedIngredients(canvas);
		if (GameControl.Instance().gameStatus == eGameStatus.castSelected)
			drawBtCastClicked(canvas);
		else
			drawBtCast(canvas);
		if (GameControl.Instance().gameStatus == eGameStatus.cancelSelected)
			drawBtCancelClicked(canvas);
		else
			drawBtCancel(canvas);
	}
	
	private void drawSpellPanel(Canvas canvas) {
		getSpellPanelImage();
		spellPanelImage.setBounds(180, 
				60, 
				spellPanelImage.getMinimumWidth() + 180, 
				spellPanelImage.getMinimumHeight() + 60);
		spellPanelImage.draw(canvas);
	}
	
	private void drawInventory(Canvas canvas) {
		getInventoryImage();		
		inventoryImage.setBounds(spellPanelImage.getBounds().left + 20, 
				spellPanelImage.getBounds().top + 20, 
				inventoryImage.getMinimumWidth() + spellPanelImage.getBounds().left + 20, 
				inventoryImage.getMinimumHeight() + spellPanelImage.getBounds().top + 20);
		inventoryImage.draw(canvas);
	}	
	
	private void drawCombinationBoard(Canvas canvas) {
		getCombinationBoardImage();
		combinationBoardImage.setBounds(inventoryImage.getBounds().right + 40, 
				260, 
				combinationBoardImage.getMinimumWidth() + inventoryImage.getBounds().right + 40, 
				combinationBoardImage.getMinimumHeight() + 260);
		combinationBoardImage.draw(canvas);
	}	
	
	
	/**TODO stop redrawing till touch**/
	private void drawIngredients(Canvas canvas) {		
		for (int i = 0; i < GameControl.Instance().activeCharacter.ingredients.length; i++)
			for (int j = 0; j < GameControl.Instance().activeCharacter.ingredients[i].length; j++) 
			{
				if (GameControl.Instance().activeCharacter.ingredients[i][j] == 0)
					return;					
				ingredientImage = Ingredients.Instance().getIngredientImage(GameControl.Instance().activeCharacter.ingredients[i][j]);
				ingredientImage.setBounds(InventoryControl.Instance().activeInventoryLeftBound + 10 + j * InventoryControl.Instance().activeIngredientSize, 
						InventoryControl.Instance().activeInventoryTopBound + 10 + i * InventoryControl.Instance().activeIngredientSize, 
						ingredientImage.getMinimumWidth() + InventoryControl.Instance().activeInventoryLeftBound + 10 + j * InventoryControl.Instance().activeIngredientSize, 
						ingredientImage.getMinimumHeight() + InventoryControl.Instance().activeInventoryTopBound + 10 + i * InventoryControl.Instance().activeIngredientSize);
				ingredientImage.draw(canvas);
			}
	}
	
	private void drawDraggingIngredient(Canvas canvas) {
		InventoryControl.Instance().getDraggingIngredient();
		ingredientImage = Ingredients.Instance().getIngredientImage(GameControl.Instance().activeCharacter.ingredients[InventoryControl.Instance().selRow][InventoryControl.Instance().selCol]);
		ingredientImage.setBounds(GameControl.Instance().curX - ingredientImage.getMinimumWidth(), 
				GameControl.Instance().curY - ingredientImage.getMinimumHeight(), 
				GameControl.Instance().curX, 
				GameControl.Instance().curY);
		ingredientImage.draw(canvas);
	}
	
	private void drawCombinatedIngredients(Canvas canvas) {		
		for (int i = 0; i < CombinationBoardControl.Instance().combinationBoard.length; i++)
			for (int j = 0; j < CombinationBoardControl.Instance().combinationBoard[i].length; j++) 
			{
				if (CombinationBoardControl.Instance().combinationBoard[i][j] == 0)
					continue;					
				ingredientImage = Ingredients.Instance().getIngredientImage(CombinationBoardControl.Instance().combinationBoard[i][j]);
				ingredientImage.setBounds(CombinationBoardControl.Instance().activeCombinationBoardLeftBound + 10 + i * CombinationBoardControl.Instance().activeIngredientSize, 
						CombinationBoardControl.Instance().activeCombinationBoardTopBound + 10 + j * CombinationBoardControl.Instance().activeIngredientSize, 
						ingredientImage.getMinimumWidth() + CombinationBoardControl.Instance().activeCombinationBoardLeftBound + 10 + i * CombinationBoardControl.Instance().activeIngredientSize, 
						ingredientImage.getMinimumHeight() + CombinationBoardControl.Instance().activeCombinationBoardTopBound + 10 + j * CombinationBoardControl.Instance().activeIngredientSize);
				ingredientImage.draw(canvas);
			}
	}
	
	private void drawBtCast(Canvas canvas) {
		getBtCastImage();
		btCastImage.setBounds(1200, 
				100, 
				btCastImage.getMinimumWidth() + 1200, 
				btCastImage.getMinimumHeight() + 100);
		btCastImage.draw(canvas);
	}
	
	private void drawBtCancel(Canvas canvas) {
		getBtCancelImage();
		btCancelImage.setBounds(1200, 
				840, 
				btCancelImage.getMinimumWidth() + 1200, 
				btCancelImage.getMinimumHeight() + 840);
		btCancelImage.draw(canvas);
	}
	
	private void drawBtCastClicked(Canvas canvas) {
		getBtCastClickedImage();
		btCastClickedImage.setBounds(1200, 
				100, 
				btCastClickedImage.getMinimumWidth() + 1200, 
				btCastClickedImage.getMinimumHeight() + 100);
		btCastClickedImage.draw(canvas);
	}
	
	private void drawBtCancelClicked(Canvas canvas) {
		getBtCancelClickedImage();
		btCancelClickedImage.setBounds(1200, 
				840, 
				btCancelClickedImage.getMinimumWidth() + 1200, 
				btCancelClickedImage.getMinimumHeight() + 840);
		btCancelClickedImage.draw(canvas);
	}
	
}
