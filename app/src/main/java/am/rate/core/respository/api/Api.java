package am.rate.core.respository.api;

import android.content.Context;

import com.dm.dmnetworking.DMNetworkBaseRequestConfig;
import com.dm.dmnetworking.DMNetworkLiveDataBag;
import com.dm.dmnetworking.DMNetworkParserConfigs;

import am.rate.core.constants.IRateConstants;
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
                        .setUrl(IRateConstants.Method.BANK_LIST)
//                        .setFullUrl("http://www.mocky.io/v2/5d0d5bfe3400008a00ca49f4")
                        .setParserConfigs(new DMNetworkParserConfigs<>())
                        .setErrorParserConfigs(new DMNetworkParserConfigs<>(RequestError.class));


        return RateNetworking.getInstance().request(config);
    }

    @Override
    public DMNetworkLiveDataBag<Branch, RequestError> bankDetails(Context context, String guid) {

        String url = String.format(IRateConstants.Method.BANK_DETAILS,guid);
        final DMNetworkBaseRequestConfig<Branch, RequestError> config =
                new DMNetworkBaseRequestConfig<Branch, RequestError>(context)
                        .setUrl(url)
//                        .setFullUrl("http://www.mocky.io/v2/5d0e86053200001200dc68e2")
                        .setParserConfigs(new DMNetworkParserConfigs<>())
                        .setErrorParserConfigs(new DMNetworkParserConfigs<>(RequestError.class));


        return RateNetworking.getInstance().request(config);
    }
}
