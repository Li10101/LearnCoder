package c.lizhen.hencodecustomerview.view;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import c.lizhen.hencodecustomerview.utils.Utils;

public class CameraView1 extends View {

    private static final float PADDING = Utils.dp2px(100);
    private static final float IMAG_WIDTH = Utils.dp2px(200);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    float topFlip = 0;
    float bottomFlip = 0;
    float flipRotation = 0;
    Camera camera = new Camera();

    public CameraView1(Context context, AttributeSet attrs) {

        super(context, attrs);

    }

    {


        camera.setLocation(0, 0, Utils.getDensity());

    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        //绘制上半部分
        canvas.save();
        canvas.translate((PADDING +IMAG_WIDTH/2), (PADDING +IMAG_WIDTH/2));
        canvas.rotate(-flipRotation);
        camera.save();
        camera.rotateX(topFlip);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.clipRect(-IMAG_WIDTH,-IMAG_WIDTH,IMAG_WIDTH,0);
        canvas.rotate(flipRotation);
        canvas.translate(-(PADDING +IMAG_WIDTH/2), -(PADDING +IMAG_WIDTH/2));
        canvas.drawBitmap(Utils.getAvatar(getResources(), (int) IMAG_WIDTH), PADDING, PADDING, paint);
        canvas.restore();

//        canvas.save();
//        canvas.clipRect(-150,-150,150,0);
//        canvas.drawBitmap(Utils.getAvatar(getResources(), 400), 100, 100, paint);
//        canvas.restore();

        //绘制下半部分
        canvas.save();
        canvas.translate((PADDING +IMAG_WIDTH/2), (PADDING +IMAG_WIDTH/2));
        canvas.rotate(-flipRotation);
        camera.save();
        camera.rotateX(bottomFlip);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.clipRect(-IMAG_WIDTH,0,IMAG_WIDTH,IMAG_WIDTH);
        canvas.rotate(flipRotation);
        canvas.translate(-(PADDING +IMAG_WIDTH/2), -(PADDING +IMAG_WIDTH/2));
        canvas.drawBitmap(Utils.getAvatar(getResources(), (int) IMAG_WIDTH), PADDING, PADDING, paint);
        canvas.restore();

    }

    public float getTopFlip() {
        return topFlip;
    }

    public void setTopFlip(float topFlip) {
        this.topFlip = topFlip;
        invalidate();
    }

    public float getBottomFlip() {
        return bottomFlip;
    }

    public void setBottomFlip(float bottomFlip) {
        this.bottomFlip = bottomFlip;
        invalidate();
    }

    public float getFlipRotation() {
        return flipRotation;
    }

    public void setFlipRotation(float flipRotation) {
        this.flipRotation = flipRotation;
        invalidate();
    }
}
