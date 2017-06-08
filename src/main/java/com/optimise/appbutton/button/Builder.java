package com.optimise.appbutton.button;

import com.optimise.appbutton.model.ItemLocation;
import com.optimise.appbutton.model.Placement;
import com.optimise.appbutton.model.UserLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anoop.singh on 05-May-17.
 */

public class Builder {

    private Placement placementItem;
    private List<String> itemProperties;


    public Builder() {
        placementItem = new Placement();
        itemProperties = new ArrayList<>();
    }

    public Builder addUserLocation(double latitude, double longitude) {
        UserLocation location = new UserLocation();
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        placementItem.setUserLocation(location);
//        locationProperties.setUserLocation(location);
        return this;
    }
    /*public Builder addUserLocation(UserLocation location) {
        userLocationList.add(location);
        placementItem.setUserLocation(location);
        return this;
    }*/

    public Builder addItemLocation(double latitude, double longitude) {
        ItemLocation location = new ItemLocation();
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        placementItem.setItemLocation(location);
//        locationProperties.setItemLocation(location);
        return this;
    }

/*    public Builder addItemLocation(ItemLocation location) {
        itemLocationList.add(location);
        placementItem.setItemLocation(itemLocationList);
//        placementItem.setItemLocation(location);
        return this;
    }*/

    public Builder addPlacementId(int placementId) {
        placementItem.setPlacementId(placementId);
        return this;
    }

    /*public Builder addPlacementId(int placementId) {
        placementIdList.add(placementId);
        placementItem.setPlacementId(placementIdList);
        return this;
    }*/

    public Builder addTimeZone(String timeZone) {
        placementItem.setUserLocalTime(timeZone);
        return this;
    }

    public Builder addItemProperty(String itemProperties) {
        this.itemProperties.add(itemProperties);
        placementItem.setItemProperties(this.itemProperties);
        return this;
    }

    public Builder addButtonPlacement(ButtonPlacement buttonPlacement) {
        placementItem.setButtonPlacement(buttonPlacement);
        return this;
    }

    public Placement build() {
        return placementItem;
    }

   /* public static Builder newBuilder(){
       return new Builder();
    }*/

}
