package am.rate.ui.fragments.bankDetails.view;


import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.util.Locale;

import am.rate.R;
import am.rate.application.RateApplication;
import am.rate.core.constants.IRateConstants;
import am.rate.core.model.Bank;
import am.rate.core.model.BankData;
import am.rate.core.model.Branch;
import am.rate.core.model.Currency;
import am.rate.core.model.WorkDay;
import am.rate.databinding.CurrencyItemBinding;
import am.rate.databinding.FragmentBankDetailBinding;
import am.rate.ui.fragments.bankDetails.viewModel.BankDetailsViewModel;
import am.rate.ui.fragments.base.base.DMBaseFragment;
import am.rate.ui.fragments.base.base.DMBaseIOnSharedDataListener;
import am.rate.ui.fragments.base.base.DMBaseIRequestListener;
import androidx.databinding.DataBindingUtil;


public class BankDetailFragment extends DMBaseFragment<RateApplication, BankDetailsViewModel,
        FragmentBankDetailBinding> {


    private BankData mBankData;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_bank_detail;
    }

    @Override
    protected Class<BankDetailsViewModel> getViewModelClass() {
        return BankDetailsViewModel.class;
    }

    @Override
    protected void setBinding(FragmentBankDetailBinding binding, BankDetailsViewModel viewModel) {
        binding.setViewModel(viewModel);
    }


    @Override
    public void initialize() {
        getSharedDataOnActiveScreen(IRateConstants.SEND_DATA.BANK, (DMBaseIOnSharedDataListener<Bank>) bank -> {
            setTitle(bank.getTitle());
            mBinding.setBank(bank);
            getBankDetails(bank);
        });
    }

    private void getBankDetails(Bank bank) {
        handleRequestFor(mViewModel.apiBankDetails(bank.getGuid()),
                new DMBaseIRequestListener<Branch>() {
            @Override
            public void onSuccessJsonObject(JSONObject successJSONObject) {
                mBankData = mViewModel.getParsedBranchData(successJSONObject);
                mBinding.setBranch(mBankData.getHeadBranch());
                createUI(bank);
            }
        });
    }

    private void createUI(Bank bank) {
        mBinding.rootLayout.setVisibility(View.VISIBLE);
        Branch branch = mBankData.getHeadBranch();
        Glide.with(mActivity).load(bank.getLogo()).into(mBinding.bankLogo);

        if (branch.getWorkhours() != null && branch.getWorkhours().size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (WorkDay w : branch.getWorkhours()) {
                stringBuilder.append(w.getDays()).append(" ").append(w.getHours()).append("\n");
            }
            mBinding.bankWorking.setText(stringBuilder);
        }

        mBinding.map.setOnClickListener(v -> openGoogleMap(branch.getLat(),branch.getLng()));

        for (Currency currency: bank.getCurrency()) {
            LayoutInflater inflater = LayoutInflater.from(mActivity);
            CurrencyItemBinding valueBinding = DataBindingUtil.inflate(inflater,
                    R.layout.currency_item,null,true);
            valueBinding.setCurrency(currency);

            valueBinding.imageView2.setImageResource(mApp.getSupportUtils().getDrawable(mActivity
                    ,currency.getCurrency().toLowerCase()));

            mBinding.llCurrency.addView(valueBinding.getRoot());
        }

    }

    private void openGoogleMap(double latitude, double longitude){
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        mActivity.startActivity(intent);
    }
}
