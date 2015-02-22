package geek.hub.potionmaster.Controls.GameItemControls;

import geek.hub.potionmaster.Controls.GameControl;
import android.view.MotionEvent;

public class ActionPanel {

	private static ActionPanel instance;

	public static ActionPanel Instance() {
		return instance == null ? instance = new ActionPanel() : instance;
	}
	
	public static boolean isBtAttackClicked(MotionEvent event) {
		if ((event.getX() < GameControl.Instance().gameView.getBtAttackImage().getBounds().left)
				|| (event.getX() > GameControl.Instance().gameView.getBtAttackImage().getBounds().right)
				|| (event.getY() < GameControl.Instance().gameView.getBtAttackImage().getBounds().top)
				|| (event.getY() > GameControl.Instance().gameView.getBtAttackImage().getBounds().bottom))
			return false;
		return true;
	}
	
	public static boolean isBtSpellClicked(MotionEvent event) {
		if ((event.getX() < GameControl.Instance().gameView.getBtSpellImage().getBounds().left)
				|| (event.getX() > GameControl.Instance().gameView.getBtSpellImage().getBounds().right)
				|| (event.getY() < GameControl.Instance().gameView.getBtSpellImage().getBounds().top)
				|| (event.getY() > GameControl.Instance().gameView.getBtSpellImage().getBounds().bottom))
			return false;
		return true;
	}
	
}
