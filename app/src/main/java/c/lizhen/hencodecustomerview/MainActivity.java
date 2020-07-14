package c.lizhen.hencodecustomerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import c.lizhen.hencodecustomerview.customer.CustomerCameraActivity;
import c.lizhen.hencodecustomerview.customer.CustomerDrawActivity;
import c.lizhen.hencodecustomerview.customer.CustomerFigureActivity;
import c.lizhen.hencodecustomerview.customer.CustomerTextActivity;
import c.lizhen.hencodecustomerview.customer.ViewPropertyAnimationActivity;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.bt_ondraw)
    Button btOndraw;
    @BindView(R.id.bt_onPoint)
    Button btOnPoint;
    @BindView(R.id.bt_onHenCodePlus)
    Button btOnHenCodePlus;
    @BindView(R.id.bt_camera)
    Button btCamera;
    @BindView(R.id.bt_ViewPropertyAnimation)
    Button btViewPropertyAnimation;

//    @BindView(R.id.bt_ondraw)
//    Button btOndraw;
//    @BindView(R.id.bt_onPoint)
//    Button btOnPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_ondraw, R.id.bt_onPoint, R.id.bt_onHenCodePlus, R.id.bt_camera,R.id.bt_ViewPropertyAnimation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_ondraw:
                Intent intent = new Intent(MainActivity.this, CustomerDrawActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_onPoint:
                Intent intentText = new Intent(MainActivity.this, CustomerTextActivity.class);
                startActivity(intentText);
                break;
            case R.id.bt_onHenCodePlus:

                Intent intentFig = new Intent(MainActivity.this, CustomerFigureActivity.class);
                startActivity(intentFig);

                break;
            case R.id.bt_camera:
                Intent intentCamera = new Intent(MainActivity.this, CustomerCameraActivity.class);
                startActivity(intentCamera);

                break;

            case R.id.bt_ViewPropertyAnimation:
                Intent intentViewProper = new Intent(MainActivity.this, ViewPropertyAnimationActivity.class);
                startActivity(intentViewProper);

                break;
        }
    }

//    @OnClick({R.id.bt_ondraw, R.id.bt_onPoint})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.bt_ondraw:
//                Intent intent = new Intent(MainActivity.this,CustomerDrawActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.bt_onPoint:
//                break;
//        }
//    }
}
