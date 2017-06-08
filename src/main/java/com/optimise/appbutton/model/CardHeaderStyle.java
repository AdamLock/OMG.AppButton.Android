package com.optimise.appbutton.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anoop.singh on 01-May-17.
 */

public class CardHeaderStyle implements Parcelable{
    private String backgroundColor;
    private String primaryTextColor;
    private String primaryTextFontSize;

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
     * @return primaryTextFontSize
     */
    public String getPrimaryTextFontSize() {
        return primaryTextFontSize;
    }

    /**
     * Set the primaryTextFontSize to set
     * @param primaryTextFontSize
     */
    public void setPrimaryTextFontSize(String primaryTextFontSize) {
        this.primaryTextFontSize = primaryTextFontSize;
    }

    public CardHeaderStyle(){

    }

    protected CardHeaderStyle(Parcel in) {
        backgroundColor = in.readString();
        primaryTextColor = in.readString();
        primaryTextFontSize = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(backgroundColor);
        dest.writeString(primaryTextColor);
        dest.writeString(primaryTextFontSize);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CardHeaderStyle> CREATOR = new Creator<CardHeaderStyle>() {
        @Override
        public CardHeaderStyle createFromParcel(Parcel in) {
            return new CardHeaderStyle(in);
        }

        @Override
        public CardHeaderStyle[] newArray(int size) {
            return new CardHeaderStyle[size];
        }
    };
}
