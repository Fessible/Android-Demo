package example.com.meituanlogin;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<String>  mTitles = new ArrayList<>();//标题集合
    private List<Fragment> mfragments = new ArrayList<>();//视图集合
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PhoneLoginFragment phoneLoginFragment = new PhoneLoginFragment();//初始化PhoneLoginFragment对象
        AccountLoginFragment accountLoginFragment = new AccountLoginFragment();//初始化AccountLoginFragment对象
        mfragments.add(accountLoginFragment);
        mfragments.add(phoneLoginFragment);
        mTitles.add("账号密码登录");
        mTitles.add("手机号快捷登录");
        InitView();
    }

    private void InitView() {
        InitTablayout();
        InitViewPager();
    }

    //初始化viewPager
    private void InitViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        //初始化适配器
        FragAdapter adapter = new FragAdapter(getSupportFragmentManager(), mfragments,mTitles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);//让TabLayout随着ViewPager的变换而变换
    }

    //初始化Tablayout
    private void InitTablayout() {
        tabLayout = (TabLayout) findViewById(R.id.tab_title);
        //给TabLayout添加内容
        tabLayout.addTab(tabLayout.newTab().setText(mTitles.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(mTitles.get(1)));
 }
}
