package com.example.administrator.wuziqi;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_voice,btn_restart,btn_exit;
    private  Wuziqipanel wuziqipanel;
    private MediaPlayer mediaPlayer;//音乐
    private  int position;//位置信息
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wuziqipanel = (Wuziqipanel) findViewById(R.id.wuziqi);
        btn_voice = (Button) findViewById(R.id.btn_voice);
        btn_restart = (Button) findViewById(R.id.btn_restart);
        btn_exit = (Button) findViewById(R.id.btn_exit);
        mediaPlayer = MediaPlayer.create(this, R.raw.tears);

        //监听事件
        btn_voice.setOnClickListener(this);
        btn_restart.setOnClickListener(this);
        btn_exit.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mediaPlayer.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case  R.id.menu:
                wuziqipanel.Start();
                break;

        }
        return true;
    }



    //设置监听事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_voice:{
                AnimationScale(btn_voice);
                mediaPlayer.stop();
                break;
            }
            case R.id.btn_restart:{
                AnimationScale(btn_restart);
                wuziqipanel.Start();


                break;
            }
            case R.id.btn_exit:{
                AnimationScale(btn_exit);
                MainActivity.this.finish();
                break;
            }
        }

    }

 //点击变化动画
    public  void  AnimationScale(Button btn){
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 1.2f, 1, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(500);
        btn.startAnimation(scaleAnimation);
    }
}
