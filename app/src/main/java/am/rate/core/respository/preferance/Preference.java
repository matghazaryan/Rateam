package am.rate.core.respository.preferance;

public class Preference implements IPreferenceHelper {

    private static Preference instance;

    public static Preference getInstance() {
        if (instance == null) {
            synchronized (Preference.class) {
                if (instance == null) {
                    instance = new Preference();
                }
            }
        }
        return instance;
    }

    @Override
    public void saveCurrency(String currency) {
        RatePrefUtils.saveCurrency(currency);
    }

    @Override
    public String getCurrency() {
        return RatePrefUtils.getCurrency();
    }
}
