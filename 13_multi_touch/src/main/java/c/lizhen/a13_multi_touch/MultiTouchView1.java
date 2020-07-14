package c.lizhen.a13_multi_touch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class MultiTouchView1 extends View {


    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final float IMAGE_WIDTH = Utils.dp2px(200);
    Bitmap bitmap;
    float offerX;
    float offerY;

    float downX;
    float downY;
    float originalOfferX;
    float originalOfferY;
    int trankingPointerId;
    //private int actionIndex;


    public MultiTouchView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        bitmap = Utils.getAvatar(getResources(), (int) IMAGE_WIDTH);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float sumX = 0;
        float sumY = 0;
        int pointerCount = event.getPointerCount();//获取手指的个数
        boolean isPointerUp = event.getActionMasked() == MotionEvent.ACTION_POINTER_UP;

        for (int i = 0; i < pointerCount; i++) {
            if (!(isPointerUp && i == event.getActionIndex())){
                sumX += event.getX(i);
                sumY += event.getY(i);
            }
        }
        if (isPointerUp){
            pointerCount -= 1;
        }

        float focusX = sumX / pointerCount;
        float focusY = sumY / pointerCount;

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_POINTER_UP:
                trankingPointerId = event.getPointerId(0);
                downX = focusX;//event.getX()知识获取index为0 的值
                downY = focusY;

                originalOfferX = offerX;
                originalOfferY = offerY;

                break;
            case MotionEvent.ACTION_MOVE:
                int index = event.findPointerIndex(trankingPointerId);
                offerX = originalOfferX + focusX - downX;
                offerY = originalOfferY + focusY - downY;
                invalidate();
                break;


        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, offerX, offerY, paint);
    }
}
