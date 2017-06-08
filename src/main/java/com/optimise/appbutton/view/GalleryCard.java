package com.optimise.appbutton.view;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerTabStrip;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.optimise.appbutton.R;
import com.optimise.appbutton.adapter.SwipeablePagerAdapter;
import com.optimise.appbutton.model.Button;
import com.optimise.appbutton.model.Card;
import com.optimise.appbutton.utility.Preferences;
import com.optimise.appbutton.utility.StringUtils;
import com.optimise.appbutton.utility.imageutils.ImageLoader;
import com.optimise.appbutton.viewpager.MultipleDataViewPager;

/**
 * Created by anoop.singh on 10-May-17.
 */

public class GalleryCard extends BaseActivity {
    private static final String TAG = "GalleryCard";
    private Card mCard;
    private Button mButton;
    private MultipleDataViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mButton = getIntent().getParcelableExtra("button");
        mCard = mButton.getCard();
        setContentView(R.layout.gallery_card_layout);
        viewPager = (MultipleDataViewPager) findViewById(R.id.view_pager);
        SwipeablePagerAdapter pagerAdapter = new SwipeablePagerAdapter(this, mCard.getCardBody());
        pagerAdapter.setButton(mButton);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(mCard.getCardBody().getCardBodyItem().size());
        viewPager.setCurrentItem(0);
        ViewPagerIndicator dots = (ViewPagerIndicator) findViewById(R.id.pager_indicator);
        dots.setPager(viewPager);
        pagerAdapter.notifyDataSetChanged();
        addHeader();

        LinearLayout layoutCard = (LinearLayout) findViewById(R.id.layout_card);
        if (!StringUtils.isNullOrEmpty(mCard.getCardBody().getCardBodyStyle().getBackgroundColor())) {
            layoutCard.setBackgroundColor(Color.parseColor(mCard.getCardBody().getCardBodyStyle().getBackgroundColor()));
        }
        addFooter();
    }

    @Override
    public void addHeader() {
        PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_tab);
        try {
            /*for (int i = 0; i < pagerTabStrip.getChildCount(); i++) {
                View nextChild = pagerTabStrip.getChildAt(i);
                if (nextChild instanceof TextView) {
                    TextView textViewToConvert = (TextView) nextChild;
                    textViewToConvert.setSingleLine(true);
                    textViewToConvert.setGravity(Gravity.CENTER);
                    if(i == 0){
                        textViewToConvert.setText(mCard.getCardBody().getCardBodyItem().get(0).getPrimaryText());
                    }
                }
            }*/
            if (!StringUtils.isNullOrEmpty(mCard.getCardHeader().getCardHeaderStyle().getBackgroundColor())) {
                pagerTabStrip.setBackgroundColor(Color.parseColor(mCard.getCardHeader().getCardHeaderStyle().getBackgroundColor()));
            }
            if (!StringUtils.isNullOrEmpty(mCard.getCardHeader().getCardHeaderStyle().getBackgroundColor())) {
                pagerTabStrip.setTabIndicatorColor(Color.parseColor(mCard.getCardHeader().getCardHeaderStyle().getBackgroundColor()));
            }

            if (!StringUtils.isNullOrEmpty(mCard.getCardHeader().getCardHeaderStyle().getPrimaryTextFontSize())) {
                int textSize = Integer.parseInt(mCard.getCardHeader().getCardHeaderStyle().getPrimaryTextFontSize().replace("px", ""));
                pagerTabStrip.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
            }

            if (!StringUtils.isNullOrEmpty(mCard.getCardHeader().getCardHeaderStyle().getPrimaryTextColor())) {
                pagerTabStrip.setTextColor(Color.parseColor(mCard.getCardHeader().getCardHeaderStyle().getPrimaryTextColor()));
            }
        }catch (Exception ex){
            Log.e(TAG, "addHeader()" + ex);
        }
    }

    @Override
    public void addFooter() {
        TextView textViewFooter = (TextView) findViewById(R.id.text_view_footer_label);
        ImageView imageViewFooter = (ImageView) findViewById(R.id.card_footer_image);
        LinearLayout footerLayout = (LinearLayout) findViewById(R.id.footer_card);
        try {
            if (!StringUtils.isNullOrEmpty(mCard.getCardFooter().getCardFooterStyle().getBackgroundColor())) {
                footerLayout.setBackgroundColor(Color.parseColor(mCard.getCardFooter().getCardFooterStyle().getBackgroundColor()));
            }

            if (!StringUtils.isNullOrEmpty(mCard.getCardFooter().getCardFooterStyle().getPrimaryTextColor())) {
                textViewFooter.setTextColor(Color.parseColor(mCard.getCardFooter().getCardFooterStyle().getPrimaryTextColor()));
            }

            if (!StringUtils.isNullOrEmpty(mCard.getCardFooter().getCardFooterStyle().getPrimaryFontSize())) {
                int textSize = Integer.parseInt(mCard.getCardFooter().getCardFooterStyle().getPrimaryFontSize().replace("px", ""));
                textViewFooter.setTextSize(textSize);
            }

            if (!StringUtils.isNullOrEmpty(mCard.getCardFooter().getPrimaryText())) {
                textViewFooter.setText(mCard.getCardFooter().getPrimaryText());
            }

            if (!StringUtils.isNullOrEmpty(mCard.getCardFooter().getImageUri())) {
                ImageLoader imageLoader = new ImageLoader(this);
                imageLoader.DisplayImage(mCard.getCardFooter().getImageUri(), imageViewFooter);
            }
        }catch (Exception ex){
            Log.e(TAG, "addFooter()" + ex);
        }

        footerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (!StringUtils.isNullOrEmpty(mCard.getCardBody().getCardBodyItem().get(viewPager.getCurrentItem()).getDeepLink())) {
                        PackageManager pm = getPackageManager();
                        pm.getPackageInfo(mButton.getMetadata().getStoreId(), PackageManager.GET_ACTIVITIES);
                        Intent intent = new Intent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(mCard.getCardBody().getCardBodyItem().get(viewPager.getCurrentItem()).getDeepLink()));
                        startActivity(intent);
                    }
                } catch (Exception ex) {
                    if (!StringUtils.isNullOrEmpty(mButton.getMetadata().getStoreId())) {
                        String url = "https://play.google.com/store/apps/details?id=" + mButton.getMetadata().getStoreId();
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    }
                    Log.e("OptimiseButton", "Error in deep-linking " + ex);
                }
            }
        });
    }

    /**
     * @param context
     * @param button
     * @return
     */
    public static Intent cardViewIntent(Context context, Button button) {
        Intent intent = new Intent(context, GalleryCard.class);
        intent.putExtra("button", button);
        return intent;
    }
}
