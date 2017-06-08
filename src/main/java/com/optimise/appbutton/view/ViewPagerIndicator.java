package com.optimise.appbutton.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.optimise.appbutton.R;

/**
 * Created by anoop.singh on 10-May-17.
 */

public class ViewPagerIndicator extends LinearLayout {

    public ViewPagerIndicator(Context context) {
        super(context);
    }

    public ViewPagerIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(11)
    public ViewPagerIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private ViewPager mPager;
    private ViewPager.OnPageChangeListener mOnPageChanged = new ViewPager.OnPageChangeListener() {
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        public void onPageSelected(int position) {
            mPager.setCurrentItem(position);
            updateIndicatorDots(position);
        }

        public void onPageScrollStateChanged(int state) {
        }
    };

    /**
     * Set pager to indicator
     * @param pager
     */
    public void setPager(ViewPager pager) {
        if (this.mPager != null) {
            this.mPager.removeOnPageChangeListener(this.mOnPageChanged);
        }

        mPager = pager;
        mPager.addOnPageChangeListener(this.mOnPageChanged);
        updateIndicatorDots(mPager.getCurrentItem());
    }

    /**
     *
     * @param index
     */
    private void updateIndicatorDots(int index) {
        int totalCount = mPager.getAdapter().getCount();
        if (totalCount != getChildCount()) {
            addDots(totalCount);
        }

        for (int i = 0; i < getChildCount(); ++i) {
            getChildAt(i).setEnabled(i == index);
        }

    }

    /**
     *
     * @param totalCount
     */
    private void addDots(int totalCount) {
        while (this.getChildCount() > totalCount) {
            this.removeViewAt(0);
        }

        while (this.getChildCount() < totalCount) {
            this.addView(createView());
        }

    }

    /**
     * Create the dots dynamically
     * @return
     */
    private View createView() {
        View dot = new View(this.getContext());
        int size = this.getResources().getDimensionPixelSize(R.dimen.dot_size);
        int margin = this.getResources().getDimensionPixelSize(R.dimen.dot_margin);
        LayoutParams params = new LayoutParams(size, size);
        params.setMargins(margin, margin, margin, margin);
        dot.setLayoutParams(params);
        dot.setBackgroundResource(R.drawable.dot_selector);
        return dot;
    }
}
