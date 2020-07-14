package c.lizhen.customlayout;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class TagLayout extends ViewGroup {

    List<Rect> childrenBounds = new ArrayList<>();

    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {//父VIew对我的要求
        int widthUsed = 0;
        int heightUsed = 0;
        int lineWidthUsed = 0;
        int lineMaxHeight = 0;

        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);//测量子View
            if (specMode != MeasureSpec.UNSPECIFIED && lineWidthUsed + child.getMeasuredWidth() > specWidth){//若可用宽度+子View的宽度大于父View的宽度
                lineWidthUsed = 0;//回车
                heightUsed += lineMaxHeight;//换行
                lineMaxHeight = 0;
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            }
            Rect childBound;
            if (childrenBounds.size() <= i) {
                childBound = new Rect();
                childrenBounds.add(childBound);
            } else {
                childBound = childrenBounds.get(i);
            }
            //给每一个子View设置位置
            childBound.set(lineWidthUsed, heightUsed, lineWidthUsed + child.getMeasuredWidth(), heightUsed + child.getMeasuredHeight());
            lineWidthUsed += child.getMeasuredWidth();
            widthUsed = Math.max(widthUsed,lineWidthUsed);
            lineMaxHeight = Math.max(lineMaxHeight, child.getMeasuredHeight());
        }
        //计算父View的尺寸并保存
        int width = widthUsed;
        int height = heightUsed +lineMaxHeight;
        setMeasuredDimension(width, height);



    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            Rect childBounds = childrenBounds.get(i);
            childAt.layout(childBounds.left, childBounds.top, childBounds.right, childBounds.bottom);
        }

    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
//
//            LayoutParams layoutParams = childAt.getLayoutParams();//获取子View的值
//            int specWidthMode = MeasureSpec.getMode(widthMeasureSpec);
//            int specWidthSize = MeasureSpec.getSize(widthMeasureSpec);
//            int childWidthMode;
//            int childWidthSize;
//
//            switch (layoutParams.width){
//                case LayoutParams.MATCH_PARENT://开发者要求填满我
//                    switch (specWidthMode){
//                        case MeasureSpec.AT_MOST:
//                        case MeasureSpec.EXACTLY://所以子View的值也就是已确定值
//                            childWidthMode = MeasureSpec.EXACTLY;
//                            childWidthSize = specWidthSize - userWidth;
//                            //MeasureSpec.makeMeasureSpec(childWidthSize,childWidthMode);
//                            break;
//
//                        case MeasureSpec.UNSPECIFIED:
//                            childWidthMode = MeasureSpec.UNSPECIFIED;
//                            childWidthSize = 0;
//                            break;
//                    }
//                    break;
//            }
//
//
//
//
//            childAt.measure(childWidthSpec,childHeightSpec);

