package com.optimise.appbutton.utility.controller;

import android.app.Activity;
import android.util.Log;

import com.optimise.appbutton.constants.Constants;
import com.optimise.appbutton.exception.AppButtonException;
import com.optimise.appbutton.model.ButtonResponse;
import com.optimise.appbutton.model.Placement;
import com.optimise.appbutton.parser.JsonParser;
import com.optimise.appbutton.utility.StringUtils;
import com.optimise.appbutton.utility.network.AsyncConnection;
import com.optimise.appbutton.utility.network.Response;
import com.optimise.appbutton.utility.network.ServiceRequest;
import com.optimise.appbutton.utility.ui.IScreen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


/**
 * Created by anoop.singh on 06-Feb-17.
 */

public class ButtonApiController extends BaseController {
    int placementId[];

    @Override
    public ServiceRequest getData(int requestType, Object requestData, String appId, String googleAid) {
        List<Placement> placementList = (List<Placement>)requestData;
        placementId = new int[placementList.size()];
        int count = 0;
        for(Placement placement : placementList){
            placementId[count] = placement.getPlacementId();
            count++;
        }

        ServiceRequest serviceRq = new ServiceRequest();
        serviceRq.setHttpMethod(AsyncConnection.HTTP_METHOD.POST);
        serviceRq.setPriority(AsyncConnection.PRIORITY.LOW);
        serviceRq.setResponseController(this);
        serviceRq.setDataType(requestType);
        switch (requestType) {
            case Constants.REQUEST_TYPE_BUTTON: {
                String url = Constants.API;
                JSONObject jsonObject = null;
                try {
                    jsonObject = createRequest(placementList, appId, googleAid);
                } catch (AppButtonException e) {
                    Log.e("ButtonApiController", e.getMessage());
                    return serviceRq;
                }
                serviceRq.setRequestData(jsonObject);
                serviceRq.setUrl(url);
                break;
            }
        }

        AsyncConnection connection = new AsyncConnection();
        connection.execute(serviceRq);
        return serviceRq;
    }

    /**
     * @param activity
     * @param screen
     */
    public ButtonApiController(Activity activity, IScreen screen) {
        super(activity, screen);
    }

    /**
     * Method to create button placement list
     *
     * @return
     */
    private JSONObject createRequest(List<Placement> buttonPlacements, String appId, String googleAid) throws AppButtonException {
        JSONObject request = new JSONObject();
        try {
           request.put("mobileAppId", appId);
            request.put("deviceId", googleAid);
//            request.put("appName", "uber");
            request.put("deviceOS", "android");
            request.put("url", JSONObject.NULL);
            JSONArray placements = new JSONArray();
            request.put("placements", placements);
            int size = buttonPlacements.size();
            for (int i = 0; i < size; i++) {
                Placement buttonPlacement = buttonPlacements.get(i);
                JSONObject placement = new JSONObject();
                placement.put("placementId", buttonPlacement.getPlacementId());
                JSONObject context = new JSONObject();
                if (buttonPlacement.getUserLocation() != null) {
                    JSONObject userLocation = buttonPlacement.getUserLocation().toJson();
                    context.put("userLocation", userLocation);
                }

                if (buttonPlacement.getItemLocation() != null) {
                    JSONObject itemLocation = buttonPlacement.getItemLocation().getJson();
                    context.put("itemLocation", itemLocation);
                }

                if (buttonPlacement.getItemProperties() != null && buttonPlacement.getItemProperties().size() > 0) {
                    JSONObject itemPropertiesJson = new JSONObject();
                    JSONArray jsonKeywords = new JSONArray();
                    itemPropertiesJson.put("keywords", jsonKeywords);
                    for (int j = 0; j < buttonPlacement.getItemProperties().size(); j++) {
                        jsonKeywords.put(j, buttonPlacement.getItemProperties().get(j));
                    }
                    context.put("ItemProperties", itemPropertiesJson);
                }

                placement.put("context", context);
                placement.put("userLocalTime", buttonPlacement.getUserLocalTime());
                placements.put(placement);
            }


        } catch (JSONException je) {
            Log.e("ButtonApiController", "Error: exception occurred during request creation " + je);
        }

        return request;
    }


    @Override
    public void parseResponse(Response response) {
        switch (response.getDataType()) {
            case Constants.REQUEST_TYPE_BUTTON: {
                ButtonResponse buttonResponse = JsonParser.getButtonResponse(response, placementId);
                response.setResponseObject(buttonResponse);
                break;
            }
        }
    }
}
