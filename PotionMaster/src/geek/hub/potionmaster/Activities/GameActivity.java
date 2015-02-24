package geek.hub.potionmaster.Activities;

import geek.hub.potionmaster.R;
import geek.hub.potionmaster.Controls.GameControl;
import geek.hub.potionmaster.Controls.GameControl.eGameStatus;
import geek.hub.potionmaster.Controls.GameItemControls.ActionPanelControl;
import geek.hub.potionmaster.Controls.GameItemControls.BoardControl;
import geek.hub.potionmaster.Controls.GameItemControls.CombinationBoardControl;
import geek.hub.potionmaster.Controls.GameItemControls.InventoryControl;
import geek.hub.potionmaster.Controls.GameItemControls.SpellPanelControl;
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
		if (!BoardControl.Instance().isPouchExist((int)event.getX(), (int)event.getY()))
			return;
		if (GameControl.Instance().gameStatus == eGameStatus.noAction || GameControl.Instance().gameStatus == eGameStatus.pouchSelecting)
			GameControl.Instance().gameStatus = eGameStatus.pouchSelected;	
	}
	
	private void actionMove(MotionEvent event) {
		if (!BoardControl.Instance().isPouchExist((int)event.getX(), (int)event.getY()))
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
						if (!BoardControl.Instance().isOn(event))
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
					case spellPanelDisplaying:
						if (event.getAction() == MotionEvent.ACTION_DOWN && InventoryControl.Instance().isOn(event) 
							&& InventoryControl.Instance().isIngredientSelected(event)) {
							GameControl.Instance().gameStatus = eGameStatus.ingredientDragging;
							return true;
						}
						if (event.getAction() == MotionEvent.ACTION_DOWN && SpellPanelControl.isBtCancelClicked(event)) {
							GameControl.Instance().gameStatus = eGameStatus.cancelSelected;
							return true;
						}
						if (event.getAction() == MotionEvent.ACTION_DOWN && SpellPanelControl.isBtCastClicked(event)) {
							GameControl.Instance().gameStatus = eGameStatus.castSelected;
							return true;
						}
					case ingredientDragging:
						if (event.getAction() == MotionEvent.ACTION_UP) {
							if (CombinationBoardControl.Instance().isOn(event)) {								
								GameControl.Instance().gameStatus = eGameStatus.ingredientPutting;
								CombinationBoardControl.Instance().putIngredient(event);
								return true;
							}
							GameControl.Instance().gameStatus = eGameStatus.spellPanelDisplaying;
							return true;
						}
//					case ingredientPutting:
//						CombinationBoardControl.Instance().putIngredient(event);
//						GameControl.Instance().gameStatus = eGameStatus.spellPanelDisplaying;
//						return false;
					default:
						return false;
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
