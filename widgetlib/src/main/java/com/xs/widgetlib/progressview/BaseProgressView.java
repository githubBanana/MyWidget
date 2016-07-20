package com.xs.widgetlib.progressview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * @version V1.0 <进度条 Base class>
 * @author: Xs
 * @date: 2016-07-19 17:06
 * @email Xs.lin@foxmail.com
 */
public abstract class BaseProgressView extends View {

    public BaseUIThread         mFreshThread;
    public boolean              _threadRunning = false;
    public int                  _delayTime = 30;

    public BaseProgressView(Context context) {
        super(context);
        mFreshThread = new BaseUIThread();
    }

    public BaseProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mFreshThread = new BaseUIThread();
    }

    @Override
    protected final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawSub(canvas);
    }

    /**
     * 回调draw方法
     * @param canvas
     */
    protected abstract void drawSub(Canvas canvas);

    /**
     * 逻辑
     */
    protected abstract void logic();

    /**
     * UI刷新线程
     */
    class BaseUIThread extends Thread {

        @Override
        public void run() {

            while (_threadRunning) {

                try {
                    Thread.sleep(_delayTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                logic();
                postInvalidate();

            }
        }
    }

    /**
     * UI线程开跑
     */
    public synchronized void runThread() {
        mFreshThread = null;
        mFreshThread = new BaseUIThread();
        _threadRunning = true;
        mFreshThread.start();
    }

    /**
     * 销毁UI线程
     */
    public synchronized void stopThread() {
        _threadRunning = false;
        mFreshThread = null;
    }

    /**
     * 设置延迟时长
     * @param time
     */
    public synchronized void setFreshSpeedMs(int time) {
        this._delayTime = time <= 0 ? 1 : time;
    }

    @Override
    protected void onDetachedFromWindow() {
        stopThread();
        super.onDetachedFromWindow();
    }

}
