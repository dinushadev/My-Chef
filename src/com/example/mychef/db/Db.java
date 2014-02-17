package com.example.mychef.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Db extends SQLiteOpenHelper {
	
	private static final String DB_NAME = "hekathon.db";
	private static final int DB_VERSION = 1;

	private static final String CREATE_TABLE_RECIPE = "CREATE TABLE "
			+ RecipeDbHelper.TABLE + "( " + RecipeDbHelper.ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT,  " + RecipeDbHelper.IMAGE
			+ " BLOB, " 
			+RecipeDbHelper.KEY + " TEXT, "
			+RecipeDbHelper.THUMB + " BLOB, "
			+ RecipeDbHelper.NAME + " TEXT )";

	

	public Db(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_RECIPE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
