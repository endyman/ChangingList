package de.subaqueous.changinglist;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;

public class ChangingListAdapter extends SimpleCursorAdapter {

	private TypedArray languages;
	public static SparseIntArray list_lang_state = new SparseIntArray();

	public ChangingListAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to, int flags) {
		super(context, layout, c, from, to, flags);
		if (languages == null) {
			languages = context.getResources().obtainTypedArray(
					R.array.list_languages);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.widget.SimpleCursorAdapter#bindView(android.view.View,
	 * android.content.Context, android.database.Cursor)
	 */
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		super.bindView(view, context, cursor);
		context.getResources();
		TextView title = (TextView) view.findViewById(R.id.title);
		title.setText(getStringResourceforPosition(cursor.getPosition()));
	}

	/**
	 * Get the Text String for the current cursor position
	 * 
	 * @param pos
	 * @param context
	 * @return Text String
	 */
	public String getStringResourceforPosition(int pos) {
		String[] titles = ChangingListApp
				.getAppContext()
				.getResources()
				.getStringArray(
						languages.getResourceId(list_lang_state.get(pos), -1));
		if (titles != null) {
			return titles[pos];
		}
		return null;
	}

	/**
	 * get next Language from language array
	 * 
	 * @param current_lang
	 * @return
	 */
	public int getNewLang(int current_lang) {
		if (current_lang < languages.length() - 1) {
			return current_lang + 1;

		} else {
			return 0;
		}
	}

}
