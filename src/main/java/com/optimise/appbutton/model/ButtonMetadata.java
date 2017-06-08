package com.optimise.appbutton.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anoop.singh on 01-May-17.
 */

public class ButtonMetadata implements Parcelable{

    private String appName;
    private String deepLinkingProtocol;
    private String storeId;

    /**
     *
     * @return storeId
     */
    public String getStoreId() {
        return storeId;
    }

    /**
     * Set the storeId to set
     * @param storeId
     */
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    /**
     *
     * @return appName
     */
    public String getAppName() {
        return appName;
    }

    /**
     * Set the appName to set
     * @param appName
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     *
     * @return deepLinkingProtocol
     */
    public String getDeepLinkingProtocol() {
        return deepLinkingProtocol;
    }

    /**
     * Set the deepLinkingProtocol to set
     * @param deepLinkingProtocol
     */
    public void setDeepLinkingProtocol(String deepLinkingProtocol) {
        this.deepLinkingProtocol = deepLinkingProtocol;
    }

    public static final Creator<ButtonMetadata> CREATOR = new Creator<ButtonMetadata>() {
        @Override
        public ButtonMetadata createFromParcel(Parcel in) {
            return new ButtonMetadata(in);
        }

        @Override
        public ButtonMetadata[] newArray(int size) {
            return new ButtonMetadata[size];
        }
    };

    //no-args constructor
    public ButtonMetadata(){

    }

    protected ButtonMetadata(Parcel in) {
        appName = in.readString();
        storeId = in.readString();
        deepLinkingProtocol = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(appName);
        parcel.writeString(storeId);
        parcel.writeString(deepLinkingProtocol);
    }
}
