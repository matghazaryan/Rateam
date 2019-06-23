package am.rate.ui.fragments.base.base;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

/**
 * BaseViewModelItemClick is the abstract class for handle click in the BaseAdapter
 *
 * @param <O>     Object type which will return after click on item view
 * @param <Empty> Empty is object type for send data and show in the empty view, use in the recycler view
 */
public abstract class DMBaseViewModelItemClick<App extends DMBaseApplication, O, Empty> extends DMBaseViewModelEmptyView<App, Empty> implements DMBaseIOnItemClickListener<O> {

    public final ObservableField<DMBaseIOnItemClickListener<O>> baseOnItemClickListener = new ObservableField<>();

    public final ObservableField<List<O>> baseList = new ObservableField<>();
    public final ObservableField<DMBaseListContainer<O>> baseListContainer = new ObservableField<>();

    public DMBaseViewModelItemClick(final @NonNull Application application) {
        super(application);
        baseOnItemClickListener.set(this);
    }

    /**
     * Base function for set list in the fragment
     *
     * @param oList list which need to send to the RecycleView, must be binding witch baseList
     */
    public void setBaseList(final List<O> oList) {
        this.baseList.set(oList);
    }

    public void setBaseContainerList(final List<O> oList) {
        baseListContainer.set(new DMBaseListContainer<>(oList));
    }

    public void setBaseContainer(final DMBaseListContainer<O> container) {
        baseListContainer.set(container);
    }
}
