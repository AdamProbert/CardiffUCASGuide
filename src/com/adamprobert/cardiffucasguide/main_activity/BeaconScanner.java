package com.adamprobert.cardiffucasguide.main_activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.util.Log;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

public class BeaconScanner implements Runnable {

	// Beacon Variables
	private BeaconManager beaconManager;
	private BeaconCallback callback;

	public BeaconScanner(BeaconManager beaconManager, BeaconCallback callback) {
		this.beaconManager = beaconManager;
		this.callback = callback;

	}

	@Override
	public void run() {

		beaconManager.setBackgroundScanPeriod(TimeUnit.SECONDS.toMillis(1), 1);
		beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {

			@Override
			public void onExitedRegion(Region region) {

				callback.onLeftBeaconRange();
			}

			@Override
			public void onEnteredRegion(Region region, List<Beacon> beacons) {

				Beacon closestBeacon = null;

				/**
				 * Determine which beacon is closest (has strongest RSSI value)
				 * and displays content for that beacon
				 */
				for (Beacon b : beacons) {
					if (closestBeacon != null) {
						if (b.getRssi() > closestBeacon.getRssi()) {
							closestBeacon = b;
						}
					} else {
						closestBeacon = b;

					}
				}

				/**
				 * Used for History fragment Checks if beacon has already been
				 * found If not, adds to the BeaconTracker
				 */
				if (!BeaconTracker.getInstance().hasBeaconBeenFound(closestBeacon)) {
					BeaconTracker.getInstance().addBeacon(closestBeacon);
					

				}
				
				callback.onGetBeacon(closestBeacon);

			}
		});

	}

	public BeaconManager getBeaconManager() {
		return beaconManager;
	}

	public interface BeaconCallback {
		void onGetBeacon(Beacon b);
		void onLeftBeaconRange();
	}

	
}
