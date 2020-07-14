package c.lizhen.a13_multi_touch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class MultiTouchView extends View {


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


    public MultiTouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        bitmap = Utils.getAvatar(getResources(), (int) IMAGE_WIDTH);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                trankingPointerId = event.getPointerId(0);
                downX = event.getX();//event.getX()知识获取index为0 的值
                downY = event.getY();

                originalOfferX = offerX;
                originalOfferY = offerY;

                break;
            case MotionEvent.ACTION_MOVE:
                int index = event.findPointerIndex(trankingPointerId);
                offerX = originalOfferX + event.getX(index) - downX;
                offerY = originalOfferY + event.getY(index) - downY;
                invalidate();
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                int actionIndex = event.getActionIndex();
                trankingPointerId = event.getPointerId(actionIndex);
                downX = event.getX(actionIndex);//event.getX()知识获取index为0 的值
                downY = event.getY(actionIndex);

                originalOfferX = offerX;
                originalOfferY = offerY;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                actionIndex = event.getActionIndex();
                int pointerId = event.getPointerId(actionIndex);
                if (pointerId == trankingPointerId){
                    int newindex ;
                    if (actionIndex == event.getPointerCount() -1){
                        newindex = event.getPointerCount() - 2;
                    }else {
                        newindex = event.getPointerCount() - 1;
                    }
                    trankingPointerId = event.getPointerId(newindex);
                    downX = event.getX(actionIndex);
                    downY = event.getY(actionIndex);
                    originalOfferX = offerX;
                    originalOfferY = offerY;

                }
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
