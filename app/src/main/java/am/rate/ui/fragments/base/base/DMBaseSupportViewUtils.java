package am.rate.ui.fragments.base.base;

import android.widget.EditText;

public abstract class DMBaseSupportViewUtils {

    public void editTextState(final EditText editText, final boolean isEditable) {
        editText.setFocusable(isEditable);
        editText.setFocusableInTouchMode(isEditable);
        editText.setClickable(isEditable);
        editText.setCursorVisible(isEditable);
    }
}
