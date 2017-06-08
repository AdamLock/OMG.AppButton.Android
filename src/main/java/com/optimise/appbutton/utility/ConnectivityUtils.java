package com.optimise.appbutton.utility;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
 */
public class ConnectivityUtils {

	/**
	 * @param pContext
	 * @return
	 */
	public static boolean isNetworkEnabled(Context pContext) {
		NetworkInfo activeNetwork = getActiveNetwork(pContext);
		return activeNetwork != null && activeNetwork.isConnected();
	}


	/**
	 * @param pContext
	 * @return
	 */
	public static NetworkInfo getActiveNetwork(Context pContext) {
		ConnectivityManager conMngr = (ConnectivityManager) pContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		return conMngr == null ? null : conMngr.getActiveNetworkInfo();
	}

}