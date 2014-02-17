package com.example.mychef.activity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mychef.R;
import com.example.mychef.db.Db;
import com.example.mychef.db.RecipeDbHelper;
import com.example.mychef.dto.RecipeDto;
import com.example.mychef.dto.RecipeResponceDto;
public class MainActivity extends ListActivity {

	ArrayList<RecipeDto> data;
	private String endpoint="https://api.pearson.com/kitchen-manager/v1/courses/appetizers?apikey=8bbdcd911e80ce01ec2280a270f87477";
	
	private static final String TAG = MainActivity.class.getSimpleName();


	ArrayAdapter adapter;
	Db dbConnection;
	SQLiteDatabase db;
	RecipeDbHelper recipHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		//this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	protected void onStart() {
	
		super.onStart();
		dbConnection = new Db(this);

		db = dbConnection.getWritableDatabase();
		recipHelper= new RecipeDbHelper(db);
		
		
		
		new LoadRecipeListTask().execute(endpoint);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		RecipeDto res= (RecipeDto) adapter.getItem(position);
		
		Intent intent = new Intent(getBaseContext(), RecipeActivity.class);
		intent.putExtra("EXTRA_RESIPE", res);
		startActivity(intent);
		
		//Toast.makeText(this, "Item click"+adapter.getItem(position)+" Position:"+position, Toast.LENGTH_LONG).show();
	}
	
	private void showResult(List<RecipeDto> hangouts) {
		 adapter = new HangoutListCustomAdapter(this,R.layout.recipe_item, hangouts);
		setListAdapter(adapter);
	}
	
	class HangoutListCustomAdapter extends ArrayAdapter<RecipeDto> {

		private Context context;
		private List<RecipeDto> data;

		public HangoutListCustomAdapter(Context context, int textViewResourceId,
				List<RecipeDto> data) {
			super(context, textViewResourceId, data);

			this.context = context;
			this.data = data;
		}


		
		
		
		private class DownloadImageTask extends AsyncTask<RecipeDto, Void, Bitmap> {
		    ImageView bmImage;
		    RecipeDto curntDto;

		    public DownloadImageTask(ImageView bmImage) {
		        this.bmImage = bmImage;
		    }

		    protected Bitmap doInBackground(RecipeDto... dto) {
		    	curntDto=dto[0];
		        String urldisplay = dto[0].getImage();
		        Bitmap mIcon11 = null;
		        try {
		            InputStream in = new java.net.URL(urldisplay).openStream();
		            mIcon11 = BitmapFactory.decodeStream(in);
		        } catch (Exception e) {
		            Log.e("Error", e.getMessage());
		            e.printStackTrace();
		        }
		        return mIcon11;
		    }


		/*    @Override
		    protected void onPreExecute() {
		        // TODO Auto-generated method stub
		        super.onPreExecute();
		        showProgressDialog();
		    }*/

		    protected void onPostExecute(Bitmap result) {
		        //pDlg.dismiss();
		        bmImage.setImageBitmap(result);
		        
		        //insert to db
		    	db.execSQL("BEGIN TRANSACTION");
		    	
		    	try {
					
						ContentValues cv = new ContentValues();
						
						ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
						result.compress(CompressFormat.JPEG, 100, outputStream);       
						


					    
						cv.put(RecipeDbHelper.KEY, curntDto.getId());
						cv.put(RecipeDbHelper.THUMB, outputStream.toByteArray());
						cv.put(RecipeDbHelper.NAME, curntDto.getName());

						db.insert(RecipeDbHelper.TABLE, null, cv);

						Log.d(getClass().getName(),  curntDto.getName() + " => "
								+ curntDto.getId());

					} catch (Exception e) {
						e.printStackTrace(); 
					}
				

				db.execSQL("COMMIT");
			//	db.close();
		    }}
		
		

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View view;
			ViewHolder viewHolder;

			if (null == convertView) {

				LayoutInflater inflator = (LayoutInflater) context
						.getSystemService(LAYOUT_INFLATER_SERVICE);
				view = inflator.inflate(R.layout.recipe_item, null);

				viewHolder = new ViewHolder(view);
				view.setTag(viewHolder);


			} else {
				view = convertView;
				viewHolder = (ViewHolder) view.getTag();

			}


			viewHolder.name.setText(data.get(position).getName());
			viewHolder.cuisine.setText( data.get(position).getCuisine());
			
			try{
			Cursor cursor= recipHelper.findRecipeById(data.get(position).getId());
			
			byte[] accImage =null;
			if(cursor!= null &&cursor.moveToFirst()){
				 accImage = cursor.getBlob(2);
				 if(accImage!=null){

					 ByteArrayInputStream imageStream = new ByteArrayInputStream(accImage);
					 Bitmap theImage = BitmapFactory.decodeStream(imageStream);
					 
						
						ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
						theImage.compress(CompressFormat.JPEG, 100, outputStream);       
						

						viewHolder.profile.setImageBitmap(theImage);
				 }else{
					 new DownloadImageTask((ImageView)  viewHolder.profile ).execute(data.get(position));
				 }
			}else{
				 new DownloadImageTask((ImageView)  viewHolder.profile ).execute(data.get(position));
			}
			
			
			}catch (Exception e) {
				Log.e(TAG, e.getMessage(),e);
			}
			/*
			URL url;
			try {
				url = new URL(data.get(position).getThumb());
				Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
				
				
				viewHolder.profile.setImageBitmap(bmp);
			} catch (MalformedURLException e) {
				Log.e(TAG, e.getMessage(), e);
				e.printStackTrace();
			} catch (IOException e) {
				Log.e(TAG, e.getMessage(), e);
			}
		*/
			
			/*try {
			Bitmap bmp = BitmapFactory.decodeStream(new FlushedInputStream((InputStream) new URL(data.get(position).getThumb()).getContent()));
			
		
			
			viewHolder.profile.setImageBitmap(bmp);
		} catch (MalformedURLException e) {
			Log.e(TAG, e.getMessage(), e);
			e.printStackTrace();
		} catch (IOException e) {
			Log.e(TAG, e.getMessage(), e);
		}
	
		*/
			
			
			

			return view;

		}
	}

	class ViewHolder {
		ImageView profile;
		TextView name;
		TextView cuisine;

		ViewHolder(View view) {
			profile =(ImageView)view.findViewById(R.id.imgProfile);
			name = (TextView) view.findViewById(R.id.txtRecipName);
			cuisine = (TextView) view.findViewById(R.id.txtCuisinName);

		}

	}
	
	
	
	public void addToList(String response) {

		Log.d(getClass().getName(), "Done.. parsing data now..");

		

		JSONObject json = null;
		JSONArray jsonArry = null;
		
		try {
			json = new JSONObject(response);
			
		
		} catch (JSONException e) {
			e.printStackTrace();
		}

		RecipeResponceDto responceDto =RecipeResponceDto.ConsumeObject(json);
		
		Log.i(TAG,response);
		
		showResult(responceDto.getRecipes());

		
		
		

	}

	
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		 /* switch (item.getItemId())
	        {
		  case R.id.main_search:
			  break;

		  case R.id.main_preferences:
			  
				Intent regActivityIntent = new Intent(this, HangOutSettingsActivity.class);
                startActivity(regActivityIntent);  
			  break;

		  case R.id.main_friends:
			  
				Intent friendActivityIntent = new Intent(this, FriendActivity.class);
                startActivity(friendActivityIntent);  
			  break;
		  case R.id.main_new:
			  
				Intent newHangActivityIntent = new Intent(this, NewHangOutActivity.class);
              startActivity(newHangActivityIntent);  
			  break;
		  
	        }*/

		
		return super.onOptionsItemSelected(item);
	}
	
		
	
	public class LoadRecipeListTask extends AsyncTask<String, Void, String> {
		ProgressDialog pd;
		
	
		@Override
		protected String doInBackground(String... url) {	
			
	HttpClient client = new DefaultHttpClient();

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
			pd = ProgressDialog.show(MainActivity.this, "Loding..",
					"Please wait, Loding the list");}
	
		@Override
		protected void onPostExecute(String result) {
			pd.dismiss();
			addToList(result);
		}
	}
}
