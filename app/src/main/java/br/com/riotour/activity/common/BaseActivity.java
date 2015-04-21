package br.com.riotour.activity.common;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import br.com.riotour.app.RioTourApp;

/**
 * Activity responsible for common activity attributes and methods.
 */
public abstract class BaseActivity extends ActionBarActivity {

    /**
     * Get the screen name to be tracked on Google Analytics.
     * @return Screen name
     */
    protected abstract String getScreenName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trackScreen();
    }

    /**
     * Track screen on Google Analytics.
     */
    private void trackScreen() {
        RioTourApp app = (RioTourApp) getApplication();
        app.trackScreen(getScreenName());
    }
}
