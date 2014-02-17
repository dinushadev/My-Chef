package com.example.mychef.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RecipeDbHelper {

	public static final String TABLE = "recipes";

	public static final String ID = "_id";
	public static final String KEY = "_key";
	public static final String IMAGE = "image";
	public static final String THUMB = "thumb";
	public static final String NAME = "name";

	SQLiteDatabase db = null;

	public RecipeDbHelper(SQLiteDatabase db) {
		this.db = db;

	}

	public Cursor findRecipeById(String id) {

	//	name = "%" + id.trim() + "%";

		String sql = "SELECT " + NAME + ", " + ID + ", " + THUMB +", " + IMAGE + " FROM "
				+ TABLE + " WHERE " + KEY + " = ?";

		Cursor cursor = db.rawQuery(sql, new String[] { id });

		return cursor;
	}

}
