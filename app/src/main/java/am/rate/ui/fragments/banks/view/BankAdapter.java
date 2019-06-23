package am.rate.ui.fragments.banks.view;

import android.view.View;

import com.bumptech.glide.Glide;

import java.util.List;

import am.rate.R;
import am.rate.core.model.Bank;
import am.rate.core.model.Currency;
import am.rate.core.respository.DataRepository;
import am.rate.databinding.BankItemBinding;
import am.rate.ui.fragments.base.base.DMBaseAdapter;
import am.rate.ui.fragments.base.base.DMBaseIOnItemClickListener;
import androidx.annotation.NonNull;

public class BankAdapter extends DMBaseAdapter<Bank, BankItemBinding> {

    public BankAdapter(final List<Bank> tariffList,
                       final DMBaseIOnItemClickListener<Bank> listener) {
        super(tariffList, listener);
    }

    @Override
    protected int getItemLayout() {
        return R.layout.bank_item;
    }

    @Override
    protected View onBaseBindViewHolder(@NonNull BaseViewHolder<BankItemBinding> holder, int position, Bank bank) {
        holder.binding.setBank(bank);
        if (bank.getCurrency() != null && bank.getCurrency().size() > 0) {
            holder.binding.buy.setText(String.valueOf(bank.getCurrency().get(findCurrencyPosition(bank)).getBuy()));
            holder.binding.sell.setText(String.valueOf(bank.getCurrency().get(findCurrencyPosition(bank)).getSell()));
        }
        bank.setPosition(position);

        Glide.with(holder.binding.getRoot().getContext()).load(bank.getLogo()).into(holder.binding.imageView2);

        return holder.binding.getRoot();
    }

    private int findCurrencyPosition(Bank bank){
        int position = 0;
        String selectedCurrency = DataRepository.preference().getCurrency();
        if (selectedCurrency == null) return  0;
        for (int i=0; i < bank.getCurrency().size(); i++){
            Currency currency = bank.getCurrency().get(i);
            if (selectedCurrency.equals(currency.getCurrency())){
                position = i;
                break;
            }
        }
        return position;
    }
}
