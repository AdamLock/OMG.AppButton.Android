package com.optimise.appbutton.utility.ui;


import com.optimise.appbutton.utility.network.Response;

/**
 * @author anoop.singh
 */
public interface IScreen {
	/**
     * Subclass should over-ride this method to update the UI with response.
     * Caller of this method should be calling this method only from UI thread.
     * @param response
     */
	void handleUiUpdate(Response response);
}

