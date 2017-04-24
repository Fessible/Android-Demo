package example.com.shopdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //RadionButtion
    private RadioButton main_rb;
    private RadioButton menu_rb;
    private RadioButton person_rb;


    private TextView locationText;

    //声明AMapLocationClient类对象
    public AMapLocationClient mapLocationClient = null;
    public AMapLocationListener mapLocationListener;
    //声明操作对象
    public AMapLocationClientOption locationClientOption = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapLocationClient = new AMapLocationClient(getApplicationContext());
        setContentView(R.layout.fragment_show_main);
        locationText = (TextView) findViewById(R.id.location_text);
        initView();//初始化控件
        permission();//运行时权限控制


        mapLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                StringBuilder cureentPosition = new StringBuilder();
                /*cureentPosition.append("国家：").append(aMapLocation.getCountry()).append("\n");
                cureentPosition.append("省：").append(aMapLocation.getProvince()).append("\n");
                cureentPosition.append("市：").append(aMapLocation.getCity()).append("\n");
                cureentPosition.append("区：").append(aMapLocation.getDistrict()).append("\n");
                cureentPosition.append("街道：").append(aMapLocation.getStreet()).append("\n");*/
                //cureentPosition.append(aMapLocation.getAddress()).append("\n");//详细地址
                //ureentPosition.append(aMapLocation.getAoiName()).append("\n");//福建师范大学旗山校区
                cureentPosition.append(aMapLocation.getPoiName()).append("\n");//福建师范大学旗山校区

                locationText.setText(cureentPosition);


            }
        };

        mapLocationClient.setLocationListener(mapLocationListener);//设置定位回调监听


    }

    //初始化控件
    private void initView() {
        main_rb = (RadioButton) findViewById(R.id.rb_main);
        menu_rb = (RadioButton) findViewById(R.id.rb_menu);
        person_rb = (RadioButton) findViewById(R.id.rb_personal);
    }

    private void permission() {
        //运行时权限控制
        List<String> permissionList = new ArrayList<String>();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this, permissions, 1);
        } else {
            initLocation();
            requestLocation();
        }
    }


    private void requestLocation() {
        mapLocationClient.startLocation();
    }

    private void initLocation() {

        locationClientOption = new AMapLocationClientOption();//初始化
        //设置获取位置的模式
        locationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        locationClientOption.setInterval(1000);//连续获取位置
        locationClientOption.setNeedAddress(true);//返回地址信息
        mapLocationClient.setLocationOption(locationClientOption);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_main:
                break;
            case R.id.rb_menu:
                break;
            case R.id.rb_personal:
                break;
        }
    }
}

