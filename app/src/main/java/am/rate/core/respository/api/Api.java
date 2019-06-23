package am.rate.core.respository.api;

import android.content.Context;

import com.dm.dmnetworking.DMNetworkBaseRequestConfig;
import com.dm.dmnetworking.DMNetworkLiveDataBag;
import com.dm.dmnetworking.DMNetworkParserConfigs;

import am.rate.core.constants.IRateConstants;
import am.rate.core.constants.IRateURL;
import am.rate.core.model.Bank;
import am.rate.core.model.Branch;
import am.rate.core.model.RequestError;

public class Api implements IAPIHelper {

    private static Api instance;

    public static Api getInstance() {
        if (instance == null) {
            synchronized (Api.class) {
                if (instance == null) {
                    instance = new Api();
                }
            }
        }
        return instance;
    }

    @Override
    public DMNetworkLiveDataBag<Bank, RequestError> banks(final Context context) {
        final DMNetworkBaseRequestConfig<Bank, RequestError> config =
                new DMNetworkBaseRequestConfig<Bank, RequestError>(context)
                        .setUrl(IRateURL.BANK_LIST)
                        .setParserConfigs(new DMNetworkParserConfigs<>())
                        .setErrorParserConfigs(new DMNetworkParserConfigs<>(RequestError.class));


        return RateNetworking.getInstance().request(config);
    }

    @Override
    public DMNetworkLiveDataBag<Branch, RequestError> bankDetails(Context context, String guid) {

        String url = String.format(IRateURL.BANK_DETAILS,guid);
        final DMNetworkBaseRequestConfig<Branch, RequestError> config =
                new DMNetworkBaseRequestConfig<Branch, RequestError>(context)
                        .setUrl(url)
                        .setParserConfigs(new DMNetworkParserConfigs<>())
                        .setErrorParserConfigs(new DMNetworkParserConfigs<>(RequestError.class));


        return RateNetworking.getInstance().request(config);
    }
}
