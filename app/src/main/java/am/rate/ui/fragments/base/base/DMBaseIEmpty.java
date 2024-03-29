package am.rate.ui.fragments.base.base;

/**
 * IBaseEmpty is interface for show empty view
 *
 * @param <Empty> Empty is object type for send data and show in the empty view, use in the recycler view
 */
public interface DMBaseIEmpty<Empty> {

    /**
     * For get empty object and send to binding view
     *
     * @return Empty is object type for send data and show in the empty view, use in the recycler view
     */
    default Empty getEmptyObject() {
        return null;
    }
}
