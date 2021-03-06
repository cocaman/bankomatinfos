package at.zweng.bankomatinfos.ui;

import static at.zweng.bankomatinfos.util.Utils.TAG;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import at.zweng.bankomatinfos.AppController;
import at.zweng.bankomatinfos.R;
import at.zweng.bankomatinfos.model.CardInfo;

/**
 * A simple fragment subclass, showing the result log tab.
 */
public class ResultLogFragment extends Fragment {

	private TextView _tvLog;
	private View _cachedView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// try to return a cached view
		if (_cachedView != null) {
			ViewParent parent = _cachedView.getParent();
			if (parent != null && parent instanceof ViewGroup) {
				((ViewGroup) parent).removeView(_cachedView);
				return _cachedView;
			}
		}
		// otherwise create new view
		View v = inflater.inflate(R.layout.fragment_result_log, container,
				false);
		_tvLog = (TextView) v.findViewById(R.id.textViewLog);

		loadDataIntoUi();
		// cache view (for quicker rebuild when we come back to this tab)
		_cachedView = v;
		return v;
	}

	/**
	 * load values into UI
	 */
	private void loadDataIntoUi() {
		AppController controller = AppController.getInstance();
		CardInfo cardInfo = controller.getCardInfo();
		if (cardInfo == null) {
			Log.e(TAG, "card info object is null");
			return;
		}
		_tvLog.setText(controller.getLog());
	}

}
