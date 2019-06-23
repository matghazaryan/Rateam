package am.rate.core.respository;

import am.rate.core.respository.api.Api;
import am.rate.core.respository.api.IAPIHelper;
import am.rate.core.respository.preferance.IPreferenceHelper;
import am.rate.core.respository.preferance.Preference;

public final class DataRepository {

    public static IAPIHelper api() {
        return Api.getInstance();
    }

    public static IPreferenceHelper preference() {
        return Preference.getInstance();
    }

}
