package com.adamprobert.cardiffucasguide.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adamprobert.cardiffucasguide.R;

public class Cardiff extends Fragment{
	
	public Cardiff() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.cardiff_layout, container, false);
		return rootView;
	}

}