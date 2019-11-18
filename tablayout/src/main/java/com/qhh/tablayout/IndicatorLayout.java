package com.qhh.tablayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * @author admin
 * @version $Rev$
 * @time 2018/7/20 9:23
 * @des 控件下的 indicator
 * @packgename com.example.qhhtablayout
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */

public class IndicatorLayout extends LinearLayout {

    private Context mContext;
    private int mIndicatorCount;
    private float mIndicatorRightMargin;
    private float mIndicatorLeftMargin;
    private int mCurrentIndicator; //当前indicator记录
    private int mNormalIndicator; //未选中的indicator样式
    private int mSelectIndicatot; //选中的indicator样式

    public IndicatorLayout(Context context) {
        this(context, null, 0);
    }

    public IndicatorLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        obtainAttr(context, attrs);
        addDynamicIndicator();
    }

    private void obtainAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyIndicator);

        mIndicatorCount = typedArray.getInteger(R.styleable.MyIndicator_my_indicator_count, 3);
        mIndicatorLeftMargin = typedArray.getDimension(R.styleable.MyIndicator_my_indicator_margin_left, 0);
        mIndicatorRightMargin = typedArray.getDimension(R.styleable.MyIndicator_my_indicator_margin_right, 0);
        mNormalIndicator = typedArray.getInteger(R.styleable.MyIndicator_my_indicator_image_unselect, R.drawable.red_hollow_dot);
        mSelectIndicatot = typedArray.getInteger(R.styleable.MyIndicator_my_indicator_image_select, R.drawable.red_solid_dot);

        typedArray.recycle();
    }

    private void addDynamicIndicator() {
        this.removeAllViews();
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.rightMargin = dp2px(mIndicatorRightMargin);
        layoutParams.leftMargin = dp2px(mIndicatorLeftMargin);
        for (int i = 0; i < mIndicatorCount; i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(i == 0 ? mSelectIndicatot : mNormalIndicator);
            addView(imageView, i, layoutParams);
        }
    }

    public void setCurrentIndicator(int position) {
        mCurrentIndicator = position;
        updateIndicator(position);
    }

    private void updateIndicator(int position) {
        for (int i = 0; i < mIndicatorCount; i++) {
            ImageView indicator = (ImageView) getChildAt(i);
            indicator.setImageResource(position == i ? mSelectIndicatot : mNormalIndicator);
        }
    }

    public void setIndicatorCount(int indicatorCount) {
        mIndicatorCount = indicatorCount;
        addDynamicIndicator();
    }

    protected int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    protected int sp2px(float sp) {
        final float scale = this.mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scale + 0.5f);
    }

}
