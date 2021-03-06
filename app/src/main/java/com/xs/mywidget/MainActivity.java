package com.xs.mywidget;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.xs.widgetlib.dialog.progress.RoundFragment;
import com.xs.widgetlib.progressview.RoundProgressView;
import com.xs.widgetlib.progressview.StripProgressView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RoundProgressView mRpv = (RoundProgressView) findViewById(R.id.rpv_test);
        mRpv.setFinalValue(400);
        mRpv.setLimitValue(400);
        final Button mBtnBegin = (Button) findViewById(R.id.btn_begin);
        mBtnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRpv.setFreshSpeedMs(1);
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



        final StripProgressView mSpv = (StripProgressView) findViewById(R.id.spv_test);

        final Button mBtnSpvForword = (Button) findViewById(R.id.btn_spv_start);
        mBtnSpvForword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSpv.setLimitValue(1000);
                mSpv.setFinalValue(1000);
                mSpv.setFreshSpeedMs(10);
                mSpv.runThread();
            }
        });

        /**
         * dialog show
         */
        final Button mBtnShowRoundDialog = (Button) findViewById(R.id.btn_roundDialog);
        mBtnShowRoundDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RoundFragment.getInstance().show(getSupportFragmentManager(), RoundFragment.TAG);
                mBtnShowRoundDialog.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismissProgress(RoundFragment.TAG);
                    }
                },3000);
            }
        });




    }

    private void dismissProgress(final String tag) {
        Fragment prev = getSupportFragmentManager().findFragmentByTag(tag);
        if (prev != null) {
            DialogFragment df = (DialogFragment) prev;
            df.dismiss();
        }
    }

}
