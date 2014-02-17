package com.example.mychef.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecipeDto implements Serializable{

	private static String CUISINE="cuisine";
	private static String URL="url";
	private static String NAME="name";
	private static String ID="id";
	private static String COOKING_METHOD="cooking_method";
	private static String THUMB="thumb";
	private static String IMAGE="image";
	
	
	private String url;
	private String name;
	private String id;
	private String cuisine;
	private String cooking_method;
	private List<String > ingredients;
	
	private String image;
	private String thumb;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCuisine() {
		return cuisine;
	}
	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
	public String getCooking_method() {
		return cooking_method;
	}
	public void setCooking_method(String cooking_method) {
		this.cooking_method = cooking_method;
	}
	public List<String> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	
	
	public static RecipeDto ConsumeObject(JSONObject jo) {
		RecipeDto object =null;
	if (jo != null) {
				
				object = new RecipeDto();
				object.id = jo.optString(ID);
				object.name = jo.optString(NAME);
				object.url = jo.optString(URL);
				object.cuisine = jo.optString(CUISINE);
				object.thumb = jo.optString(THUMB);
				object.image = jo.optString(IMAGE);
				object.cooking_method = jo.optString(COOKING_METHOD);
				
				
				
				ArrayList<String> objects = new ArrayList<String>();
				JSONArray ja =null;
				if (jo != null) {
					ja = jo.optJSONArray("ingredients");
					if (ja != null) {
						for (int i = 0; i < ja.length(); i++)
							try {
								objects.add(ja.getString(i));
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
					
				}
				object.setIngredients(objects);
		
	}
		return object;
	}
	
}
