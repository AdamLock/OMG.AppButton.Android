package com.optimise.appbutton.utility.controller;

import android.app.Activity;
import android.util.Log;

import com.optimise.appbutton.utility.network.Response;
import com.optimise.appbutton.utility.network.ServiceRequest;
import com.optimise.appbutton.utility.ui.IScreen;


/**
 * This class will be used as a base class for all controllers
 */
public abstract class BaseController implements IController {

    private static String LOG_TAG = "BaseController";

    private Activity activity;
    private IScreen screen;

    /**
     * @param activity
     * @param screen
     */
    public BaseController(Activity activity, IScreen screen) {
        this.activity = activity;
        this.screen = screen;
    }

    /**
     * @return the activity
     */
    @Override
    public Activity getActivity() {
        return activity;
    }

    /**
     * @return the screen
     */
    @Override
    public IScreen getScreen() {
        return screen;
    }

    /**
     * @return the screen
     */
    @Override
    public final void sendResponseToScreen(final Response response) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                try {
//                    while(!Thread.currentThread().isInterrupted()) {Ç
                    getScreen().handleUiUpdate(response);
//                    }

                } catch (Throwable tr) {
                    Log.e(LOG_TAG, "sendResponseToScreen()", tr);
                }
            }
        });
    }

    /**
     * This method is common for all button-sdk web-services
     */
    public final void handleResponse(final Response response) {
        if (response.getResponseData() instanceof byte[]) {
            try {
                parseResponse(response);
            } catch (Exception e) {
                Log.i(LOG_TAG, "parseResponse()");
                response.setSuccess(false);
            }
        }
        sendResponseToScreen(response);
    }

    /**
     * Must be overridden by subclass to support ServiceRequest without requestType
     */
    @Override
    public ServiceRequest getData(Object requestData) {
        throw new UnsupportedOperationException();
    }

    /**
     * Must be overridden by subclass to support ServiceRequest with requestType
     */
    @Override
    public ServiceRequest getData(int requestType, Object requestData, String appId, String googleAid) {
        throw new UnsupportedOperationException();
    }
}