package de.subaqueous.changinglist;

import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class ChangingListFragment extends ListFragment {

	private String[] list_fields = new String[] { "image" };
	private int[] list_ids = new int[] { R.id.image };

	ChangingListAdapter mAdapter;

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onActivityCreated(android.os.Bundle)
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mAdapter = new ChangingListAdapter(getActivity(),
				R.layout.main_list_item, getListItems(), list_fields, list_ids,
				ChangingListAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

		setListAdapter(mAdapter);
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.ListFragment#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		TextView title = (TextView) v.findViewById(R.id.title);

		ChangingListAdapter.list_lang_state.append(position, mAdapter
				.getNewLang(ChangingListAdapter.list_lang_state.get(position,
						0)));
		title.setText(mAdapter.getStringResourceforPosition(position));
	}

	/**
	 * Simple Function to return a matrix cursor holding image ids for each row.
	 * @return
	 */
	private Cursor getListItems() {

		MatrixCursor res_c = null;

		String[] cols = new String[] { "_id", "image" };
		;
		TypedArray mIconIds = getActivity().getResources().obtainTypedArray(
				R.array.list_icons);

		res_c = new MatrixCursor(cols);

		// create Cursor Rows defined in arrays.xml
		int len = mIconIds.length();
		for (int i = 0; i < len; ++i) {
			res_c.addRow(new Object[] { i, mIconIds.getResourceId(i, -1) });
		}
		return res_c;
	}

}
