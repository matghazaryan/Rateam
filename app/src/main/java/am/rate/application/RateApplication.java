package am.rate.application;

import am.rate.core.utils.RateSupportUtils;
import am.rate.core.utils.RateSupportViewUtils;
import am.rate.ui.fragments.base.base.DMBaseApplication;
import am.rate.ui.fragments.base.base.DMBaseApplicationConfigs;

public class RateApplication extends DMBaseApplication {
    @Override
    public DMBaseApplicationConfigs getApplicationConfigs() {
        return RateApplicationConfigs.getInstance();
    }

    @Override
    public RateSupportUtils getSupportUtils() {
        return RateSupportUtils.getInstance();
    }

    @Override
    public RateSupportViewUtils getSupportViewUtils() {
        return RateSupportViewUtils.getInstance();
    }
}
