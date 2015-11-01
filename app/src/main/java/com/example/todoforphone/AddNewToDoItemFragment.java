package com.example.todoforphone;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class AddNewToDoItemFragment extends Fragment {

	Button saveButton;
	EditText details;

	OnNewToDoItemCreatedListener newItemListener;


	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			newItemListener = (OnNewToDoItemCreatedListener) activity;
		} catch (ClassCastException cce) {
			throw new ClassCastException(activity.toString() + " must implement onNewToDoItemListener");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_add_new_to_do_item, group, false);
		details = (EditText) view.findViewById(R.id.new_todo_text);

		saveButton = (Button) view.findViewById(R.id.save_button);
		saveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Get text from EditText and send back to host Activity
				String newText = details.getText().toString();
				newItemListener.newItemCreated(newText);

			}
		});
		return view;
	}


	interface OnNewToDoItemCreatedListener {
		void newItemCreated(String newItem);
	}

}
