package com.xs.widgetlib.dialog.progress;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xs.widgetlib.R;
import com.xs.widgetlib.progressview.RoundScaleProgressView;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-07-28 15:31
 * @email Xs.lin@foxmail.com
 */
public class RoundFragment extends DialogFragment {
    public static final String TAG = RoundFragment.class.getSimpleName();
    public static RoundFragment getInstance() {
        final RoundFragment rf = new RoundFragment();

        return rf;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL,R.style.AppTheme_Round);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View _view = LayoutInflater.from(getActivity()).inflate(R.layout.roundprogress_layout,null);
        final RoundScaleProgressView _rp = (RoundScaleProgressView) _view.findViewById(R.id.rpv);
        _rp.setFinalValue(400);
        _rp.setLimitValue(400);
        _rp.setFreshSpeedMs(1);
        _rp.runThread();
        return _view;
    }
 /*   @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        final View _view = LayoutInflater.from(getActivity()).inflate(R.layout.roundprogress_layout,null);
        final RoundProgressView _rp = (RoundProgressView) _view.findViewById(R.id.rpv);
        _rp.setFinalValue(400);
        _rp.setLimitValue(400);
        _rp.setFreshSpeedMs(1);
        _rp.runThread();
        return new AlertDialog.Builder(getActivity()).setView(_view).show();

    }*/

}
