package am.rate.ui.fragments.base.base;

import android.annotation.SuppressLint;
import android.util.Log;

import am.rate.BuildConfig;
import am.rate.R;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

/**
 * BaseActivity has few functions for work with action bar, set title , show/hide and display current class name
 */
@SuppressLint("Registered")
public abstract class DMBaseActivity extends AppCompatActivity {

    private boolean isHandleBackPressed = false;
    private IBackPressedListener backPressedListener;

    @Override
    protected void onResume() {
        super.onResume();
        final DMBaseApplicationConfigs applicationConfigs = ((DMBaseApplication) getApplication()).getApplicationConfigs();

        if (BuildConfig.DEBUG) {
            Log.d(applicationConfigs.getTag(), "-----------------------------------------------------------------------------------------------------> Page is " + this.getClass().getSimpleName());
        }
    }

    protected void hideActionBar() {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    public void setTitle(final String title) {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    public void setTitle(final int title) {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    public void showBackButton() {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        }
    }

    public void hideBackButton() {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    @Override
    public void onBackPressed() {
        if (backPressedListener != null) {
            backPressedListener.onBackPressed();
        }

        if (!isHandleBackPressed) {
            super.onBackPressed();
        }
    }

    public boolean isHandleBackPressed() {
        return isHandleBackPressed;
    }

    public void setHandleBackPressed(final boolean handleBackPressed) {
        isHandleBackPressed = handleBackPressed;
    }

    public IBackPressedListener getBackPressedListener() {
        return backPressedListener;
    }

    public void setBackPressedListener(final IBackPressedListener backPressedListener) {
        this.backPressedListener = backPressedListener;
    }
}
