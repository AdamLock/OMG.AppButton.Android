package com.optimise.appbutton.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anoop.singh on 26-Apr-17.
 */

public class ButtonStyle implements Parcelable{
    private String backgroundColor;
    private String borderColor;
    private String borderStyle;
    private String borderWidth;
    private String textColor;
    private String fontSize;

    /**
     *
     * @return backgroundColor
     */
    public String getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * set the backgroundColor to set
     * @param backgroundColor
     */
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     *
     * @return borderColor
     */
    public String getBorderColor() {
        return borderColor;
    }

    /**
     * Set the borderColor to set
     * @param borderColor
     */
    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    /**
     *
     * @return borderStyle
     */
    public String getBorderStyle() {
        return borderStyle;
    }

    /**
     * Set the borderStyle to set
     * @param borderStyle
     */
    public void setBorderStyle(String borderStyle) {
        this.borderStyle = borderStyle;
    }

    /**
     *
     * @return borderWidth
     */
    public String getBorderWidth() {
        return borderWidth;
    }

    /**
     *
     * @param borderWidth
     */
    public void setBorderWidth(String borderWidth) {
        this.borderWidth = borderWidth;
    }

    /**
     *
     * @return textColor
     */
    public String getTextColor() {
        return textColor;
    }

    /**
     * Set the textColor to set
     * @param textColor
     */
    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    /**
     *
     * @return fontSize
     */
    public String getFontSize() {
        return fontSize;
    }

    /**
     * Set the fontSize to set
     * @param fontSize
     */
    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }


    //No-args constructor
    public ButtonStyle(){

    }


    public ButtonStyle(Parcel parcel){
        backgroundColor = parcel.readString();
        borderColor = parcel.readString();
        borderStyle = parcel.readString();
        borderWidth = parcel.readString();
        textColor = parcel.readString();
        fontSize = parcel.readString();
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(backgroundColor);
        parcel.writeString(borderColor);
        parcel.writeString(borderStyle);
        parcel.writeString(borderWidth);
        parcel.writeString(textColor);
        parcel.writeString(fontSize);
    }

    public static Creator<ButtonStyle>	CREATOR	= new Creator<ButtonStyle>() {
        public ButtonStyle createFromParcel(Parcel source) {
            return new ButtonStyle(source);
        }

        public ButtonStyle[] newArray(int size) {
            return new ButtonStyle[size];
        }
    };
}
