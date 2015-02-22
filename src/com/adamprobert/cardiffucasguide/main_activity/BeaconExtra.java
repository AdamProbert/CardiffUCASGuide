package com.adamprobert.cardiffucasguide.main_activity;

import java.util.Calendar;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;

public class BeaconExtra extends Beacon{
	
	Content content;
	Calendar triggerTime;
	boolean notificationOpened;
	long triggerDistance;
	
	public BeaconExtra(String proximityUUID, String name, String macAddress, int major, int minor, int measuredPower,
			int rssi, long triggerDistance) {
		super(proximityUUID, name, macAddress, major, minor, measuredPower, rssi);
		
		ConvertBeaconToContent converter = new ConvertBeaconToContent(minor);
		content = converter.convert();
		
		triggerTime = Calendar.getInstance();
		notificationOpened = false;
		
		
		
	}

	
	
	public Content getContent() {
		return content;
	}



	public void setContent(Content content) {
		this.content = content;
	}



	public Calendar getTriggerTime() {
		return triggerTime;
	}



	public void setTriggerTime(Calendar triggerTime) {
		this.triggerTime = triggerTime;
	}



	public Boolean getNotificationOpened() {
		return notificationOpened;
	}



	public void setNotificationOpened(Boolean notificationOpened) {
		this.notificationOpened = notificationOpened;
	}



	public Long getTriggerDistance() {
		return triggerDistance;
	}



	public void setTriggerDistance(Long triggerDistance) {
		this.triggerDistance = triggerDistance;
	}



	
	

}
