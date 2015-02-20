package geek.hub.potionmaster.Activities;

import geek.hub.potionmaster.R;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class BaseActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
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
	
	public void initComponents() {
		
	}
	
}
