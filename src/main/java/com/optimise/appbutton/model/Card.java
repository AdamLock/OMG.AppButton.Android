package com.optimise.appbutton.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anoop.singh on 01-May-17.
 */

public class Card implements Parcelable{
    private String cardType;
    private CardHeader cardHeader;
    private CardBody cardBody;
    private CardFooter cardFooter;

    /**
     *
     * @return cardType
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * Set the cardType to set
     * @param cardType
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    /**
     *
     * @return cardHeader
     */
    public CardHeader getCardHeader() {
        return cardHeader;
    }

    /**
     * Set the cardHeader to set
     * @param cardHeader
     */
    public void setCardHeader(CardHeader cardHeader) {
        this.cardHeader = cardHeader;
    }

    /**
     *
     * @return cardBody
     */
    public CardBody getCardBody() {
        return cardBody;
    }

    /**
     * Set the cardBody to set
     * @param cardBody
     */
    public void setCardBody(CardBody cardBody) {
        this.cardBody = cardBody;
    }

    /**
     *
     * @return cardFooter
     */
    public CardFooter getCardFooter() {
        return cardFooter;
    }

    /**
     * Set the cardFooter to set
     * @param cardFooter
     */
    public void setCardFooter(CardFooter cardFooter) {
        this.cardFooter = cardFooter;
    }

    public Card(){

    }

    protected Card(Parcel in) {
        cardType = in.readString();
        cardHeader = in.readParcelable(CardHeader.class.getClassLoader());
        cardBody = in.readParcelable(CardBody.class.getClassLoader());
        cardFooter = in.readParcelable(CardFooter.class.getClassLoader());
    }

    public static final Creator<Card> CREATOR = new Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(cardType);
        parcel.writeParcelable(cardHeader, i);
        parcel.writeParcelable(cardBody, i);
        parcel.writeParcelable(cardFooter, i);
    }
}
