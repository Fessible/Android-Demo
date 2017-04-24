package example.com.fragmenttabhostdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    FragmentTabHost fragmentTabHost;
    private List<Class> frgList;
    private List<String> textList;
    private LayoutInflater mLayoutInflater;
    private int btns[] = {R.drawable.home_selector, R.drawable.menu_selector, R.drawable.person_selector};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        frgList = new ArrayList<>();//fragment的集合
        textList = new ArrayList<>();//text的集合
        frgList.add(Fragment1.class);
        frgList.add(Fragment2.class);
        frgList.add(Fragment3.class);
        textList.add("主页");
        textList.add("菜单");
        textList.add("个人");
        mLayoutInflater = LayoutInflater.from(this);
        //设置fragment内容的id
        fragmentTabHost.setup(this, getSupportFragmentManager(), R.id.maincontent);
        for (int i = 0; i < frgList.size(); i++) {
            // 给每个Tab按钮设置图标、文字和内容
            //newTabSpec(textList.get(i))
            TabHost.TabSpec spec = fragmentTabHost.newTabSpec("").setIndicator(getView(i));
            // 将Tab按钮添加进Tab选项卡中
            fragmentTabHost.addTab(spec, frgList.get(i), null);
        }
    }

    private View getView(int index) {
        View view = mLayoutInflater.inflate(R.layout.tab_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(btns[index]);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(textList.get(index));

        return view;
    }
}
