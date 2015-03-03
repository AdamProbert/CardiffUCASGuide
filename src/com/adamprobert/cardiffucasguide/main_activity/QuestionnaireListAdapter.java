package com.adamprobert.cardiffucasguide.main_activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.adamprobert.cardiffucasguide.R;

public class QuestionnaireListAdapter extends ArrayAdapter<String> {

	private Context context;
	private List<String> items;
	private Map<Object, Integer> checkId;

	public QuestionnaireListAdapter(Context context, int resource, List<String> items) {
		super(context, resource);

		this.context = context;
		this.items = items;
		// <Question index, Radio button ticked>
		checkId = new HashMap<Object, Integer>();

	}

	public boolean allAnswered() {

		// Checks the hashmap has values for every question
		if (checkId.size() == items.size()) {
			
			// Second check to ensure each field has a correct value
			for (int i = 0; i < checkId.size(); i++) {
				if (checkId.get(i) == null || checkId.get(i) == 0) { return false; }
			}
		} else {
			// hashmap has less entries than questions
			return false;
		}

		// hashmap all fields have a correct value
		return true;

	}

	public String getCheckedValues() {

		String checkedValues = "Q ";
		for (int i = 0; i < checkId.size(); i++) {
			// Gets last integer of radio button id which is a number between 0
			// + 4 then +1
			// This is risky, need to test!!

			int id = (checkId.get(i) % 10) + 1;
			checkedValues += Integer.toString(id) + ",";

		}
		// Removes last comma from the asnwers string
		checkedValues = checkedValues.substring(0,checkedValues.length()-1);
		return checkedValues;
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

		/*
		 * Set width of questions on questionnaire to half screen size. This
		 * probably won't work
		 */
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;

		TextView question = (TextView) rowView.findViewById(R.id.question);
		question.setWidth(width / 2);
		question.setText(items.get(position));

		RadioGroup rGroup = (RadioGroup) rowView.findViewById(R.id.answer);
		rGroup.setTag(position);
		if (checkId.get(rGroup.getTag()) != null) {
			rGroup.check(checkId.get(rGroup.getTag()));

		}
		rGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				// Save checked state to hashmap
				Log.d("UCAS", "OncheckedChanged called");
				checkId.put(group.getTag(), checkedId);

			}

		});

		return rowView;
	}

}
