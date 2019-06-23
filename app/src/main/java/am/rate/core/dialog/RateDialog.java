package am.rate.core.dialog;

import android.content.Context;

import alertdialog.dm.com.dmalertdialog.DMDialogBaseConfigs;
import am.rate.R;

public class RateDialog {

    public static void showErrorDialog(final Context context, final String message) {
        new RateAlertDialog().showErrorDialog(new DMDialogBaseConfigs<>(context).setTitle(context.getString(R.string.general_dialog_error)).setContent(message).setPositive(context.getString(R.string.general_dialog_ok)));
    }

    public static void showNoInternetDialog(final Context context) {
        new RateAlertDialog().showWarningDialog(new DMDialogBaseConfigs<>(context).setTitleRes(R.string.general_dialog_warning).setContentRes(R.string.general_dialog_no_internet_connection).setPositiveRes(R.string.general_dialog_ok));
    }

}
