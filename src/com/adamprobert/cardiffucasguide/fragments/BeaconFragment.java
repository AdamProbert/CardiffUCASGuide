package com.adamprobert.cardiffucasguide.fragments;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.adamprobert.cardiffucasguide.R;
import com.adamprobert.cardiffucasguide.main_activity.BeaconTracker;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

public class BeaconFragment extends Fragment {

	private Context context;

	// Beacon Variables
	private BeaconManager beaconManager;
	private static final String ESTIMOTE_PROXIMITY_UUID = "B9407F30-F5F8-466E-AFF9-25556B57FE6D";
	private static final Region ALL_ESTIMOTE_BEACONS = new Region("regionId", ESTIMOTE_PROXIMITY_UUID, null, null);

	public BeaconFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		if (getActivity() != null) {
			context = getActivity();
			beaconManager = new BeaconManager(context);

		}

		final View rootView = inflater.inflate(R.layout.content_layout, container, false);
		final TextView textView = (TextView) rootView.findViewById(R.id.content_title);

		/**
		 * 
		 * NEED TO CHECK IF PHONE CAN SUPPORT BEACONS
		 */
		if (beaconManager.isBluetoothEnabled()) {
			textView.setText("Searching...");
		} else {
			textView.setText("Please enable bluetooth");
		}

		beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {
			TextView textView1 = (TextView) rootView.findViewById(R.id.no_beacons);
			TextView textView2 = (TextView) rootView.findViewById(R.id.beacon_details);
			TextView contentText = (TextView) rootView.findViewById(R.id.content);

			@Override
			public void onExitedRegion(Region region) {
				System.out.println("On Exited Region has been called");
				TextView contentText = (TextView) rootView.findViewById(R.id.content);
				contentText.setText("On Exited Region has been called");
				textView1.setText("");
				textView2.setText("");

			}

			@Override
			public void onEnteredRegion(Region region, List<Beacon> beacons) {
				contentText.setText("On Entered Region has been called");

				String foundBeacons = "";

				for (Beacon b : beacons) {
					foundBeacons += b.getMinor() + ", ";
					if (!BeaconTracker.getInstance().hasBeaconBeenFound(b)) {
						BeaconTracker.getInstance().addBeacon(b);

					}
				}

				textView1.setText(foundBeacons);
				textView2.setText("Ranged beacons: " + beacons);

			}
		});
		return rootView;
	}


	@Override
	public void onStart() {
		super.onStart();

		beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
			@Override
			public void onServiceReady() {
				try {
					beaconManager.startMonitoring(ALL_ESTIMOTE_BEACONS);
				} catch (RemoteException e) {
					Toast.makeText(context, "Beacon manager cant start ranging", Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	@Override
	public void onStop() {
		try {
			beaconManager.stopMonitoring(ALL_ESTIMOTE_BEACONS);
		} catch (RemoteException e) {
			Log.e("UCAS", "Cannot stop beacon manager but it does not matter now", e);
		}
		super.onStop();

	}

	@Override
	public void onDestroy() {
		beaconManager.disconnect();
		super.onDestroy();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
	}

}
