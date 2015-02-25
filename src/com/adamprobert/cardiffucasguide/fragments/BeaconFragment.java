package com.adamprobert.cardiffucasguide.fragments;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.adamprobert.cardiffucasguide.R;
import com.adamprobert.cardiffucasguide.main_activity.BeaconNotification;
import com.adamprobert.cardiffucasguide.main_activity.BeaconScanner;
import com.adamprobert.cardiffucasguide.main_activity.BeaconScanner.BeaconCallback;
import com.adamprobert.cardiffucasguide.main_activity.Content;
import com.adamprobert.cardiffucasguide.main_activity.ConvertBeaconToContent;
import com.adamprobert.cardiffucasguide.main_activity.Questionnaire;
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

	private void showNotification(Beacon b) {

		/**
		 * Create Beacon Notification
		 */
		ConvertBeaconToContent converter = new ConvertBeaconToContent(b);
		Content content = converter.convert();

		NotificationCompat.Builder myBuilder = new NotificationCompat.Builder(context)
				.setSmallIcon(R.drawable.antenna2).setContentTitle("BEACON FOUND!").setContentText(content.getTitle());

		/**
		 * Create intent to handle clicking the notification
		 * Beacon 5 is for the questionnaire
		 */
		Intent notifyIntent;

		if (b.getMinor() == 5) {
			
			notifyIntent = new Intent(context, Questionnaire.class);
			Log.d("UCAS", "Intent has been made for questionnaire");


		} else {

			notifyIntent = new Intent(context, BeaconNotification.class);
			notifyIntent.putExtra("beaconID", b.getMinor());

		}
		
		// Sets the Activity to start in a new, empty task
		notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

		// Creates the PendingIntent
		PendingIntent notifyPendingIntent = PendingIntent.getActivity(context, 0, notifyIntent,
				PendingIntent.FLAG_UPDATE_CURRENT);

		Log.d("UCAS", "Pending intent has been created");

		// Builder preferences

		myBuilder.setContentIntent(notifyPendingIntent);
		myBuilder.setAutoCancel(true);
		myBuilder.setVibrate(new long[] { 1000, 1000, 1000, 1000 });

		// Notifications are issued by sending them to the
		// NotificationManager system service.
		NotificationManager mNotificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);

		// Builds an anonymous Notification object from the builder, and
		// passes it to the NotificationManager
		mNotificationManager.notify(b.getMinor(), myBuilder.build());
		Log.d("UCAS", "Notification manager is waiting");

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
		showNotification(b);
		updateLog(b);

	}

	@Override
	public void onLeftBeaconRange() {
		removeContent();

	}

	// This is where we update the file with relevant info!
	private void updateLog(Beacon b) {

		String text = "\n" + b.toString();
		FileOutputStream fos = null;
		String file = "data.csv";

		try {
			fos = context.openFileOutput(file, Context.MODE_APPEND | Context.MODE_PRIVATE);
			fos.write(text.getBytes());

		} catch (FileNotFoundException e) {
			Log.d("UCAS", "updateLog - file not found");
			e.printStackTrace();
		} catch (IOException e) {
			Log.d("UCAS", "update log - ioexception");
			e.printStackTrace();
		} finally {
			// Close streams
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException ioe) {
				Log.d("UCAS", "Error closing file output stream");
			}

		}

	}

}
