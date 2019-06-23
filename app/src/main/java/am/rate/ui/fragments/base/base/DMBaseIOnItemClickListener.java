package am.rate.ui.fragments.base.base;

/**
 * IBaseOnItemClickListener is the interface for handle click in BaseAdapter
 *
 * @param <T> Object type which will return, when click on item view
 */
public interface DMBaseIOnItemClickListener<T> extends DmBaseIEmptyViewListener {

    default void onItemClick(final T t) {
    }
}
