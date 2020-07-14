package c.lizhen.touchview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TouchView view_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nested_scalable_image_view);


       // onTouchEvent();

    }
}
