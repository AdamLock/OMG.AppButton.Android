package com.optimise.appbutton.utility.controller;

import android.app.Activity;

import com.optimise.appbutton.utility.network.Response;
import com.optimise.appbutton.utility.network.ServiceRequest;
import com.optimise.appbutton.utility.ui.IScreen;


/**
 * This interface will be used as a base interface for all controllers
 */
public interface IController {

	IScreen getScreen();

	Activity getActivity();

	ServiceRequest getData(int dataType, Object requestData, String appId, String googleAid);

	ServiceRequest getData(Object requestData);

	void handleResponse(Response response);

	void parseResponse(Response response);
	
	void sendResponseToScreen(Response response);
}
