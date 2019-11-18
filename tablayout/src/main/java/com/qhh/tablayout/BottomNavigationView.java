package com.qhh.tablayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author qinhaihang_vendor
 * @time 2019/11/18 15:37
 * @des
 * @packgename com.sensetime.qinhaihang_vendor.commonview
 */
public class BottomNavigationView extends LinearLayout {

    private static final String TAG = BottomNavigationView.class.getSimpleName();

    private Context mContext;
    private int mNavigationCount;

    public BottomNavigationView(Context context) {
        super(context);
        init(context,null,0);
    }

    public BottomNavigationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public BottomNavigationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, @Nullable AttributeSet attrs, int defStyleAttr){
        mContext = context;
        setOrientation(HORIZONTAL);
        obtainAttributes(context, attrs);
    }

    private void obtainAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomNavigationView);

        mNavigationCount = typedArray.getInt(R.styleable.BottomNavigationView_navigation_count, 1);

        typedArray.recycle();
    }

    public void setData(@NonNull Map<String, Drawable> dataMap){

    }

}
