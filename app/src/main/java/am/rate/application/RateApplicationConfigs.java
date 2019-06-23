package am.rate.application;

import android.content.Context;

import am.rate.R;
import am.rate.core.dialog.RateDialog;
import am.rate.ui.fragments.base.base.DMBaseApplicationConfigs;

public class RateApplicationConfigs extends DMBaseApplicationConfigs {


    private static RateApplicationConfigs instance;

    private RateApplicationConfigs() {
    }

    public static RateApplicationConfigs getInstance() {
        if (instance == null) {
            synchronized (RateApplicationConfigs.class) {
                if (instance == null) {
                    instance = new RateApplicationConfigs();
                }
            }
        }
        return instance;
    }


    @Override
    public void showErrorDialog(Context context, String message) {
        RateDialog.showErrorDialog(context,message);
    }

    @Override
    public void showNoInternetDialog(Context context) {
        RateDialog.showNoInternetDialog(context);
    }

    @Override
    public int getGeneralErrorMessage() {
        return R.string.general_error_error;
    }

    @Override
    public int getNoInternetMessage() {
        return R.string.general_dialog_no_internet_connection;
    }

    @Override
    public String getTag() {
        return "Rate.am";
    }
}
