package br.com.riotour.app;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import br.com.riotour.R;

public class RioTourApp extends Application {

    Tracker tracker;

    /**
     * Get Google Analytics tracker.
     * @return Tracker
     */
    private Tracker getTracker() {
        if (tracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            tracker = analytics.newTracker(getResources().getString(R.string.google_analytics_key));
            tracker.enableAdvertisingIdCollection(true);
        }
        return tracker;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
        tracker = analytics.newTracker(getResources().getString(R.string.google_analytics_key));
        tracker.enableAdvertisingIdCollection(true);
    }

    /**
     * Track the screen on Google Analytics.
     * @param screenName Screen name
     */
    public void trackScreen(String screenName) {
        getTracker().setScreenName(screenName);
        getTracker().send(new HitBuilders.ScreenViewBuilder().build());
    }
}