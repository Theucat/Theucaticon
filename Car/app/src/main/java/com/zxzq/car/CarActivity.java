package com.zxzq.car;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 主界面
 */
public class CarActivity extends AppCompatActivity implements View.OnClickListener{

    private TimerTask timeTask;
    private Timer timer = new Timer();
    private boolean isExit = false;
    private EditText city;
    private EditText car;
    private String  cityText;
    private String carText;
    private ImageButton enter;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        city = (EditText) findViewById(R.id.et_city);
        car = (EditText) findViewById(R.id.et_car);
        enter = (ImageButton) findViewById(R.id.iv_enter);
        enter.setOnClickListener(this);
    }

    /**
     * 实体返回键点击事件
     */
    @Override
    public void onBackPressed() {
        if (isExit) {
            finish();
        } else {
            isExit = true;
            Toast.makeText(getApplication(), "再按一次退出", Toast.LENGTH_SHORT).show();
            timeTask = new TimerTask() {

                @Override
                public void run() {
                    isExit = false;
                }
            };
            timer.schedule(timeTask, 2000);
        }
    }

    /**
     * 查找键点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        cityText = city.getText().toString();
        carText = car.getText().toString();
        if (cityText.isEmpty()||carText.isEmpty()){
            Toast.makeText(this,"城市和车次不能为空!",Toast.LENGTH_SHORT).show();
        }else {
            mIntent = new Intent(CarActivity.this, SiteActivity.class);
            mIntent.putExtra("car", carText);
            mIntent.putExtra("city", cityText);
            startActivity(mIntent);
        }

    }
}
