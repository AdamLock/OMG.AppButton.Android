package com.optimise.appbutton.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.optimise.appbutton.R;
import com.optimise.appbutton.model.CardBodyItem;
import com.optimise.appbutton.model.CardBodyStyle;
import com.optimise.appbutton.utility.StringUtils;
import com.optimise.appbutton.utility.imageutils.ImageLoader;

import java.util.List;

/**
 * Created by anoop.singh on 07-Mar-17.
 */

public class ListCardAdapter extends BaseAdapter {
    private final String TAG = "ListCardAdapter";
    private List<CardBodyItem> destinationModelList;
    private LayoutInflater mInflater;
    private CardBodyStyle cardBodyStyle;
    private ImageLoader mImageLoader;

    public ListCardAdapter(Context pContext) {
        mInflater = (LayoutInflater) pContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mImageLoader = new ImageLoader(pContext);
    }

    public void setData(List<CardBodyItem> pList) {
        destinationModelList = pList;
    }

    /**
     * @param cardBodyStyle
     */
    public void setCardBodyStyle(CardBodyStyle cardBodyStyle) {
        this.cardBodyStyle = cardBodyStyle;
    }

    @Override
    public int getCount() {
        return destinationModelList == null ? 0 : destinationModelList.size();
    }

    @Override
    public Object getItem(int i) {
        try {
            return destinationModelList.get(i);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_list_card, null);
            convertView.setTag(holder);
            holder.mTextViewPrimary = (TextView) convertView.findViewById(R.id.text_view_destination_item_hotel_name);
            holder.mTextViewSecondary = (TextView) convertView.findViewById(R.id.text_view_destination_item_hotel_price);
            holder.mImageViewItem = (ImageView) convertView.findViewById(R.id.image_view_item);
            holder.cardDivider = (View) convertView.findViewById(R.id.card_list_divider);
            try {
                if (!StringUtils.isNullOrEmpty(cardBodyStyle.getPrimaryTextColor())) {
                    holder.mTextViewPrimary.setTextColor(Color.parseColor(cardBodyStyle.getPrimaryTextColor()));
                }

                if (!StringUtils.isNullOrEmpty(cardBodyStyle.getPrimaryTextFontSize())) {
                    int textSizePrimary = Integer.parseInt(cardBodyStyle.getPrimaryTextFontSize().replace("px", ""));
                    holder.mTextViewPrimary.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizePrimary);
                }

                if (!StringUtils.isNullOrEmpty(cardBodyStyle.getSecondaryTextColor())) {
                    holder.mTextViewSecondary.setTextColor(Color.parseColor(cardBodyStyle.getSecondaryTextColor()));
                }

                if (!StringUtils.isNullOrEmpty(cardBodyStyle.getSecondaryTextFontSize())) {
                    int textSizeSecondary = Integer.parseInt(cardBodyStyle.getSecondaryTextFontSize().replace("px", ""));
                    holder.mTextViewSecondary.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSecondary);
                }

                if (!StringUtils.isNullOrEmpty(cardBodyStyle.getItemDividerColor())) {
                    holder.cardDivider.setBackgroundColor(Color.parseColor(cardBodyStyle.getItemDividerColor()));
                }

                GradientDrawable bgShape = new GradientDrawable();
                if (!StringUtils.isNullOrEmpty(cardBodyStyle.getItemDividerStyle())) {
                    int borderWidth = Integer.parseInt(cardBodyStyle.getItemDividerHeight().replace("px", ""));
                    bgShape.setStroke(borderWidth, Color.parseColor(cardBodyStyle.getItemDividerColor()));
                    holder.cardDivider.setBackgroundDrawable(bgShape);
                }
            }catch (Exception ex){
                Log.e(TAG, "getView()" + ex);
            }
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        try {
            CardBodyItem model = destinationModelList.get(i);
            holder.mTextViewPrimary.setText(model.getPrimaryText());
            holder.mTextViewSecondary.setText(model.getSecondaryText());
            if (!StringUtils.isNullOrEmpty(model.getImageUri())) {
                mImageLoader.DisplayImage(model.getImageUri(), holder.mImageViewItem);
            }
        } catch (Exception ex) {
            Log.e(TAG, "getView()" + ex);
        }

        return convertView;
    }

    class ViewHolder {
        ImageView mImageViewItem;
        TextView mTextViewPrimary;
        TextView mTextViewSecondary;
        View cardDivider;
    }
}
