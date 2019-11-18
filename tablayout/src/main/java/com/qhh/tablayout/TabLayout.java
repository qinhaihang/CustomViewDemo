package com.qhh.tablayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author admin
 * @version $Rev$
 * @time 2018/7/6 16:54
 * @des ${TODO}
 * @packgename com.hbjs.renrenshengyi.utils.myview
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */

public class TabLayout extends FrameLayout {

    private Context mContext;
    private LinearLayout mTabRoot;
    protected ArrayList<String> tabData = new ArrayList<>();
    protected Drawable indicator;
    private int mTabCount;
    private int mTextSelectColor;
    private int mTextUnselectorColor;
    private int mCurrentTab = 0; // 当前选中tab记录器
    private float mTabTextSize;
    private int mIndicatorMargin; //指示器和文字的距离

    private OnselectTabListener listener;

    public TabLayout(@NonNull Context context) {
        this(context, null, 0);
    }

    public TabLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mTabRoot = new LinearLayout(mContext);
        addView(mTabRoot);

        obtainAttributes(context, attrs);
    }

    private void obtainAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabLayout);

        mTextSelectColor = typedArray.getColor(R.styleable.TabLayout_tab_text_selector_color, Color.parseColor("#ff4d4d4d"));
        mTextUnselectorColor = typedArray.getColor(R.styleable.TabLayout_tab_text_unselector_color, Color.parseColor("#ff4d4d4d"));
        //getDimension无法获取到xml中属性的设置字体大小的值
        //mTabTextSize = typedArray.getDimension(R.styleable.MyTabLayout_tab_text_size, 10);
        mTabTextSize = typedArray.getFloat(R.styleable.TabLayout_tab_text_size, sp2px(10));
        mIndicatorMargin = (int) typedArray.getDimension(R.styleable.TabLayout_tab_indicator_right_margin, dp2px(4));

        typedArray.recycle();
    }

    public void setTabData(ArrayList<String> tabData, Drawable indicator) {
        if (tabData == null || tabData.size() == 0) {
            throw new IllegalStateException("TabData can not be NULL or EMPTY !");
        }

        this.tabData.clear();
        this.tabData.addAll(tabData);
        this.indicator = indicator;

        notifyDataSetChanged();
    }

    /**
     * 更新数据
     */
    private void notifyDataSetChanged() {
        mTabRoot.removeAllViews();
        mTabCount = tabData.size();
        mTabRoot.setOrientation(LinearLayout.HORIZONTAL);
        for (int i = 0; i < mTabCount; i++) {

            LinearLayout tabView = new LinearLayout(mContext);
            tabView.setOrientation(LinearLayout.HORIZONTAL);
            tabView.setGravity(Gravity.CENTER);

            LinearLayout.LayoutParams imageViewParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            imageViewParam.setMargins(0, 0, mIndicatorMargin, 0);
            imageViewParam.gravity = Gravity.CENTER;
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(imageViewParam);

            LinearLayout.LayoutParams textViewParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            textViewParam.gravity = Gravity.CENTER;
            TextView textView = new TextView(mContext);
            textView.setLayoutParams(textViewParam);

            tabView.addView(imageView, 0);
            tabView.addView(textView, 1);

            tabView.setTag(i);
            addTab(i, tabView);
        }

        updateTabSelection(mCurrentTab);
    }

    private void addTab(int i, LinearLayout tabView) {
        ImageView indicator = (ImageView) tabView.getChildAt(0);
        TextView tabTitle = (TextView) tabView.getChildAt(1);
        tabTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTabTextSize);
        tabTitle.setText(tabData.get(i));

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        mTabRoot.addView(tabView, i, layoutParams);


        tabView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) v.getTag();
                if (mCurrentTab != position) {
                    setCurrentTab(position);
                    if (null != listener)
                        listener.tabChange(position);
                } else {
                    //点击是已选中的则不变
                    if (null != listener)
                        listener.tabUnChange(position);
                }
            }
        });
    }

    private void addTab(int i, TextView tabView) {
        tabView.setText(tabData.get(i));
        tabView.setGravity(Gravity.CENTER);
        tabView.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTabTextSize);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        mTabRoot.addView(tabView, i, layoutParams);

        tabView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) v.getTag();
                if (mCurrentTab != position) {
                    setCurrentTab(position);
                    if (null != listener)
                        listener.tabChange(position);
                } else {
                    //点击是已选中的则不变
                    if (null != listener)
                        listener.tabUnChange(position);
                }
            }
        });

    }

    public void setCurrentTab(int currentTab) {
        this.mCurrentTab = currentTab;
        updateTabSelection(currentTab);
    }

    //private void updateTabSelection(int currentTab) {
    //    for (int i = 0; i < mTabCount; i++) {
    //        TextView tabView = (TextView) mTabRoot.getChildAt(i);
    //        boolean isSelect = i == currentTab;
    //        tabView.setTextColor(isSelect ? mTextSelectColor : mTextUnselectorColor);
    //        tabView.setCompoundDrawablesWithIntrinsicBounds(isSelect ? indicator : null, null, null, null);
    //        tabView.setCompoundDrawablePadding(isSelect ? 4 : 0);
    //    }
    //}

    private void updateTabSelection(int currentTab) {
        for (int i = 0; i < mTabCount; i++) {
            LinearLayout tabView = (LinearLayout) mTabRoot.getChildAt(i);
            boolean isSelect = i == currentTab;
            ImageView indicatorPic = (ImageView) tabView.getChildAt(0);
            TextView tabTitle = (TextView) tabView.getChildAt(1);
            tabTitle.setTextColor(isSelect ? mTextSelectColor : mTextUnselectorColor);
            indicatorPic.setImageDrawable(indicator);
            indicatorPic.setVisibility(isSelect ? VISIBLE : GONE);
        }
    }

    protected int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    protected int sp2px(float sp) {
        final float scale = this.mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scale + 0.5f);
    }

    public void setOnselectTabListener(OnselectTabListener listener) {
        this.listener = listener;
    }

    public interface OnselectTabListener {
        void tabChange(int position);

        void tabUnChange(int position);
    }
}
