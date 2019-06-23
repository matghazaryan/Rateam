package am.rate.ui.fragments.base.base;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import am.rate.R;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class DMActionBar {

    private DMBaseActivity activity;

    public DMActionBar(final DMBaseActivity activity) {
        this.activity = activity;
    }

    private void displayTitle(final String title) {
        final ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);

            Spannable text = new SpannableString(actionBar.getTitle());
            text.setSpan(new ForegroundColorSpan(ContextCompat.getColor(activity, R.color.text_view_color_2)), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            actionBar.setTitle(text);
        }
    }

    protected void hideActionBar(final AppCompatActivity activity) {
        final ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    public void setTitle(final String title) {
        displayTitle(title);
    }

    public void setTitle(final int title) {
        displayTitle(activity.getString(title));
    }

    public void showBackButton() {
        final ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);

            actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        }
    }

    public void hideBackButton() {
        final ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
        }
    }
}
