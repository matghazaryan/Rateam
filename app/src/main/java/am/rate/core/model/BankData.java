package am.rate.core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import am.rate.core.listeners.ISpinnerData;

public class BankData {

    List<Bank> bankList;
    Map<String, Currency> currencyMap;

    List<Branch> branchList;
    private Branch headBranch;

    public BankData(List<Bank> bankList, Map<String, Currency> currencyMap) {
        this.bankList = bankList;
        this.currencyMap = currencyMap;
    }

    public BankData(List<Branch> branchList, Branch headBranch) {
        this.branchList = branchList;
        this.headBranch = headBranch;
    }

    public List<Bank> getBankList() {
        return bankList;
    }

    public Map<String, Currency> getCurrencyMap() {
        return currencyMap;
    }

    public List<ISpinnerData> getCurrencyList(){
        return new ArrayList<>(currencyMap.values());
    }

    public List<Currency> getCurrencyListArray(){
        return new ArrayList<>(currencyMap.values());
    }

    public List<Branch> getBranchList() {
        return branchList;
    }

    public Branch getHeadBranch() {
        return headBranch;
    }
}
