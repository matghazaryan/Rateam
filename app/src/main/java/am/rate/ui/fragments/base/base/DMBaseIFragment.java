package am.rate.ui.fragments.base.base;

import java.util.List;

import am.rate.R;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;

/**
 * IBaseFragment interface which has few function for use in child classes
 */
interface DMBaseIFragment {

    default void initialize() {
    }

    default void subscribers(final LifecycleOwner owner) {
    }

    default void actionsForClearOnDestroyView(final List<Integer> actions) {

    }

    default int getTitleRes() {
        return R.string.app_name;
    }

    default String getTitle() {
        return null;
    }

    default boolean isShowActionBar() {
        return true;
    }

    default FragmentActivity getActivityForViewModel() {
        return null;
    }

    default void onSwipeRefreshListener() {

    }

    default boolean handleOnBackPressed() {
        return false;
    }
}
