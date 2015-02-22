package geek.hub.potionmaster.Activities;

import geek.hub.potionmaster.R;
import geek.hub.potionmaster.Settings.GameSettings;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.WindowManager;

public class BaseActivity extends FragmentActivity {

	protected MediaPlayer backgroundMusic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
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
		startMusic();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		stopMusic();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		stopMusic();
	}
	
	public void initComponents() {
		
	}
	
	public void startMusic() {
		if (backgroundMusic == null || !GameSettings.Instance().isMusicOn)
			return;
		backgroundMusic.start();
	}
	
	public void stopMusic() {
		if (backgroundMusic == null || !GameSettings.Instance().isMusicOn)
			return;
		backgroundMusic.stop();
	}
	
}
