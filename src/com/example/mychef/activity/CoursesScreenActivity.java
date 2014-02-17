package com.example.mychef.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mychef.R;

public class CoursesScreenActivity extends Activity{
		public enum MenuName {
			Appetizers("appetizers"),
			Beverages("beverages"),
			Bread("bread"),
			Breakfast("breakfast-brunch"),
			Cake("cakes-and-tortes"),
			Cookies("cookies"),
			Custards("custards-and-creams"),
			Desserts("desserts"),
			Dressings("dressings");

			  private String text;

			  MenuName(String text) {
			    this.text = text;
			  }

			  public String getText() {
			    return this.text;
			  }

			  public static MenuName fromString(String text) {
			    if (text != null) {
			      for (MenuName b : MenuName.values()) {
			        if (text.equalsIgnoreCase(b.text)) {
			          return b;
			        }
			      }
			    }
			    return null;
			  }
			}
		
		private void callWebService(String option){
			MenuName key = MenuName.fromString(option);
			String id;
			switch(key){
			case Appetizers:
				id = key.getText();
				break;
			case Beverages:
				id = key.getText();
				break;
			case Bread:
				id = key.getText();
				break;
			case Breakfast:
				id = key.getText();
				break;
			case Cake:
				id = key.getText();
				break;
			case Cookies:
				id = key.getText();
				break;
			case Custards:
				id = key.getText();
				break;	
			case Desserts:
				id = key.getText();
				break;
			case Dressings:
				id = key.getText();
				break;
				
			}
			
		}
		
		

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_coursesmenu);
			
			// Appetizers
			Button btnAppertizers = (Button) findViewById(R.id.btnAppetizers);
			btnAppertizers.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					callWebService("appetizers");
				}
			});	
			
			TextView txtAppatizers = (TextView) findViewById(R.id.textAppetizers);
			txtAppatizers.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					callWebService("appetizers");
				}
			});
			
			// Beverages
					Button btnBeverages = (Button) findViewById(R.id.btnBeverages);
					btnBeverages.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							callWebService("beverages");
						}
					});	
					
					TextView txtBeverages = (TextView) findViewById(R.id.textBeverages);
					txtBeverages.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							callWebService("beverages");
						}
					});
					
			// Bread
					Button btnBread = (Button) findViewById(R.id.btnBread);
					btnBread.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							callWebService("bread");
						}
					});	
					
					TextView txtBread = (TextView) findViewById(R.id.textBread);
					txtBread.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							callWebService("bread");
						}
					});
					
			// Breakfast 
					Button btnBreakfast  = (Button) findViewById(R.id.btnBreakfast);
					btnBreakfast.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							callWebService("breakfast-brunch");
						}
					});	
					
					TextView txtBreakfast  = (TextView) findViewById(R.id.textBreakfast);
					txtBreakfast.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							callWebService("breakfast-brunch");
						}
					});
					
			// Cakes 
					Button btnCakes   = (Button) findViewById(R.id.btnCakes);
					btnCakes.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							callWebService("cakes-and-tortes");
						}
					});	
					
					TextView txtCakes   = (TextView) findViewById(R.id.textCakes);
					txtCakes.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							callWebService("cakes-and-tortes");
						}
					});
					
			// Cookies 
					Button btnCookies   = (Button) findViewById(R.id.btnCookies);
					btnCookies.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							callWebService("cookies");
						}
					});	
					
					TextView txtCookies   = (TextView) findViewById(R.id.textCookies);
					txtCookies.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							callWebService("cookies");
						}
					});
					
			// Custards 
					Button btnCustards   = (Button) findViewById(R.id.btnCustards);
					btnCustards.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							callWebService("custards-and-creams");
						}
					});	
					
					TextView txtCustards   = (TextView) findViewById(R.id.textCustards);
					txtCustards.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							callWebService("custards-and-creams");
						}
					});
					
			// Desserts 
					Button btnDesserts   = (Button) findViewById(R.id.btnDesserts);
					btnDesserts.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							callWebService("desserts");
						}
					});	
					
					TextView txtDesserts   = (TextView) findViewById(R.id.textDesserts);
					txtDesserts.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							callWebService("desserts");
						}
					});
					
			// Dressings
					Button btnDressings   = (Button) findViewById(R.id.btnDressings);
					btnDressings.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							callWebService("dressings");
						}
					});	
					
					TextView txtDressings   = (TextView) findViewById(R.id.textDressings);
					txtDressings.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							callWebService("dressings");
						}
					});
		}
		
		
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// TODO Auto-generated method stub
	        MenuInflater menuInflater = getMenuInflater();
	        menuInflater.inflate(R.menu.startscreen, menu);
	        return true;
		}

		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			// TODO Auto-generated method stub
		       switch (item.getItemId())
		        {
		        case R.id.menu_Back:
		        	startActivity(new Intent("com.example.hackton.mainmenu"));
		        	finish();
		        	return true;
		        case R.id.menu_Exit:
		            onDestroy();
		            return true;
		        default:
		            return super.onOptionsItemSelected(item);
		        }
		}
					
					
	}
		
		
		
		
