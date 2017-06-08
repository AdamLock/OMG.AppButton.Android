package com.optimise.appbutton.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anoop.singh on 02-May-17.
 */

public class ResponseMessageMetadata implements Parcelable {

    private String status;
    private String info;
    private String additionalInfo;

    /**
     *
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the status to set
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return info
     */
    public String getInfo() {
        return info;
    }

    /**
     * Set the info to set
     * @param info
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     *
     * @return additionalInfo
     */
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    /**
     * Set the additionalInfo to set
     * @param additionalInfo
     */
    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    protected ResponseMessageMetadata(Parcel in) {
        status = in.readString();
        info = in.readString();
        additionalInfo = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(info);
        dest.writeString(additionalInfo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResponseMessageMetadata> CREATOR = new Creator<ResponseMessageMetadata>() {
        @Override
        public ResponseMessageMetadata createFromParcel(Parcel in) {
            return new ResponseMessageMetadata(in);
        }

        @Override
        public ResponseMessageMetadata[] newArray(int size) {
            return new ResponseMessageMetadata[size];
        }
    };
}
