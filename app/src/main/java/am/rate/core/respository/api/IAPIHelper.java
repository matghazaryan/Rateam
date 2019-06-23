package am.rate.core.respository.api;

import android.content.Context;

import com.dm.dmnetworking.DMNetworkLiveDataBag;

import am.rate.core.model.Bank;
import am.rate.core.model.Branch;
import am.rate.core.model.RequestError;

public interface IAPIHelper {

    DMNetworkLiveDataBag<Bank, RequestError> banks(final Context context);

    DMNetworkLiveDataBag<Branch, RequestError> bankDetails(final Context context, final String guid);

}
