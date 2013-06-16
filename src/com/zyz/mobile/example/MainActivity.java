package com.zyz.mobile.example;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends Activity {

	private SelectableTextView mTextView;
	private int mTouchX;
	private int mTouchY;
	private final static int DEFAULT_SELECTION_LEN = 5;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// make sure the TextView's BufferType is Spannable, see the main.xml
		mTextView = (SelectableTextView) findViewById(R.id.main_text);
		mTextView.setDefaultSelectionColor(0x40FF00FF);


		mTextView.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				showSelectionCursors(mTouchX, mTouchY);
				return true;
			}
		});
		mTextView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextView.hideCursor();
			}
		});
		mTextView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				mTouchX = (int) event.getX();
				mTouchY = (int) event.getY();
				return false;
			}
		});
	}

	private void showSelectionCursors(int x, int y) {
		int start = mTextView.getPreciseOffset(x, y);

		if (start > -1) {
			int end = start + DEFAULT_SELECTION_LEN;
			if (end >= mTextView.getText().length()) {
				end = mTextView.getText().length() - 1;
			}
			mTextView.showSelectionControls(start, end);
		}
	}
}
