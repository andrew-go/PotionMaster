package geek.hub.potionmaster.Activities;

import geek.hub.potionmaster.R;
import geek.hub.potionmaster.Controls.GameControl;
import geek.hub.potionmaster.Controls.GameControl.eGameStatus;
import geek.hub.potionmaster.Controls.GameItemsControls.BoardControl;
import geek.hub.potionmaster.Views.GameView;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class GameActivity extends BaseActivity {
	
	/**Members**/
	
	MediaPlayer backgroundMusic;
	
	/**Virtual methods**/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		initComponents();	
	}
	
	@Override
	public void initComponents() {
		GameControl.Instance().initPouches();
		initGameView();
		GameControl.Instance().initDrawThread();
		GameControl.Instance().initGameThread();
		backgroundMusic = MediaPlayer.create(this, R.raw.game_music);
		//backgroundMusic.start();	
	}
	
	@Override
	public void onBackPressed() {
		backgroundMusic.stop();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		//backgroundMusic.start();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		backgroundMusic.stop();
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
		if (GameControl.Instance().gameStatus != eGameStatus.pouchSelecting)
			GameControl.Instance().gameStatus = eGameStatus.pouchSelecting;
		
	}
	
	/**Internal methods**/
	
	private void initGameView() {
		GameControl.Instance().gameView = (GameView) findViewById(R.id.gameView);
		GameControl.Instance().gameView.setOnTouchListener(new OnTouchListener() {			
			
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				if (!BoardControl.isTouchOn(event))
					return false;
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
