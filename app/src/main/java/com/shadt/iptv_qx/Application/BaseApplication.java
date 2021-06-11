package com.shadt.iptv_qx.Application;

import android.app.Application;
import android.util.DisplayMetrics;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.shadt.iptv_qx.R;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class BaseApplication extends Application {

    public  static  RequestOptions requestOptions = new RequestOptions()
            .placeholder(R.drawable.default_bg)
            .error(R.drawable.default_bg)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);




    public static float proportion=1;
    public static int fontsize=20;
    public static int fontsize_small=18;
    public static int fontsize_news=15;
    public static  String TAG = "wangben";
     @Override
    public void onCreate() {
        super.onCreate();
         IjkMediaPlayer.loadLibrariesOnce(null);
         IjkMediaPlayer.native_profileBegin("libijkplayer.so");
         DisplayMetrics dm = getResources().getDisplayMetrics();
       int  screenWidth = dm.widthPixels;

         if (screenWidth==1920){
             proportion=1;
             fontsize=23;
             fontsize_news=20;
             fontsize_small=21;
         }else if(screenWidth==1280){
             proportion=1.5f;
             fontsize=20;
             fontsize_news=18;
             fontsize_small=19;
         }
    }
}
