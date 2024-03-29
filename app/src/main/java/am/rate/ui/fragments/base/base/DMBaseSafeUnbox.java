package am.rate.ui.fragments.base.base;

/**
 * BaseSafeUnbox abstract class which has few functions for safe convert object type to simple types
 */
public abstract class DMBaseSafeUnbox {

    public static int safeUnbox(final Integer boxed) {
        return boxed == null ? 0 : boxed;
    }

    public static long safeUnbox(final Long boxed) {
        return boxed == null ? 0L : boxed;
    }

    public static short safeUnbox(final Short boxed) {
        return boxed == null ? 0 : (short) boxed;
    }

    public static byte safeUnbox(final Byte boxed) {
        return boxed == null ? 0 : (byte) boxed;
    }

    public static char safeUnbox(final Character boxed) {
        return boxed == null ? '\u0000' : boxed;
    }

    public static double safeUnbox(final Double boxed) {
        return boxed == null ? 0.0 : boxed;
    }

    public static float safeUnbox(final Float boxed) {
        return boxed == null ? 0f : boxed;
    }

    public static boolean safeUnbox(final Boolean boxed) {
        return boxed != null && boxed;
    }
}
