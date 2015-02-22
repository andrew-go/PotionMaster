package geek.hub.potionmaster.Views;

import geek.hub.potionmaster.R;
import geek.hub.potionmaster.Controls.GameControl;
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
	
	private Drawable backgroundImage;
	private Drawable boardImage;
	
	private Drawable pouchImage;
	
	private Drawable pouchImage1;
	private Drawable pouchActiveImage;
	
	private Drawable pouchOpenedImage;
	
	private Drawable pristImage;
	
	private Drawable actionPanelImage;
	
	private Drawable btAttackImage;
	private Drawable btAttackClickedImage;
	private Drawable btSpellImage;
	private Drawable btSpellClickedImage;
	
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
	
	public Drawable getPristImage() {
		return pristImage == null 
				? pristImage = context.getResources().getDrawable(R.drawable.prist) 
				: pristImage;
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
			case attacking:
				drawActivePanel(canvas);
				drawBtAttackClicked(canvas);
				drawBtSpell(canvas);
				break;
			case spelling:
				drawActivePanel(canvas);
				drawBtAttack(canvas);
				drawBtSpellClicked(canvas);
				break;
		}

	}
	
	private void drawStandardItems(Canvas canvas) {
			drawBackGround(canvas);
			drawBoard(canvas);
			drawPouches(canvas);
			drawCharacters(canvas);
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
		getPristImage();
		pristImage.setBounds(160, 440, pristImage.getMinimumWidth() + 160,  pristImage.getMinimumHeight() + 440);
		pristImage.draw(canvas);
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
		Paint paint = new Paint();
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

}
