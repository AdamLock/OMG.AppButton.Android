package com.optimise.appbutton.model;

/**
 * Created by anoop.singh on 26-Apr-17.
 */

public class BaseModel {

    protected String status;
    protected String info;
    protected String additionalInfo;


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

}
