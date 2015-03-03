package com.adamprobert.cardiffucasguide.main_activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;

import com.estimote.sdk.Beacon;

public class BeaconTracker {

	private List<Beacon> knownBeacons = new ArrayList<Beacon>();
	private Map<Integer, String> beaconTimeTracker = new HashMap<Integer, String>();
	
	private static final BeaconTracker bt = new BeaconTracker();

	public Map<Integer, String> getTimeTracker(){
		return beaconTimeTracker;
	}
	
	public static synchronized BeaconTracker getInstance() {
		return bt;
	}

	public List<Beacon> getBeacons() {
		if (knownBeacons.size() == 0) {
			return null;
		} else {
			return knownBeacons;
		}
	}

	public void addBeacon(Beacon b) {
		knownBeacons.add(b);
		String input = Calendar.getInstance().getTime().toString();
		input = (String) input.subSequence(11, 19);
		beaconTimeTracker.put(b.getMinor(), input);
		Log.d("UCAS", "Beacon time tracker added a beacon");
		Log.d("UCAS", "Beacon time tracker entry = " + beaconTimeTracker.get(b.getMinor()));
	}
	
	public void addNotificationTime(int minor){
		String input = Calendar.getInstance().getTime().toString();
		input = (String) input.subSequence(11, 19);
		beaconTimeTracker.put(minor, beaconTimeTracker.get(minor) + "," + input);
		Log.d("UCAS", "Beacon time tracker added a notification time to " + minor);
		Log.d("UCAS", "Beacon time tracker entry = " + beaconTimeTracker.get(minor));

		
	}


	public boolean hasBeaconBeenFound(Beacon b) {

		if (knownBeacons.contains(b)) {
			return true;
		} else {
			return false;
		}
	}
}
