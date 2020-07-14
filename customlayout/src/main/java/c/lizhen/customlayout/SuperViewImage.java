package c.lizhen.customlayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class SuperViewImage extends AppCompatImageView {


    public SuperViewImage(Context context) {
        super(context);
    }

    public SuperViewImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SuperViewImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int measuredWidth = getMeasuredWidth()/2;
        int measuredHeight = getMeasuredHeight()/2;

        setMeasuredDimension(measuredWidth,measuredHeight);

    }
}
