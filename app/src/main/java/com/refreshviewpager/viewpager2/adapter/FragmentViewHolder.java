package com.refreshviewpager.viewpager2.adapter;


/*
 * Copyright 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import android.widget.FrameLayout;


/**
 * {@link ViewHolder} implementation for handling {@link Fragment}s. Used in
 * {@link FragmentStateAdapter}.
 */
public final class FragmentViewHolder extends ViewHolder {
    Fragment mFragment;

    private FragmentViewHolder(FrameLayout container) {
        super(container);
    }

    static FragmentViewHolder create(ViewGroup parent) {
        FrameLayout container = new FrameLayout(parent.getContext());
        container.setLayoutParams(
            new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        container.setId(ViewCompat.generateViewId());
        container.setSaveEnabled(false);
        return new FragmentViewHolder(container);
    }

    FrameLayout getContainer() {
        return (FrameLayout) itemView;
    }
}

