package am.rate.ui.fragments.base.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dm.dmnetworking.DMNetworkLiveDataBag;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import am.rate.BuildConfig;
import am.rate.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;


/**
 * BaseFragment is the abstract class where declared functions which must have each fragment for make work more easy and fast
 * contains functions for initialize ,initialize in viewModel, subscribers, initActionBar
 * <p>
 * initActionBar();
 * initialize();
 * mViewModel.initialize();
 * mViewModel.initialize(getArguments());
 * subscribers(getViewLifecycleOwner());
 *
 * @param <ViewModel> ViewModel extends BaseViewModel , this is main viewModel for fragment
 * @param <Binding>   this is auto generated class for binding view and use in the viewModel
 */
public abstract class DMBaseFragment<Application extends DMBaseApplication, ViewModel extends DMBaseViewModel<Application>, Binding extends ViewDataBinding> extends Fragment implements DMBaseIFragment, DMBaseIConstants {

    private View mView;

    public Application mApp;

    public DMBaseActivity mActivity;

    protected ViewModel mViewModel;

    protected DMBaseISharedViewModel mSharedViewModel;

    protected Binding mBinding;

    private final List<Integer> mActionsForClearOnDestroyViewList = new ArrayList<>();

    protected DMActionBar mActionBar;

    protected DMBaseFragment() {
    }

    /**
     * Give layout resource id at fragment which extends BaseFragment
     *
     * @return Layout resource id
     */
    protected abstract int getLayoutRes();

    /**
     * Give viewModel class for current fragment which extends BaseFragment
     *
     * @return ViewModel class for current fragment, like Object.class
     */
    protected abstract Class<ViewModel> getViewModelClass();

    /**
     * Functions where we must set viewModel to binding and if necessary set handler for click and other things what we need to
     * setup before fragment showing
     *
     * @param binding   Binding view for current fragment
     * @param viewModel ViewModel for current fragment
     */
    protected abstract void setBinding(final Binding binding, final ViewModel viewModel);

