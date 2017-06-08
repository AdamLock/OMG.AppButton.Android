package com.optimise.appbutton.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anoop.singh on 01-May-17.
 */

public class CardHeader implements Parcelable{

    private String primaryText;
    private CardHeaderStyle cardHeaderStyle;

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
     * @return cardHeaderStyle
     */
    public CardHeaderStyle getCardHeaderStyle() {
        return cardHeaderStyle;
    }

    /**
     * Set the cardHeaderStyle to set
     * @param cardHeaderStyle
     */
    public void setCardHeaderStyle(CardHeaderStyle cardHeaderStyle) {
        this.cardHeaderStyle = cardHeaderStyle;
    }

    public CardHeader(){

    }


    protected CardHeader(Parcel in) {
        primaryText = in.readString();
        cardHeaderStyle = in.readParcelable(CardHeaderStyle.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(primaryText);
        dest.writeParcelable(cardHeaderStyle, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CardHeader> CREATOR = new Creator<CardHeader>() {
        @Override
        public CardHeader createFromParcel(Parcel in) {
            return new CardHeader(in);
        }

        @Override
        public CardHeader[] newArray(int size) {
            return new CardHeader[size];
        }
    };
}
