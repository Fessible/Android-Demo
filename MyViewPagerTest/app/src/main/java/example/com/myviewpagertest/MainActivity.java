package example.com.myviewpagertest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                viewPager.setCurrentItem(currentPosition,false);

            }
        }
    };
    //设置图片的来源
    int[] imageId = new int[]{
            R.drawable.angry, R.drawable.nhh, R.drawable.cat, R.drawable.pig};
    List<ImageView> imageViews;
    List<View> docts;
    ViewPager viewPager;
    int currentPosition = 1;
    int dotPosition = 0;
    int prePosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        setViewPager();
        autoPlay();
    }

    private void initData() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        docts = new ArrayList<>();
        imageViews = new ArrayList<>();
        ImageView imageView;
        for (int i = 0; i < imageId.length + 2; i++) {
            if (i == 0) {//第一个页面为最后一张图片
                imageView = new ImageView(this);
                imageView.setBackgroundResource(imageId[imageId.length - 1]);
                imageViews.add(imageView);
            } else if (i == imageId.length + 1) {//当为最后一个页面显示第一张图片
                imageView = new ImageView(this);
                imageView.setBackgroundResource(imageId[0]);
                imageViews.add(imageView);
            } else {//其他情况下
                imageView = new ImageView(this);
                imageView.setBackgroundResource(imageId[i - 1]);
                imageViews.add(imageView);
            }
        }
        docts.add(findViewById(R.id.doc_01));
        docts.add(findViewById(R.id.doc_02));
        docts.add(findViewById(R.id.doc_03));
        docts.add(findViewById(R.id.doc_04));

    }

    private void setViewPager() {
        MyPagerAdapter adapter = new MyPagerAdapter(imageViews);
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        viewPager.setCurrentItem(currentPosition);
        //页面改变监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (position == 0) {    //判断当切换到第0个页面时把currentPosition设置为imageId.length,即倒数第二个位置，小圆点位置为length-1
                    currentPosition = imageId.length;
                    dotPosition = imageId.length - 1;
                } else if (position == imageId.length + 1) {    //当切换到最后一个页面时currentPosition设置为第一个位置，小圆点位置为0
                    currentPosition = 1;
                    dotPosition = 0;
                } else {
                    currentPosition = position;
                    dotPosition = position - 1;
                }
                docts.get(prePosition).setBackgroundResource(R.drawable.dot_normal);
                docts.get(dotPosition).setBackgroundResource(R.drawable.dot_focus);
                prePosition = dotPosition;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //当state为SCROLL_STATE_IDLE即没有滑动的状态时切换页面
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    viewPager.setCurrentItem(currentPosition, false);

                }
            }
        });}
        //  设置自动播放
        private void autoPlay() {
            new Thread(){
                @Override
                public void run() {
                    super.run();

                    while(true){
                        SystemClock.sleep(5000);
                        currentPosition++;
                        handler.sendEmptyMessage(1);

                    }
                }
            }.start();
        }
    }






