package com.example.mychef.dto;

import java.io.Serializable;

import org.json.JSONObject;

public class IngreadintDto implements Serializable {

	
	private static String URL="url";
	private static String NAME="name";
	private static String ID="id";
	
	private static String QUANTITY="quantity";
	private static String PREPARATION="preparation";
	private static String UNIT="unit";
	

	private String url;
	private String name;
	private String id;
	private String quantity;
	private String unit;
	private String preparation;
	
	
	
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



	public String getQuantity() {
		return quantity;
	}



	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}



	public String getUnit() {
		return unit;
	}



	public void setUnit(String unit) {
		this.unit = unit;
	}



	public String getPreparation() {
		return preparation;
	}



	public void setPreparation(String preparation) {
		this.preparation = preparation;
	}



	public static IngreadintDto ConsumeObject(JSONObject jo) {
		IngreadintDto object =null;
		if (jo != null) {
					
					object = new IngreadintDto();
					object.id = jo.optString(ID);
					object.name = jo.optString(NAME);
					object.url = jo.optString(URL);
					object.quantity = jo.optString(QUANTITY);
					object.unit = jo.optString(UNIT);
					object.preparation = jo.optString(PREPARATION);
					
		}
		
		return object;
	}
	
	
	
}
