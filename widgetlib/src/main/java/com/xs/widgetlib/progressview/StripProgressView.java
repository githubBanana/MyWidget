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
 * @version V1.0 <条形进度条>
 * @author: Xs
 * @date: 2016-07-20 10:11
 * @email Xs.lin@foxmail.com
 */
public class StripProgressView extends BaseProgressView {

    private int         _paddingLeft,_paddingTop,_paddingRight,_paddingBottom;
    private float       _radius;//矩形圆角度
    private float       _width;
    private int         _runningValue;
    private float       _realValue;
    private int         _finalValue;//最终值
    private int         _limitValue;//极限值
    private float       _ratio;//占比
    private int         text;
    private float       textSize;
    private Paint       mStriPaint;
    private Paint       mRunPaint;
    private Paint       mTextPaint;

    public StripProgressView(Context context) {
        super(context);
    }

    public StripProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    private void initView(Context context,AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.StripProgressView);
        final int stripColor = ta.getColor(R.styleable.StripProgressView_strip_color, Color.GRAY);
        final int runColor = ta.getColor(R.styleable.StripProgressView_run_color,Color.GREEN);
        final int textColor = ta.getColor(R.styleable.StripProgressView_textColor,Color.GRAY);
        textSize = ta.getDimension(R.styleable.StripProgressView_textSize,12);
        _radius = ta.getFloat(R.styleable.StripProgressView_radius,0.5f);
        _paddingLeft = (int) ta.getDimension(R.styleable.StripProgressView_padding_left,0);
        _paddingTop = (int) ta.getDimension(R.styleable.StripProgressView_padding_top,0);
        _paddingRight = (int) ta.getDimension(R.styleable.StripProgressView_padding_right,0);
        _paddingBottom = (int) ta.getDimension(R.styleable.StripProgressView_padding_bottom,0);


        mStriPaint = new Paint();
        mStriPaint.setAntiAlias(true);
        mStriPaint.setColor(stripColor);

        mRunPaint = new Paint();
        mRunPaint.setAntiAlias(true);
        mRunPaint.setColor(runColor);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(textColor);
        mTextPaint.setTextSize(textSize);

    }

    @Override
    protected void drawSub(Canvas canvas) {

        _width = getWidth();

        canvas.drawRoundRect(new RectF(_paddingLeft,_paddingTop,getWidth() - _paddingRight, getHeight() - _paddingBottom),getHeight() * _radius,getHeight() * _radius,mStriPaint);

        canvas.drawRoundRect(new RectF(_paddingLeft,_paddingTop,_realValue,getHeight() - _paddingBottom),getHeight() * _radius,getHeight() * _radius,mRunPaint);

        canvas.drawText( text + "%",_realValue, (float) (_paddingTop + textSize * 0.5),mTextPaint);
    }

    @Override
    protected void logic() {
        if (_runningValue < _finalValue) {
            _runningValue++;
            _realValue = _paddingLeft + _ratio * _runningValue;
            text = (int) (_runningValue * 100 / (float)_limitValue);
        }
    }

    /**
     * 1
     * 设置临界值
     * @param limitValue
     */
    public void setLimitValue(int limitValue){
        this._limitValue = limitValue;
        _ratio = (_width - ( _paddingLeft + _paddingRight)) / (float) _limitValue;
    }

    /**
     * 2
     * 设置最终值
     * @param finalValue
     */
    public void setFinalValue(int finalValue) {
        this._finalValue = finalValue;
    }

}
