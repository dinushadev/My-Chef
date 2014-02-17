package com.example.mychef.dto;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class RecipeResponceDto {
	
	private static String KIND="KIND";
	private static String URL="url";
	private static String NAME="name";
	private static String ID="id";
	
	private String kind;
	private String url;
	private String name;
	private String id;
	private List<RecipeDto> recipes;
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
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
	public List<RecipeDto> getRecipes() {
		return recipes;
	}
	public void setRecipes(List<RecipeDto> recipes) {
		this.recipes = recipes;
	}
	
	
	
	
	
	

	public static RecipeResponceDto ConsumeObject(JSONObject jo) {
		RecipeResponceDto object = null;
		if (jo != null) {
			
			if (jo != null) {
				object = new RecipeResponceDto();
				object.id = jo.optString(ID);
				object.name = jo.optString(NAME);
				object.url = jo.optString(URL);
				object.kind = jo.optString(KIND);
				
				
				ArrayList<RecipeDto> objects = new ArrayList<RecipeDto>();
				JSONArray ja =null;
				if (jo != null) {
					ja = jo.optJSONArray("recipes");
					if (ja != null) {
						for (int i = 0; i < ja.length(); i++)
							objects.add(RecipeDto.ConsumeObject(ja.optJSONObject(i)));
					}
					
				}
				object.setRecipes(objects);
				
				
			}
		}
		return object;
	}

	public String toString() {
		return name + " " + kind + " - " + id;
	}
	
	

}
