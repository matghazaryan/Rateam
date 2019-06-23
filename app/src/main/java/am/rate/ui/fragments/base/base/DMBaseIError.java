package am.rate.ui.fragments.base.base;

import java.util.HashMap;

/**
 * IBaseError interface for get error message and errors map from json, and use in the BaseViewModel
 */
public interface DMBaseIError {

    String getMessage();

    HashMap<String, String> getErrors();
}
