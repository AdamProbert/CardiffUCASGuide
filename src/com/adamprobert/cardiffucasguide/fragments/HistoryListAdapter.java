package com.adamprobert.cardiffucasguide.fragments;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.adamprobert.cardiffucasguide.R;
import com.adamprobert.cardiffucasguide.main_activity.Content;

public class HistoryListAdapter extends ArrayAdapter<String> {

	private Context context;
	private List<Content> items;

	public HistoryListAdapter(Context context, int resource, List<Content> items) {
		super(context, resource);

		this.context = context;
		this.items = items;

	}
	
	public void setNewItems(List<Content> newItems)
	{
		items = null;
		this.items = newItems;
	}
	
	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public long getItemId(int position) {

		return items.get(position).getBeaconMinorID();
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Log.w("LIST ADAPTER _ GET VIEW", "Position  = " + position);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.history_item, parent, false);
		
		TextView textView2 = (TextView) rowView.findViewById(R.id.firstLine);
		textView2.setText(items.get(position).getTitle());
		
		TextView textView = (TextView) rowView.findViewById(R.id.secondLine);
		textView.setText(items.get(position).getSubHeading());

		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
//		String uri = "@" + items.get(position).getImageLocation();
		String uri = "@drawable/antenna2";
		int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
		Drawable res = context.getResources().getDrawable(imageResource);
		imageView.setImageDrawable(res);

		return rowView;
	}

	
}
