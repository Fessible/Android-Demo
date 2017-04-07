package example.com.xueziapp.login;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 49995 on 2017/3/5.
 */
class LoginPagerAdapter extends PagerAdapter {
    private List<View> mviewList;
    private List<String> mTitles;
    public LoginPagerAdapter(List<View> viewList, List<String> titles){
        mTitles = titles;
        mviewList = viewList;

    }
    @Override
    public int getCount() {
        return mviewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mviewList.get(position));
        return mviewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        container.removeView(mviewList.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}

