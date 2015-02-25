package com.adamprobert.cardiffucasguide.main_activity;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.adamprobert.cardiffucasguide.R;

public class QuestionnaireListAdapter extends ArrayAdapter<String> {
	
	private Context context;
	private List<String> items;
	private int[] checkedRadio;

	public QuestionnaireListAdapter(Context context, int resource, List<String> items) {
		super(context, resource);

		this.context = context;
		this.items = items;
		checkedRadio = new int[10];

	}
	
	

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.questionnair_question, parent, false);
		
		TextView question = (TextView) rowView.findViewById(R.id.question);
		question.setText(items.get(position));

		RadioGroup answerGroup = (RadioGroup)rowView.findViewById(R.id.answer);

		return rowView;
	}

	

}
