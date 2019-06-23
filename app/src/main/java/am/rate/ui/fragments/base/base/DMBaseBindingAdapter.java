package am.rate.ui.fragments.base.base;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.databinding.BindingAdapter;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * BaseBindingAdapter is abstract class which has few functions for us in the xml files
 * show/hide
 * set checked status
 * make fade animation show/hide
 */
public abstract class DMBaseBindingAdapter {

    @BindingAdapter("baseVisibleView")
    public static void baseVisibleView(final View view, final boolean isVisible) {
        view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("android:checked")
    public static void setChecked(final AppCompatCheckBox checkBox, final Boolean isChecked) {
        checkBox.setChecked(isChecked != null ? isChecked : false);
    }

    @BindingAdapter("baseFadAnim")
    public static void baseFadAnim(final View view, final boolean isVisible) {
        boolean isFirstTime = false;
        if (view.getTag() == null) {
            isFirstTime = true;
        }
        view.setTag("");

        if (isFirstTime) {
            view.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
        } else {
            if (isVisible) {
                view.setVisibility(View.VISIBLE);
                final ObjectAnimator anim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
                anim.setDuration(DMBaseIConstants.AnimDuration.ALPHA);
                anim.start();
            } else {
                view.animate()
                        .alpha(0.0f)
                        .setDuration(DMBaseIConstants.AnimDuration.ALPHA)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                view.setVisibility(View.INVISIBLE);
                            }
                        });
            }
        }
    }

    @BindingAdapter(value = {"isBaseSwipeRefreshing", "isBaseOnlyLoader", "baseRefreshColor", "baseOnSwipeRefreshListener"})
    public static void baseOnSwipeRefreshListener(final SwipeRefreshLayout swipeRefreshLayout, final boolean isRefreshing, final boolean isOnlyLoader, final int[] refreshColor, final SwipeRefreshLayout.OnRefreshListener listener) {
        swipeRefreshLayout.setEnabled(!isOnlyLoader);
        swipeRefreshLayout.setOnRefreshListener(listener);
        swipeRefreshLayout.setRefreshing(isRefreshing);
        if (refreshColor.length != 0) {
            swipeRefreshLayout.setColorSchemeResources(refreshColor);
        }
    }
}