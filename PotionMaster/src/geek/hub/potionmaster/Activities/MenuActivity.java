package geek.hub.potionmaster.Activities;

import geek.hub.potionmaster.R;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends BaseActivity {

	/**Members**/
	
	MediaPlayer btClickSound;
	
	/**Virtual methods**/
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		initComponents();
		startMusic();
	}
	
	@Override
	public void initComponents() {
		backgroundMusic = MediaPlayer.create(this, R.raw.menu_music);
		btClickSound  = MediaPlayer.create(this, R.raw.menu_bt_click);
	}
	
	/**Events**/
	
	public void btStartGameClick(View view) {
		Intent intent = new Intent(getBaseContext(), GameActivity.class);
		startActivity(intent);
		btClickSound.start();
		stopMusic();
	}
	
}
