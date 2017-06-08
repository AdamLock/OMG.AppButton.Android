package com.optimise.appbutton.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by anoop.singh on 06-Feb-17.
 */

public class UserLocation extends ButtonBaseModel{

    private String name;
    private String city;
    private String customId;

    public UserLocation(){

    }
    public UserLocation(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }


    /**
     *
     * @return
     * @throws JSONException
     */
    public JSONObject toJson() throws JSONException{
        JSONObject jsonObject = null;
        if(this.longitude != 0 && this.latitude != 0) {
            jsonObject = new JSONObject();
            jsonObject.put("latitude", this.latitude);
            jsonObject.put("longitude", this.longitude);
        }
        return jsonObject;
    }
}
