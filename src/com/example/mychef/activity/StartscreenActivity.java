package com.example.mychef.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.mychef.R;

public class StartscreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startscreen);
        
        Thread splash_screen = new Thread(){
        	
        	public void run(){
        		try{
        			int counter = 4000;
        			while(counter>0)
        			{
        				sleep(100);
        				counter = counter - 100;
        			}
        			Intent intent = new Intent(getBaseContext(), ManiMenuActivity.class);
        			startActivity(intent);
        		}
        		catch(Exception e)
        		{
        			e.printStackTrace();
        		}
        		finally
        		{
        			finish();	
        		}
        	}
        	
        };
        splash_screen.start();
    }

    
}
