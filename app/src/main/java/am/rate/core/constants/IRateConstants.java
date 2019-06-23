package am.rate.core.constants;

import am.rate.ui.fragments.base.base.DMBaseIConstants;

public interface IRateConstants {

    String TAG = "Rate.am";


    interface Action extends DMBaseIConstants.BaseAction {
        int OPEN_BANK_DETAILS = 1;
    }
    interface SEND_DATA {
        int BANK = 1000;
    }

    interface Lang {
        String currentLang = "am";
    }

    interface PrefName {
        String currency = "currency";
    }
}
