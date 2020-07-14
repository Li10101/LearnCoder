package c.lizhen.customlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CircleView extends View {
    private static final int RADIUS = (int) Utils.dp2px(80);
    private static final int PADDING = (int) Utils.dp2px(30);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = (RADIUS +PADDING) * 2;
        int height = (RADIUS +PADDING) * 2;

        width = resolveSize(width,widthMeasureSpec);
       // width = resolveSizeAndState(width,widthMeasureSpec,0);//会增加一个东西，区别是否强制压小，父View会看到，并不是都使用这个方法
        //getMeasuredWidth();获取存下来的大小，会做位运算
        //getMeasuredState()做位运算判断是否为0
        height = resolveSize(height,heightMeasureSpec);
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);//所画图形的颜色
        canvas.drawColor(Color.RED);//整个画布的颜色
        canvas.drawCircle(RADIUS +PADDING,RADIUS +PADDING,RADIUS,paint);

    }
}
