package com.adamprobert.cardiffucasguide.main_activity;

import java.util.ArrayList;
import java.util.List;

import com.estimote.sdk.Beacon;

public class BeaconTracker {

	private List<Beacon> knownBeacons = new ArrayList<Beacon>();
	private static final BeaconTracker bt = new BeaconTracker();

	public static BeaconTracker getInstance() {
		return bt;
	}
	
	public List<Beacon> getBeacons(){
		return knownBeacons;
	}
	
	public void addBeacon(Beacon b){
		knownBeacons.add(b);
	}	
	
	public boolean hasBeaconBeenFound(Beacon b){
		
		if(knownBeacons.contains(b)){
			return true;
		}
		else{
			return false;
		}
	}
}
