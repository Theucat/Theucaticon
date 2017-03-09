package com.zxzq.car;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zxzq.car.adapter.MyAdapter;
import com.zxzq.car.bean.JsonBean;
import com.zxzq.car.util.HttpClientUtil;
import com.zxzq.car.view.RecycleViewDivider;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 路线显示界面
 */
public class SiteActivity extends AppCompatActivity implements View.OnClickListener{

    private TimerTask timeTask;
    private Timer timer = new Timer();
    private boolean isExit = false;
    private List<JsonBean.ResultEntity> resultList;
    private ImageButton back;
    private TextView site;
    private ImageButton change;
    private RecyclerView recycle;
    private String city;
    private String car;
    private TextView cotent;
    private TextView time;
    private MyAdapter mAdapter;
    private JsonBean.ResultEntity result;
    private int order = 1;
    private List<JsonBean.ResultEntity.StationdesEntity> mEntities;
    private JsonBean mJsonBean;
    private LinearLayout resy;
    private ImageView iv_sorry;
    private TextView tv_sorry;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);
        city = getIntent().getStringExtra("city");
        car = getIntent().getStringExtra("car");
        getJsonData();
    }

    /**
     * 获得Json数据
     */
    private void getJsonData() {
        getDialog();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String path = "http://op.juhe.cn/189/bus/busline?key=6f0e2a5d983cd6045f11eb0086eb5b3c&dtype=json&city=" + city + "&bus=" + car;
                SystemClock.sleep(4000);//线程睡4秒
                dialog.cancel();//关闭dialog
                String s = HttpClientUtil.HttpGet(path);
                Gson gson = new Gson();
                mJsonBean = gson.fromJson(s,JsonBean.class);
                resultList = mJsonBean.getResult();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initView();
                        reverseContent(order);
                    }
                });
            }
        }).start();
    }

    /**
     * 加载弹出框
     */
    private void getDialog() {
        dialog = new ProgressDialog(this);
        dialog.setTitle("路线查询");//设置标题
        dialog.setMessage("加载中，请稍后...");//设置内容
        dialog.setIndeterminate(false);
        dialog.setCanceledOnTouchOutside(false);//是否点击退出
        dialog.setIcon(R.mipmap.cup);//设置图标
        dialog.show();
    }

    /**
     * 来回路线切换、填充视图
     * @param order
     */
    private void reverseContent(int order) {
        try {
            if (order == 1) {
                result = resultList.get(0);
            } else if (order == -1) {
                result = resultList.get(1);
            }
        cotent.setText(result.getKey_name() + "详情");
        String start = result.getStart_time().substring(0,2) + ":" + result.getStart_time().substring(2);
        String end = result.getEnd_time().substring(0,2) + ":" + result.getEnd_time().substring(2);
        site.setText(result.getFront_name() + " ===> " + result.getTerminal_name());
        time.setText("首" + start + " 末" + end + " 全程" + result.getLength().substring(0,result.getLength().indexOf(".")) + "公里");
        recycle.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL));//添加分割线
        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycle.setHasFixedSize(true);
        mEntities = result.getStationdes();

        mAdapter = new MyAdapter(mEntities);
        recycle.setAdapter(mAdapter);
        }catch (Exception e){
            cotent.setText("未知路线");
            resy = (LinearLayout) findViewById(R.id.ll_resy);
            iv_sorry = (ImageView) findViewById(R.id.iv_sorry);
            tv_sorry = (TextView) findViewById(R.id.tv_sorry);
            resy.setVisibility(View.INVISIBLE);
            recycle.setVisibility(View.INVISIBLE);
            iv_sorry.setVisibility(View.VISIBLE);
            tv_sorry.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 初始化视图
     */
    private void initView() {
        back = (ImageButton) findViewById(R.id.iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SiteActivity.this.finish();
            }
        });
        cotent = (TextView) findViewById(R.id.tv_cotent);
        site = (TextView) findViewById(R.id.tv_site);
        change = (ImageButton) findViewById(R.id.iv_change);
        change.setOnClickListener(this);
        time = (TextView) findViewById(R.id.tv_time);
        recycle = (RecyclerView) findViewById(R.id.rv_site);
    }

    /**
     * 切换键的点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_change:
                order = 0 - order;
                reverseContent(order);
                break;
        }
    }

    /**
     * 实体返回键的点击事件
     */
    @Override
    public void onBackPressed() {
            SiteActivity.this.finish();
    }

    /**
     * 非空判断
     * @param tag
     * @param o
     */
    public void isNull(String tag,Object o){
        if (o == null){
            System.out.println(tag + ":is null  XXXXXXXXXXXXXX");
        }
    }
}
