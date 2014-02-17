package com.example.mychef.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CookingMethodResponceDto implements Serializable {
	
	private static String CUISINE="cuisine";
	private static String URL="url";
	private static String NAME="name";
	private static String ID="id";
	private static String COOKING_METHOD="cooking_method";
	private static String THUMB="thumb";
	private static String IMAGE="image";
	
	private static String SERVERS="servers";
	private static String YIELDS="yields";
	private static String COST="cost";
	
	
	private String url;
	private String name;
	private String id;
	private String cuisine;
	private String cooking_method;
	
	
	private List<IngreadintDto > ingredients;
	
	private String image;
	private String thumb;
	
	private int servers;
	private String yields;
	private double cost;
	
	private List<String> directions;
	
	
	
	
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

	public List<IngreadintDto> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngreadintDto> ingredients) {
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

	public int getServers() {
		return servers;
	}

	public void setServers(int servers) {
		this.servers = servers;
	}

	public String getYields() {
		return yields;
	}

	public void setYields(String yields) {
		this.yields = yields;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public List<String> getDirections() {
		return directions;
	}

	public void setDirections(List<String> directions) {
		this.directions = directions;
	}

	public static CookingMethodResponceDto ConsumeObject(JSONObject jo) {
		CookingMethodResponceDto object = null;
		if (jo != null) {
			
			if (jo != null) {
				object = new CookingMethodResponceDto();
				object.id = jo.optString(ID);
				object.name = jo.optString(NAME);
				object.url = jo.optString(URL);
				object.cuisine = jo.optString(CUISINE);
				object.thumb = jo.optString(THUMB);
				object.image = jo.optString(IMAGE);
				object.cooking_method = jo.optString(COOKING_METHOD);
				
				object.servers=jo.optInt(SERVERS);
				object.yields=jo.optString(YIELDS);
				object.cost=jo.optDouble(COST);
				
				
				
				ArrayList<IngreadintDto> ingredints = new ArrayList<IngreadintDto>();
				JSONArray ja =null;
				if (jo != null) {
					ja = jo.optJSONArray("ingredients");
					if (ja != null) {
						for (int i = 0; i < ja.length(); i++)
							ingredints.add(IngreadintDto.ConsumeObject(ja.optJSONObject(i)));
					}
					
				}
				object.ingredients=ingredints;
				
				
				
				ArrayList<String> directinsList = new ArrayList<String>();
				JSONArray ja1 =null;
				if (jo != null) {
					ja1 = jo.optJSONArray("directions");
					if (ja1 != null) {
						for (int i = 0; i < ja1.length(); i++)
							try {
								directinsList.add(ja1.getString(i));
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
					
				}
				object.directions=directinsList;
				
				
			}
		}
		return object;
	}

	public String toString() {
		return name + " " + cooking_method + " - " + id;
	}
	

}
