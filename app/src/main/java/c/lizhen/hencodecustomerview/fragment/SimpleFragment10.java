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
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import c.lizhen.hencodecustomerview.R;

public class SimpleFragment10 extends Fragment {
    private static String ARG_PARAM = "param_key";


    View view;
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

        View inflate = inflater.inflate(R.layout.fragment_simple10, container, false);
        view = inflate.findViewById(R.id.view);
        view.animate()
                .translationX(100)
                .setStartDelay(1000)
                .setDuration(1500)
                .start();
        return inflate;
    }


    public static SimpleFragment10 getInstance(String str) {
        SimpleFragment10 simpleFragment = new SimpleFragment10();
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
