package com.optimise.appbutton.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anoop.singh on 01-May-17.
 */

public class CardBodyItem implements Parcelable {

    private String imageUri;
    private String primaryText;
    private String secondaryText;
    private String deepLink;


    /**
     *
     * @return imageUri
     */
    public String getImageUri() {
        return imageUri;
    }

    /**
     * Set the imageUri to set
     * @param imageUri
     */
    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    /**
     *
     * @return primaryText
     */
    public String getPrimaryText() {
        return primaryText;
    }

    /**
     * Set the primaryText to set
     * @param primaryText
     */
    public void setPrimaryText(String primaryText) {
        this.primaryText = primaryText;
    }

    /**
     *
     * @return secondaryText
     */
    public String getSecondaryText() {
        return secondaryText;
    }

    /**
     * Set the secondaryText to set
     * @param secondaryText
     */
    public void setSecondaryText(String secondaryText) {
        this.secondaryText = secondaryText;
    }

    /**
     *
     * @return deepLink
     */
    public String getDeepLink() {
        return deepLink;
    }

    /**
     * Set the deepLink to set
     * @param deepLink
     */
    public void setDeepLink(String deepLink) {
        this.deepLink = deepLink;
    }

    public CardBodyItem(){

    }

    protected CardBodyItem(Parcel in) {
        imageUri = in.readString();
        primaryText = in.readString();
        secondaryText = in.readString();
        deepLink = in.readString();
//        bitmap = Bitmap.CREATOR.createFromParcel(in);
    }

    public static final Creator<CardBodyItem> CREATOR = new Creator<CardBodyItem>() {
        @Override
        public CardBodyItem createFromParcel(Parcel in) {
            return new CardBodyItem(in);
        }

        @Override
        public CardBodyItem[] newArray(int size) {
            return new CardBodyItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imageUri);
        parcel.writeString(primaryText);
        parcel.writeString(secondaryText);
        parcel.writeString(deepLink);
//        bitmap.writeToParcel(parcel, 0);
//        parcel.setDataPosition(0);
    }
}
