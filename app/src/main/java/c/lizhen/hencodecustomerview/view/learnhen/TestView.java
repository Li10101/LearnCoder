package c.lizhen.hencodecustomerview.view.learnhen;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import c.lizhen.hencodecustomerview.utils.Utils;

public class TestView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public TestView(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }
    {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(100,100,200,200,paint);

        //这种是使用像素
        //canvas.drawCircle(getWidth()/2,getHeight()/2,100,paint);
        //这种是使用dp
        canvas.drawCircle(getWidth()/2,getHeight()/2, Utils.dp2px(150),paint);


    }
}
