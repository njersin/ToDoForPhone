package com.example.todoforphone;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by admin on 9/10/15.
 */
public class ToDoItemFragment extends Fragment {

	TextView toDoDetail;
	Button deleteItem;
	String itemText;

	OnDeleteToDoItemListener mDeleteListener;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mDeleteListener = (OnDeleteToDoItemListener) activity;
		} catch (ClassCastException cce) {
			throw new ClassCastException(activity.toString() + " must implement OnDeleteToDoItemListener");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_to_do_item_details, group, false);

		toDoDetail = (TextView) view.findViewById(R.id.to_do_detail_text);
		itemText = getArguments().getString(ToDoManagerActivityOn.TODO_DETAIL);
		toDoDetail.setText(itemText);

		deleteItem = (Button) view.findViewById(R.id.to_do_detail_delete_button);
		deleteItem.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mDeleteListener.deleteToDoItem(itemText);
			}
		});

		return view;
	}

	interface OnDeleteToDoItemListener {
		void deleteToDoItem(String item);
	}
}
