package com.adamprobert.cardiffucasguide.main_activity;

import com.estimote.sdk.Beacon;

public class ConvertBeaconToContent {

	Beacon beacon;
	
	public ConvertBeaconToContent(Beacon b){
		this.beacon = b;
		
	}
	
	public Content convert(){
		int beaconID = beacon.getMinor();
		switch(beaconID){
		case 1:
			Content c1= new Content("Windows Lab","Content", "drawable/antenna2","For all your Windows needs!", 1);
			return c1;
			
		case 2:
			Content c2 = new Content("Linux Lab","Content", "drawable/antenna2","For all your Linux needs!", 2);
			return c2;
			
		case 3:
			Content c3 = new Content("Mac Lab","Content", "drawable/antenna2","For all your Mac needs!", 3);
			return c3;
		default:
			return null;
			
		}
	}
}
