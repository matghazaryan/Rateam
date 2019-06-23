package am.rate.ui.fragments.banks.view;

import android.view.View;

import org.json.JSONObject;

import java.util.List;

import am.rate.R;
import am.rate.application.RateApplication;
import am.rate.core.constants.IRateConstants;
import am.rate.core.listeners.IEmptyViewHandler;
import am.rate.core.listeners.ISpinnerData;
import am.rate.core.listeners.ISpinnerSelectedListener;
import am.rate.core.model.Bank;
import am.rate.core.model.BankData;
import am.rate.databinding.FragmentBanksBinding;
import am.rate.ui.fragments.banks.viewModel.BankViewModel;
import am.rate.ui.fragments.base.base.DMBaseFragment;
import am.rate.ui.fragments.base.base.DMBaseIRequestListener;
import am.rate.ui.fragments.base.base.IActionListener;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;


public class BanksFragment extends DMBaseFragment<RateApplication,BankViewModel,
        FragmentBanksBinding> implements IEmptyViewHandler {


    private BankData mBankData;

    @Override
    public void onEmptyViewClick(View view) { }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_banks;
    }

    @Override
    protected Class<BankViewModel> getViewModelClass() {
        return BankViewModel.class;
    }

    @Override
    protected void setBinding(FragmentBanksBinding binding, BankViewModel viewModel) {
        binding.setViewModel(viewModel);
    }

    @Override
    public int getTitleRes() {
        return R.string.hello_blank_fragment;
    }


    @Override
    public void initialize() {
        getAllData();
    }

    @Override
    public void subscribers(LifecycleOwner owner) {
        getAction(IRateConstants.Action.OPEN_BANK_DETAILS, (IActionListener<Bank>) bank -> {
            sendSharedData(IRateConstants.SEND_DATA.BANK,bank);
            Navigation.findNavController(mActivity, R.id.nav_host_fragment).navigate(R.id.action_banksFragment_to_bankDetailFragment);
        });
    }

    private void getAllData() {
        if (mViewModel.bankData == null) {
            handleRequestFor(mViewModel.apiBanks(), new DMBaseIRequestListener<Bank>() {
                @Override
                public void onSuccessJsonObject(JSONObject successJSONObject) {
                    mBankData = mViewModel.getParsedBankData(successJSONObject);
                    mViewModel.setBaseList(mBankData.getBankList());
                    createSpinner();
                }
            });
        } else {
            mViewModel.setBaseList(mBankData.getBankList());
            createSpinner();
        }
    }

    private void createSpinner() {
        mApp.getSupportViewUtils().initializeSpinner(mActivity, mBinding.currencySpinner,
                mBankData.getCurrencyList(), "USD", (data, i) -> {
                    mBinding.rvMyList.setVisibility(View.GONE);
                    List<Bank> filteredList = mViewModel.filterBankListByCurrency(data.getSpinnerID());
                    mViewModel.setBaseList(filteredList);
                    mViewModel.saveSelectedCurrency(data.getSpinnerID());
//                        mViewModel.setBaseContainer(new BankContainer(filteredList,i));
                    mViewModel.saveSelectedCurrency(data.getSpinnerID());
                    mBinding.rvMyList.setVisibility(View.VISIBLE);
                });
    }

}
