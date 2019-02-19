package com.sensetime.qinhaihang_vendor.chartview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author qinhaihang_vendor
 * @version $Rev$
 * @time 2019/2/19 19:58
 * @des
 * @packgename com.sensetime.qinhaihang_vendor.chartview
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes
 */
public class BarChart extends View {

    private Context mContext;

    //控件默认的宽高
    int defaultWidth = 300;
    int defaultHeight = 200;

    private Paint mPaint;

    private float mDefaultAxisW = 10f;
    private float mAxsiMaxX = 100f;
    private float mAxsiMaxY = 100f;

    public BarChart(Context context) {
        super(context);
        init(context, null, 0);
    }

    public BarChart(Context context, @androidx.annotation.Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public BarChart(Context context, @androidx.annotation.Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, @androidx.annotation.Nullable AttributeSet attrs, int defStyleAttr) {
        mContext = context;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BarChart);

        typedArray.recycle();

        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(mDefaultAxisW);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int withSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT
                && getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(defaultWidth, defaultHeight);
        } else if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(defaultWidth, heightSize);
        } else if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(withSize, defaultHeight);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //x line
        canvas.drawLine(10, 0, 10, -mAxsiMaxX, mPaint);
        //y line
        canvas.drawLine(10,-mAxsiMaxX,mAxsiMaxY,-mAxsiMaxX,mPaint);
    }
}
