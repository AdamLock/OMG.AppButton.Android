package com.optimise.appbutton.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anoop.singh on 01-May-17.
 */

public class Button implements Parcelable {

    private int buttonId;
    private int displayIndex;
    private ButtonMetadata metadata;
    private ButtonPreview ctaButton;
    private Card card;


    /**
     *
     * @return buttonId
     */
    public int getButtonId() {
        return buttonId;
    }

    /**
     * Set the buttonId to set
     * @param buttonId
     */
    public void setButtonId(int buttonId) {
        this.buttonId = buttonId;
    }

    /**
     *
     * @return metadata
     */
    public ButtonMetadata getMetadata() {
        return metadata;
    }

    /**
     * Set the metadata to set
     * @param metadata
     */
    public void setMetadata(ButtonMetadata metadata) {
        this.metadata = metadata;
    }

    /**
     *
     * @return ctaButton
     */
    public ButtonPreview getCtaButton() {
        return ctaButton;
    }

    /**
     * Set the ctaButton to set
     * @param ctaButton
     */
    public void setCtaButton(ButtonPreview ctaButton) {
        this.ctaButton = ctaButton;
    }

    /**
     *
     * @return displayIndex
     */
    public int getDisplayIndex() {
        return displayIndex;
    }

    /**
     * Set the displayIndex to set
     * @param displayIndex
     */
    public void setDisplayIndex(int displayIndex) {
        this.displayIndex = displayIndex;
    }

    /**
     *
     * @return card
     */
    public Card getCard() {
        return card;
    }

    /**
     * Set the card to set
     * @param card
     */
    public void setCard(Card card) {
        this.card = card;
    }

    public Button(){

    }

    protected Button(Parcel in) {
        buttonId = in.readInt();
        metadata = in.readParcelable(ButtonMetadata.class.getClassLoader());
        ctaButton = in.readParcelable(ButtonPreview.class.getClassLoader());
        card = in.readParcelable(ButtonPreview.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(buttonId);
        dest.writeParcelable(metadata, flags);
        dest.writeParcelable(ctaButton, flags);
        dest.writeParcelable(card, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Button> CREATOR = new Creator<Button>() {
        @Override
        public Button createFromParcel(Parcel in) {
            return new Button(in);
        }

        @Override
        public Button[] newArray(int size) {
            return new Button[size];
        }
    };
}
