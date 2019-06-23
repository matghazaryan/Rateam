package am.rate.ui.fragments.base.base;

import android.app.Application;

import dmutils.com.dmutils.general.DMUtilMemory;
import dmutils.com.dmutils.pref.DMUtilPrefsCacheManager;

/**
 * BaseApplication abstract class for initialize Preference cache manager, free memory when memory is low and
 * get application configs
 */

public abstract class DMBaseApplication<Utils extends DMBaseSupportUtils, ViewUtils extends DMBaseSupportViewUtils, Configs extends DMBaseApplicationConfigs> extends Application {

    /**
     * Get application configs for use in the fragments, viewModels and activities
     *
     * @return Object of class which extends BaseApplicationConfigs abstract class
     */
    public abstract Configs getApplicationConfigs();

    public abstract Utils getSupportUtils();

    public abstract ViewUtils getSupportViewUtils();

    @Override
    public void onCreate() {
        super.onCreate();

        initialize();
    }

    @Override
    public void onLowMemory() {
        DMUtilMemory.freeMemory();
        super.onLowMemory();
    }

    private void initialize() {
        DMUtilPrefsCacheManager.getInstance().initialize(getApplicationContext());
    }
}
