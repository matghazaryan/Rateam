package am.rate.core.model;

import java.util.HashMap;

import am.rate.ui.fragments.base.base.DMBaseIError;

public final class RequestError implements DMBaseIError {

    private String status;
    private String message;
    private HashMap<String, String> errors;

    public String getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HashMap<String, String> getErrors() {
        return errors;
    }
}
