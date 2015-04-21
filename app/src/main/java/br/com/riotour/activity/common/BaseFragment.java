package br.com.riotour.activity.common;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import br.com.riotour.app.RioTourApp;

/**
 * Fragment responsible for common fragment attributes and methods.
 */
public abstract class BaseFragment extends Fragment {

    /**
     * Get the screen name to be tracked on Google Analytics.
     * @return Screen name
     */
    protected abstract String getScreenName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trackScreen();
    }

    /**
     * Track screen on Google Analytics.
     */
    private void trackScreen() {
        RioTourApp app = (RioTourApp) getActivity().getApplication();
        app.trackScreen(getScreenName());
    }

}
