package com.optimise.appbutton.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anoop.singh on 26-Apr-17.
 */

public class ButtonPreview implements Parcelable{

    private String label;
    private String imageUri;
    private ButtonStyle buttonStyle;

    public static final Creator<ButtonPreview> CREATOR = new Creator<ButtonPreview>() {
        @Override
        public ButtonPreview createFromParcel(Parcel in) {
            return new ButtonPreview(in);
        }

        @Override
        public ButtonPreview[] newArray(int size) {
            return new ButtonPreview[size];
        }
    };

    /**
     *
     * @return label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Set the label to set
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
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
     * @return buttonStyle
     */
    public ButtonStyle getButtonStyle() {
        return buttonStyle;
    }

    /**
     * Set the buttonStyle to set
     * @param buttonStyle
     */
    public void setButtonStyle(ButtonStyle buttonStyle) {
        this.buttonStyle = buttonStyle;
    }

    //no-args constructor
    public ButtonPreview(){

    }

    public ButtonPreview(Parcel parcel){
        imageUri = parcel.readString();
        label = parcel.readString();
        buttonStyle = parcel.readParcelable(getClass().getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imageUri);
        parcel.writeString(label);
        parcel.writeParcelable(buttonStyle, i);
    }


}
