package com.adamprobert.cardiffucasguide.main_activity;

import java.io.Serializable;


public class Content implements Serializable{

	private static final long serialVersionUID = -5083294196716680655L;
	String title;
	String content;
	String imageLocation;
	int beaconMinorID;
	String subHeading;
	
	public Content(String title, String content, String imageLocation, String subHeading, int beaconMinorID){
		this.title = title;
		this.content = content;
		this.imageLocation = imageLocation;
		this.beaconMinorID = beaconMinorID;
		this.subHeading = subHeading;
	}
	
	
	/**
	 * GETTERS AND SETTERS
	 */
	
	
	public String getSubHeading(){
		return subHeading;
	}
	
	public void setSubHeading(String subHeading){
		this.subHeading = subHeading;
	}
	
	public int getBeaconMinorID() {
		return beaconMinorID;
	}

	public void setBeaconMinorID(int beaconMinorID) {
		this.beaconMinorID = beaconMinorID;
	}


	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getImageLocation() {
		return imageLocation;
	}
	
	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}
	

}
