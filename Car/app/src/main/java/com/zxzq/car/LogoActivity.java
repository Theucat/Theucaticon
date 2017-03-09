package com.zxzq.car;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.io.IOException;

/**
 * 瞎搞的logo界面
 */
public class LogoActivity extends BaseActivity {
    private MediaPlayer mMediaPlayer;
    private ImageView mLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        playmusic();
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        mLogo = (ImageView) findViewById(R.id.iv_logo);
        mLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CarActivity.class);
                LogoActivity.this.finish();
            }
        });
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.logo);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener(){
            //动画启动时调用
            @Override
            public void onAnimationStart(Animation animation) {
            }
            //动画重复时调用
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
            //动画结束时调用
            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(CarActivity.class);
                LogoActivity.this.finish();
            }
        });
        mLogo.setAnimation(animation);
    }

    /**
     * 音乐播放
     */
    private void playmusic() {
        AssetManager assetManager = getAssets();//用于获取Asset文件夹下的资源文件
        try {
            AssetFileDescriptor fileDescriptor = assetManager.openFd("logo.mp3");//加载音乐资源
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(),fileDescriptor.getStartOffset(),fileDescriptor.getLength());
            mMediaPlayer.prepare();//准备音乐
            mMediaPlayer.start();//播放音乐

        } catch (IOException e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 音乐停止
     */
    public void onDestroy(){
        mMediaPlayer.stop();//停止音乐
        super.onDestroy();
    }

}


