package com.adamprobert.cardiffucasguide.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.adamprobert.cardiffucasguide.R;
import com.adamprobert.cardiffucasguide.main_activity.Content;

public class History extends Fragment{
	
	Context context;
	HistoryListAdapter lA;
	List<Content> items;
	ListView list;
	
	public History(){
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		if (getActivity() != null) {
			context = getActivity();
		}

		View rootView = inflater.inflate(R.layout.history_layout, container, false);
		list = (ListView) rootView.findViewById(R.id.historyList);
		items = new ArrayList<Content>();
		Content c1 = new Content("Windows Lab","Content", "drawable/antenna2","For all your Windows needs!", 1);
		Content c2 = new Content("Linux Lab","Content", "drawable/antenna2","For all your Linux needs!", 2);
		Content c3 = new Content("Mac Lab","Content", "drawable/antenna2","For all your Mac needs!", 3);
		items.add(c1);
		items.add(c2);
		items.add(c3);
		
		lA = new HistoryListAdapter(context, R.layout.history_layout, items);
		list.setAdapter(lA);

		return rootView;
	}
	
	
	
	
}





//
//import java.util.ArrayList;
//import java.util.List;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.v4.app.ListFragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import com.adamprobert.cardiffucasguide.R;
//import com.adamprobert.cardiffucasguide.main_activity.MainActivity.HistoryUpdate;
//
//public class History extends ListFragment implements HistoryUpdate {
//	private int[] knownBeacons;
//	private List<String> listContent;
//	private Context context;
//	private boolean beaconFlag;
//	HistoryListAdapter adapter;
//	View rootView;
//
//	public History() {
//
//		if (getActivity() != null) {
//			context = getActivity();
//		}
//		beaconFlag = false;
//
//		Bundle bundle = getArguments();
//		if (bundle != null) {
//			knownBeacons = new int[bundle.getIntArray("knownBeacons").length];
//			knownBeacons = bundle.getIntArray("knownBeacons");
//			listContent = new ArrayList<String>();
//			for (int i = 1; i < 5; i++) {
//				listContent.add("Well Done! You found beacon: " + Integer.toString(i));
//			}
//			beaconFlag = true;
//
//		}
//
//	}
//
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//		rootView = inflater.inflate(R.layout.history_layout, container, false);
//
//		return rootView;
//	}
//
//	@Override
//	public void onStart() {
//		super.onStart();
//
//		if (beaconFlag == true) {
//
//			final ListView listview = (ListView) rootView.findViewById(android.R.id.list);
//
//			adapter = new HistoryListAdapter(context, R.layout.history_item_layout, listContent);
//
//			listview.setAdapter(adapter);
//
//		} else {
//			final TextView textView = (TextView) rootView.findViewById(R.id.noContent);
//			textView.setText("Sorry, no beacons found yet.");
//		}
//	}
//
//	public void updateHistory() {
//
//		listContent = new ArrayList<String>();
//		for (int i : knownBeacons) {
//			listContent.add("Well Done! You found beacon: " + knownBeacons[i]);
//		}
//
//		adapter.notifyDataSetChanged();
//
//	}
//
//	@Override
//	public void onHistoryUpdate(int[] listContent) {
//		this.knownBeacons = listContent;
//		updateHistory();
//
//	}
//
//}
