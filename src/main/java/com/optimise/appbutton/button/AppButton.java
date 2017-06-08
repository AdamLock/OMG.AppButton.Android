package com.optimise.appbutton.button;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import com.optimise.appbutton.constants.Constants;
import com.optimise.appbutton.exception.AppButtonException;
import com.optimise.appbutton.model.ButtonResponse;
import com.optimise.appbutton.model.ButtonsList;
import com.optimise.appbutton.model.Placement;
import com.optimise.appbutton.utility.AdvertisingIdClient;
import com.optimise.appbutton.utility.ConnectivityUtils;
import com.optimise.appbutton.utility.StringUtils;
import com.optimise.appbutton.utility.controller.ButtonApiController;
import com.optimise.appbutton.utility.network.Response;
import com.optimise.appbutton.utility.ui.IScreen;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by anoop.singh on 06-Feb-17.
 */

public class AppButton implements IScreen, AdvertisingIdClient.Listener {
    private static AppButton iAppButton;
    public static String APP_ID;
    private Context mContext;
    protected Bundle savedInstanceState;
    private static final String EXTRA_PLACEMENT_LIST = "placement_list";
    private static final String EXTRA_BUTTON_RESPONSE = "button_response";
    protected boolean isResponseReceived, isConfigChanged;

    private AppButton() {
    }

    public static AppButton getInstance() {
        if (iAppButton == null) {
            iAppButton = new AppButton();
        }
        return iAppButton;
    }


    private List<Placement> placements = new ArrayList<>();

    /**
     * Initialize the SDK
     *
     * @param pContext
     */
    public void initSDK(Context pContext, Placement placement) {

        try {
            if (StringUtils.isNullOrEmpty(APP_ID)) {
                throw new AppButtonException("Please set your APP_ID AppButton.APP_ID = \"Your APP_ID\"");
            } else if (pContext == null) {
                throw new AppButtonException("AppButton: context can not be null");
            }
            if (!isConfigChanged) {
                isResponseReceived = false;
                savedInstanceState = new Bundle();
                if (this.placements != null) {
                    this.placements.clear();
                }

                this.mContext = pContext;
                if (placement == null) {
                    throw new AppButtonException("AppButton: Placement can not be null.");
                }
                this.placements.add(placement);
                savedInstanceState.putParcelableArrayList(EXTRA_PLACEMENT_LIST, (ArrayList<? extends Parcelable>) placements);
                AdvertisingIdClient.getAdvertisingId(mContext, this);
//                new ButtonApiController((Activity) pContext, this).getData(Constants.REQUEST_TYPE_BUTTON, placements, APP_ID, GOOGLE_AID);
            }
        } catch (Exception ex) {
            Log.e("AppButton", "initSDK()" + ex.getMessage());
        }
    }


    /**
     * @param pContext
     * @param placement1
     * @param placement2
     */
    public void initSDK(Context pContext, Placement placement1, Placement placement2) {
        try {
            if (StringUtils.isNullOrEmpty(APP_ID)) {
                throw new AppButtonException("AppButton: Please set your APP_ID AppButton.APP_ID = \"Your APP_ID\".");
            } else if (pContext == null) {
                throw new AppButtonException("AppButton: context can not be null.");
            }

            if (!isConfigChanged) {
                isResponseReceived = false;
                savedInstanceState = new Bundle();
                if (this.placements != null) {
                    this.placements.clear();
                }

                if (placement1 == null && placement2 == null) {
                    throw new AppButtonException("AppButton: Placement can not be null.");
                }

                if (placement1 != null) {
                    this.placements.add(placement1);
                }
                if (placement2 != null) {
                    this.placements.add(placement2);
                }
                this.mContext = pContext;
                savedInstanceState.putParcelableArrayList(EXTRA_PLACEMENT_LIST, (ArrayList<? extends Parcelable>) placements);
                AdvertisingIdClient.getAdvertisingId(mContext, this);
            }
        } catch (Exception ex) {
            Log.e("AppButton", "initSDK()" + ex.getMessage());
        }
    }

    @Override
    public void handleUiUpdate(Response response) {
        ButtonResponse buttonResponse = (ButtonResponse) response.getResponseObject();
        savedInstanceState.putParcelable(EXTRA_BUTTON_RESPONSE, buttonResponse);
        showButtons(buttonResponse);
    }

    /**
     * @param buttonResponse
     */
    private void showButtons(ButtonResponse buttonResponse) {
        if (buttonResponse != null) {
            if (buttonResponse.getStatus().equals("200")) {
                isResponseReceived = true;
                List<ButtonsList> buttonList = buttonResponse.getButtonList();
                if (null != buttonList) {
                    for (int i = 0; i < buttonList.size(); i++) {
                        if (placements.get(i).getButtonPlacement().getChildCount() > 0) {
                            placements.get(i).getButtonPlacement().removeAllViews();
                        }
                        placements.get(i).getButtonPlacement().displayOptimiseButton(buttonList.get(i), mContext);
                    }
                }
            }
        }
    }

    protected Context getContext() {
        return mContext;
    }


    @Override
    public void onAdvertisingIdClientSuccess(AdvertisingIdClient.AdInfo adInfo) {
        Log.i("AdInfo", "******************Advertising ID " + adInfo.getId());
        if (ConnectivityUtils.isNetworkEnabled(mContext)) {
            new ButtonApiController((Activity) mContext, this).getData(Constants.REQUEST_TYPE_BUTTON, placements, APP_ID, adInfo.getId());
        } else {
            Log.e("AppButton", "Internet connectivity is not available.");
        }
    }

    @Override
    public void onAdvertisingIdClientFail(Exception exception) {
        if (ConnectivityUtils.isNetworkEnabled(mContext)) {
            new ButtonApiController((Activity) mContext, this).getData(Constants.REQUEST_TYPE_BUTTON, placements, APP_ID, "");
        } else {
            Log.e("AppButton", "Internet connectivity is not available.");
        }
    }
}
