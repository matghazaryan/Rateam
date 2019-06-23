package am.rate.ui.fragments.base.base;

import java.util.List;

public class DMBaseListContainer<T> {

    protected List<T> tList;

    public DMBaseListContainer() {
    }

    public DMBaseListContainer(final List<T> tList) {
        this.tList = tList;
    }

    public List<T> getList() {
        return tList;
    }
}
