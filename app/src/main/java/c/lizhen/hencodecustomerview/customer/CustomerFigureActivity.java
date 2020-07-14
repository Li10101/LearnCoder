package c.lizhen.hencodecustomerview.customer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import c.lizhen.hencodecustomerview.R;
import c.lizhen.hencodecustomerview.fragment.SimpleFragment2;
import c.lizhen.hencodecustomerview.fragment.SimpleFragment3;
import c.lizhen.hencodecustomerview.fragment.SimpleFragment4;
import c.lizhen.hencodecustomerview.fragment.SimpleFragment5;
import c.lizhen.hencodecustomerview.fragment.SimpleFragment6;

public class CustomerFigureActivity extends AppCompatActivity {

    @BindView(R.id.tl_1)
    SlidingTabLayout tl1;
    @BindView(R.id.vp_customer_test)
    ViewPager vpCustomerTest;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private final String[] mTitles = {
            "canvasTest", "CanvasDirection", "CanvasDashBoard"
            , "画饼图", "Bitmap", "设计", "工具资源"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custome_text);
        ButterKnife.bind(this);
        fragments.add(SimpleFragment2.getInstance(""));
        fragments.add(SimpleFragment3.getInstance(""));
        fragments.add(SimpleFragment4.getInstance(""));
        fragments.add(SimpleFragment5.getInstance(""));
        fragments.add(SimpleFragment6.getInstance(""));

        vpCustomerTest.setAdapter(new CustomerFigureActivity.MyPageAdapter(getSupportFragmentManager()));
        tl1.setViewPager(vpCustomerTest);

    }
    class MyPageAdapter extends FragmentPagerAdapter {

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            return mTitles[position];
        }
    }
}
