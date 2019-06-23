package am.rate.ui.fragments.banks.viewModel;

import android.app.Application;

import com.dm.dmnetworking.DMNetworkLiveDataBag;
import com.dm.dmnetworking.model.error.ErrorE;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import am.rate.application.RateApplication;
import am.rate.core.model.Bank;
import am.rate.core.model.BankData;
import am.rate.core.model.Currency;
import am.rate.core.model.Empty;
import am.rate.core.model.RequestError;
import am.rate.core.respository.DataRepository;
import am.rate.ui.fragments.base.base.DMBaseIError;
import am.rate.ui.fragments.base.base.DMBaseViewModelItemClick;
import androidx.annotation.NonNull;

public class BankViewModel extends DMBaseViewModelItemClick<RateApplication, Bank, Empty> {

    public BankData bankData;

    public BankViewModel(@NonNull Application application) {
        super(application);
        baseOnItemClickListener.set(this);
    }

    public DMNetworkLiveDataBag<Bank, RequestError> apiBanks() {
        return DataRepository.api().banks(mContext);
    }

    @Override
    protected <ErrorRequest extends DMBaseIError> void handleErrors(final ErrorE<ErrorRequest> errorE) {
        super.handleErrors(errorE);
    }

    @Override
    public void onItemClick(Bank bank) {
        doAction(Action.OPEN_BANK_DETAILS, bank);
    }

    public BankData getParsedBankData(JSONObject successJSONObject) {
        List<Bank> bankList = new ArrayList<>();
        Map<String, Currency> currencyMap = new HashMap<>();

        for (int i = 0; i < successJSONObject.names().length(); i++) {
            try {
                String guid = successJSONObject.names().getString(i);
                JSONObject bData =
                        (JSONObject) successJSONObject.get(guid);

                Bank bank = new Bank(guid);
                bank.setTitle(bData.getString("title"));
                bank.setLogo(bData.getString("logo"));
                bank.setDate(bData.getLong("date"));

                List<Currency> currencyList = new ArrayList<>();


                for (int j = 0; j < bData.getJSONObject("list").names().length(); j++) {
                    String currencyListKey = bData.getJSONObject("list").names().getString(j);

                    JSONObject currencyListJsonObject = (JSONObject) bData.getJSONObject("list").get(currencyListKey);

                    Currency currency = new Currency(currencyListKey);

                    if (currencyListJsonObject.names().length() > 0) {
                        String currencyCodeJSON = currencyListJsonObject.names().getString(0);
                        double buy = currencyListJsonObject.getJSONObject(currencyCodeJSON).getDouble("buy");
                        double sell = currencyListJsonObject.getJSONObject(currencyCodeJSON).getDouble("sell");
                        currency.setBuy(buy);
                        currency.setSell(sell);
                    }
                    currencyList.add(currency);

                    currencyMap.put(currency.getCurrency(), currency);

                }
                bank.setCurrency(currencyList);
                bankList.add(bank);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        bankData = new BankData(bankList, currencyMap);
        return bankData;
    }

    public List<Bank>  filterBankListByCurrency(String currency){
        List<Bank> filteredBankList = new ArrayList<>();

        for (Bank bank: bankData.getBankList()){
            for (Currency c: bank.getCurrency()){
                if (c.getCurrency().equals(currency)){
                    filteredBankList.add(bank);
                }
            }
        }
        return filteredBankList;
    }

    public void saveSelectedCurrency(String currency){
        DataRepository.preference().saveCurrency(currency);
    }


}
