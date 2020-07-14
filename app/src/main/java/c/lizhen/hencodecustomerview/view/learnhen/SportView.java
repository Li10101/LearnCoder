package c.lizhen.hencodecustomerview.view.learnhen;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import c.lizhen.hencodecustomerview.utils.Utils;

public class SportView extends View {

    private static final float STROKEWIDTH = Utils.dp2px(10);
    private static final float READER = Utils.dp2px(100);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Rect rect = new Rect();

    Paint.FontMetrics fontMetrics = new Paint.FontMetrics();

    public SportView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    {
        paint.setTextSize(Utils.dp2px(50));
        paint.setTextAlign(Paint.Align.CENTER);
        paint.getFontMetrics(fontMetrics);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘画圆环
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(STROKEWIDTH);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, READER, paint);


        //绘画圆弧
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.MAGENTA);
        paint.setStrokeWidth(STROKEWIDTH);
        paint.setStrokeCap(Paint.Cap.ROUND);//设置弧线的端头是圆或者其他
        canvas.drawArc(getWidth()/2 - READER,getHeight()/2 - READER,getWidth()/2 +
                READER,getHeight()/2 + READER,-90,225,false,paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);//指定x轴为正中心
       // 第一种文字居中文字变化会跳动，不固定
//        paint.getTextBounds("abab",0,"abab".length(),rect);
//        int offSet = (rect.top + rect.bottom)/2;
//        canvas.drawText("abab",0,"abab".length(),getWidth()/2,getHeight()/2-offSet,paint);
       //第二种文字变化是固定的位置
        float offSet = (fontMetrics.ascent + fontMetrics.descent)/2;
        canvas.drawText("aaaa",0,"aaaa".length(),getWidth()/2,getHeight()/2-offSet,paint);

        //绘制文字靠左边位置一直
        paint.setTextSize(Utils.dp2px(100));
        paint.setTextAlign(Paint.Align.LEFT);
        paint.getTextBounds("abab",0,"abab".length(),rect);
        canvas.drawText("abab",-rect.left,200,paint);

        //绘制文字靠左边位置一直
        paint.setTextSize(Utils.dp2px(50));
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("abab",0,200+paint.getFontSpacing(),paint);

    }
}
