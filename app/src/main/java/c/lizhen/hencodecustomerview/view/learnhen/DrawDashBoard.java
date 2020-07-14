package c.lizhen.hencodecustomerview.view.learnhen;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import c.lizhen.hencodecustomerview.utils.Utils;

public class DrawDashBoard extends View {

    private static final int ANFLE = 120;
    private static final float RADIUS = Utils.dp2px(150);
    private static final float LENGTH = Utils.dp2px(100);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    Path path = new Path();


    PathDashPathEffect pathEffect;
    private PathMeasure pathMeasure;//测量周长

    public DrawDashBoard(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Utils.dp2px(2));

        path.addRect(0, 0, Utils.dp2px(2), Utils.dp2px(10), Path.Direction.CW);//设置小方块的朝向

        Path arc = new Path();
        arc.addArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 +
                RADIUS, 90 + ANFLE / 2, 360 - ANFLE);
        pathMeasure = new PathMeasure(arc, false);
        //DashPathEffect作用是将Path的线段虚线化。
        //advance方块之间的距离，phase第一个点需要空多少，style设置虚线的转弯的属性
        pathEffect = new PathDashPathEffect(path, (pathMeasure.getLength() - Utils.dp2px(2)) / 20, 0, PathDashPathEffect.Style.MORPH);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画弧线
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 +
                RADIUS, 90 + ANFLE / 2, 360 - ANFLE, false, paint);

        //画刻度
        paint.setPathEffect(pathEffect);
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 +
                RADIUS, 90 + ANFLE / 2, 360 - ANFLE, false, paint);
        paint.setPathEffect(null);

        //画指针
        canvas.drawLine(getWidth() / 2, getHeight() / 2,
                (float) Math.cos(Math.toRadians(getAngleFromMark(100))) * LENGTH + getWidth() / 2,
                (float) Math.sin(Math.toRadians(getAngleFromMark(100))) * LENGTH + getHeight() / 2,
                paint);


    }

    int getAngleFromMark(int mark) {
        return (int) (90 + (float) ANFLE / 2 + (360 - (float) ANFLE) / 2 * mark);
    }


}
