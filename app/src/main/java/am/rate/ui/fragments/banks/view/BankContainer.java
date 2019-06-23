package am.rate.ui.fragments.banks.view;

import java.util.List;

import am.rate.core.model.Bank;
import am.rate.ui.fragments.base.base.DMBaseListContainer;

public class BankContainer extends DMBaseListContainer<Bank> {

    private int position;

    public BankContainer(List<Bank> bankList, int position) {
        super(bankList);
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
