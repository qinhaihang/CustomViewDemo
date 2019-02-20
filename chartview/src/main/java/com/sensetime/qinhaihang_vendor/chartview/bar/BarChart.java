package com.sensetime.qinhaihang_vendor.chartview.bar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.sensetime.qinhaihang_vendor.chartview.R;

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

    private float mDefaultAxisW = 1f;
    private float mAxsiMaxX = 100f;
    private float mAxsiMinX = 0f;
    private int mAxsiStepX = 1;
    private float mAxsiMaxY = 100f;
    private float mAxsiMinY = 0f;
    private int mAxsiStepY = 1;

    private float mBaseX = 0f;
    private float mBaseY = 0f;
    private int mPaddingBottom;
    private int mPaddingTop;
    private int mPaddingLeft;
    private int mPaddingRight;

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

        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(mDefaultAxisW);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int withSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT
                && getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(defaultWidth, defaultHeight);
        } else if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(defaultWidth, heightSize);
        } else if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(withSize, defaultHeight);
        } else{
            setMeasuredDimension(withSize, heightSize);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        getPaddingValue();

        drawAxsi(canvas);
    }

    private void getPaddingValue() {
        mPaddingBottom = getPaddingBottom();
        mPaddingTop = getPaddingTop();
        mPaddingLeft = getPaddingLeft();
        mPaddingRight = getPaddingRight();
    }

    public void setAxsiY(float axsiMaxY,float axsiMinY,int axsiStepY){
        mAxsiMaxY = axsiMaxY;
        mAxsiMinY = axsiMinY;
        mAxsiStepY = axsiStepY;

        invalidate();
    }

    private void drawAxsi(Canvas canvas){
        int axsiYH = getHeight();
        int axsiXW = getWidth();

        //y axis
        canvas.drawLine(mBaseX + mPaddingLeft,
                mBaseY + mPaddingTop,
                mBaseX + mPaddingLeft,
                axsiYH - mPaddingBottom, mPaint);
        int axsiYActualH = axsiYH - mPaddingBottom;


        //x axis
        canvas.drawLine(mBaseX + mPaddingLeft,
                axsiYH - mPaddingBottom,
                axsiXW - mPaddingRight,
                axsiYH - mPaddingBottom, mPaint);

    }

}
