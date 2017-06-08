package com.optimise.appbutton.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by anoop.singh on 15-May-17.
 */

public class ButtonsList extends ArrayList<Button> implements Parcelable{

    public ButtonsList(){
        super();
    }


    protected ButtonsList(Parcel in) {
        in.readList(this, String.class.getClassLoader());
    }

    public static final Creator<ButtonsList> CREATOR = new Creator<ButtonsList>() {
        @Override
        public ButtonsList createFromParcel(Parcel in) {
            return new ButtonsList(in);
        }

        @Override
        public ButtonsList[] newArray(int size) {
            return new ButtonsList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this);
    }
}
