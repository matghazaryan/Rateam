package am.rate.ui.fragments.base.base;

public interface DMBaseIApplicationMethods extends DMBaseIConstants {

    default int[] getSwipeRefreshColors() {
        return new int[]{};
    }

    default SwipeType isSwipeDefaultWorkLikeLoader() {
        return SwipeType.SWIPE_FOR_REFRESH;
    }
}
