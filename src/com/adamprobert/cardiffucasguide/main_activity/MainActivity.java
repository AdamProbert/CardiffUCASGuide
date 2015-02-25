package com.adamprobert.cardiffucasguide.main_activity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.widget.Toast;

import com.adamprobert.cardiffucasguide.R;
import com.adamprobert.cardiffucasguide.fragments.BeaconFragment;
import com.adamprobert.cardiffucasguide.fragments.Cardiff;
import com.adamprobert.cardiffucasguide.fragments.ComSci;
import com.adamprobert.cardiffucasguide.fragments.History;

public class MainActivity extends ActionBarActivity implements ActionBar.TabListener {

	private SectionsPagerAdapter mSectionsPagerAdapter;
	private ViewPager mViewPager;
	private static final String PREFS_NAME = "UCASPreferencesFile";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		/**
		 * If first time running:
		 * Will display welcome message / tutorial
		 * Create log file
		 * 
		 */
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		boolean firstRun = settings.getBoolean("firstRun", true);
		
		if(firstRun){
			
			// Welcome message
			Toast.makeText(this, "Welcome to your UCAS day tour guide!", Toast.LENGTH_LONG).show();
			
			// Create file
			String filename = "data.csv";
			
			String _RELEASE = "BUILD VERSION: " + android.os.Build.VERSION.RELEASE;
			String _DEVICE = "DEVICE: " + android.os.Build.DEVICE; 
			String _MODEL = "MODEL: " + android.os.Build.MODEL; 
			String _PRODUCT = "PRODUCT: " + android.os.Build.PRODUCT; 
			String _HARDWARE = "HARDWARE: " + android.os.Build.HARDWARE;
			String _USER = "USER: " + android.os.Build.USER; 

			String info = _USER + "\n" + _RELEASE + "\n" + _DEVICE + "\n" + _MODEL + "\n" + _PRODUCT + "\n" + _HARDWARE;
			FileOutputStream fos = null;
			try {
				fos = openFileOutput(filename, Context.MODE_PRIVATE);
				fos.write(info.getBytes());
				fos.close();
				Log.d("UCAS", "data.csv has been created");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// Set first run to false
			SharedPreferences.Editor editor = settings.edit();
			editor.putBoolean("firstRun", false);
			editor.commit();
		}

		// Set up the action bar.
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setTitle(Html.fromHtml("<font color='#cc0000'>Cardiff UCAS Guide </font>"));

		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});

		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {

			actionBar.addTab(actionBar.newTab().setText(mSectionsPagerAdapter.getPageTitle(i)).setTabListener(this));
		}

	}

	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		mViewPager.setCurrentItem(tab.getPosition());
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.

			switch (position) {
			case 0:
				BeaconFragment beacon = new BeaconFragment();
				return beacon;
			case 1:
				History history = new History();
				return history;
			case 2:
				Cardiff cardiff = new Cardiff();
				return cardiff;
			case 3:
				ComSci comsci = new ComSci();
				return comsci;
			default:
				Log.w("MainActivity - getItem", "Error creating fragment instances");
				Cardiff fragment = new Cardiff();
				return fragment;
			}

		}

		@Override
		public int getCount() {
			// Show 4 total pages.
			return 4;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			case 3:
				return getString(R.string.title_section4).toUpperCase(l);
			}
			return null;
		}
	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
	}

}
