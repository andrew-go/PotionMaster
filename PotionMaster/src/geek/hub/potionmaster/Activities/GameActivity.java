package geek.hub.potionmaster.Activities;

import geek.hub.potionmaster.R;
import geek.hub.potionmaster.Controls.GameControl;
import geek.hub.potionmaster.Controls.GameControl.eGameStatus;
import geek.hub.potionmaster.Controls.GameItemControls.ActionPanelControl;
import geek.hub.potionmaster.Controls.GameItemControls.BoardControl;
import geek.hub.potionmaster.Controls.GameItemControls.InventoryControl;
import geek.hub.potionmaster.Views.GameView;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class GameActivity extends BaseActivity {
	
	/**Virtual methods**/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		initComponents();	
	}
	
	@Override
	public void initComponents() {
		BoardControl.fillUp();
		initGameView();
		GameControl.Instance().initDrawThread();
		GameControl.Instance().initGameThread();
		GameControl.Instance().initCharacters();
		backgroundMusic = MediaPlayer.create(this, R.raw.game_music);
	}
	
	/**Events**/
	
	private void actionUp(MotionEvent event) {
		if (!BoardControl.isPouchExist((int)event.getX(), (int)event.getY()))
			return;
		if (GameControl.Instance().gameStatus == eGameStatus.noAction || GameControl.Instance().gameStatus == eGameStatus.pouchSelecting)
			GameControl.Instance().gameStatus = eGameStatus.pouchSelected;	
	}
	
	private void actionMove(MotionEvent event) {
		if (!BoardControl.isPouchExist((int)event.getX(), (int)event.getY()))
			return;		
	}
	
	/**Internal methods**/
	
	private void initGameView() {
		GameControl.Instance().gameView = (GameView) findViewById(R.id.gameView);
		GameControl.Instance().gameView.setOnTouchListener(new OnTouchListener() {			
			
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				GameControl.Instance().curX = (int) event.getX();
				GameControl.Instance().curY = (int) event.getY();
				switch (GameControl.Instance().gameStatus) {
					case noAction:
					case pouchSelecting:
						if (!BoardControl.isTouchOn(event))
							return false;
						
						break;
					case pouchSelected:
						return false;
					case ingredientDisplaying:
						GameControl.Instance().gameStatus = eGameStatus.actionOffer;
							return false;
					case actionOffer:
						if (ActionPanelControl.isBtAttackClicked(event)) 
						{
							GameControl.Instance().gameStatus = eGameStatus.attackSelected;
							return false;
						}
						if (ActionPanelControl.isBtSpellClicked(event)) 
						{
							GameControl.Instance().gameStatus = eGameStatus.spellSelected;
							return false;
						}
						return false;
					case attackSelected:
					case spellSelected:
					case attacking:
						return false;
					case inventoryDisplaying:
						if (event.getAction() == MotionEvent.ACTION_DOWN && InventoryControl.Instance().isIngredientSelected(event)) {
							GameControl.Instance().gameStatus = eGameStatus.ingredientDragging;
							return true;
						}
					case ingredientDragging:
						if (event.getAction() == MotionEvent.ACTION_UP) {
							GameControl.Instance().gameStatus = eGameStatus.inventoryDisplaying;
							return true;
						}
//						if (event.getAction() == MotionEvent.ACTION_MOVE) {
//							GameControl.Instance().gameStatus = eGameStatus.inventoryDisplaying;
//							return true;
//						}
						return false;
					default:
						break;
				}
				
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:					
					break;
				case MotionEvent.ACTION_UP:
					actionUp(event);
					view.performClick();
					break;
				case MotionEvent.ACTION_MOVE:
					actionMove(event);
					break;
				default:
					return false;
				}
				return true;
			}
		});
	}

}
