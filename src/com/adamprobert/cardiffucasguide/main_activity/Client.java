package com.adamprobert.cardiffucasguide.main_activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import android.os.AsyncTask;
import android.util.Log;

public class Client extends AsyncTask<String, Void, Void> {

	/**
	 * Connect to external IP Router routes traffic to my server
	 */
	public final static String hostName = "82.10.140.245";
	public static final int portNumber = 7325;

	@Override
	protected Void doInBackground(String... params) {
		/**
		 * Connect to external IP Router routes traffic to my server
		 */

		String hostName = "82.10.140.245";
		int portNumber = 6453;
		PrintWriter out = null;
		BufferedReader in = null;

		/** Creates connection and initialises Writer/Reader */

		Socket socket = null;
		try {
			socket = new Socket(hostName, portNumber);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		} catch (IOException e1) {
			Log.e("UCAS", "Client - error creating socket, printwriter, bufferedReader");
			e1.printStackTrace();
		}

		/** Communication between server/client using KnockKnock Protocol */
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String fromServer = null;
		String fromUser = null;

		try {
			fromServer = in.readLine();
			if(fromServer != null){
			Log.d("UCAS", "Server: " + fromServer);
			if (fromServer.equals("Bye."))
				socket.close();

			}
			
			fromUser = stdIn.readLine();
			if (fromUser != null) {
				System.out.println("Client: " + fromUser);
				out.println(fromUser);
			}

		} catch (IOException e) {
			Log.e("UCAS", "Client - error recieving data");
			e.printStackTrace();
		}
		
		return null;
	}
}
