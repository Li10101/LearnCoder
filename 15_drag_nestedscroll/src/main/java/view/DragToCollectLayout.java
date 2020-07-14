package view;

import android.content.ClipData;
import android.content.Context;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.view.ViewCompat;
import c.lizhen.a15_drag_nestedscroll.R;

public class DragToCollectLayout extends RelativeLayout {


    private ImageView avatarView;
    private ImageView logoView;
    private LinearLayout collectorLayout;
    OnLongClickListener dragStarter = new OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData imageData = ClipData.newPlainText("name", v.getContentDescription());
            //这个方法会自动实现拖拽，半透明
            //1.data数据只有在松手后才可以到这个复制的数据，
            //2.myLocalState在任何时候都能拿到这个数据
            //3.data这个数据是支持夸进程的，数据比较重，需要额外包装
            //4.DragShadowBuilder可定制样式
            return ViewCompat.startDragAndDrop(v,imageData,new DragShadowBuilder(v),null,0);
        }
    };
    OnDragListener dragListener = new CollectListener();
    public DragToCollectLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        avatarView = findViewById(R.id.avatarView);
        logoView = findViewById(R.id.logoView);
        collectorLayout = findViewById(R.id.collectorLayout);

        avatarView.setOnLongClickListener(dragStarter);
        logoView.setOnLongClickListener(dragStarter);
        collectorLayout.setOnDragListener(dragListener);
    }

    private class CollectListener implements OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()){
                case DragEvent.ACTION_DROP:
                    if (v instanceof LinearLayout){
                        LinearLayout layout = (LinearLayout) v;
                        TextView textView = new TextView(getContext());
                        textView.setTextSize(16);
                        textView.setText(event.getClipData().getItemAt(0).getText());
                        layout.addView(textView);

                    }
                    break;
            }
            return true;
        }
    }
}
