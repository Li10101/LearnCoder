package c.lizhen.hencodecustomerview.view.learnhen;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import c.lizhen.hencodecustomerview.utils.Utils;

public class PieChart extends View {

    private static final int RADIUS = (int) Utils.dp2px(150);
    private static final int LENGTH = (int) Utils.dp2px(40);
    private static final int POSITION = 2;

    //设置抗锯齿
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    //定义饼图所在区域的矩形
    RectF bounds = new RectF();


    int[] angles = {60, 120, 120, 80};
    int[] colors = {Color.parseColor("#FFD81B60"), Color.parseColor("#FF008577"),
            Color.parseColor("#FF7F9492"), Color.parseColor("#FF84A432")};


    public PieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    //layout布局发生变化后会调用这个方法
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        bounds.set(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS,
                getHeight() / 2 + RADIUS);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int currentAngle = 0;
        for (int i = 0; i < angles.length; i++) {
            paint.setColor(colors[i]);
            canvas.save();
            if (i == POSITION) {
                canvas.translate((float) Math.cos(Math.toRadians(currentAngle + angles[i] / 2)) * LENGTH,
                        (float) Math.sin(Math.toRadians(currentAngle + angles[i] / 2)) * LENGTH);
            }
            canvas.drawArc(bounds, currentAngle, angles[i], true, paint);
            //canvas.restore()写在绘画的后面恢复到save之前的位置
            canvas.restore();
            currentAngle += angles[i];
        }


    }
}
