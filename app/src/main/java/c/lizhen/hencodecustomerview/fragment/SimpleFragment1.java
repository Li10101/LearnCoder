package c.lizhen.hencodecustomerview.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import c.lizhen.hencodecustomerview.R;

public class SimpleFragment1 extends Fragment {
    private static String ARG_PARAM = "param_key";

    Unbinder unbinder;
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

        View inflate = inflater.inflate(R.layout.fragment_simple1, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }


    public static SimpleFragment1 getInstance(String str) {
        SimpleFragment1 simpleFragment = new SimpleFragment1();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM, str);
        simpleFragment.setArguments(bundle);
        return simpleFragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
