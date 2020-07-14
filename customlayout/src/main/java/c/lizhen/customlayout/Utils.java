package c.lizhen.customlayout;

import android.content.res.Resources;
import android.util.TypedValue;

public class Utils {

    //Resources.getSystem().getDisplayMetrics()获取系统配置级别的
    public static float dp2px(float dp){

        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,Resources.getSystem().getDisplayMetrics());
    }


}
