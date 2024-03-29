package am.rate.ui.fragments.base.base;

public interface DMBaseIConstants {

    interface AnimDuration {
        int ROOT_VISIBLE_DELAY = 280;
        int PROGRESS_DIALOG_VISIBLE_DELAY = 250;
        int ALPHA = 300;
        int UI_FIELDS_INIT_DELAY = 100;
    }

    interface BaseAction {
        int SHOW_ERROR_DIALOG = 1000;
        int SHOW_NO_INTERNET_DIALOG = 1001;
        int SHOW_TOAST = 1002;
        int ON_SWIPE_REFRESH = 1003;
        int ADD_ACTION_FOR_REMOVE_ON_DESTROY_VIEW = 1004;
    }

    enum SwipeType {
        LOADER,
        SWIPE_FOR_REFRESH,
        FROM_CONFIG
    }
}
