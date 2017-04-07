package example.com.xueziapp.login;

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

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
