package com.example.mychef.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mychef.R;

public class ManiMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mainmenu);
	
		Button btnCourses = (Button) findViewById(R.id.Button01);
		btnCourses.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(), CoursesScreenActivity.class);
				startActivity(intent);				
			}
		});	
		
		Button btnSearch = (Button) findViewById(R.id.Button04);
		btnSearch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(), SearchScreenActivity.class);
				startActivity(intent);				
			}
		});	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
	
	

}
