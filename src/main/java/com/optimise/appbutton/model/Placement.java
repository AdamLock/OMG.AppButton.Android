package com.optimise.appbutton.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.optimise.appbutton.button.Builder;
import com.optimise.appbutton.button.ButtonPlacement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anoop.singh on 03-May-17.
 */

public class Placement implements Parcelable{

    private UserLocation userLocation;
    private ItemLocation itemLocation;
    private int placementId;
    private String userLocalTime;
    private List<String> itemProperties;
    private ButtonPlacement buttonPlacement;

    public Placement(){

    }

    protected Placement(Parcel in) {
        placementId = in.readInt();
        userLocalTime = in.readString();
        itemProperties = in.createStringArrayList();
    }

    public static final Creator<Placement> CREATOR = new Creator<Placement>() {
        @Override
        public Placement createFromParcel(Parcel in) {
            return new Placement(in);
        }

        @Override
        public Placement[] newArray(int size) {
            return new Placement[size];
        }
    };

    /**
     * @return placementId
     */
    public int getPlacementId() {
        return placementId;
    }

    /**
     * Set the placementId to set
     *
     * @param placementId
     */
    public void setPlacementId(int placementId) {
        this.placementId = placementId;
    }

    /*
     * @return userLocation
     */
    public UserLocation getUserLocation() {
        return userLocation;
    }

    /*
     * Set the userLocation to set
     *
     * @param userLocation
     */
    public void setUserLocation(UserLocation userLocation) {
        this.userLocation = userLocation;
    }

    /*
     * @return itemLocation
     */
    public ItemLocation getItemLocation() {
        return itemLocation;
    }

    /*
     * Set the itemLocation to set
     *@param itemLocation
     * */

    public void setItemLocation(ItemLocation itemLocation) {
        this.itemLocation = itemLocation;
    }

    /**
     * @return userLocalTime
     */
    public String getUserLocalTime() {
        return userLocalTime;
    }

    /**
     * Set the userLocalTime to set
     *
     * @param userLocalTime
     */
    public void setUserLocalTime(String userLocalTime) {
        this.userLocalTime = userLocalTime;
    }

    /**
     * @return itemProperties
     */
    public List<String> getItemProperties() {
        return itemProperties;
    }

    /**
     * Set the itemProperties to set
     *
     * @param itemProperties
     */
    public void setItemProperties(List<String> itemProperties) {
        this.itemProperties = itemProperties;
    }

    /**
     * @return buttonPlacement
     */
    public ButtonPlacement getButtonPlacement() {
        return buttonPlacement;
    }

    /**
     * Set the buttonPlacement to set
     *
     * @param buttonPlacement
     */
    public void setButtonPlacement(ButtonPlacement buttonPlacement) {
        this.buttonPlacement = buttonPlacement;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(placementId);
        parcel.writeString(userLocalTime);
        parcel.writeStringList(itemProperties);
    }




    public static final class Builder {

        private Placement placementItem;
        private List<String> itemProperties;


        public Builder() {
            placementItem = new Placement();
            itemProperties = new ArrayList<>();
        }

        public Placement.Builder addUserLocation(double latitude, double longitude) {
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

        public Placement.Builder addItemLocation(double latitude, double longitude) {
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

        public Placement.Builder addPlacementId(int placementId) {
            placementItem.setPlacementId(placementId);
            return this;
        }

    /*public Builder addPlacementId(int placementId) {
        placementIdList.add(placementId);
        placementItem.setPlacementId(placementIdList);
        return this;
    }*/

        public Placement.Builder addTimeZone(String timeZone) {
            placementItem.setUserLocalTime(timeZone);
            return this;
        }

        public Placement.Builder addItemProperty(String itemProperties) {
            this.itemProperties.add(itemProperties);
            placementItem.setItemProperties(this.itemProperties);
            return this;
        }

        public Placement.Builder addButtonPlacement(ButtonPlacement buttonPlacement) {
            placementItem.setButtonPlacement(buttonPlacement);
            return this;
        }

        public Placement build() {
            return placementItem;
        }

    }

}
