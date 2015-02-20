package geek.hub.potionmaster.Activities;

import geek.hub.potionmaster.R;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends BaseActivity {

	/**Members**/
	
	MediaPlayer backgroundMusic;
	MediaPlayer click_sound;
	
	/**Virtual methods**/
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		initComponents();		
	}
	
	@Override
	public void initComponents() {
		backgroundMusic = MediaPlayer.create(this, R.raw.menu_music);
		click_sound  = MediaPlayer.create(this, R.raw.menu_button_click);
		backgroundMusic.start();
	}

	@Override
	protected void onResume() {
		super.onResume();
		backgroundMusic.start();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		backgroundMusic.stop();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		backgroundMusic.stop();
	}
	
	/**Events**/
	
	public void btStartGameClick(View view) {
		click_sound.start();
		Intent intent = new Intent(getBaseContext(), GameActivity.class);
		startActivity(intent);
		backgroundMusic.stop();
	}
	
}
