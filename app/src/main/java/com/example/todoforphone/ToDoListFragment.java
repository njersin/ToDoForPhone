package com.example.todoforphone;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class ToDoListFragment extends Fragment {

	private ListView toDoListView;
	private ArrayAdapter<String> toDoArray;

	private Button addNewItem;

	private OnListItemSelectedListener selectedListener;
	private OnNewItemRequestListener newItemListener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_to_do_list, group, false);
		toDoListView = (ListView) v.findViewById(R.id.to_do_list_view);


		toDoListView.setAdapter(toDoArray);
		toDoListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		toDoListView.setDividerHeight(5);
		toDoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				selectedListener.onListItemSelected(toDoArray.getItem(position));
			}
		});


		addNewItem = (Button) v.findViewById(R.id.add_new_todo_item);
		addNewItem.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//This should tell the Activity to launch a new fragment to add the new item.
				newItemListener.onNewItemRequest();
			}
		});


		return v;

	}

	void addNewItem(String newItem) {

		toDoArray.add(newItem);
		toDoArray.notifyDataSetChanged();

	}

	void deleteItem(String item) {
		toDoArray.remove(item);
		toDoArray.notifyDataSetChanged();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		toDoArray = new ArrayAdapter<>(this.getActivity(), R.layout.list_view_to_do_item, R.id.list_item);

		try {
			selectedListener = (OnListItemSelectedListener) activity;
			newItemListener = (OnNewItemRequestListener) activity;
		} catch (ClassCastException cce) {
			throw new ClassCastException(activity.toString() + " must implement all required listeners");
		}
	}

	public interface OnListItemSelectedListener {
		void onListItemSelected(String toDoItem);
	}

	public interface OnNewItemRequestListener {
		void onNewItemRequest();
	}
}
