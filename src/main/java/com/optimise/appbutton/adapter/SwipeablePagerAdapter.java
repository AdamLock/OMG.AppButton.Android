package com.optimise.appbutton.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.optimise.appbutton.R;
import com.optimise.appbutton.model.Button;
import com.optimise.appbutton.model.CardBody;
import com.optimise.appbutton.model.CardBodyItem;
import com.optimise.appbutton.model.CardBodyStyle;
import com.optimise.appbutton.utility.StringUtils;
import com.optimise.appbutton.utility.imageutils.ImageLoader;

/**
 * Created by anoop.singh on 10-May-17.
 */

public class SwipeablePagerAdapter extends PagerAdapter {
    private static final String TAG = "SwipeablePagerAdapter";
    private LayoutInflater mLayoutInflater;
    private ImageLoader mImageLoader;
    private Context mContext;
    private CardBody mCardBody;
    private Button mButton;

    public SwipeablePagerAdapter(Context pContext, CardBody cardBody) {
        mLayoutInflater = (LayoutInflater) pContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mImageLoader = new ImageLoader(pContext);
        mCardBody = cardBody;
        mContext = pContext;
    }

    /**
     * @param button
     */
    public void setButton(Button button) {
        this.mButton = button;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return inflateList(container, position);
    }


    @Override
    public int getCount() {
        return /*mCardList.size()*/mCardBody.getCardBodyItem().size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.getTag() == object;
    }


    @NonNull
    @SuppressLint({"InflateParams"})
    protected View inflateList(ViewGroup container, final int position) {
        LinearLayout groupContainer = (LinearLayout) mLayoutInflater.inflate(R.layout.swipeable_card_layout, (ViewGroup) null);
        ViewGroup itemsContainer = (ViewGroup) groupContainer.findViewById(R.id.root_view_swipeable_layout);
        ImageView imageView = (ImageView) itemsContainer.findViewById(R.id.image_view_product);
        TextView textViewTitle = (TextView) itemsContainer.findViewById(R.id.text_view_product_title);
        TextView textViewPrice = (TextView) itemsContainer.findViewById(R.id.text_view_product_price);
        try {
            CardBodyItem item = mCardBody.getCardBodyItem().get(position);
            if (!StringUtils.isNullOrEmpty(item.getImageUri())) {
                mImageLoader.DisplayImage(item.getImageUri(), imageView);
            }
            textViewTitle.setText(item.getPrimaryText());
            textViewPrice.setText(item.getSecondaryText());

            CardBodyStyle style = mCardBody.getCardBodyStyle();
            int textSize = 0;
            if (!StringUtils.isNullOrEmpty(style.getPrimaryTextFontSize())) {
                textSize = Integer.parseInt(style.getPrimaryTextFontSize().replace("px", ""));
                textViewTitle.setTextSize(textSize);
            }

            if (!StringUtils.isNullOrEmpty(style.getSecondaryTextFontSize())) {
                textSize = Integer.parseInt(style.getSecondaryTextFontSize().replace("px", ""));
                textViewPrice.setTextSize(textSize);
            }

            if (!StringUtils.isNullOrEmpty(style.getPrimaryTextColor())) {
                textViewTitle.setTextColor(Color.parseColor(style.getPrimaryTextColor()));
            }

            if (!StringUtils.isNullOrEmpty(style.getSecondaryTextColor())) {
                textViewPrice.setTextColor(Color.parseColor(style.getSecondaryTextColor()));
            }

            if (!StringUtils.isNullOrEmpty(style.getBackgroundColor())) {
                itemsContainer.setBackgroundColor(Color.parseColor(style.getBackgroundColor()));
            }

        } catch (Exception ex) {
            Log.e(TAG, "inflateList()" + ex);
        }
        groupContainer.setTag(groupContainer);
        container.addView(groupContainer);

        itemsContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (!StringUtils.isNullOrEmpty(mCardBody.getCardBodyItem().get(position).getDeepLink())) {
                        PackageManager pm = mContext.getPackageManager();
                        pm.getPackageInfo(mButton.getMetadata().getStoreId(), PackageManager.GET_ACTIVITIES);
                        Intent intent = new Intent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(mCardBody.getCardBodyItem().get(position).getDeepLink()));
                        mContext.startActivity(intent);
                    }
                } catch (Exception ex) {
                    if (!StringUtils.isNullOrEmpty(mButton.getMetadata().getStoreId())) {
                        String url = "https://play.google.com/store/apps/details?id=" + mButton.getMetadata().getStoreId();
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        mContext.startActivity(intent);
                    }
                    Log.e("OptimiseButton", "Error in deep-linking " + ex);
                }
            }
        });

        if(position == 0){
            getPageTitle(0);
        }
        return groupContainer;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ViewGroup) object);
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        destroyItem((ViewGroup) container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mCardBody.getCardBodyItem().get(position).getPrimaryText();
    }
}
