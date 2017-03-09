package com.zxzq.car;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;



/**
 * Created by Administrator on 2017/1/16 0016.
 */
public class BaseActivity extends Activity{
    protected int screen_w,screen_h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         screen_w = getWindowManager().getDefaultDisplay().getWidth();
         screen_h = getWindowManager().getDefaultDisplay().getHeight();
    }
    //带类名，bundle，uri数据的跳转
    public void startActivity(Class<?> pClass,Bundle bundle,Uri uri){
        Intent intent = new Intent(this,pClass);
        if(bundle!=null) {
            intent.putExtras(bundle);
        }
        if(uri!=null){
            intent.setData(uri);
        }
        startActivity(intent);

    }
    //带类名，bundle的跳转
    public void startActivity(Class<?> pclass,Bundle bundle){
        startActivity(pclass,bundle,null);
    }
    //带类名的跳转
    public void startActivity(Class<?> pclass){
        startActivity(pclass,null,null);
    }
    //带字符串的页面跳转
    public void startActivity(String action,Bundle bundle,Uri uri){
        Intent intent = new Intent(action);
        if(bundle!=null)
        intent.putExtras(bundle);
        if(uri!=null)
        intent.setData(uri);
        startActivity(intent);
    }
    //只带bundle的字符串跳转
    public void startActivity(String action,Bundle bundle){
        startActivity(action,bundle,null);
    }
    private Toast mToast;
    //自定义String消息的吐司
    public void showToast(String msg){
        if(mToast==null){
            mToast=Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        }
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setText(msg);
        mToast.show();
    }
    //带资源ID的吐司
    public void showToast(int resId){
        showToast(getString(resId));
    }


}
