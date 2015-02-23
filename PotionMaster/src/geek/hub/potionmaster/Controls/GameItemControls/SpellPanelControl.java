package geek.hub.potionmaster.Controls.GameItemControls;

import geek.hub.potionmaster.Controls.GameControl;
import android.view.MotionEvent;

public class SpellPanelControl {

	public static boolean isBtCastClicked(MotionEvent event) {
		if ((event.getX() < GameControl.Instance().gameView.getBtCastImage().getBounds().left)
				|| (event.getX() > GameControl.Instance().gameView.getBtCastImage().getBounds().right)
				|| (event.getY() < GameControl.Instance().gameView.getBtCastImage().getBounds().top)
				|| (event.getY() > GameControl.Instance().gameView.getBtCastImage().getBounds().bottom))
			return false;
		return true;
	}
	
	public static boolean isBtCancelClicked(MotionEvent event) {
		if ((event.getX() < GameControl.Instance().gameView.getBtCancelImage().getBounds().left)
				|| (event.getX() > GameControl.Instance().gameView.getBtCancelImage().getBounds().right)
				|| (event.getY() < GameControl.Instance().gameView.getBtCancelImage().getBounds().top)
				|| (event.getY() > GameControl.Instance().gameView.getBtCancelImage().getBounds().bottom))
			return false;
		return true;
	}

}
