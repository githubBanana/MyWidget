package com.xs.mywidget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.xs.widgetlib.progressview.RoundProgressView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RoundProgressView mRpv = (RoundProgressView) findViewById(R.id.rpv_test);
        mRpv.setFinalValue(300);
        mRpv.setLimitValue(400);
        final Button mBtnBegin = (Button) findViewById(R.id.btn_begin);
        mBtnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRpv.setFreshSpeed(1);
                mRpv.runThread();
            }
        });
        final Button mBtnStop = (Button) findViewById(R.id.btn_stop);
        mBtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRpv.stopThread();
            }
        });

    }
}
