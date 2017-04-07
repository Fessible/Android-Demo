package example.com.xueziapp;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import example.com.xueziapp.login.AccountLoginFragment;
import example.com.xueziapp.login.FragAdapter;
import example.com.xueziapp.login.PhoneNumberLoginFragment;
import example.com.xueziapp.login.RegisterActivity;

public class MainActivity extends AppCompatActivity {
    private TextView textRegister;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<String>  mTitles = new ArrayList<>();
    private LayoutInflater inflater;
    private  View view1,view2;
    private List<Fragment> mfragments = new ArrayList<>();//视图集合


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textRegister = (TextView) findViewById(R.id.text_register);
        textRegister.setOnClickListener(new View.OnClickListener() {//点击注册文本后跳转到注册界面
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        inflater = LayoutInflater.from(this);

        PhoneNumberLoginFragment phoneNumberLoginFragment = new PhoneNumberLoginFragment();
        AccountLoginFragment accountLoginFragment = new AccountLoginFragment();
        mfragments.add(accountLoginFragment);
        mfragments.add(phoneNumberLoginFragment);




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
//        LoginPagerAdapter adapter = new LoginPagerAdapter(mviewList,mTitles);
//        viewPager.setAdapter(adapter);
        FragAdapter adapter = new FragAdapter(getSupportFragmentManager(), mfragments,mTitles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setTabsFromPagerAdapter(adapter);
    }

    //初始化Tablayout
    private void InitTablayout() {
        tabLayout = (TabLayout) findViewById(R.id.tab_title);
        tabLayout.addTab(tabLayout.newTab().setText(mTitles.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(mTitles.get(1)));
 }




}
