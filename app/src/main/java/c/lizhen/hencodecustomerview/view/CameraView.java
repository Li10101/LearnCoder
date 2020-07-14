package c.lizhen.hencodecustomerview.view;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import c.lizhen.hencodecustomerview.utils.Utils;

public class CameraView extends View {

    private static final float PADDING = Utils.dp2px(100);
    private static final float IMAG_WIDTH = Utils.dp2px(400);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    Camera camera = new Camera();

    public CameraView(Context context, AttributeSet attrs) {

        super(context, attrs);

    }

    {

        camera.rotateX(45);
        camera.setLocation(0, 0, Utils.getDensity());

    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        //绘制上半部分
        canvas.save();
        canvas.translate(300, 300);
        canvas.rotate(-20);
        canvas.clipRect(-300,-300,300,0);
        canvas.rotate(20);
        canvas.translate(-300, -300);
        canvas.drawBitmap(Utils.getAvatar(getResources(), (int) IMAG_WIDTH), PADDING, PADDING, paint);
        canvas.restore();

//        canvas.save();
//        canvas.clipRect(-150,-150,150,0);
//        canvas.drawBitmap(Utils.getAvatar(getResources(), 400), 100, 100, paint);
//        canvas.restore();
        //绘制下半部分
        canvas.save();
        canvas.translate(300, 300);
        canvas.rotate(-20);
        camera.applyToCanvas(canvas);
        canvas.clipRect(-300,0,300,300);
        canvas.rotate(20);
        canvas.translate(-300, -300);
        canvas.drawBitmap(Utils.getAvatar(getResources(), 400), 100, 100, paint);
        canvas.restore();
    }
}
