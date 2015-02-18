package geek.hub.potionmaster.Activities;

import geek.hub.potionmaster.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
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
	
	public void btStartGameClick(View view) {
		Intent intent = new Intent(getBaseContext(), GameActivity.class);
		startActivity(intent);
	}
	
}
