package c.lizhen.materialedittext;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

public class MaterialEditText extends android.support.v7.widget.AppCompatEditText {

    private static final float TEXT_SIZE = Utils.dp2px(12);
    private static final float TEXT_MARGEN = Utils.dp2px(8);
    private static final int TEXT_VERTICAL_OFFSET = (int) Utils.dp2px(22);
    private static final int TEXT_HORIZONTAL_OFFSET = (int) Utils.dp2px(5);
    private static final int TEXT_ANIMATION_OFFSET = (int) Utils.dp2px(16);

    boolean floatingLabelShow ;
    float floatingLabelFraction;
    ObjectAnimator animator;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private boolean userFloatingLabel = true;

    Rect backgroundPadding = new Rect();

    public MaterialEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs );
    }

   public void init(Context context ,AttributeSet attributeSet) {
        //获取配置文件的信息
       TypedArray typedArray = context.obtainStyledAttributes(attributeSet,R.styleable.MaterialEditText);
       userFloatingLabel = typedArray.getBoolean(R.styleable.MaterialEditText_userFloatingLabel, true);
       typedArray.recycle();
       paint.setTextSize(TEXT_SIZE);
        onUserFloatingLable();
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (userFloatingLabel){
                    if (floatingLabelShow && TextUtils.isEmpty(s)){
                        floatingLabelShow = false;
                        getAnimator().reverse();
                    }else if (!floatingLabelShow && !TextUtils.isEmpty(s)){
                        floatingLabelShow = true;
                        getAnimator().start();
                    }
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        }


private void onUserFloatingLable(){
    getBackground().getPadding(backgroundPadding);
    if (userFloatingLabel){
        setPadding(getPaddingLeft(), (int) (backgroundPadding.top+TEXT_MARGEN+TEXT_SIZE),getPaddingRight(),getPaddingBottom());

    }else{
        setPadding(getPaddingLeft(),  backgroundPadding.top,getPaddingRight(),getPaddingBottom());
    }

}
    public ObjectAnimator getAnimator(){
        if (animator == null){
            animator  = ObjectAnimator.ofFloat(MaterialEditText.this,"floatingLabelFraction",0,1);
        }

        return animator;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        if (!getText().toString().isEmpty()){
//            canvas.drawText(getHint().toString(),TEXT_HORIZONTAL_OFFSET,TEXT_VERTICAL_OFFSET,paint);
//        }

        paint.setAlpha((int)(0xff * floatingLabelFraction));
        float extraOfft = TEXT_ANIMATION_OFFSET * (1 - floatingLabelFraction);
        canvas.drawText(getHint().toString(),TEXT_HORIZONTAL_OFFSET,TEXT_VERTICAL_OFFSET+extraOfft,paint);

    }

    public void setUserFloatingLable(boolean userFloatingLabel){

        if (this.userFloatingLabel != userFloatingLabel){
            this.userFloatingLabel = userFloatingLabel;
            onUserFloatingLable();

        }


    }


    public float getFloatingLabelFraction() {
        return floatingLabelFraction;
    }

    public void setFloatingLabelFraction(float floatingLabelFraction) {
        this.floatingLabelFraction = floatingLabelFraction;
        invalidate();
    }
}
