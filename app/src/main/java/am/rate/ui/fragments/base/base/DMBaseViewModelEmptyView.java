package am.rate.ui.fragments.base.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

/**
 * BaseViewModelEmptyView is abstract class for show empty view , prepare and set empty object for show in view
 *
 * @param <Empty> Empty is object type for send data and show in the empty view, use in the recycler view
 */
public abstract class DMBaseViewModelEmptyView<App extends DMBaseApplication, Empty> extends DMBaseViewModel<App> implements DmBaseIEmptyViewListener, DMBaseIEmpty<Empty> {

    public final ObservableField<DmBaseIEmptyViewListener> baseEmptyViewListener = new ObservableField<>();
    public final ObservableField<Boolean> isBaseEmptyViewVisible = new ObservableField<>(false);
    public final ObservableField<Empty> baseEmpty = new ObservableField<>();

    private boolean isEnableEmptyView = true;
    private boolean isEnableEmptyFromNetwork = true;

    public DMBaseViewModelEmptyView(final @NonNull Application application) {
        super(application);
        baseEmptyViewListener.set(this);
        baseEmpty.set(getEmptyObject());
    }

    @Override
    public void onEmptyVisible(final boolean isEmptyVisible) {
        if (isEnableEmptyFromNetwork && isEnableEmptyView) {
            isBaseEmptyViewVisible.set(isEmptyVisible);
        }
    }

    @Override
    public void setEnableEmptyView(final boolean enableEmptyView) {
        isEnableEmptyView = enableEmptyView;
    }

    @Override
    void setEnableEmptyViewFromNetwork(final boolean isEnableEmptyFromNetwork) {
        this.isEnableEmptyFromNetwork = isEnableEmptyFromNetwork;
    }
}
