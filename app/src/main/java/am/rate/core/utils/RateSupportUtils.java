package am.rate.core.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import am.rate.ui.fragments.base.base.DMBaseSupportUtils;

public class RateSupportUtils extends DMBaseSupportUtils {

    private static RateSupportUtils instance;

    private RateSupportUtils() {
    }

    public static RateSupportUtils getInstance() {
        if (instance == null) {
            synchronized (RateSupportUtils.class) {
                if (instance == null) {
                    instance = new RateSupportUtils();
                }
            }
        }
        return instance;
    }

    public  int getDrawable(Context context,String name) {
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }
}
