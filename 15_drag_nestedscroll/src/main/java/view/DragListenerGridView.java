package view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class DragListenerGridView extends ViewGroup {

    private static final int COLUMNS = 2;
    private static final int ROWS = 3;
    ViewConfiguration viewConfiguration;
    OnDragListener dragListener = new HenDragListener();
    View draggedView;
    List<View> orderedChildren = new ArrayList<>();

    public DragListenerGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        viewConfiguration = ViewConfiguration.get(context);
        setChildrenDrawingOrderEnabled(true);
    }

    @Override
    public boolean onDragEvent(DragEvent event) {
        //任何时候都会调用这个方法
        return super.onDragEvent(event);
    }

    //当我们的XML布局被加载完后，就会回调onFinshInfalte这个方法，在这个方法中我们可以初始化控件和数据。
    //但是在 onFinishInflate 中不能获取 view 宽高，需要在 onMeasure 之后获取，
    //
    //setContentView > onFinishInflate > view绘制流程（performMeasure、performLayout、performDraw）
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            orderedChildren.add(child);
            child.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    draggedView = v;
                    //这个方法会自动实现拖拽，半透明   1.data数据只有在松手后才可以到这个复制的数据，myLocalState在任何时候都能拿到这个数据
                    //2.data这个数据是支持夸进程的，数据比较重，需要额外包装
                    //DragShadowBuilder可定制样式
                    v.startDrag(null, new DragShadowBuilder(v), v, 0);

                    return false;
                }
            });
            child.setOnDragListener(dragListener);//响应拖拽的操作
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        int specHeight = MeasureSpec.getSize(heightMeasureSpec);

        int childWidth = specWidth / COLUMNS;
        int childHeight = specHeight / ROWS;
        measureChildren(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY));
        setMeasuredDimension(specWidth, specHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childLeft;
        int childTop;
        int childWidth = getWidth() / COLUMNS;
        int childHeight = getHeight() / ROWS;
        int childCount = getChildCount();
        for (int index = 0; index < childCount; index++) {
            View child = getChildAt(index);
            childLeft = index % 2 * childWidth;
            childTop = index / 2 * childHeight;
            child.layout(0, 0, childWidth, childHeight);
            child.setTranslationX(childLeft);
            child.setTranslationY(childTop);

        }

    }


    private class HenDragListener implements OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED://开始
                    if (event.getLocalState() == v) {
                        v.setVisibility(INVISIBLE);//隐藏
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENTERED://触摸到某个区域内
                    if (event.getLocalState() != v) {
                        sort(v);//排序
                    }
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setVisibility(VISIBLE);//显示
                    break;
                case DragEvent.ACTION_DROP://松手时
                    break;
            }
            return true;
        }



    }
    private void sort(View targetView) {
        int draggedIndex = -1;
        int targetIndex = -1;
        for (int i = 0; i < getChildCount(); i++) {
            View child = orderedChildren.get(i);
            if (targetView == child) {
                targetIndex = i;
            } else if (draggedView == child) {
                draggedIndex = i;
            }
        }
        if (targetIndex < draggedIndex) {
            orderedChildren.remove(draggedIndex);
            orderedChildren.add(targetIndex, draggedView);
        } else if (targetIndex > draggedIndex) {
            orderedChildren.remove(draggedIndex);
            orderedChildren.add(targetIndex, draggedView);

        }

        int childLeft;
        int childTop;
        int childWidth = getWidth() / COLUMNS;
        int childHeight = getHeight() / ROWS;
        for (int index = 0; index < getChildCount(); index++) {
            View child = orderedChildren.get(index);
            childLeft = index % 2 * childWidth;
            childTop = index / 2 * childHeight;
            child.animate()
                    .translationX(childLeft)
                    .translationY(childTop)
                    .setDuration(150);
        }
    }
}
