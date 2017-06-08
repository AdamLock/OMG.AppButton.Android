package com.optimise.appbutton.view;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.optimise.appbutton.R;
import com.optimise.appbutton.adapter.ListCardAdapter;
import com.optimise.appbutton.model.Button;
import com.optimise.appbutton.model.Card;
import com.optimise.appbutton.model.CardBodyStyle;
import com.optimise.appbutton.utility.StringUtils;
import com.optimise.appbutton.utility.Util;
import com.optimise.appbutton.utility.imageutils.ImageLoader;

/**
 * Created by anoop.singh on 28-Feb-17.
 */

public class ListCard extends BaseActivity {
    public static final String TAG = "ListCard";
    private Card card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_card_layout);
        final Button button = getIntent().getParcelableExtra("button");
        card = button.getCard();

        addHeader();

        ListView listView = (ListView) findViewById(R.id.list_view_list_card);
        CardBodyStyle cardBodyStyle = card.getCardBody().getCardBodyStyle();
        if (!StringUtils.isNullOrEmpty(cardBodyStyle.getBackgroundColor())) {
            listView.setBackgroundColor(Color.parseColor(cardBodyStyle.getBackgroundColor()));
        }

        ListCardAdapter listCardAdapter = new ListCardAdapter(this);
        listCardAdapter.setData(card.getCardBody().getCardBodyItem());
        listCardAdapter.setCardBodyStyle(card.getCardBody().getCardBodyStyle());
        listView.setAdapter(listCardAdapter);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            listView.setNestedScrollingEnabled(true);
        } else {
            Util.setListViewHeightBasedOnChildren(listView);
        }

        listCardAdapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (!StringUtils.isNullOrEmpty(card.getCardBody().getCardBodyItem().get(i).getDeepLink())) {
                        PackageManager pm = getPackageManager();
                        pm.getPackageInfo(button.getMetadata().getStoreId(), PackageManager.GET_ACTIVITIES);
                        Intent intent = new Intent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(card.getCardBody().getCardBodyItem().get(i).getDeepLink()));
                        startActivity(intent);
                    }
                } catch (Exception ex) {
                    if (!StringUtils.isNullOrEmpty(button.getMetadata().getStoreId())) {
                        String url = "https://play.google.com/store/apps/details?id=" + button.getMetadata().getStoreId();
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    }
                    Log.e(TAG, "Error in deep-linking " + ex);
                }
            }
        });

        addFooter();
    }


    @Override
    public void addHeader() {

        if (card.getCardHeader() == null) {
            findViewById(R.id.card_header).setVisibility(View.GONE);
        } else {
            try {
                TextView cardHeader = (TextView) findViewById(R.id.card_header);
                cardHeader.setText(card.getCardHeader().getPrimaryText());

                if (!StringUtils.isNullOrEmpty(card.getCardHeader().getCardHeaderStyle().getPrimaryTextFontSize())) {
                    int headerFontSize = Integer.parseInt(card.getCardHeader().getCardHeaderStyle().getPrimaryTextFontSize().replace("px", ""));
                    cardHeader.setTextSize(headerFontSize);
                }

                if (!StringUtils.isNullOrEmpty(card.getCardHeader().getCardHeaderStyle().getPrimaryTextColor())) {
                    cardHeader.setTextColor(Color.parseColor(card.getCardHeader().getCardHeaderStyle().getPrimaryTextColor()));
                }

                if (!StringUtils.isNullOrEmpty(card.getCardHeader().getCardHeaderStyle().getBackgroundColor())) {
                    cardHeader.setBackgroundColor(Color.parseColor(card.getCardHeader().getCardHeaderStyle().getBackgroundColor()));
                }
            } catch (Exception ex) {
                Log.e(TAG, "addHeader()" + ex.getMessage());
            }
        }
    }

    @Override
    public void addFooter() {
        try {
            //Footer
            LinearLayout cardFooter = (LinearLayout) findViewById(R.id.footer_card);
            TextView cardFooterText = (TextView) findViewById(R.id.text_view_footer_label);
            ImageView cardFooterImage = (ImageView) findViewById(R.id.card_footer_image);

            if (!StringUtils.isNullOrEmpty(card.getCardFooter().getImageUri())) {
                ImageLoader imageLoader = new ImageLoader(this);
                imageLoader.DisplayImage(card.getCardFooter().getImageUri(), cardFooterImage);
            } else {
                cardFooterImage.setVisibility(View.GONE);
            }

            if (!StringUtils.isNullOrEmpty(card.getCardFooter().getPrimaryText())) {
                cardFooterText.setText(card.getCardFooter().getPrimaryText());
            }

            if (!StringUtils.isNullOrEmpty(card.getCardFooter().getCardFooterStyle().getPrimaryFontSize())) {
                int primaryTextFontSize = Integer.parseInt(card.getCardFooter().getCardFooterStyle().getPrimaryFontSize().replace("px", ""));
                cardFooterText.setTextSize(primaryTextFontSize);
            }

            if (!StringUtils.isNullOrEmpty(card.getCardFooter().getCardFooterStyle().getPrimaryTextColor())) {
                cardFooterText.setTextColor(Color.parseColor(card.getCardFooter().getCardFooterStyle().getPrimaryTextColor()));
            }

            if (!StringUtils.isNullOrEmpty(card.getCardFooter().getCardFooterStyle().getBackgroundColor())) {
                cardFooter.setBackgroundColor(Color.parseColor(card.getCardFooter().getCardFooterStyle().getBackgroundColor()));
            }
        } catch (Exception ex) {
            Log.e(TAG, "addFooter()" + ex.getMessage());
        }
    }

    /**
     * @param context
     * @param button
     * @return
     */
    public static Intent destinationCardIntent(Context context, Button button) {
        Intent intent = new Intent(context, ListCard.class);
        intent.putExtra("button", button);
        return intent;
    }
}
