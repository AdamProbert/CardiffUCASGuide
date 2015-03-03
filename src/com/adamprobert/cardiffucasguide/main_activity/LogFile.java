package com.adamprobert.cardiffucasguide.main_activity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.util.Log;

public class LogFile {
	
	private Context context;
	
	public LogFile(Context context){
		Log.d("UCAS", "new log file object created");
		this.context = context;
		
	}
	
	public void appendToFile(String input){
		
		String text = "\n\n" + input;
		FileOutputStream fos = null;
		String file = "data.csv";

		try {
			fos = context.openFileOutput(file, Context.MODE_APPEND | Context.MODE_PRIVATE);
			fos.write(text.getBytes());

		} catch (FileNotFoundException e) {
			Log.d("UCAS", "LogFile - file not found");
			e.printStackTrace();
		} catch (IOException e) {
			Log.d("UCAS", "LogFile - ioexception");
			e.printStackTrace();
		} finally {
			// Close streams
			try {
				if (fos != null) {
					fos.close();
					Log.d("UCAS","Logfile has been updated");
				}
			} catch (IOException ioe) {
				Log.d("UCAS", "Error closing file output stream");
			}

		}

		
		
	}
	

}
