package com.adamprobert.cardiffucasguide.main_activity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
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

		/*
		 * Check network connection before connecting socket If no connection,
		 * wait for a connection
		 */

		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

		while (!isConnected) {

			activeNetwork = cm.getActiveNetworkInfo();
			isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
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

		/** Creates connection and initialises Writer/Reader */
		Socket socket = null;

		try {
			socket = new Socket(hostName, portNumber);
		} catch (IOException e1) {
			Log.e("UCAS", "Client - error creating socket");
			e1.printStackTrace();
		}
		
		Log.d("UCAS", "CLient - Connected to server");


		/** Send file server data.csv */

		try {

			FileInputStream fis = context.openFileInput("data.csv");
			byte[] byteArray = new byte[(int) fis.getChannel().size()];
			BufferedInputStream bis = new BufferedInputStream(fis);

			bis.read(byteArray, 0, byteArray.length);
			OutputStream os = socket.getOutputStream();
			Log.d("UCAS", "Sending...");

			os.write(byteArray, 0, byteArray.length);
			os.flush();
			Log.d("UCAS", "Sent!");

			socket.close();
			bis.close();

		} catch (IOException e) {
			Log.e("UCAS", "Client - error sending data");
			e.printStackTrace();
		}

		Log.d("UCAS", "Client - Returned");
		return null;
	}
}
