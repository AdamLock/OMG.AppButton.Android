package com.optimise.appbutton.viewpager;

import android.content.Context;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by anoop.singh on 18-Apr-17.
 */

public class MultipleDataViewPager extends ViewPager {
    private static final String TAG = "MultipleDataViewPager";

    public MultipleDataViewPager(Context context) {
        super(context);
    }

    public MultipleDataViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int height = 0;
        int tabHeight = 0;
        View tabStrip = getChildAt(0);
        if (tabStrip instanceof PagerTabStrip) {
//            tabStrip.measure(MeasureSpec.EXACTLY, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            tabHeight += tabStrip.getMeasuredHeight();
        }

       /* View viewPagerIndicator = getChildAt(1);
        int indicatorHeight = 0;
        if(viewPagerIndicator instanceof ViewPagerIndicator){
            viewPagerIndicator.measure(0,MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            indicatorHeight += viewPagerIndicator.getMeasuredHeight();
        }*/


        for (int i = 1; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if (h > height) height = h;
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height + tabHeight, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


}
