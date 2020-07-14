package c.lizhen.hencodecustomerview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class DrawDirection extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    Path path = new Path();

    public DrawDirection(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }


    //layout过程发现尺寸改变会调用此方法，否则不会调用
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);


        path.reset();
        path.addRect(getWidth()/2-150,getHeight()/2-300,
                getWidth()/2+150,getHeight()/2,Path.Direction.CW);

        path.addCircle(getWidth()/2,getHeight()/2,150,Path.Direction.CCW);
        path.addCircle(getWidth()/2,getHeight()/2,300,Path.Direction.CW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(path,paint);
    }
}
