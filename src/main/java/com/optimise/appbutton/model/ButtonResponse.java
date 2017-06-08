package com.optimise.appbutton.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anoop.singh on 02-May-17.
 */

public class ButtonResponse extends BaseModel implements Parcelable {

    private String requestId;
    private List<ButtonsList> buttonList;

    public ButtonResponse(){

    }


    protected ButtonResponse(Parcel in) {
        requestId = in.readString();
        buttonList = new ArrayList<>();
        in.readTypedList(buttonList, ButtonsList.CREATOR);
        info = in.readString();
        status = in.readString();
        additionalInfo = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(requestId);
        dest.writeList(buttonList);
        dest.writeString(info);
        dest.writeString(status);
        dest.writeString(additionalInfo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ButtonResponse> CREATOR = new Creator<ButtonResponse>() {
        @Override
        public ButtonResponse createFromParcel(Parcel in) {
            return new ButtonResponse(in);
        }

        @Override
        public ButtonResponse[] newArray(int size) {
            return new ButtonResponse[size];
        }
    };

    /**
     *
     * @return requestId
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Set the requestId to set
     * @param requestId
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /**
     *
     * @return buttonList
     */
    public List<ButtonsList> getButtonList() {
        return buttonList;
    }

    /**
     * Set the buttonList to set
     * @param buttonList
     */
    public void setButtonList(List<ButtonsList> buttonList) {
        this.buttonList = buttonList;
    }
}
