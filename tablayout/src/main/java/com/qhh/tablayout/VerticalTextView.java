package com.qhh.tablayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * @author admin
 * @version $Rev$
 * @time 2018/7/30 17:26
 * @des ${TODO}
 * @packgename com.example.qhhtablayout
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */

public class VerticalTextView extends LinearLayout {

    private Context mContext;
    private TextView mTvTop;
    private TextView mTvBottom;
    private String mTopText;
    private String mBottomText;
    private float mBottomTextSize;
    private float mTopTextSize;
    private float mSpaceSize;
    private int mTopTextColor;
    private int mBottomTextColor;

    public VerticalTextView(Context context) {
        this(context, null, 0);
    }

    public VerticalTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_vertical_textview, this, true);

        mTvTop = view.findViewById(R.id.tv_top);
        mTvBottom = view.findViewById(R.id.tv_bottom);

        obtainAttributes(context, attrs);

        updateUI();
    }

    private void obtainAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.VerticalTextView);

        mTopText = typedArray.getString(R.styleable.VerticalTextView_vt_top_text);
        mBottomText = typedArray.getString(R.styleable.VerticalTextView_vt_bottom_text);
        mBottomTextSize = typedArray.getDimension(R.styleable.VerticalTextView_vt_bottom_text_size, sp2px(14));
        mTopTextSize = typedArray.getDimension(R.styleable.VerticalTextView_vt_top_text_size, sp2px(16));
        mSpaceSize = typedArray.getDimension(R.styleable.VerticalTextView_vt_space, dp2px(10));
        mTopTextColor = typedArray.getColor(R.styleable.VerticalTextView_vt_top_text_color, Color.parseColor("#ff4d4d4d"));
        mBottomTextColor = typedArray.getColor(R.styleable.VerticalTextView_vt_bottom_text_color, Color.parseColor("#ff4d4d4d"));

        typedArray.recycle();
    }

    private void updateUI() {
        mTvTop.setText(mTopText);
        mTvTop.setTextColor(mTopTextColor);
        mTvTop.getPaint().setTextSize(mTopTextSize);

        mTvBottom.setText(mBottomText);
        mTvBottom.setTextColor(mBottomTextColor);
        mTvBottom.getPaint().setTextSize(mBottomTextSize);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = (int) mSpaceSize;
        mTvBottom.setLayoutParams(layoutParams);
    }

    public void setTopText(String topText) {
        mTopText = topText;
        mTvTop.setText(mTopText);
    }

    public void setBottomText(String bottomText) {
        mBottomText = bottomText;
        mTvBottom.setText(mBottomText);
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
