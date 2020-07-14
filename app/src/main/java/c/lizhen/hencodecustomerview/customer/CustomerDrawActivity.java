package c.lizhen.hencodecustomerview.customer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import c.lizhen.hencodecustomerview.R;
import c.lizhen.hencodecustomerview.fragment.SimpleFragment;
import c.lizhen.hencodecustomerview.fragment.SimpleFragment1;

public class CustomerDrawActivity extends AppCompatActivity {

    @BindView(R.id.tl_1)
    com.flyco.tablayout.SlidingTabLayout tl1;
    @BindView(R.id.vp_content)
    ViewPager vpContent;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private final String[] mTitles = {
            "DrawColor", "DrawCircle", "Android"
            , "前端", "后端", "设计", "工具资源"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_draw);
        ButterKnife.bind(this);

        fragments.add(SimpleFragment.getInstance(""));
        fragments.add(SimpleFragment1.getInstance(""));

        vpContent.setAdapter(new MyPageAdapter(getSupportFragmentManager()));
        tl1.setViewPager(vpContent);

    }

    class MyPageAdapter extends FragmentPagerAdapter{

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
