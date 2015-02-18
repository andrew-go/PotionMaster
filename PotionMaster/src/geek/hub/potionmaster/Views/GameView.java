package geek.hub.potionmaster.Views;

import geek.hub.potionmaster.R;
import geek.hub.potionmaster.Controls.Game;
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
	
	private Drawable pouchImageNormal;
	private Drawable pouchImageSelected;
	
	public Drawable getBoardImage() {
		return boardImage == null 
				? boardImage = context.getResources().getDrawable(R.drawable.board) 
				: boardImage;
	}
	
	public Drawable getPouchNormalImage() {
		return pouchImageNormal == null 
				? pouchImageNormal = context.getResources().getDrawable(R.drawable.pouch) 
				: pouchImageNormal;
	}
	
	public Drawable getPouchImage(int i, int j) {

		if (Game.Instance().selRow != -1 && i == Game.Instance().selCol && j == Game.Instance().selRow)
			return pouchImageSelected == null 
					? pouchImageSelected = context.getResources().getDrawable(R.drawable.pouch_selected) 
					: pouchImageSelected;
		return pouchImageNormal == null 
					? pouchImageNormal = context.getResources().getDrawable(R.drawable.pouch) 
					: pouchImageNormal;
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
		drawItems(canvas);
	}
	
	private void drawItems(Canvas canvas) {
			drawBackGround(canvas);
			drawBoard(canvas);
			drawPouches(canvas);
	}
	
	private void drawBackGround(Canvas canvas) {
		if (backgroundImage == null) {
			backgroundImage = context.getResources().getDrawable(R.drawable.background);
			//Rect imageBounds = canvas.getClipBounds();  // Adjust this for where you want it
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
		int[][] pouches = Game.Instance().pouches;
		for (int i = 0; i < pouches.length; i++)
			for (int j = 0; j < pouches[i].length; j++)
			{
				if (pouches[i][j] == -1) /*pouch was clicked*/
					continue;
				pouchImage = getPouchImage(i, j); /*is not selected pouch*/
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

}
