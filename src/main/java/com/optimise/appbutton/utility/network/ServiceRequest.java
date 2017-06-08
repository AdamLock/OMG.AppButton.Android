package com.optimise.appbutton.utility.network;


import com.optimise.appbutton.utility.controller.IController;

/**
 * Storage class for data related to a service request which is to be executed
 * by {@link AsyncConnection}
 *
 * @author anoop.singh
 */
public final class ServiceRequest {
    private String url;
    private byte priority;
    private int httpMethod;

    private boolean isCompressed;
    private int requestTimeOut;
    private boolean isCancelled;

    /**
     * Used while returning the response data
     */
    private int dataType;
    private Object requestData;
    private IController responseController;

    /**
     * @param url //set the url for request
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * get the requested url
     *
     * @return url
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * set the priority of the request
     *
     * @param priority
     */
    public void setPriority(byte priority) {
        this.priority = priority;
    }

    /**
     * get the request priority
     *
     * @return priority
     */
    public byte getPriority() {
        return priority;
    }

    /**
     * set parameter to compress the request data
     *
     * @param isCompressed
     */
    public void setIsCompressed(boolean isCompressed) {
        this.isCompressed = isCompressed;
    }

    /**
     * get the request is compressed
     *
     * @return isCompressed
     */
    public boolean getIsCompressed() {
        return this.isCompressed;
    }

    /**
     * set the time in miliseconds when the request has to be time out
     *
     * @param requestTimeOut
     */
    public void setRequestTimeOut(int requestTimeOut) {
        this.requestTimeOut = requestTimeOut;
    }

    /**
     * get when the request has to be time out
     *
     * @return requestTimeOut
     */
    public int getRequestTimeOut() {
        return this.requestTimeOut;
    }

    /**
     * @return the httpMethod
     */
    public int getHttpMethod() {
        return httpMethod;
    }

    /**
     * @param httpMethod the httpMethod to set
     */
    public void setHttpMethod(int httpMethod) {
        this.httpMethod = httpMethod;
    }

    /**
     * @return the dataType
     */
    public int getDataType() {
        return dataType;
    }

    /**
     * @param dataType the dataType to set
     */
    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    /**
     * @return the requestData
     */
    public Object getRequestData() {
        return requestData;
    }

    /**
     * @param requestData the requestData to set
     */
    public void setRequestData(Object requestData) {
        this.requestData = requestData;
    }

    /**
     * @return the isCancelled
     */
    public boolean isCancelled() {
        return isCancelled;
    }

    /**
     * @param isCancelled the isCancelled to set
     */
    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    /**
     * @return the responseController
     */
    public IController getResponseController() {
        return responseController;
    }

    /**
     * @param responseController the responseController to set
     */
    public void setResponseController(IController responseController) {
        this.responseController = responseController;
    }
}
