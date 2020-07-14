package c.lizhen.hencodecustomerview.fragment;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import c.lizhen.hencodecustomerview.R;
import c.lizhen.hencodecustomerview.utils.Utils;
import c.lizhen.hencodecustomerview.view.CircleView;

public class SimpleFragment11 extends Fragment {
    private static String ARG_PARAM = "param_key";


    CircleView view;
    private String mParam;
    private Activity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
        mParam = getArguments().getString(ARG_PARAM);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_simple11, container, false);
        view = inflate.findViewById(R.id.view);

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view,"radius",Utils.dp2px(200));
        objectAnimator.setStartDelay(1000);
        objectAnimator.setDuration(1500);
        objectAnimator.start();

        return inflate;

    }


    public static SimpleFragment11 getInstance(String str) {
        SimpleFragment11 simpleFragment = new SimpleFragment11();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM, str);
        simpleFragment.setArguments(bundle);
        return simpleFragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
