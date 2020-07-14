package view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;

public class DragHelperGridView extends ViewGroup {


    private static final int COLUMNS = 2;
    private static final int ROWS = 3;

    ViewDragHelper viewDragHelper;//拖拽排序比较适合用ViewDragHelper

    public DragHelperGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        viewDragHelper = ViewDragHelper.create(this,new DragCallBack());
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        int specHeight = MeasureSpec.getSize(heightMeasureSpec);

        int childWidth = specWidth/COLUMNS;
        int childHeight = specHeight/ROWS;
        measureChildren(MeasureSpec.makeMeasureSpec(childWidth,MeasureSpec.EXACTLY),MeasureSpec.makeMeasureSpec(childHeight,MeasureSpec.EXACTLY));
        setMeasuredDimension(specWidth,specHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int childLeft;
        int childTop;
        int childWidth = getWidth()/COLUMNS;
        int childHeight = getHeight()/ROWS;
        for (int index = 0; index < count; index++) {
            View child = getChildAt(index);
            childLeft = index % 2 * childWidth;
            childTop = index / 2 * childHeight;
            child.layout(childLeft,childTop,childLeft + childWidth,childTop + childHeight);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (viewDragHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private class DragCallBack extends ViewDragHelper.Callback{

        float capturedLeft;
        float capturedTop;

        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {//尝试抓住view
            return true;
        }


        @Override
        public void onViewDragStateChanged(int state) {
            if (state == ViewDragHelper.STATE_IDLE){
                View capturedView = viewDragHelper.getCapturedView();// captured 捕获
                if(capturedView != null){

                    capturedView.setElevation(capturedView.getElevation() - 1);
                }
            }
        }
        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {//
            return top;
        }

        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            return left;
        }

        @Override
        public void onViewCaptured(@NonNull View capturedChild, int activePointerId) {//初始化准备工作
            capturedChild.setElevation(getElevation() + 1);
            capturedLeft = capturedChild.getLeft();
            capturedTop = capturedChild.getTop();

        }

        @Override
        public void onViewPositionChanged(@NonNull View changedView, int left, int top, int dx, int dy) {//移动过程中

        }

        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {//松手后计算在computeScroll()方法
           viewDragHelper.settleCapturedViewAt((int)capturedLeft,(int)capturedTop);
           postInvalidateOnAnimation();
        }
    }
}
