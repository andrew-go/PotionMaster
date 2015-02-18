package geek.hub.potionmaster.Activities;

import geek.hub.potionmaster.R;
import geek.hub.potionmaster.Controls.Game;
import geek.hub.potionmaster.Controls.Game.DrawThread;
import geek.hub.potionmaster.Views.GameView;
import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class GameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		Game.Instance().initPouches();
		Game.Instance().gameView = (GameView) findViewById(R.id.gameView);
		Game.Instance().drawThread = new DrawThread(Game.Instance().gameView);
		Game.Instance().drawThread.start();
		Game.Instance().gameView.setOnTouchListener(new OnTouchListener() {			
			
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				if (!Game.Instance().isOnBoard(event))
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
	
	@Override
	protected void onResume() {
		super.onResume();
		View decorView = getWindow().getDecorView();
		decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
		                              | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
		                              | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
		                              | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
		                              | View.SYSTEM_UI_FLAG_FULLSCREEN
		                              | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
	}
	
	
	
	private void actionUp(MotionEvent event) {
		Game.Instance().removeSelectedPouch((int)event.getX(), (int)event.getY());		
	}
	

	
	private void actionMove(MotionEvent event) {
		Game.Instance().markSelectedPouch((int)event.getX(), (int)event.getY());
	}

}
