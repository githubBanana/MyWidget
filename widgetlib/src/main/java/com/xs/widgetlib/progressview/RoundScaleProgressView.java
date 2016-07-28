package com.xs.widgetlib.progressview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.xs.widgetlib.R;

/**
 * @version V1.0 <圆形进度条>
 * @author: Xs
 * @date: 2016-07-19 17:33
 * @email Xs.lin@foxmail.com
 */
public class RoundScaleProgressView extends BaseProgressView {

    private Paint       mRoundPaint;
    private Paint       mArcPaint;
    private Paint       mTextPaint;
    private float       _roundWidth;//弧段宽度
    private float       _textSize;//字体大小
    private float       _value;//进度值(0 - 360)
    private int         _valueShow;//进度值(0 - 100)
    private float       _ratio;//占比
    private int         _finalValue = 100;
    private int         _ingValue = 0;
    private int         _limitValue ;

    public RoundScaleProgressView(Context context) {
        super(context);
    }

    public RoundScaleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    /**
     * 初始化 画笔
     * @param context
     * @param attrs
     */
    private void initView(Context context,AttributeSet attrs) {
        TypedArray td = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressView);
        _roundWidth = td.getDimension(R.styleable.RoundProgressView_roundWidth,50);
        final int roundColor = td.getColor(R.styleable.RoundProgressView_round_color, Color.GRAY);
        final int arcColor = td.getColor(R.styleable.RoundProgressView_arc_color,Color.GREEN);
        final int textColor = td.getColor(R.styleable.RoundProgressView_text_color,Color.GRAY);
        _textSize = td.getDimension(R.styleable.RoundProgressView_text_size,12f);

        /*底圆画笔*/
        mRoundPaint = new Paint();
        mRoundPaint.setColor(arcColor);
        mRoundPaint.setStrokeWidth(_roundWidth);
        mRoundPaint.setAntiAlias(true);
        mRoundPaint.setStyle(Paint.Style.STROKE);

        /*弧段画笔*/
        mArcPaint = new Paint();
        mArcPaint.setColor(roundColor);
        mArcPaint.setStrokeWidth(_roundWidth);
        mArcPaint.setAntiAlias(true);
        mArcPaint.setStyle(Paint.Style.STROKE);
/*
        final Shader mShader = new LinearGradient(0, 0, 50, 50, new int[] {
                Color.argb(255, 0, 255, 0), Color.argb(150, 0, 255, 0),
                Color.argb(0, 0, 255, 0) }, null, Shader.TileMode.CLAMP);*/

        /*文字画笔*/
        mTextPaint = new Paint();
        mTextPaint.setColor(textColor);
        mTextPaint.setTextSize(_textSize);
        mTextPaint.setAntiAlias(true);
//        mTextPaint.setShader(mShader);

        td.recycle();

    }

    @Override
    protected void drawSub(Canvas canvas) {
        float radius = getWidth() / 2 - _roundWidth;
        canvas.drawCircle(getWidth() / 2,getHeight() / 2,radius,mRoundPaint);
        canvas.drawArc(new RectF(_roundWidth,_roundWidth,getWidth() - _roundWidth,getWidth() - _roundWidth),0,_value,false,mArcPaint);
        canvas.drawText(_valueShow+"%",getWidth() / 2 - _textSize * 0.9f,getHeight() / 2 + _textSize / 2,mTextPaint );
    }


    @Override
    protected void logic() {
        if (_ingValue >= 0 && _ingValue < _finalValue) {
            _ingValue  = _ingValue + 1;
            _value =  (_ingValue * _ratio);
            _valueShow = (int) (_value / 360 * 100);
        } else {
            _threadRunning = false;
        }
    }

    /**
     * 设置最终值
     * @param value
     */
    public synchronized void setFinalValue(int value) {
        this._finalValue = value;
    }

    /**
     * 设置极限值
     * @param limit
     */
    public synchronized void setLimitValue(int limit) {
        this._limitValue = limit;
        this._ratio = 360 / (float)_limitValue;
    }





}
