package com.adamprobert.cardiffucasguide.main_activity;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.adamprobert.cardiffucasguide.R;

public class BeaconNotification extends Activity{
	
	private int beaconID;
	private boolean actualNotification;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("UCAS", "BeaconNotification has been created");
		setContentView(R.layout.content_layout);
		
		// Get beacon id
		Bundle extras = getIntent().getExtras();
		beaconID = extras.getInt("beaconID");
		actualNotification = extras.getBoolean("actualNotification");
		
		//Update log file
		if(actualNotification){
			BeaconTracker.getInstance().addNotificationTime(beaconID);
		}
		

		
		// Get views and assign relevant content
		ConvertBeaconToContent converter = new ConvertBeaconToContent(beaconID);
		Content content = converter.convert();
		
		TextView title = (TextView)findViewById(R.id.content_title);
		TextView text = (TextView)findViewById(R.id.content);
		ImageView image = (ImageView)findViewById(R.id.imageView);
		ProgressBar pBar = (ProgressBar)findViewById(R.id.progressBar);
		
		title.setText(content.getTitle());
		text.setText(content.getContent());
		pBar.setVisibility(View.GONE);

		/**
		 * Set Image!
		 */
		String uri = "@" + content.getImageLocation();
		int imageRes = getResources().getIdentifier(uri, null, getPackageName());
		Drawable drawable = getResources().getDrawable(imageRes);
		image.setImageDrawable(drawable);
		image.setVisibility(View.VISIBLE);
		
		
		
	};
	
	 @Override
	    public void onDestroy()
	    {
		 	if(actualNotification){
		 		BeaconTracker.getInstance().addNotificationTime(beaconID);
		 	}
	        super.onDestroy();
	        
	    }
	

}
