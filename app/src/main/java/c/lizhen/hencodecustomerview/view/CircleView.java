package c.lizhen.hencodecustomerview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import c.lizhen.hencodecustomerview.utils.Utils;

public class CircleView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private float radius = Utils.dp2px(100);
    public CircleView(Context context,  AttributeSet attrs) {
        super(context, attrs);


    }

    {
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(getWidth()/2,getHeight()/2,radius,paint);
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        invalidate();
    }
}
