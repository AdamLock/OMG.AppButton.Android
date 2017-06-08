package com.optimise.appbutton.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anoop.singh on 01-May-17.
 */

public class CardBodyStyle implements Parcelable{

    private String backgroundColor;
    private String primaryTextColor;
    private String secondaryTextColor;
    private String primaryTextFontSize;
    private String secondaryTextFontSize;
    private String itemDividerHeight;
    private String itemDividerStyle;
    private String itemDividerColor;

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
     * @return secondaryTextColor
     */
    public String getSecondaryTextColor() {
        return secondaryTextColor;
    }

    /**
     * Set the secondaryTextColor to set
     * @param secondaryTextColor
     */
    public void setSecondaryTextColor(String secondaryTextColor) {
        this.secondaryTextColor = secondaryTextColor;
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

    /**
     *
     * @return secondaryTextFontSize
     */
    public String getSecondaryTextFontSize() {
        return secondaryTextFontSize;
    }

    /**
     * Set the secondaryTextFontSize to set
     * @param secondaryTextFontSize
     */
    public void setSecondaryTextFontSize(String secondaryTextFontSize) {
        this.secondaryTextFontSize = secondaryTextFontSize;
    }

    /**
     *
     * @return itemDividerHeight
     */
    public String getItemDividerHeight() {
        return itemDividerHeight;
    }

    /**
     * Set the itemDividerHeight to set
     * @param itemDividerHeight
     */
    public void setItemDividerHeight(String itemDividerHeight) {
        this.itemDividerHeight = itemDividerHeight;
    }

    /**
     *
     * @return itemDividerStyle
     */
    public String getItemDividerStyle() {
        return itemDividerStyle;
    }

    /**
     * Set the itemDividerStyle to set
     * @param itemDividerStyle
     */
    public void setItemDividerStyle(String itemDividerStyle) {
        this.itemDividerStyle = itemDividerStyle;
    }

    /**
     *
     * @return itemDividerColor
     */
    public String getItemDividerColor() {
        return itemDividerColor;
    }

    /**
     * Set the itemDividerColor to set
     * @param itemDividerColor
     */
    public void setItemDividerColor(String itemDividerColor) {
        this.itemDividerColor = itemDividerColor;
    }

    public CardBodyStyle(){

    }

    protected CardBodyStyle(Parcel in) {
        backgroundColor = in.readString();
        primaryTextColor = in.readString();
        secondaryTextColor = in.readString();
        primaryTextFontSize = in.readString();
        secondaryTextFontSize = in.readString();
        itemDividerHeight = in.readString();
        itemDividerStyle = in.readString();
        itemDividerColor = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(backgroundColor);
        dest.writeString(primaryTextColor);
        dest.writeString(secondaryTextColor);
        dest.writeString(primaryTextFontSize);
        dest.writeString(secondaryTextFontSize);
        dest.writeString(itemDividerHeight);
        dest.writeString(itemDividerStyle);
        dest.writeString(itemDividerColor);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CardBodyStyle> CREATOR = new Creator<CardBodyStyle>() {
        @Override
        public CardBodyStyle createFromParcel(Parcel in) {
            return new CardBodyStyle(in);
        }

        @Override
        public CardBodyStyle[] newArray(int size) {
            return new CardBodyStyle[size];
        }
    };
}
