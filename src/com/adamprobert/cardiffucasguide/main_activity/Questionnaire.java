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

		// Set title to questionnaire and red
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(Html.fromHtml("<font color='#cc0000'>Questionnaire</font>"));

		// Views
		questionList = (ListView) findViewById(R.id.questionList);
		questions = Arrays.asList(getResources().getStringArray(R.array.questions));
		qA = new QuestionnaireListAdapter(this, R.layout.questionnaire, questions);
		questionList.setAdapter(qA);

		Button acceptButton = (Button) findViewById(R.id.acceptButton);
		acceptButton.setOnClickListener(clickListener);
		
		// add notification time
		BeaconTracker.getInstance().addNotificationTime(5);


	};

	private OnClickListener clickListener = new OnClickListener() {
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.acceptButton:

				if (qA.allAnswered()) {
					
					String sAnswers = qA.getCheckedValues();

					LogFile log = new LogFile(Questionnaire.this);
					log.appendToFile(sAnswers);

					Toast.makeText(Questionnaire.this, "Thank you very much!", Toast.LENGTH_LONG).show();
					
					// Add questionnaire close time
					BeaconTracker.getInstance().addNotificationTime(5);
					Client client = new Client(Questionnaire.this);
					client.execute();
					
					finish();
					
				} else {
					Toast.makeText(Questionnaire.this, "Please ensure all questions are answered", Toast.LENGTH_LONG)
							.show();
				}

			}
		}
	};

}
