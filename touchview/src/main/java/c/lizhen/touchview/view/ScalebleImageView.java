package c.lizhen.touchview.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.OverScroller;
import android.widget.Scroller;

import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;
import c.lizhen.touchview.util.Utils;

public class ScalebleImageView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final float IMAGE_WIDTH = Utils.dp2px(300);
    private static final float OVER_SCALE_FACTOR = 1.5f;
    Bitmap bitmap;

    float originalOffersetX;
    float originalOffersetY;

    float offersetX;
    float offersetY;

    float smallScale;
    float bigScale;
    boolean big;

    float currentScale;

    ObjectAnimator animator;

    GestureDetectorCompat detector;//手势识别器
    GestureDoubleListener gestureDoubleListener = new GestureDoubleListener();
    HenGestureListener henGestureListener = new HenGestureListener();
    HenFlingRunnable henFlingRunnable = new HenFlingRunnable();
    HenScalListener henScalListener = new HenScalListener();
    OverScroller scroller;
    private final ScaleGestureDetector scaleGestureDetector;
    // Scroller scroller;

    public ScalebleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = Utils.getAvatar(getResources(), (int) IMAGE_WIDTH);

        detector = new GestureDetectorCompat(context, henGestureListener);
        scroller = new OverScroller(context);
        //scroller = new Scroller(context);
        detector.setOnDoubleTapListener(gestureDoubleListener);
        //detector.setIsLongpressEnabled(false);//长按关闭

        scaleGestureDetector = new ScaleGestureDetector(context,henScalListener);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        originalOffersetX = ((float) (getWidth() - bitmap.getWidth())) / 2;
        originalOffersetY = ((float) (getHeight() - bitmap.getHeight())) / 2;

        if ((float) bitmap.getWidth() / bitmap.getHeight() > (float) getWidth() / getHeight()) {
            smallScale = (float) getWidth() / bitmap.getWidth();
            bigScale = (float) getHeight() / bitmap.getHeight() * OVER_SCALE_FACTOR;
        } else {
            smallScale = (float) getHeight() / bitmap.getHeight();
            bigScale = (float) getWidth() / bitmap.getWidth() * OVER_SCALE_FACTOR;
        }

        currentScale = smallScale;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = scaleGestureDetector.onTouchEvent(event);
        if (!scaleGestureDetector.isInProgress()){//判断是否发生捏撑
            result = detector.onTouchEvent(event);
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float scaleFraction = (currentScale - smallScale) / (bigScale - smallScale);
        canvas.translate(offersetX * scaleFraction, offersetY * scaleFraction);
        //float scale = smallScale + (bigScale - smallScale) * scaleFraction;
        canvas.scale(currentScale, currentScale, getWidth() / 2.f, getHeight() / 2.f);
        canvas.drawBitmap(bitmap, originalOffersetX, originalOffersetY, paint);
    }


    private float getCurrentScale() {
        return currentScale;
    }

    private void setCurrentScale(float currentScale) {
        this.currentScale = currentScale;
        invalidate();
    }

    private ObjectAnimator getObjectAnimator() {
        if (animator == null) {
            animator = ObjectAnimator.ofFloat(this, "currentScale", 0, 1);
        }
        animator.setFloatValues(smallScale, bigScale);
        return animator;
    }

    class HenGestureListener implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {//延迟显示100ms

        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {//按下抬起，就会有一次响应，若支持长按则不会单击后立即出发，长按超过500hm之后才会再调用
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent down, MotionEvent Event, float distanceX, float distanceY) {
            if (big) {
                offersetX -= distanceX;
                offersetX = Math.min(offersetX, (bitmap.getWidth() * bigScale - getWidth()) / 2);
                offersetX = Math.max(offersetX, -(bitmap.getWidth() * bigScale - getWidth()) / 2);

                offersetY -= distanceY;
                offersetY = Math.min(offersetY, (bitmap.getHeight() * bigScale - getHeight()) / 2);
                offersetY = Math.max(offersetY, -(bitmap.getHeight() * bigScale - getHeight()) / 2);
                invalidate();
            }

            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float velocityX, float velocityY) {
            if (big) {
                scroller.fling((int) offersetX, (int) offersetY, (int) velocityX, (int) velocityY,
                        -(int) (bitmap.getWidth() * bigScale - getWidth()) / 2,
                        (int) (bitmap.getWidth() * bigScale - getWidth()) / 2,
                        -(int) (bitmap.getHeight() * bigScale - getHeight()) / 2,
                        (int) (bitmap.getHeight() * bigScale - getHeight()) / 2, 100, 100
                );
                postOnAnimation(henFlingRunnable);
            }
            return false;
        }


    }


    class HenFlingRunnable implements Runnable {

        @Override
        public void run() {
            if (scroller.computeScrollOffset()) {//计算出位置
                offersetX = scroller.getCurrX();
                offersetY = scroller.getCurrY();
                invalidate();
                postOnAnimation(this);
            }

        }
    }

    class GestureDoubleListener implements GestureDetector.OnDoubleTapListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {


            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent event) {
            big = !big;
            if (big) {
                offersetX = (event.getX() - getWidth() / 2) - (event.getX() - getWidth() / 2) * bigScale / smallScale;
                offersetY = (event.getY() - getHeight() / 2) - (event.getY() - getHeight() / 2) * bigScale / smallScale;
                getObjectAnimator().start();
            } else {
                getObjectAnimator().reverse();
//            offersetX = 0;
//            offersetY = 0;
            }
            return false;
        }
        //配套  交通  增值

        //中原科技城
        //北龙湖
        //白沙大数据产业园

        @Override
        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            return false;
        }
    }

    class HenScalListener implements ScaleGestureDetector.OnScaleGestureListener {
        float initialScale;
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            //scaleGestureDetector.getScaleFactor();//得到放大倍数   初始值1
            //scaleGestureDetector.getFocusX();//获取焦点
            currentScale = initialScale * scaleGestureDetector.getScaleFactor();
            invalidate();
            return false;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            initialScale = currentScale;
            return true;//返回true表示要消费这个事件
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {

        }
    }

}
