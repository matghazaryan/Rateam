package am.rate.core.respository.preferance;

import am.rate.core.constants.IRateConstants;
import dmutils.com.dmutils.pref.DMUtilPrefsCacheManager;

public class RatePrefUtils {

    public static String getCurrency() {
        return DMUtilPrefsCacheManager.getInstance().getStringFromCashe(IRateConstants.PrefName.currency,
                null);
    }

    public static void saveCurrency(final String configsJson) {
        DMUtilPrefsCacheManager.getInstance().putInCashe(IRateConstants.PrefName.currency, configsJson);
    }
}
