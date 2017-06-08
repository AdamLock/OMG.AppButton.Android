package com.optimise.appbutton.button;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.optimise.appbutton.R;
import com.optimise.appbutton.model.Button;
import com.optimise.appbutton.model.ButtonResponse;
import com.optimise.appbutton.model.ButtonsList;
import com.optimise.appbutton.model.Placement;
import com.optimise.appbutton.utility.Preferences;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by anoop.singh on 04-May-17.
 */

public class ButtonPlacement extends LinearLayout {

    private Context context;

    /**
     * The ButtonPlacement Visibility will be by default GONE.
     * If API returns the array of buttons than only it will be visible to user.
     *
     * @param context
     */
    public ButtonPlacement(Context context) {
        super(context);
        this.context = context;
        setVisibility(GONE);
    }

    /**
     * @param context
     * @param attrs
     */
    public ButtonPlacement(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setVisibility(GONE);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    @TargetApi(11)
    public ButtonPlacement(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setVisibility(GONE);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     * @param defStyleRes
     */
    @TargetApi(21)
    public ButtonPlacement(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setVisibility(GONE);
    }

    /**
     * Method to prepare button for display
     * The Api will decide whether to show button or not.
     */
    protected void displayOptimiseButton(List<Button> buttonsList, Context context) {
        this.context = context;
        Collections.sort(buttonsList, new Comparator<Button>() {
            @Override
            public int compare(Button button, Button t1) {
                if (button.getDisplayIndex() == t1.getDisplayIndex())
                    return 0;
                else if (button.getDisplayIndex() > t1.getDisplayIndex())
                    return 1;
                else
                    return -1;
            }
        });

        if (buttonsList == null || buttonsList.isEmpty()) {
            setVisibility(GONE);
        } else {
            setVisibility(VISIBLE);
            for (Button button : buttonsList) {
                OptimiseButton optimiseButton = new OptimiseButton(context);
                LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) this.context.getResources().getDimension(R.dimen.button_height));
                layoutParams.setMargins(10, 10, 10, 10);
                optimiseButton.setLayoutParams(layoutParams);
                optimiseButton.setButtonStyles(button);
                this.addView(optimiseButton, button.getDisplayIndex());
            }
        }
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        try {
            if (AppButton.getInstance().isResponseReceived) {
                AppButton.getInstance().isConfigChanged = true;
                if (getChildCount() > 0) {
                    removeAllViews();
                }
                Bundle savedInstanceState = AppButton.getInstance().savedInstanceState;
                ButtonResponse buttonResponse = savedInstanceState.getParcelable("button_response");
                List<Placement> placements = savedInstanceState.getParcelableArrayList("placement_list");

                if (buttonResponse.getStatus().equals("200")) {
                    List<ButtonsList> buttonList = buttonResponse.getButtonList();
                    if (null != buttonList) {
                        for (int i = 0; i < buttonList.size(); i++) {
                            if (placements.get(i).getButtonPlacement().getChildCount() > 0) {
                                placements.get(i).getButtonPlacement().removeAllViews();
                            }
                            placements.get(i).getButtonPlacement().displayOptimiseButton(buttonList.get(i), AppButton.getInstance().getContext());
                        }
                    }
                }
            }
        } catch (Exception ex) {
            Log.e("ButtonPlacement", "Exception in onConfigurationChanged()" + ex);
        }
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (getChildCount() > 0) {
            removeAllViews();
        }
        if (context != null)
            Preferences.clearAllValues(context);
    }
}
