package c.lizhen.hencodecustomerview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import c.lizhen.hencodecustomerview.utils.Utils;

public class PointView extends View {


    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Point point = new Point(0,0);

    public PointView(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }
    {
        paint.setStrokeWidth(Utils.dp2px(15));
        paint.setStrokeCap(Paint.Cap.ROUND);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPoint(point.x,point.y,paint);

    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
        invalidate();
    }
}
