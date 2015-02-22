package com.adamprobert.cardiffucasguide.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

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

		final View rootView = inflater.inflate(R.layout.history_layout, container, false);

		mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefresh);
		mSwipeRefreshLayout.setColorScheme(R.color.bggrey, R.color.red, R.color.red, R.color.red);

		list = (ListView) rootView.findViewById(R.id.historyList);
		items = new ArrayList<Content>();
		lA = new HistoryListAdapter(context, R.layout.history_layout, items);
		list.setAdapter(lA);
		update();
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ConvertBeaconToContent converter = new ConvertBeaconToContent(id);
				Content content = converter.convert();
				showContent(rootView, content);

			}

		});

		mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					public void run() {
						update();
						mSwipeRefreshLayout.setRefreshing(false);
					}
				}, 200);

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
		items = new ArrayList<Content>();
		ConvertBeaconToContent converter;
		
		if (BeaconTracker.getInstance().getBeacons() != null) {
			
			knownBeacons = BeaconTracker.getInstance().getBeacons();
			Log.d("UCAS", "History - knownBeacons size = " + knownBeacons.size());

			for (Beacon b : knownBeacons) {
				converter = new ConvertBeaconToContent(b);
				items.add(converter.convert());
				Log.d("UCAS", "History - items have added:" + converter.convert().getTitle());
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

		View popupView = getLayoutInflater(null).inflate(R.layout.content_layout, null);
		popupView.setBackground(context.getResources().getDrawable(R.drawable.text_box_field));

		PopupWindow popup = new PopupWindow(popupView, 800, 1200);
		TextView title = (TextView) popupView.findViewById(R.id.content_title);
		title.setText(content.getTitle());
		TextView text = (TextView) popupView.findViewById(R.id.content);
		text.setText(content.getContent());
		ImageView image = (ImageView) popupView.findViewById(R.id.imageView);
		ProgressBar pBar = (ProgressBar) popupView.findViewById(R.id.progressBar);
		pBar.setVisibility(View.GONE);

		String uri = "@" + content.getImageLocation();
		int imageRes = context.getResources().getIdentifier(uri, null, context.getPackageName());
		Drawable drawable = context.getResources().getDrawable(imageRes);
		image.setImageDrawable(drawable);

		/**
		 * Make popup in focus Dismiss popup when clicking fragment behind popup
		 * Centre popup in screen
		 */
		popup.setFocusable(true);
		popup.setBackgroundDrawable(new ColorDrawable());
		int location[] = new int[2];
		v.getLocationOnScreen(location);
		popup.showAtLocation(v, Gravity.CENTER, 0, 0);
	}

}
