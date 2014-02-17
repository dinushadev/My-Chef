package com.example.mychef.activity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mychef.R;
import com.example.mychef.db.Db;
import com.example.mychef.db.RecipeDbHelper;
import com.example.mychef.dto.CookingMethodResponceDto;
import com.example.mychef.dto.IngreadintDto;
import com.example.mychef.dto.RecipeDto;

public class RecipeActivity extends Activity {

	
	private static final String TAG = MainActivity.class.getSimpleName();
	RecipeDto recipeDto;
	TextView heading ;
	TextView subHeading;
	TextView method;
	TextView ingredients;
	TextView directions;
	ImageView image;
	
	
	

	Db dbConnection;
	SQLiteDatabase db;
	RecipeDbHelper recipHelper;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recipe_data);
		
		Intent intent = getIntent();
		recipeDto = (RecipeDto) getIntent().getSerializableExtra("EXTRA_RESIPE");
		
		heading = (TextView) findViewById(R.id.txtRcRecipeName);
		subHeading = (TextView) findViewById(R.id.txtRcCusineName);
		method = (TextView) findViewById(R.id.txtRcMethod);
		ingredients = (TextView) findViewById(R.id.txtRcIngredients);
		image = (ImageView) findViewById(R.id.recipeRcImage);
		directions =(TextView) findViewById(R.id.textRcDirections);
		
		
		dbConnection = new Db(this);

		db = dbConnection.getWritableDatabase();
		recipHelper= new RecipeDbHelper(db);
		
		
		if(recipeDto!=null){

			heading.setText(recipeDto.getName());
			subHeading.setText(recipeDto.getCuisine());
			method.setText(recipeDto.getCooking_method());
			
			//addIgradiant(ingredients, recipeDto.getIngredients());
				
		}
		
		
		try{
			Cursor cursor= recipHelper.findRecipeById(recipeDto.getId());
			
			byte[] accImage =null;
			if(cursor!= null &&cursor.moveToFirst()){
				 accImage = cursor.getBlob(3);
				 if(accImage!=null){

					 ByteArrayInputStream imageStream = new ByteArrayInputStream(accImage);
					 Bitmap theImage = BitmapFactory.decodeStream(imageStream);

					 image.setImageBitmap(theImage);
				 }else{
					 new DownloadImageTask((ImageView)  image ).execute(recipeDto);
				 }
			}else{
				 new DownloadImageTask((ImageView)  image ).execute(recipeDto);
			}
			
			
			}catch (Exception e) {
				Log.e(TAG, e.getMessage(),e);
			}
		
		new DownloadImageTask((ImageView)  image).execute(recipeDto);
		new LoadRecipeListTask().execute(recipeDto.getUrl());
	}
	
	
	
	
	private class DownloadImageTask extends AsyncTask<RecipeDto, Void, Bitmap> {
	    ImageView bmImage;
	    ProgressDialog pd=null;
	    RecipeDto currDto;

	    public DownloadImageTask(ImageView bmImage) {
	        this.bmImage = bmImage;
	    }

	    protected Bitmap doInBackground(RecipeDto... dto) {
	    	currDto = dto[0];
	        Bitmap mIcon11 = null;
	        try {
	            InputStream in = new java.net.URL(currDto.getImage()).openStream();
	            mIcon11 = BitmapFactory.decodeStream(in);
	        } catch (Exception e) {
	            Log.e("Error", e.getMessage());
	            e.printStackTrace();
	        }
	        return mIcon11;
	    }

		@Override
		protected void onPreExecute() {
			pd = ProgressDialog.show(RecipeActivity.this, "Loding..",
					"Please wait, Loding the recipe");
		}
	/*    @Override
	    protected void onPreExecute() {
	        // TODO Auto-generated method stub
	        super.onPreExecute();
	        showProgressDialog();
	    }*/

	    protected void onPostExecute(Bitmap result) {
	    	pd.dismiss();
	        bmImage.setImageBitmap(result);
	        
	        
	        try {
	        	//Update Recipe
		    
		    	
		    	db.execSQL("BEGIN TRANSACTION");
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				result.compress(CompressFormat.JPEG, 100, outputStream);       
				


			    

	        	ContentValues values = new ContentValues();
	        	values.put(RecipeDbHelper.IMAGE, outputStream.toByteArray());
	        	//values.put(KEY_IMAGE, result);

	        	// updating row
	        	 db.update(RecipeDbHelper.TABLE, values, RecipeDbHelper.KEY + " = ?", new String[] { currDto.getId() });
	        	 
	        	 db.execSQL("COMMIT");
	 			db.close();
			} catch (Exception e) {
			//	Log.e(TAG, e.getMessage());
			}
	        
	    }

 
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recipe, menu);
		return true;
	}
	
	private void addIgradiant(TextView tx ,List<IngreadintDto> list){
		StringBuilder sb = new StringBuilder();
		
		
		if(list!=null ){
			
			for (IngreadintDto dto : list) {
				sb.append("\t");
				sb.append("* ");
				sb.append(dto.getName());
				if(dto.getQuantity()!=null){

					sb.append(" -Quntity:"+dto.getQuantity()+"/"+dto.getUnit());
				}else{

					sb.append(" -"+dto.getUnit());
				}
				
				sb.append("\n");
				if(dto.getPreparation()!=null){

					sb.append("\t");
					sb.append(dto.getPreparation());
					sb.append("\n");
				
				}
				
			}
			
		}
		
		tx.setText(sb);
	}
	
	
	
	
	

	public class LoadRecipeListTask extends AsyncTask<String, Void, String> {
		ProgressDialog pd;
		
	
		@Override
		protected String doInBackground(String... url) {	HttpClient client = new DefaultHttpClient();

		HttpGet get = new HttpGet(url[0]);

		ResponseHandler<String> responseHandler = new BasicResponseHandler();

		String response = null;

		try {
			response = client.execute(get, responseHandler);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return response;
		}
		
		@Override
		protected void onPreExecute() {
			pd = ProgressDialog.show(RecipeActivity.this, "Loding..",
					"Please wait, Loding the recipe");}
	
		@Override
		protected void onPostExecute(String result) {
			pd.dismiss();
			loadRecipe(result);
		}

	
	}
	
	
	
	private void loadRecipe(String result) {
	
		Log.d(getClass().getName(), "Done.. parsing data now..");
		Log.i(TAG,result);
		

		JSONObject json = null;
		
		try {
			json = new JSONObject(result);
			
		
		} catch (JSONException e) {
			e.printStackTrace();
		}

		CookingMethodResponceDto responceDto =CookingMethodResponceDto.ConsumeObject(json);
		
	if(responceDto!=null){
		addIgradiant(ingredients, responceDto.getIngredients());
		
		addDirection(directions, responceDto.getDirections());
		
	}
		
		
	
		

		
		
		
	}
	
	private void addDirection(TextView tx ,List<String> ingreadiant){
		StringBuilder sb = new StringBuilder();
		
		
		if(ingreadiant!=null && !ingreadiant.isEmpty()){
			
			for (int i=0;i <ingreadiant.size(); i++) {
				sb.append(" "+(i+1)+")");
				sb.append(ingreadiant.get(i));
				sb.append("\n");
			}
			
		}
		
		tx.setText(sb);
	}
		

}
