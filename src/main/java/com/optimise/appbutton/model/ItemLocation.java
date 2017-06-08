package com.optimise.appbutton.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by anoop.singh on 06-Feb-17.
 */

public class ItemLocation extends ButtonBaseModel{

    private String name;
    private String city;
    private String customId;

    public ItemLocation(){

    }

    public ItemLocation(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name to set
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Set the city to set
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return customId
     */
    public String getCustomId() {
        return customId;
    }

    /**
     * Set the customerId to set
     * @param customId
     */
    public void setCustomId(String customId) {
        this.customId = customId;
    }

    public JSONObject getJson() throws JSONException{
        JSONObject jsonObject = null;
        if(this.latitude != 0 && this.longitude !=0){
            jsonObject = new JSONObject();
            jsonObject.put("latitude", this.latitude);
            jsonObject.put("longitude", this.longitude);
        }
        return jsonObject;
    }
}
