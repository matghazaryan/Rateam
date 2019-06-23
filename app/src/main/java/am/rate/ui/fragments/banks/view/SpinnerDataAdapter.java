package am.rate.ui.fragments.banks.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import am.rate.R;
import am.rate.core.listeners.ISpinnerData;
import androidx.annotation.NonNull;

public class SpinnerDataAdapter extends ArrayAdapter<ISpinnerData> {

    private Context context;
    private List<ISpinnerData> values;

    public SpinnerDataAdapter(final Context context, final int textViewResourceId,
                              final List<ISpinnerData> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }

    public int getCount() {
        return values.size();
    }

    public ISpinnerData getItem(final int position) {
        return values.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(final int position, final View convertView, @NonNull final ViewGroup parent) {
        return createTextView(values.get(position).getSpinnerText());
    }

    @Override
    public View getDropDownView(final int position, final View convertView, @NonNull final ViewGroup parent) {

        final TextView label = createTextView(values.get(position).getSpinnerText());

        final int smallMarginInPixels = (int) context.getResources().getDimension(R.dimen.activity_small_margin);
        label.setPadding(smallMarginInPixels, smallMarginInPixels, smallMarginInPixels, smallMarginInPixels);

        return label;
    }

    private TextView createTextView(final String text) {
        final TextView label = new TextView(context);
//        label.setTextColor(ContextCompat.getColor(context, R.color.text_color_2));
        label.setSingleLine(true);
//        label.setTextSize(TypedValue.COMPLEX_UNIT_PX,
//                context.getResources().getDimension(R.dimen.text_size_16sp));

        label.setText(text);

        return label;
    }

    public void setValues(final List<ISpinnerData> values) {
        this.values = values;
        notifyDataSetChanged();
    }
}
