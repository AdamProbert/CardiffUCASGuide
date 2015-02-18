package com.adamprobert.cardiffucasguide.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.adamprobert.cardiffucasguide.R;
import com.adamprobert.cardiffucasguide.main_activity.BeaconTracker;
import com.adamprobert.cardiffucasguide.main_activity.Content;
import com.adamprobert.cardiffucasguide.main_activity.ConvertBeaconToContent;
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

		View rootView = inflater.inflate(R.layout.history_layout, container, false);

		mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefresh);
		mSwipeRefreshLayout.setColorScheme(R.color.bggrey, R.color.red, R.color.red, R.color.red);
		

		list = (ListView) rootView.findViewById(R.id.historyList);
		items = new ArrayList<Content>();
		Content c1 = new Content("There aint no beacons yet!", "Content", "drawable/antenna2", "Motherfucker!", 1);
		items.add(c1);

		lA = new HistoryListAdapter(context, R.layout.history_layout, items);
		list.setAdapter(lA);

		mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				Log.d("UCAS", "onRefresh called from SwipeRefreshLayout");
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

	public void update() {
		Log.d("UCAS", "History - update has been called");
		knownBeacons = BeaconTracker.getInstance().getBeacons();
		Log.d("UCAS", "History - knownBeacons size = " + knownBeacons.size());

		ConvertBeaconToContent converter;
		items = new ArrayList<Content>();

		for (Beacon b : knownBeacons) {
			converter = new ConvertBeaconToContent(b);
			items.add(converter.convert());
			Log.d("UCAS", "History - items have added:" + converter.convert().getTitle());
		}

		lA.setNewItems(items);
		lA.notifyDataSetChanged();
		Log.d("UCAS", "History - Adapter should have updated!");

	}

}
