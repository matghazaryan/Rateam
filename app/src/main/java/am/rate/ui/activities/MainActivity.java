package am.rate.ui.activities;

import android.os.Bundle;

import am.rate.R;
import am.rate.ui.fragments.base.base.DMBaseActivity;

public class MainActivity extends DMBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
