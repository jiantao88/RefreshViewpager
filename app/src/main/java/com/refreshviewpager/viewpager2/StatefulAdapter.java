package com.refreshviewpager.viewpager2;

import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * {@link ViewPager2} adapters should implement this interface to be called during
 */
public interface StatefulAdapter {
    /** Saves adapter state */
    @NonNull
    Parcelable saveState();

    /** Restores adapter state */
    void restoreState(@NonNull Parcelable savedState);
}
