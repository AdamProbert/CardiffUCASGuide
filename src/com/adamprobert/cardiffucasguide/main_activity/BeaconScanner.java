package com.adamprobert.cardiffucasguide.main_activity;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

		beaconManager.setBackgroundScanPeriod(TimeUnit.SECONDS.toMillis(1), 5);
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
					//Ensure beacon is one of mine
					if (b.getMajor() == 2580) {
						if (closestBeacon != null) {
							//Get closest beacon
							if (b.getRssi() > closestBeacon.getRssi()) {
								closestBeacon = b;
							}
						} else {
							closestBeacon = b;

						}
					}
					else{
						continue;
					}
				}

				/**
				 * Used for History fragment Checks if beacon has already been
				 * found. if not, adds to the BeaconTracker
				 */
				if (!BeaconTracker.getInstance().hasBeaconBeenFound(closestBeacon)) {
					BeaconTracker.getInstance().addBeacon(closestBeacon);
					callback.onGetBeacon(closestBeacon, true);
				} else {
					callback.onGetBeacon(closestBeacon, false);
				}

			}
		});

	}

	public BeaconManager getBeaconManager() {
		return beaconManager;
	}

	public interface BeaconCallback {
		void onGetBeacon(Beacon b, boolean showNotification);

		void onLeftBeaconRange();
	}

}
