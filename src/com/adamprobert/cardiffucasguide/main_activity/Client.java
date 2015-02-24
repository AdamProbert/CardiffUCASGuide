package com.adamprobert.cardiffucasguide.main_activity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class Client extends AsyncTask<String, Void, Void> {

	/**
	 * Connect to external IP Router routes traffic to my server
	 */
	public final static String hostName = "82.10.140.245";
	public static final int portNumber = 7325;
	Context context;
	
	public Client(Context context){
		this.context = context;
	}
	
	@Override
	protected Void doInBackground(String... params) {
		/**
		 * Connect to external IP Router routes traffic to my server
		 */

		String hostName = "82.10.140.245";
		int portNumber = 6453;

		/** Creates connection and initialises Writer/Reader */
		Socket socket = null;

		try {
			socket = new Socket(hostName, portNumber);
		} catch (IOException e1) {
			Log.e("UCAS", "Client - error creating socket, printwriter, bufferedReader");
			e1.printStackTrace();
		}

		/** Send file server data.csv */

		try {
			
			FileInputStream fis = context.openFileInput("data.csv");
			byte[] byteArray = new byte[(int) fis.getChannel().size()];
			BufferedInputStream bis = new BufferedInputStream(fis);
			
			bis.read(byteArray, 0, byteArray.length);
			OutputStream os = socket.getOutputStream();
			Log.d("UCAS","Sending...");
			
			os.write(byteArray, 0, byteArray.length);
			os.flush();
			Log.d("UCAS","Sent!");

			socket.close();
			bis.close();
			
		} catch (IOException e) {
			Log.e("UCAS", "Client - error sending data");
			e.printStackTrace();
		}

		return null;
	}
}
