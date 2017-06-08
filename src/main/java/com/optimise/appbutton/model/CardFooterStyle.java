package com.optimise.appbutton.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anoop.singh on 03-May-17.
 */

public class CardFooterStyle implements Parcelable {

    private String backgroundColor;
    private String primaryTextColor;
    private String primaryFontSize;

    /**
     *
     * @return backgroundColor
     */
    public String getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Set the backgroundColor to set
     * @param backgroundColor
     */
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     *
     * @return primaryTextColor
     */
    public String getPrimaryTextColor() {
        return primaryTextColor;
    }

    /**
     * Set the primaryTextColor to set
     * @param primaryTextColor
     */
    public void setPrimaryTextColor(String primaryTextColor) {
        this.primaryTextColor = primaryTextColor;
    }

    /**
     *
     * @return primaryFontSize
     */
    public String getPrimaryFontSize() {
        return primaryFontSize;
    }

    /**
     * Set the primaryFontSize to set
     * @param primaryFontSize
     */
    public void setPrimaryFontSize(String primaryFontSize) {
        this.primaryFontSize = primaryFontSize;
    }

    public CardFooterStyle(){

    }

    public CardFooterStyle(Parcel in) {
        backgroundColor = in.readString();
        primaryTextColor = in.readString();
        primaryFontSize = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(backgroundColor);
        dest.writeString(primaryTextColor);
        dest.writeString(primaryFontSize);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CardFooterStyle> CREATOR = new Creator<CardFooterStyle>() {
        @Override
        public CardFooterStyle createFromParcel(Parcel in) {
            return new CardFooterStyle(in);
        }

        @Override
        public CardFooterStyle[] newArray(int size) {
            return new CardFooterStyle[size];
        }
    };
}
