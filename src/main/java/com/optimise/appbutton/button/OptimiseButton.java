package com.optimise.appbutton.button;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.optimise.appbutton.R;
import com.optimise.appbutton.model.Button;
import com.optimise.appbutton.model.ButtonPreview;
import com.optimise.appbutton.model.ButtonStyle;
import com.optimise.appbutton.utility.StringUtils;
import com.optimise.appbutton.utility.imageutils.ImageLoader;
import com.optimise.appbutton.view.GalleryCard;
import com.optimise.appbutton.view.ListCard;


/**
 * Created by anoop.singh on 03-Feb-17.
 */

public class OptimiseButton extends LinearLayout implements View.OnClickListener {

    private String label = "";
    private TextView textView;
    private ImageView imageViewIcon;
    private LinearLayout buttonRootView;
    private Drawable drawable;

    private Context mContext;
    public String mButtonId;
    public Typeface mTypeFace;
    private Button button;
    private ImageLoader mImageLoader;

    public OptimiseButton(Context context) {
        super(context);
        mContext = context;
        setOnClickListener(this);
        initViews(context, (AttributeSet) null);
    }

    public OptimiseButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setOnClickListener(this);
        initViews(context, attrs);
    }

    @TargetApi(11)
    public OptimiseButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        setOnClickListener(this);
        initViews(context, attrs);
    }

    @TargetApi(21)
    public OptimiseButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        setOnClickListener(this);
        initViews(context, attrs);
    }

    /**
     * Method to set views
     *
     * @param context
     * @param attrs
     */
    private void initViews(Context context, AttributeSet attrs) {
        mImageLoader = new ImageLoader(context);
        //Inflate button layout
        LayoutInflater.from(context).inflate(R.layout.optimise_btn_layout, this);
        textView = (TextView) this.findViewById(R.id.text_view_label);
        imageViewIcon = (ImageView) this.findViewById(R.id.image_view_icon);
        buttonRootView = (LinearLayout) this.findViewById(R.id.optimise_button_root_view);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.optimiseButton);
            int Color = a.getInt(R.styleable.optimiseButton_buttonTextColor, android.graphics.Color.BLACK);
            String customFont = a.getString(R.styleable.optimiseButton_buttonFont);

            setCustomFont(context, customFont);
            int resource = 0;
            try {
                // get the text and colors specified using the names in attrs.xml
                label = a.getString(R.styleable.optimiseButton_label);
                String textStyle = a.getString(R.styleable.optimiseButton_buttonTextStyle);
                if(null != textStyle) {
                    if (textStyle.equals("bold")) {
                        textView.setTypeface(null, Typeface.BOLD);
                    } else if (textStyle.equals("normal")) {
                        textView.setTypeface(null, Typeface.NORMAL);
                    } else if (textStyle.equals("italic")) {
                        textView.setTypeface(null, Typeface.ITALIC);
                    } else if (textStyle.equals("bold|italic") || textStyle.equals("italic|bold")) {
                        textView.setTypeface(null, Typeface.BOLD_ITALIC);
                    }
                }


                drawable = a.getDrawable(R.styleable.optimiseButton_buttonIconDrawable);
                resource = a.getResourceId(R.styleable.optimiseButton_buttonBackGround, android.graphics.Color.GRAY);
                buttonRootView.setBackgroundResource(resource);
                int height = a.getInt(R.styleable.optimiseButton_buttonHeight, 0);
                int width = a.getInteger(R.styleable.optimiseButton_buttonWidth, 0);
                buttonRootView.setLayoutParams(new LinearLayoutCompat.LayoutParams(width, height));
                if(null != drawable) {
                    imageViewIcon.setImageDrawable(drawable);
                }
                if(null != label) {
                    textView.setText(label);
                }
                textView.setTextColor(Color);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                a.recycle();
            }
        }
    }


    /**
     * Method to set button id.
     * This method is for setting button Id dynamically, if not set through XML
     *
     * @param pButtonId
     */
    public void setButtonId(String pButtonId) {
        this.mButtonId = pButtonId;
    }

    /**
     * Set the font to textview
     *
     * @param ctx
     * @param asset
     * @return
     */
    private boolean setCustomFont(Context ctx, String asset) {
        try {
            mTypeFace = Typeface.createFromAsset(ctx.getAssets(), asset);
        } catch (Exception e) {
            Log.e("AppButtonSDK", "Could not get typeface: " + e.getMessage());
            return false;
        }
        setTypeface(mTypeFace);
        return true;
    }

    /**
     * Method will set all button data received from Api.
     *
     * @param button
     */
    protected void setButtonStyles(Button button) {
        if (button != null) {
            setVisibility(VISIBLE);
            this.button = button;
            ButtonPreview ctaButton = button.getCtaButton();
            if(!StringUtils.isNullOrEmpty(ctaButton.getImageUri())){
                mImageLoader.DisplayImage(ctaButton.getImageUri(), imageViewIcon);
            } else {
                imageViewIcon.setVisibility(GONE);
            }
            textView.setText(ctaButton.getLabel());

            ButtonStyle buttonStyle = button.getCtaButton().getButtonStyle();
            if(!StringUtils.isNullOrEmpty(buttonStyle.getFontSize())) {
                int fontSize = Integer.parseInt(buttonStyle.getFontSize().replace("px", ""));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
            }

            if(!StringUtils.isNullOrEmpty(buttonStyle.getTextColor())){
                if(buttonStyle.getTextColor().length() == 6){

                }else {
                    textView.setTextColor(Color.parseColor(buttonStyle.getTextColor()));
                }
            }

            GradientDrawable drawable = new GradientDrawable();
            int borderWidth = 0;
            if(!StringUtils.isNullOrEmpty(buttonStyle.getBorderWidth())){
                borderWidth = Integer.parseInt(buttonStyle.getBorderWidth().replace("px", ""));
                drawable.setStroke(borderWidth, Color.parseColor(buttonStyle.getBorderColor()));
            }

            if(!StringUtils.isNullOrEmpty(buttonStyle.getBorderColor())){
                drawable.setStroke(borderWidth, Color.parseColor(buttonStyle.getBorderColor()));
            }

            if(!StringUtils.isNullOrEmpty(buttonStyle.getBackgroundColor())) {
                drawable.setColor(Color.parseColor(buttonStyle.getBackgroundColor()));
            }

            buttonRootView.setBackgroundDrawable(drawable);
            buttonRootView.setVisibility(VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        if(button.getCard().getCardType().equals("ListBasic")){
           Intent intent = ListCard.destinationCardIntent(mContext, button);
            mContext.startActivity(intent);
        }else {
            Intent intent = GalleryCard.cardViewIntent(mContext, button);
            mContext.startActivity(intent);
        }
    }


    /**
     * Method to set font in button
     *
     * @param pTypeface
     */
    public void setTypeface(Typeface pTypeface) {
        this.mTypeFace = pTypeface;
        textView.setTypeface(pTypeface);
    }

    /**
     * Method to prepare button for display
     * The Api will decide whether to show button or not.
     *
     * @param button
     */
    public void displayOptimiseButton(Button button) {
        if (null == button) {
        } else {
            setVisibility(VISIBLE);
            setButtonStyles(button);
        }
        //Temp removed
//        setButtonVisibility(buttonVisibility);
    }

    /**
     * Method for showing button visibility.
     *
     * @param buttonVisibility
     */
    private void setButtonVisibility(boolean buttonVisibility) {
        if (buttonVisibility) {
            OptimiseButton.this.setVisibility(View.VISIBLE);
        } else {
            OptimiseButton.this.setVisibility(View.GONE);
        }
    }


    @TargetApi(16)
    @Override
    public void setBackground(Drawable background) {
        buttonRootView.setBackground(background);
    }

    @Override
    public void setBackgroundResource(@DrawableRes int resid) {
        buttonRootView.setBackgroundResource(resid);
    }

    @Override
    public void setBackgroundColor(@ColorInt int color) {
        buttonRootView.setBackgroundColor(color);
    }

    @Override
    public void setVisibility(int visibility) {
        buttonRootView.setVisibility(visibility);
        super.setVisibility(visibility);
    }

    /**
     * @param textSize
     */
    public void setTextSize(int textSize){
        if(textView != null){
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }
    }

    /**
     *
     * @param colorHex
     */
    public void setTextColor(String colorHex){
        if(textView != null){
            textView.setTextColor(Color.parseColor(colorHex));
        }
    }
}