    @Override
    public void onAttach(@NonNull final Context context) {
        super.onAttach(context);

        mActivity = (DMBaseActivity) context;

        mActionBar = new DMActionBar(mActivity);

        mApp = (Application) Objects.requireNonNull(getActivity()).getApplication();

        if (BuildConfig.DEBUG) {
            Log.d(mApp.getApplicationConfigs().getTag(), "-----------------------------------------------------------------------------------------------------> Page is " + this.getClass().getSimpleName());
        }
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mViewModel.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onViewCreated(final @NonNull View view, final @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initActionBar();
        initialize();
        mViewModel.initialize();
        mViewModel.initialize(getArguments());
        subscribers(getViewLifecycleOwner());
        actionsForClearOnDestroyView(mActionsForClearOnDestroyViewList);
        handleBackPressed();
    }

    @Nullable
    @Override
    public View onCreateView(final @NonNull LayoutInflater inflater, final @Nullable ViewGroup container, final @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false, DataBindingUtil.getDefaultComponent());
            mSharedViewModel = ViewModelProviders.of(mActivity).get(DMBaseSharedViewModel.class);

            final FragmentActivity activity = getActivityForViewModel();
            if (activity != null) {
                mViewModel = ViewModelProviders.of(activity).get(getViewModelClass());
            } else {
                mViewModel = ViewModelProviders.of(this).get(getViewModelClass());
            }

            setBinding(mBinding, mViewModel);

            //For security
            mBinding.getRoot().setFilterTouchesWhenObscured(true);

            mView = mBinding.getRoot();

        }
//        return mBinding.getRoot();
        return mView;
    }

    private void handleBackPressed() {
        mActivity.setBackPressedListener(() -> mActivity.setHandleBackPressed(handleOnBackPressed()));
    }

    @Override
    public void onResume() {
        super.onResume();
        baseSubscribes();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        clearActions();
    }

    private void initActionBar() {
        setTitle(getTitle() != null ? getTitle() : getString(getTitleRes()));
        if (isShowActionBar()) {
            showActionBar();
        } else {
            hideActionBar();
        }

        int titleId = getResources().getIdentifier("action_bar_title", "id", "android");
        TextView abTitle = mActivity.findViewById(titleId);
        if (abTitle != null) {
            abTitle.setTextColor(ContextCompat.getColor(mActivity, R.color.text_view_color_2));
        }

    }

    protected void showSwipeProgress() {
        mViewModel.showSwipeProgress();
    }

    protected void hideSwipeProgress() {
        mViewModel.hideSwipeProgress();
    }

    /**
     * Base subscribes for show error dialog and no internet dialog
     */
    protected void baseSubscribes() {
        mActionsForClearOnDestroyViewList.add(BaseAction.SHOW_ERROR_DIALOG);
        mActionsForClearOnDestroyViewList.add(BaseAction.SHOW_NO_INTERNET_DIALOG);
        mActionsForClearOnDestroyViewList.add(BaseAction.ON_SWIPE_REFRESH);

        mViewModel.<String>getAction(BaseAction.SHOW_ERROR_DIALOG).observe(getViewLifecycleOwner(), this::showError);
        mViewModel.<String>getAction(BaseAction.SHOW_NO_INTERNET_DIALOG).observe(getViewLifecycleOwner(), this::showNoInternet);
        mViewModel.<String>getAction(BaseAction.ON_SWIPE_REFRESH).observe(getViewLifecycleOwner(), s -> onSwipeRefreshListener());
    }

    protected void showError(final String s) {
        mApp.getApplicationConfigs().showErrorDialog(mActivity, s);
    }

    protected void showNoInternet(final String s) {
        mApp.getApplicationConfigs().showNoInternetDialog(mActivity);
    }

    protected void setTitle(final String title) {
        mActionBar.setTitle(title);
    }

    protected void setTitle(final int title) {
        mActionBar.setTitle(title);
    }

    protected void hideActionBar() {
        final ActionBar actionBar = mActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    protected void showActionBar() {
        final ActionBar actionBar = mActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
        }
    }

    /**
     * To put data by key sendCode in the MutableLiveData in mSharedViewModel
     *
     * @param sendCode     sendCode like key for save MutableLiveData in sparseArray
     * @param data         it is data for put in the MutableLiveData
     * @param <SharedData> it is data type which put in the MutableLiveData
     */
    public <SharedData> void sendSharedData(final int sendCode, final SharedData data) {
        mSharedViewModel.sendSharedData(sendCode, data);
    }

    /**
     * To get any data each time when subscribing on MutableLiveData and screen become active and data is available or exist
     *
     * @param sendCode     sendCode like key for save MutableLiveData in sparseArray
     * @param listener     listener for to get data from MutableLiveData
     * @param <SharedData> it is data type which put in the MutableLiveData
     */
    public <SharedData> void getSharedDataOnActiveScreen(final int sendCode, final DMBaseIOnSharedDataListener<SharedData> listener) {
        mSharedViewModel.getSharedData(getViewLifecycleOwner(), sendCode, listener);
    }

    /**
     * To get any data each time when subscribing on liveData when fragment become active and data is available or exist
     *
     * @param sendCode     sendCode like key for save MutableLiveData in sparseArray
     * @param listener     listener for to get data from MutableLiveData
     * @param <SharedData> it is data type which put in the MutableLiveData
     */
    public <SharedData> void getSharedDataOnActiveScreenAlways(final int sendCode, final DMBaseIOnSharedDataListener<SharedData> listener) {
        mSharedViewModel.getSharedDataAlways(getViewLifecycleOwner(), sendCode, listener);
    }

    /**
     * To get any data each time when subscribing on MutableLiveData , but when change data it work immediately and data is available or exist
     *
     * @param sendCode     sendCode like key for save MutableLiveData in sparseArray
     * @param listener     listener for to get data from MutableLiveData
     * @param <SharedData> it is data type which put in the MutableLiveData
     */
    public <SharedData> void getSharedDataImmediately(final int sendCode, final DMBaseIOnSharedDataListener<SharedData> listener) {
        mSharedViewModel.getSharedData(mActivity, sendCode, listener);
    }

    /**
     * This function use when main viewModel owner is activity , it clear action(s) , for next navigation on the fragment
     * not call(s) actions (getAction, doAction)which declared(subscribe) on fragment
     */
    private void clearActions() {
        for (final Integer action : mActionsForClearOnDestroyViewList) {
            mViewModel.clearAction(action);
        }
    }

    protected <T> void getAction(final int action, final IActionListener<T> listener) {
        mActionsForClearOnDestroyViewList.add(action);
        mViewModel.<T>getAction(action).observe(getViewLifecycleOwner(), listener::onAction);
    }

    /**
     * Function for handle liveDataBug make easy to use network handling
     *
     * @param liveDataBug    this is bag which contain live data which give us way to subscribe and get parsed object
     * @param listener       interface for get result which we need, success response , error response object, error object, json atc
     * @param <O>            Parse object which build from success json parse
     * @param <ErrorRequest> Error object which build form error json parse
     */
    protected <O, ErrorRequest extends DMBaseIError> void handleRequestFor(final DMNetworkLiveDataBag<O, ErrorRequest> liveDataBug, final DMBaseIRequestListener<O> listener) {
        if (liveDataBug != null) {
            mViewModel.showProgress();

            liveDataBug.getSuccessT().observe(getViewLifecycleOwner(), oSuccessT -> {
                mViewModel.hideProgress();
                listener.onSuccess(Objects.requireNonNull(oSuccessT).getT());
            });

            liveDataBug.getSuccessMapT().observe(getViewLifecycleOwner(), oSuccessMapT -> {
                mViewModel.hideProgress();
                listener.onSuccessMap(Objects.requireNonNull(oSuccessMapT).getMap());
            });

            liveDataBug.getSuccessListT().observe(getViewLifecycleOwner(), oSuccessListT -> {
                mViewModel.hideProgress();
                listener.onSuccessList(Objects.requireNonNull(oSuccessListT).getList());
            });
            liveDataBug.getSuccessJsonResponse().observe(getViewLifecycleOwner(), successJSONObject -> {
                mViewModel.hideProgress();
                listener.onSuccessJsonObject(successJSONObject);
            });
            liveDataBug.getSuccessResponse().observe(getViewLifecycleOwner(), successResponse -> {
                mViewModel.hideProgress();
                listener.onSuccessResponse(successResponse);
            });
            liveDataBug.getFileProgress().observe(getViewLifecycleOwner(), fileProgress -> {
                mViewModel.hideProgress();
                listener.onSuccessFileProgress(fileProgress);
            });
            liveDataBug.getSuccessFile().observe(getViewLifecycleOwner(), file -> {
                mViewModel.hideProgress();
                listener.onSuccessFile(file);
            });
            liveDataBug.getErrorJsonResponse().observe(getViewLifecycleOwner(), errorJSONObject -> {
                mViewModel.hideProgress();
                listener.onErrorJsonResponse(errorJSONObject);
            });
            liveDataBug.getErrorResponse().observe(getViewLifecycleOwner(), errorResponse -> {
                mViewModel.hideProgress();
                listener.onErrorResponse(errorResponse);
            });
            liveDataBug.getErrorE().observe(getViewLifecycleOwner(), error -> {
                mViewModel.hideProgress();
                mViewModel.handleErrors(error);
            });
            liveDataBug.getNoInternetConnection().observe(getViewLifecycleOwner(), s -> {
                mViewModel.hideProgress();
                mViewModel.noInternetConnection();
            });
        }
    }
}
