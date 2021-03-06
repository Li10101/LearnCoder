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
import c.lizhen.hencodecustomerview.fragment.SimpleFragment10;
import c.lizhen.hencodecustomerview.fragment.SimpleFragment11;
import c.lizhen.hencodecustomerview.fragment.SimpleFragment12;
import c.lizhen.hencodecustomerview.fragment.SimpleFragment9;

public class ViewPropertyAnimationActivity extends AppCompatActivity {

    @BindView(R.id.tl_1)
    SlidingTabLayout tl1;
    @BindView(R.id.vp_customer_test)
    ViewPager vpCustomerTest;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private final String[] mTitles = {
            "ViewProperty", "ObjectAnimator", "AnimatorSet"
            , "画饼图", "Bitmap", "设计", "工具资源"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custome_text);
        ButterKnife.bind(this);
        fragments.add(SimpleFragment10.getInstance(""));
        fragments.add(SimpleFragment11.getInstance(""));
        fragments.add(SimpleFragment12.getInstance(""));



        vpCustomerTest.setAdapter(new ViewPropertyAnimationActivity.MyPageAdapter(getSupportFragmentManager()));
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
