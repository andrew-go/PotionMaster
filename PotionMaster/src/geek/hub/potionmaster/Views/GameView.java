package geek.hub.potionmaster.Views;

import geek.hub.potionmaster.R;
import geek.hub.potionmaster.Controls.GameControl;
import android.content.Context;
import android.graphics.Canvas;
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
		drawStandartItems(canvas);
		switch(GameControl.Instance().gameStatus) {
			case noAction:
				break;
			case pouchSelecting:
				drawActivePouch(canvas);
				break;
			case pouchSelected:
				drawSelectedPouch(canvas);
				break;
			case ingredientDisplaying:
				drawBigPouch(canvas);
				break;
			case actionOffer:
				break;
			case attacking:
				break;
			case inventoryDisplaying:
				break;
		}

	}
	
	private void drawStandartItems(Canvas canvas) {
			drawBackGround(canvas);
			drawBoard(canvas);
			drawPouches(canvas);
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
		getActivePouchImage();
		pouchActiveImage.setBounds(pouchesStartPoint.x + GameControl.Instance().currentCol * 140, 
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
	
	private void getPouchesStartPoint() {
		int startMargin = 160;
		int x = boardImage.getBounds().left + startMargin;
		int y = boardImage.getBounds().top + startMargin;
		pouchesStartPoint = new Point(x, y);
	}

}
