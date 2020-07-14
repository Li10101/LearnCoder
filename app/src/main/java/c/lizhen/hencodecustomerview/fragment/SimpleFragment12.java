package c.lizhen.hencodecustomerview.fragment;

import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import c.lizhen.hencodecustomerview.R;
import c.lizhen.hencodecustomerview.utils.Utils;
import c.lizhen.hencodecustomerview.view.CameraView1;
import c.lizhen.hencodecustomerview.view.CircleView;
import c.lizhen.hencodecustomerview.view.PointView;

public class SimpleFragment12 extends Fragment {
    private static String ARG_PARAM = "param_key";


    //CameraView1 view;
    PointView view;
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

        View inflate = inflater.inflate(R.layout.fragment_simple12, container, false);
        view = inflate.findViewById(R.id.view);
        //自定义属性需要用ObjeAnimation
//        ObjectAnimator bottomFlipAnimator = ObjectAnimator.ofFloat(view,"bottomFlip",45);
//        bottomFlipAnimator.setDuration(1500);
//
//
//        ObjectAnimator flipRotationAnimator = ObjectAnimator.ofFloat(view,"flipRotation",270);
//        flipRotationAnimator.setDuration(1500);
//
//        ObjectAnimator topFlipAnimator = ObjectAnimator.ofFloat(view,"topFlip",-45);
//        topFlipAnimator.setDuration(1500);
//
//        //属性按照顺序依次执行
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playSequentially(bottomFlipAnimator,flipRotationAnimator,topFlipAnimator);
//        animatorSet.setStartDelay(1000);
//        animatorSet.start();
        //属性同时执行
//        PropertyValuesHolder bottomFlipHolder = PropertyValuesHolder.ofFloat("bottomFlip",45);
//        PropertyValuesHolder flipRotationHolder = PropertyValuesHolder.ofFloat("flipRotation",270);
//        PropertyValuesHolder topFlipHolder = PropertyValuesHolder.ofFloat("topFlip",-45);
//
//        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view,bottomFlipHolder,flipRotationHolder,topFlipHolder);
//        objectAnimator.setDuration(3000);
//        objectAnimator.setStartDelay(1000);
//        objectAnimator.start();


        //根据关键帧
//        float length = Utils.dp2px(200);
//        Keyframe keyframe1 = Keyframe.ofFloat(0,0);
//        Keyframe keyframe2 = Keyframe.ofFloat(0.2f,0.4f*length);
//        Keyframe keyframe3 = Keyframe.ofFloat(0.8f,0.6f*length);
//        Keyframe keyframe4 = Keyframe.ofFloat(1,1*length);
//        PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofKeyframe("translationX",keyframe1,keyframe2,keyframe3,keyframe4);
//        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view,propertyValuesHolder);
//        objectAnimator.setDuration(3000);
//        objectAnimator.setStartDelay(1500);
//        objectAnimator.start();



//        view.animate().translationY(1000)
//                .setDuration(1500)
//                .setStartDelay(1500)
//                .setInterpolator(new AccelerateDecelerateInterpolator())
//                .start();

        Point point = new Point((int)Utils.dp2px(300),(int)Utils.dp2px(200));
        ObjectAnimator objectAnimator = ObjectAnimator.ofObject(view,"point",new PointTypeEvaluator(),point);
        objectAnimator.setDuration(1500);

        objectAnimator.setStartDelay(1500);
        objectAnimator.start();




        return inflate;

    }

    class PointTypeEvaluator implements TypeEvaluator<Point> {

        @Override
        public Point evaluate(float fraction, Point startValue, Point endValue) {


            float x = startValue.x +(endValue.x - startValue.x) * fraction;
            float y = startValue.y +(endValue.y - startValue.y) * fraction;
            return new Point((int)x,(int)y);
        }
    }


    public static SimpleFragment12 getInstance(String str) {
        SimpleFragment12 simpleFragment = new SimpleFragment12();
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
