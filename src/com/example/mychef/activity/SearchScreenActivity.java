package com.example.mychef.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.mychef.R;

public class SearchScreenActivity extends Activity{

	EditText txtName;
	EditText txtCuisin;
	EditText txtMethod;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchscreen);
		
		
		txtName=(EditText) findViewById(R.id.txtSerchName);
		txtCuisin=(EditText) findViewById(R.id.txtSerchCuisine);
		txtMethod=(EditText) findViewById(R.id.txtSerchMethod);
		
		
	}
	
	
	
	
	

}
