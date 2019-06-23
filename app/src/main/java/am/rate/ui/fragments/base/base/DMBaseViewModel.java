package am.rate.ui.fragments.base.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.util.SparseArray;

import com.dm.dmnetworking.model.error.ErrorE;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import dmutils.com.dmutils.permission.DMUtilEasyPermissions;


/**
 * BaseViewModel is the abstract class where declared functions which must have each ViewModel for make work more easy and fast
 * contains functions for initialize, showProgress, hideProgress show error dialog , show no internet dialog , keep actions MutableLiveData (baseMutableLiveDataSparseArray) for call
 * functions in the fragment
 * show/hide content after request with delay and when navigate in pages
 */
public abstract class DMBaseViewModel<App extends DMBaseApplication> extends AndroidViewModel implements DMBaseIModelView {

//    protected final DMBaseApplicationConfigs mApplicationConfigs;
//
//    protected final DMBaseSupportUtils mUtils;

    protected final App mApp;

    protected Context mContext;

    private final SparseArray<MutableLiveData<Object>> baseMutableLiveDataSparseArray = new SparseArray<>();

    private final Map<String, ObservableField<String>> baseUITextFieldsTags = new HashMap<>();

    //For show/hide progress on start/end request (include progress_dialog.xml and add app:isVisible="@{safeUnbox(viewModel.isBaseProgressDialogVisible)}" )
    public final ObservableField<Boolean> isBaseProgressDialogVisible = new ObservableField<>();

    //For gone at first and visible with fade animation after request completed
    public final ObservableField<Boolean> isBaseRootVisibleAfterLoading = new ObservableField<>(false);

    //For gone at first and visible with delay with fade animation after opened page
    public final ObservableField<Boolean> isBaseRootVisibleDelay = new ObservableField<>(false);

    //For swipe refresh
    public final ObservableField<Boolean> isBaseSwipeRefreshing = new ObservableField<>();
    public final ObservableField<Boolean> isBaseOnlyLoader = new ObservableField<>();
    public final ObservableField<SwipeRefreshLayout.OnRefreshListener> baseOnSwipeRefreshListener = new ObservableField<>();
    public final ObservableField<int[]> baseRefreshColor = new ObservableField<>();

    public DMBaseViewModel(final @NonNull Application application) {
        super(application);
        mContext = getApplication().getApplicationContext();
        mApp = (App) getApplication().getApplicationContext();

//        mApplicationConfigs = ((DMBaseApplication) Objects.requireNonNull(application)).getApplicationConfigs();
        new Handler().postDelayed(() -> isBaseRootVisibleDelay.set(true), AnimDuration.ROOT_VISIBLE_DELAY);
        new Handler().postDelayed(() -> initUiTextFieldsTags(baseUITextFieldsTags), AnimDuration.UI_FIELDS_INIT_DELAY);

//        mUtils = new DMBaseSupportUtils(getApplication().getApplicationContext());


        //For swipe refresh
        initializeSwipeToRefresh();
    }

    private void initializeSwipeToRefresh() {
        final boolean isSwipeWorkLikeLoader;
        switch (isSwipeWorkLikeLoader()) {
            case SWIPE_FOR_REFRESH:
                isSwipeWorkLikeLoader = false;
                break;
            case LOADER:
                isSwipeWorkLikeLoader = true;
                break;
            case FROM_CONFIG:
                switch (mApp.getApplicationConfigs().isSwipeDefaultWorkLikeLoader()) {
                    case SWIPE_FOR_REFRESH:
                    case FROM_CONFIG:
                        isSwipeWorkLikeLoader = false;
                        break;
                    case LOADER:
                        isSwipeWorkLikeLoader = true;
                        break;
                    default:
                        isSwipeWorkLikeLoader = false;
                }
                break;
            default:
                isSwipeWorkLikeLoader = false;
        }

        baseRefreshColor.set(getSwipeRefreshColors().length != 0 ? getSwipeRefreshColors() : mApp.getApplicationConfigs().getSwipeRefreshColors());
        isBaseOnlyLoader.set(isSwipeWorkLikeLoader);
        baseOnSwipeRefreshListener.set(() -> {
            isBaseSwipeRefreshing.set(true);
            onSwipeRefreshListener();
            doAction(BaseAction.ON_SWIPE_REFRESH, null);
        });
    }

    public void showProgress() {
        setEnableEmptyViewFromNetwork(false);
        isBaseProgressDialogVisible.set(true);
        showSwipeProgress();
    }

