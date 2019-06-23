package am.rate.core.bindingAdapter;

import android.content.Context;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import am.rate.core.glide.GlideApp;
import am.rate.core.model.Bank;
import am.rate.ui.fragments.banks.view.BankAdapter;
import am.rate.ui.fragments.base.base.DMBaseBindingAdapter;
import am.rate.ui.fragments.base.base.DMBaseIOnItemClickListener;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RateBindingAdapter extends DMBaseBindingAdapter {

    @BindingAdapter(value = {"initRecycleViewBankList", "listener"})
    public static void initRecycleViewBankList(final RecyclerView recyclerView,
                                               final List<Bank> bankList,
                                               final DMBaseIOnItemClickListener<Bank> listener) {
        final Context context = recyclerView.getContext();
        if (recyclerView.getAdapter() == null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(new BankAdapter(bankList != null ? bankList : new ArrayList<>(), listener));
        } else {
            ((BankAdapter) recyclerView.getAdapter()).replaceList(bankList);
        }
        listener.onEmptyVisible(bankList == null || bankList.size() == 0);
    }
}
