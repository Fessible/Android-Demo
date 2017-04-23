package example.com.meituanlogin;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 49995 on 2017/3/6.
 */

public class FragAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<String> titles;


    public FragAdapter(FragmentManager supportFragmentManager, List<Fragment> fragments, List<String> titles) {
        super(supportFragmentManager);
        this.fragments = fragments;
        this.titles = titles;
    }

    //获取页面内容
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    //返回fragments的个数，即页面数
    @Override
    public int getCount() {
        return fragments.size();
    }

    //获取每个页面的title
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
