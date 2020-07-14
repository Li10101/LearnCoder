package c.lizhen.hencodecustomerview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class DrawColor extends View {

    private Paint paint;
    public DrawColor(Context context) {
        super(context);
    }

    public DrawColor(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public DrawColor(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //drawaColo第一种方法
//        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawColor(Color.YELLOW);
          //drawaColo第二种方法
//        paint.setColor(Color.YELLOW);
//        RectF rectF = new RectF();
//        rectF.top = 0;
//        rectF.left = 0;
//        rectF.right = getScreenWidth();
//        rectF.bottom = getScreenHeight()/2;
//        canvas.drawRect(rectF,paint);

        //drawColor第三种方式
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.FILL);
        Path path = new Path();
        path.lineTo(getScreenWidth(),0);
        path.rLineTo(0,getScreenHeight()/2);
        path.rLineTo(-getScreenWidth(),0);
        //path.rLineTo(0,-getScreenHeight()/2);//或path.close()都可以
        path.close();

        canvas.drawPath(path,paint);
    }


    private int getScreenWidth(){
       WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    private int getScreenHeight(){
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }
}
