package am.rate.core.respository.api;

import android.content.Context;

import com.dm.dmnetworking.DMNetworkBaseRequest;
import com.dm.dmnetworking.DMNetworkIStatusHandleListener;

import org.json.JSONObject;

import am.rate.BuildConfig;
import am.rate.core.constants.IRateConstants;

public class RateNetworking extends DMNetworkBaseRequest {

    private static RateNetworking ourInstance;


    private RateNetworking() {
    }

    public static RateNetworking getInstance() {
        if (ourInstance == null) {
            synchronized (RateNetworking.class) {
                if (ourInstance == null) {
                    ourInstance = new RateNetworking();
                }
            }
        }
        return ourInstance;
    }



    @Override
    protected void handleStatuses(Context context, int statusCode, JSONObject jsonObject, DMNetworkIStatusHandleListener listener) {
        listener.onComplete(null, jsonObject);
    }

    @Override
    protected int getRequestTimeOut() {
        return 60000;
    }

    @Override
    protected String getFullUrl(Context context, String url) {
        return BuildConfig.BASE_URL  + url;
    }

    @Override
    public String getTagForLogger() {
        return IRateConstants.TAG;
    }

    @Override
    public boolean isEnableLogger() {
        return true;
    }

    @Override
    public void onSuccessOrFailureResponseForDebug(final String url, final JSONObject jsonObject, final ResponseType responseType) {

    }
}
