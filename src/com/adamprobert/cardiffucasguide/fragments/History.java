package com.adamprobert.cardiffucasguide.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.adamprobert.cardiffucasguide.R;
import com.adamprobert.cardiffucasguide.main_activity.BeaconNotification;
import com.adamprobert.cardiffucasguide.main_activity.BeaconTracker;
import com.adamprobert.cardiffucasguide.main_activity.Content;
import com.adamprobert.cardiffucasguide.main_activity.ConvertBeaconToContent;
import com.adamprobert.cardiffucasguide.main_activity.LogFile;
import com.estimote.sdk.Beacon;

public class History extends Fragment {

	Context context;
	HistoryListAdapter lA;
	List<Content> items;
	List<Beacon> knownBeacons;
	ListView list;
	SwipeRefreshLayout mSwipeRefreshLayout;

	public History() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		if (getActivity() != null) {
			context = getActivity();
		}

		final View rootView = inflater.inflate(R.layout.history_layout, container, false);


		list = (ListView) rootView.findViewById(R.id.historyList);
		items = new ArrayList<Content>();
		lA = new HistoryListAdapter(context, R.layout.history_layout, items);
		list.setAdapter(lA);
		update();
		
		Button updateButton = (Button)rootView.findViewById(R.id.refreshButton);
		
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				ConvertBeaconToContent converter = new ConvertBeaconToContent(id);
				Content content = converter.convert();
				showContent(rootView, content);

			}

		});

		/*
		 * Refreshes the list view, when user pulls down.
		 */
		
		updateButton.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				LogFile log = new LogFile(context);
				log.appendToFile("# HISTORY UPDATE BUTTON PRESSED");
				update();
			}
		});
			

		return rootView;
	}

	public void onResume() {
		super.onResume();

	}

	public void onPause() {
		Log.d("UCAS", "History - onPauseHasBeenCalled!");
		super.onPause();

	}
	
	
	/*
	 * Used to update the list of history 
	 * Called when fragment is created
	 * Called when pull to refresh 
	 */
	public void update() {

		items = new ArrayList<Content>();
		ConvertBeaconToContent converter;
		
		if (BeaconTracker.getInstance().getBeacons() != null) {
			knownBeacons = BeaconTracker.getInstance().getBeacons();

			for (Beacon b : knownBeacons) {
				converter = new ConvertBeaconToContent(b);
				items.add(converter.convert());
			}

			
		}
		else{
			//Set default "no beacons yet" content
			converter = new ConvertBeaconToContent(99);
			Content content = converter.convert();
			items.add(content);
		}
		
		lA.setNewItems(items);
		lA.notifyDataSetChanged();
		Log.d("UCAS", "History - Adapter should have updated!");

	}

	public void showContent(View v, Content content) {

		Intent intent = new Intent(context, BeaconNotification.class);
		intent.putExtra("beaconID", content.getBeaconMinorID()); 
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		context.startActivity(intent);

	}

}
