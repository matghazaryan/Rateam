package am.rate.ui.fragments.base.base;

import android.content.Intent;
import android.os.Bundle;

import java.util.Map;

import am.rate.core.constants.IRateConstants;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;

/**
 * IBaseModelView interface which has few function for use in child classes
 */
interface DMBaseIModelView extends DMBaseIConstants, IRateConstants {

    default void initUiTextFieldsTags(final Map<String, ObservableField<String>> uiTextFieldsTags) {
    }

    default void initialize() {
    }

    default void initialize(final Bundle bundle) {
    }

    default void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
    }

    <T> LiveData<T> getAction(final int action);

    <T> void doAction(final int action, final T t);

    default void setEnableEmptyView(final boolean enableEmptyView) {
    }

    default int[] getSwipeRefreshColors() {
        return new int[]{};
    }

    default void onSwipeRefreshListener() {

    }

    default SwipeType isSwipeWorkLikeLoader() {
        return SwipeType.FROM_CONFIG;
    }
}
