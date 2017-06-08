package com.optimise.appbutton.utility.network;

import android.util.Log;

import com.optimise.appbutton.utility.DataUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;
import java.util.zip.GZIPInputStream;


/**
 * Created by anoop.singh on 26-04-2016.
 */
public class AsyncConnection extends Thread {

    private final String LOG_TAG = "AsyncConnection";

    public AsyncConnection() {
        defaultStatusCodeChecker = new StatusCodeChecker() {
            @Override
            public boolean isSuccess(int statusCode) {
                return statusCode == 200;
            }
        };
    }


    private StatusCodeChecker defaultStatusCodeChecker;
    private int defaultRequestTimeOut;

    /**
     * @param defaultStatusCodeChecker the defaultStatusCodeChecker to set
     */
    public void setDefaultStatusCodeChecker(StatusCodeChecker defaultStatusCodeChecker) {
        this.defaultStatusCodeChecker = defaultStatusCodeChecker;
    }

    /**
     * @param defaultRequestTimeOut the defaultRequestTimeOut in miliseconds to set
     */
    public void setDefaultRequestTimeOut(int defaultRequestTimeOut) {
        this.defaultRequestTimeOut = defaultRequestTimeOut;
    }

    private boolean isRunning;
    private Vector<ServiceRequest> highPriorityQueue;
    private Vector<ServiceRequest> lowPriorityQueue;

    public void execute(ServiceRequest request) {
        highPriorityQueue = new Vector<ServiceRequest>();
        lowPriorityQueue = new Vector<ServiceRequest>();
        isRunning = true;
        addRequest(request);
        start();
    }

    private ServiceRequest currentRequest;

    /**
     * {@link ServiceRequest} with {@link PRIORITY#HIGH} are executed before {@link ServiceRequest} with {@link PRIORITY#LOW}
     */
    public interface PRIORITY {
        /**
         * When-ever a new {@link ServiceRequest} with {@link PRIORITY#LOW} is added,
         * it gets lower priority than previous requests with same priority.
         */
        byte LOW = 0;
        /**
         * When-ever a new {@link ServiceRequest} with {@link PRIORITY#HIGH} is added,
         * it gets higher priority than previous requests with same priority.
         */
        byte HIGH = 1;
    }

    public interface HTTP_METHOD {
        byte GET = 0;
        byte POST = 1;
       }

    /**
     * Specific instance of StatusCodeChecker can be set in ServiceRequest
     */
    public interface StatusCodeChecker {
        boolean isSuccess(int statusCode);
    }

    @Override
    public void run() {
        while (isRunning) {
            if (nextRequest()) {
                executeRequest();
            } else {
                try {
                    Thread.sleep(10 * 60);// 10 min sleep
                } catch (InterruptedException e) {
                    Log.i(LOG_TAG, "" + e);
                }
            }
        }
    }

    private boolean nextRequest() {
        if (highPriorityQueue.size() > 0) {
            currentRequest = (ServiceRequest) highPriorityQueue.remove(0);
        } else if (lowPriorityQueue.size() > 0) {
            currentRequest = (ServiceRequest) lowPriorityQueue.remove(0);
        } else {
            currentRequest = null;
        }

        return currentRequest != null;
    }

    public void executeRequest() {
        if (currentRequest.isCancelled()) {
            return;
        }

        HttpURLConnection connection = null;
        try {
            Log.i(LOG_TAG, "Request URL: " + currentRequest.getUrl());
            URL url = new URL(currentRequest.getUrl());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            switch (currentRequest.getHttpMethod()) {
                case HTTP_METHOD.GET: {
                    connection.setRequestMethod("GET");
                    break;
                }
                case HTTP_METHOD.POST: {
                    connection.setRequestMethod("POST");
                    OutputStream os = connection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(currentRequest.getRequestData().toString());
                    writer.flush();
                    writer.close();
                    os.close();
                    break;
                }
                default:
                    connection.setRequestMethod("GET");
                    break;
            }

            InputStream responseContentStream = connection.getContent() == null ? null : (InputStream) connection.getContent();
            int status = connection.getResponseCode();
            Log.i(LOG_TAG, "Status Code " + status);
            Response response = new Response();
            response.setDataType(currentRequest.getDataType());
            response.setRequestData(currentRequest.getRequestData());
            if (responseContentStream != null) {
                if (currentRequest.getIsCompressed()) {
                    responseContentStream = new GZIPInputStream(responseContentStream);
                }
                if (currentRequest.isCancelled()) {
                    return;
                }
                response.setResponseData(DataUtils.convertStreamToBytes(responseContentStream));
            }

            if (currentRequest.isCancelled()) {
                return;
            }
            currentRequest.getResponseController().handleResponse(response);
        } catch (MalformedURLException me) {
            Log.e(LOG_TAG, "MalformedURLException", me);
            notifyError("MalformedURLException", me);
        } catch (IOException io) {
            Log.e(LOG_TAG, "IOException", io);
            notifyError("IOException", io);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Exception", e);
            notifyError("Exception", e);
        } finally {
            if (connection != null)
                connection.disconnect();
        }
    }

    /**
     * @param errorMessage
     * @param exception
     */
    private void notifyError(String errorMessage, Exception exception) {
        if (exception == null) {
            Log.e(LOG_TAG, "Error Response: " + errorMessage);
        } else {
            Log.e(LOG_TAG, "Error Response: " + errorMessage, exception);
        }
        Response response = new Response();
        response.setRequestData(currentRequest.getRequestData());
        response.setDataType(currentRequest.getDataType());
        response.setErrorMessage(errorMessage);
        response.setSuccess(false);
        response.setException(exception);
        if (currentRequest.isCancelled()) {
            return;
        }
        currentRequest.getResponseController().handleResponse(response);
    }

    public void addRequest(ServiceRequest request) {
        try {
            if (request.getPriority() == PRIORITY.HIGH) {
                highPriorityQueue.add(0, request);
            } else {
                lowPriorityQueue.addElement(request);
            }
            interrupt();
        } catch (Exception ex) {
            Log.e(LOG_TAG, "addRequest()", ex);
        }
    }
}
