package am.rate.core.utils;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.List;

import am.rate.core.listeners.ISpinnerData;
import am.rate.core.listeners.ISpinnerSelectedListener;
import am.rate.ui.fragments.banks.view.SpinnerDataAdapter;
import am.rate.ui.fragments.base.base.DMBaseSupportViewUtils;

public class RateSupportViewUtils extends DMBaseSupportViewUtils {

    private static RateSupportViewUtils instance;

    private RateSupportViewUtils() {
    }

    public static RateSupportViewUtils getInstance() {
        if (instance == null) {
            synchronized (RateSupportViewUtils.class) {
                if (instance == null) {
                    instance = new RateSupportViewUtils();
                }
            }
        }
        return instance;
    }

    public void initializeSpinner(final Activity activity, final Spinner spinner, final List<ISpinnerData> spinnerDataList, final String selectedText, final ISpinnerSelectedListener listener) {

        final SpinnerDataAdapter adapter = new SpinnerDataAdapter(activity,
                android.R.layout.simple_spinner_item,
                spinnerDataList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int i, final long l) {
                if (i >= 0) {
                    listener.onSelected(spinnerDataList.get(i),i);
                }
            }

            @Override
            public void onNothingSelected(final AdapterView<?> adapterView) {

            }
        });

        int selection = 0;
        if (selectedText != null && selectedText.length() != 0) {
            final int size = spinnerDataList.size();
            for (int i = 0; i < size; i++) {
                if (spinnerDataList.get(i).getSpinnerText().equalsIgnoreCase(selectedText)) {
                    selection = i;
                    break;
                }
            }
        }
        spinner.setSelection(selection);
    }
}
