package c.lizhen.touchview.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.TypedValue;

import c.lizhen.touchview.R;


public class Utils {

    //Resources.getSystem().getDisplayMetrics()获取系统配置级别的
    public static float dp2px(float dp){

        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,Resources.getSystem().getDisplayMetrics());
    }

    public static Bitmap getAvatar(Resources res,int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, R.drawable.avater, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(res, R.drawable.avater, options);
    }

    public static float getDensity(){
        return -8 * Resources.getSystem().getDisplayMetrics().density;
    }
}
