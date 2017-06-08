package com.optimise.appbutton.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anoop.singh on 03-May-17.
 */

public class CardFooter implements Parcelable {

    private String primaryText;
    private String imageUri;
    private CardFooterStyle cardFooterStyle;

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
     * @return cardFooterStyle
     */
    public CardFooterStyle getCardFooterStyle() {
        return cardFooterStyle;
    }

    /**
     * Set the cardFooterStyle to set
     * @param cardFooterStyle
     */
    public void setCardFooterStyle(CardFooterStyle cardFooterStyle) {
        this.cardFooterStyle = cardFooterStyle;
    }

    public CardFooter(){

    }

    public CardFooter(Parcel in) {
        primaryText = in.readString();
        imageUri = in.readString();
        cardFooterStyle = in.readParcelable(CardFooterStyle.class.getClassLoader());
    }

    public static final Creator<CardFooter> CREATOR = new Creator<CardFooter>() {
        @Override
        public CardFooter createFromParcel(Parcel in) {
            return new CardFooter(in);
        }

        @Override
        public CardFooter[] newArray(int size) {
            return new CardFooter[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(primaryText);
        parcel.writeString(imageUri);
        parcel.writeParcelable(cardFooterStyle, i);
    }
}
