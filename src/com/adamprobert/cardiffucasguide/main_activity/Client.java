package com.adamprobert.cardiffucasguide.main_activity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;
import android.util.Log;

public class Client extends AsyncTask<String, Void, Void> {

	/**
	 * Connect to external IP Router routes traffic to my server
	 */
	public final static String hostName = "82.10.140.245";
	public static final int portNumber = 7325;
	Context context;

	public Client(Context context) {
		this.context = context;
		Log.d("UCAS","Client has been initiated");
	}

	@Override
	protected Void doInBackground(String... params) {

		
		// Make sure file log is up to date
		// This needs to go somewhere else
		
		LogFile log = new LogFile(context);
		Map<Integer, String> beaconTimes = BeaconTracker.getInstance().getTimeTracker();
		
		// Loop through map looking for beacon minor id's
		for(int i = 1;i<=9;i++){
			if(beaconTimes.containsKey(i)){
				String input = "B" + " " + Integer.toString(i);
				String times = " " + beaconTimes.get(i);
				log.appendToFile(input + times);
				
			}
		}
		
		/*
		 * Check network connection before connecting socket If no connection,
		 * wait for a connection
		 */

		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		boolean networkConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
		
		// Waits for a secure connection
		while (!networkConnected) {

			activeNetwork = cm.getActiveNetworkInfo();
			networkConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
			
			try {
				Thread.sleep(5000);
				Log.d("UCAS", "CLient - waiting for secure connection");
			} catch (InterruptedException e) {
				Log.e("UCAS", "Client - Error waiting for internet connection");
				e.printStackTrace();
			}

		}

		/**
		 * Connect to external IP Router routes traffic to my server
		 */
		String hostName = "82.10.140.245";
		int portNumber = 6453;

		/** Creates connection and initializes Writer/Reader */
		Socket socket = null;

		try {
			socket = new Socket(hostName, portNumber);
		} catch (IOException e1) {
			Log.e("UCAS", "Client - error creating socket");
			e1.printStackTrace();
		}
		
		Log.d("UCAS", "CLient - Connected to server");


		
		
		
		TelephonyManager tManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		String uuid = tManager.getDeviceId();
		
		DataOutputStream dos = null;
	    
	    try {
	    	
	    	/*
			 * Send device UUID
			 */
	    	
			BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());

	    	dos = new DataOutputStream(bos);
	    	dos.writeUTF(uuid);
	    	
	    	/*
	    	 * Send File
	    	 */
	    	
	    	FileInputStream fis = context.openFileInput("data.csv");
			byte[] byteArray = new byte[(int) fis.getChannel().size()];
			BufferedInputStream bis = new BufferedInputStream(fis);
			bis.read(byteArray, 0, byteArray.length);
			bis.close();
			
			// Sending
			dos.write(byteArray);
			Log.d("UCAS", "Sending...");
			dos.flush();
			Log.d("UCAS", "Sent!");


	    } catch (IOException e) {
	    	Log.d("UCAS","Error sending file");
	        System.err.print(e);
	    } 


		
		try {

			dos.close();
			socket.close();

		} catch (IOException e) {
			Log.e("UCAS", "Client - error closing connection");
			e.printStackTrace();
		}

		Log.d("UCAS", "Client - Returned");
		return null;
	}
}
