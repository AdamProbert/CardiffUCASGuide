package com.adamprobert.cardiffucasguide.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.adamprobert.cardiffucasguide.R;
import com.adamprobert.cardiffucasguide.main_activity.BeaconScanner;
import com.adamprobert.cardiffucasguide.main_activity.BeaconScanner.BeaconCallback;
import com.adamprobert.cardiffucasguide.main_activity.Content;
import com.adamprobert.cardiffucasguide.main_activity.ConvertBeaconToContent;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

public class BeaconFragment extends Fragment implements BeaconCallback {

	private Context context;
	private View rootView;
	private BeaconManager beaconManager;
	private static final String ESTIMOTE_PROXIMITY_UUID = "B9407F30-F5F8-466E-AFF9-25556B57FE6D";
	private static final Region ALL_ESTIMOTE_BEACONS = new Region("regionId", ESTIMOTE_PROXIMITY_UUID, null, null);

	BluetoothAdapter bluetoothAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (getActivity() != null) {
			context = getActivity();

		}
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.content_layout, container, false);
		
		/**
		 * Checks if device supports Bluetooth
		 * 
		 */
		bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

		if (bluetoothAdapter == null) {
			Toast.makeText(context, "Your device does not support Bluetooth", Toast.LENGTH_LONG).show();

		} else {
			if (!bluetoothAdapter.isEnabled()) {
				new AlertDialog.Builder(context).setIcon(android.R.drawable.ic_dialog_alert)
						.setTitle(R.string.bluetooth).setMessage(R.string.enableBluetooth)
						.setPositiveButton(R.string.enable, new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								Intent turnOnIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
								startActivityForResult(turnOnIntent, 1);
								Toast.makeText(context, "Bluetooth turned on", Toast.LENGTH_LONG).show();
							}
						}).show();
			}
		}
		
		TextView title = (TextView) rootView.findViewById(R.id.content_title);
		title.setText("Searching...");

		/**
		 * Initialise beacon scanner thread
		 */
		beaconManager = new BeaconManager(context);
		BeaconScanner scanner = new BeaconScanner(beaconManager, this);
		Thread thread = new Thread(scanner);
		thread.start();

		return rootView;
	}

	private void showContent(Beacon beacon) {

		TextView title = (TextView) rootView.findViewById(R.id.content_title);
		TextView text = (TextView) rootView.findViewById(R.id.content);
		ImageView image = (ImageView) rootView.findViewById(R.id.imageView);
		ProgressBar pBar = (ProgressBar) rootView.findViewById(R.id.progressBar);

		pBar.setVisibility(View.GONE);
		
		ConvertBeaconToContent converter = new ConvertBeaconToContent(beacon);
		Content content = converter.convert();
		title.setText(content.getTitle());
		text.setText(content.getContent());

		/**
		 * Set Image!
		 */
		String uri = "@" + content.getImageLocation();
		int imageRes = context.getResources().getIdentifier(uri, null, context.getPackageName());
		Drawable drawable = context.getResources().getDrawable(imageRes);
		image.setImageDrawable(drawable);
		image.setVisibility(View.VISIBLE);

	}

	private void removeContent() {

		TextView title = (TextView) rootView.findViewById(R.id.content_title);
		TextView text = (TextView) rootView.findViewById(R.id.content);
		ImageView image = (ImageView) rootView.findViewById(R.id.imageView);
		ProgressBar pBar = (ProgressBar) rootView.findViewById(R.id.progressBar);

		pBar.setVisibility(View.VISIBLE);

		title.setText("Searching...");
		text.setText("");
		image.setVisibility(View.GONE);
		

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

	@Override
	public void onGetBeacon(Beacon b) {
		showContent(b);

	}

	@Override
	public void onLeftBeaconRange() {
		removeContent();

	}

}
