package com.optimise.appbutton.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by anoop.singh on 01-May-17.
 */

public class CardBody implements Parcelable{
    private String action;
    private CardBodyStyle cardBodyStyle;
    private List<CardBodyItem> cardBodyItem;

    /**
     *
     * @return action
     */
    public String getAction() {
        return action;
    }

    /**
     * Set the action to set
     * @param action
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     *
     * @return cardBodyStyle
     */
    public CardBodyStyle getCardBodyStyle() {
        return cardBodyStyle;
    }

    /**
     * Set the cardBodyStyle to set
     * @param cardBodyStyle
     */
    public void setCardBodyStyle(CardBodyStyle cardBodyStyle) {
        this.cardBodyStyle = cardBodyStyle;
    }

    /**
     *
     * @return cardBodyItem
     */
    public List<CardBodyItem> getCardBodyItem() {
        return cardBodyItem;
    }

    /**
     * Set the list of cardBodyItem to set
     * @param cardBodyItem
     */
    public void setCardBodyItem(List<CardBodyItem> cardBodyItem) {
        this.cardBodyItem = cardBodyItem;
    }

    public CardBody(){

    }

    protected CardBody(Parcel in) {
        action = in.readString();
        cardBodyStyle = in.readParcelable(CardBodyStyle.class.getClassLoader());
        cardBodyItem = in.createTypedArrayList(CardBodyItem.CREATOR);
    }

    public static final Creator<CardBody> CREATOR = new Creator<CardBody>() {
        @Override
        public CardBody createFromParcel(Parcel in) {
            return new CardBody(in);
        }

        @Override
        public CardBody[] newArray(int size) {
            return new CardBody[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(action);
        parcel.writeParcelable(cardBodyStyle, i);
        parcel.writeTypedList(cardBodyItem);
    }
}
