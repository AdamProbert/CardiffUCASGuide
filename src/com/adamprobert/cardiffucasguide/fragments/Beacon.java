package com.adamprobert.cardiffucasguide.fragments;

import com.adamprobert.cardiffucasguide.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Beacon extends Fragment {
	
	public Beacon() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.beacon_layout, container, false);
		TextView textView = (TextView) rootView.findViewById(R.id.section_label);
		textView.setText("This is beacon");
		return rootView;
	}

}
