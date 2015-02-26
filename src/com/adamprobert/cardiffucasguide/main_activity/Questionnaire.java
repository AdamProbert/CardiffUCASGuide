package com.adamprobert.cardiffucasguide.main_activity;

import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.adamprobert.cardiffucasguide.R;

public class Questionnaire extends ActionBarActivity {
	
	ListView questionList;
	List<String> questions;
	QuestionnaireListAdapter qA;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("UCAS", "Questionnaire has been created");
		setContentView(R.layout.questionnaire);

		final ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(Html.fromHtml("<font color='#cc0000'>Questionnaire</font>"));
		
		// Views
		
		questionList = (ListView)findViewById(R.id.questionList);
		questions = Arrays.asList(getResources().getStringArray(R.array.questions));
		qA = new QuestionnaireListAdapter(this, R.layout.questionnaire, questions);
		questionList.setAdapter(qA);

		Button acceptButton = (Button) findViewById(R.id.acceptButton);
		acceptButton.setOnClickListener(clickListener);
		
		

	};

	private OnClickListener clickListener = new OnClickListener() {
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.acceptButton:
				
				boolean allQuestionsAnswered = true;

				/**
				 * Check if all boxes have been selected Save questionnaire file
				 */
				for (int i = 0; i < questionList.getCount(); i++) {
					View v = questionList.getChildAt(i);
					RadioGroup answer = (RadioGroup) v.findViewById(R.id.answer);
					if(answer.getCheckedRadioButtonId() == -1){
						Toast.makeText(getBaseContext(), "Please ensure all questions are answered", Toast.LENGTH_LONG).show();
						allQuestionsAnswered = false;
						
					}
				}
				
				if(allQuestionsAnswered){
					getAnswers();	
				}
				
				break;
			}

		}
	};

	private void getAnswers() {
		Log.d("UCAS", "Questionnaire - getAnswers called");

		String stringAnswers = "";
		View v;
		RadioGroup answer;
		
		/*
		 * Loop through each row of questionnaire
		 * Collect each answer
		 * Store in comma separated string
		 */
		
		for (int i = 0; i < questionList.getCount(); i++) {
	        v = questionList.getChildAt(i);
	        answer = (RadioGroup) v.findViewById(R.id.answer);
	        int id = answer.getCheckedRadioButtonId();
	        View radioButton = answer.findViewById(id);
	        int checkedId = answer.indexOfChild(radioButton) +1; // Values 1 - 5
	        
			stringAnswers += checkedId + ", ";
	    }
		
		LogFile log = new LogFile(this);
		log.appendToFile(stringAnswers);
		
		Toast.makeText(this, "Thankyou very much!", Toast.LENGTH_LONG).show();
		
		this.finish();
	}
}