    public void hideProgress() {
        new Handler().postDelayed(() -> {
            isBaseProgressDialogVisible.set(false);
            hideSwipeProgress();
        }, AnimDuration.PROGRESS_DIALOG_VISIBLE_DELAY);
        new Handler().postDelayed(() -> isBaseRootVisibleAfterLoading.set(true), AnimDuration.ROOT_VISIBLE_DELAY);
        setEnableEmptyViewFromNetwork(true);
    }

    protected void showSwipeProgress() {
        isBaseSwipeRefreshing.set(true);
    }

    protected void hideSwipeProgress() {
        isBaseSwipeRefreshing.set(false);
    }

    /**
     * getAction function for subscribe in the fragment and to wait call at doAction function
     * work on pair with doAction function
     *
     * @param action It is constant action with which int keep MutableLiveData in the baseMutableLiveDataSparseArray, and
     *               with this int call doAction function
     * @param <T>    Object type which you want to send
     * @return LiveData for subscribe in fragment
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> LiveData<T> getAction(final int action) {
        final LiveData<Object> data = baseMutableLiveDataSparseArray.get(action);
        if (data == null) {
            baseMutableLiveDataSparseArray.put(action, new MutableLiveData<>());
        }

        return (LiveData<T>) baseMutableLiveDataSparseArray.get(action);
    }

    /**
     * doAction is the function for call functions in the fragment or activity
     *
     * @param action It is constant action
     * @param t      Object which we want to send to fragment or activity
     * @param <T>    Object type which we want to send to fragment or activity
     */
    @Override
    public <T> void doAction(final int action, final T t) {
        MutableLiveData<Object> data = baseMutableLiveDataSparseArray.get(action);
        if (data == null) {
            baseMutableLiveDataSparseArray.put(action, new MutableLiveData<>());
        }

        data = baseMutableLiveDataSparseArray.get(action);
        if (data != null) {
            data.setValue(t);
        }
    }

    /**
     * Clear action in the baseMutableLiveDataSparseArray
     *
     * @param action It is constant action
     */
    public void clearAction(final int action) {
        baseMutableLiveDataSparseArray.remove(action);
    }

    /**
     * handleError is function for handle error from network request and show message
     *
     * @param errorE         Error object from json pars
     * @param <ErrorRequest> Error object type
     */
    protected <ErrorRequest extends DMBaseIError> void handleErrors(final ErrorE<ErrorRequest> errorE) {
        if (errorE != null) {
            final ErrorRequest error = errorE.getE();
            if (error != null) {
                final HashMap<String, String> errors = error.getErrors();
                if (errors != null && errors.size() > 0) {
                    showInvalidData(baseUITextFieldsTags, error.getErrors());
                } else if (error.getMessage() != null) {
                    doAction(BaseAction.SHOW_ERROR_DIALOG, error.getMessage());
                } else {
                    doAction(BaseAction.SHOW_ERROR_DIALOG, getApplication().getApplicationContext().getString(mApp.getApplicationConfigs().getGeneralErrorMessage()));
                }
            } else {
                doAction(BaseAction.SHOW_ERROR_DIALOG, getApplication().getApplicationContext().getString(mApp.getApplicationConfigs().getGeneralErrorMessage()));
            }
        }
    }

    /**
     * Show error when error json like key->value, with mapUiFields set values by key
     *
     * @param mapUiFields map string(key) and observableField(value), error field in json and error filed in xml
     * @param errorMap    parsed error map in json
     */
    private static void showInvalidData(final Map<String, ObservableField<String>> mapUiFields, final Map<String, String> errorMap) {
        if (mapUiFields != null && errorMap != null) {
            for (final Map.Entry<String, String> entry : errorMap.entrySet()) {
                final ObservableField<String> field = mapUiFields.get(entry.getKey());
                if (field != null) {
                    field.set(entry.getValue());
                }
            }
        }
    }

    /**
     * Call action in BaseFragment for show no internet dialog
     */
    void noInternetConnection() {
        new Handler().postDelayed(() -> doAction(BaseAction.SHOW_NO_INTERNET_DIALOG, getApplication().getApplicationContext().getString(mApp.getApplicationConfigs().getNoInternetMessage())), AnimDuration.ROOT_VISIBLE_DELAY);
    }

    /**
     * Disable editing status for display empty view during network request
     *
     * @param setEnableEmptyViewFromNetwork status
     */
    void setEnableEmptyViewFromNetwork(final boolean setEnableEmptyViewFromNetwork) {

    }

    void onRequestPermissionsResult(final int requestCode, final String[] permissions, final int[] grantResults, final Object... receivers) {
        DMUtilEasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, receivers);
    }
}
