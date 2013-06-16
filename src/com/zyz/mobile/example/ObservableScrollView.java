/*
Copyright (C) 2013 Ray Zhou

JadeRead is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

JadeRead is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with JadeRead.  If not, see <http://www.gnu.org/licenses/>

Author: Ray Zhou
Date: 2013 04 26

*/
package com.zyz.mobile.example;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import java.util.ArrayList;

/**
 * ScrollView with a onScrollChangedListener
 *
 * http://stackoverflow.com/questions/3948934/synchronise-scrollview-scroll-positions-android
 *
 */
public class ObservableScrollView extends ScrollView{

	private ArrayList<OnScrollChangedListener> mOnScrollChangedListeners;
	
	@SuppressWarnings("unused")
	public ObservableScrollView(Context context) {
		super(context);
		init();
	}

	@SuppressWarnings("unused")
	public ObservableScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	@SuppressWarnings("unused")
	public ObservableScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		mOnScrollChangedListeners = new ArrayList<OnScrollChangedListener>(2);
	}

	public void addOnScrollChangedListener(OnScrollChangedListener onScrollChangedListener) {
		mOnScrollChangedListeners.add(onScrollChangedListener);
	}

	@SuppressWarnings("unused")
	public void removeOnScrollChangedListener(OnScrollChangedListener onScrollChangedListener) {
		mOnScrollChangedListeners.remove(onScrollChangedListener);
	}
	
	/**
	 * google should make this method public and add a setOnScrollChangedListener
	 * override to allow listener
	 */
	@Override
	protected void onScrollChanged(int x, int y, int oldx, int oldy) {
		super.onScrollChanged(x, y, oldx, oldy);
		for (OnScrollChangedListener listener : mOnScrollChangedListeners) {
			listener.onScrollChanged(this, x, y, oldx, oldy);
		}
	}
	
}
