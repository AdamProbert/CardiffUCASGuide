package com.adamprobert.cardiffucasguide.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.adamprobert.cardiffucasguide.R;
import com.adamprobert.cardiffucasguide.main_activity.Client;

public class ComSci extends Fragment {

	Button button;
	Context context;

	public ComSci() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		if (getActivity() != null) {
			context = getActivity();

		}
		
		View rootView = inflater.inflate(R.layout.comsci_layout, container, false);
		TextView textView = (TextView) rootView.findViewById(R.id.section_label);
		textView.setText("This is comsci");

		button = (Button) rootView.findViewById(R.id.button1);
		button.setOnClickListener(handleClick);

		return rootView;
	}

	
	// Launch Client 
	
	private OnClickListener handleClick = new OnClickListener() {
		public void onClick(View view) {
			
			switch (view.getId()) {
			case R.id.button1:
				Client client = new Client(context);
				client.execute();

				break;

			}

		}
	};

}
