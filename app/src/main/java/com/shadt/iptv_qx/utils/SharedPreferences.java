package com.shadt.iptv_qx.utils;

import android.content.Context;


public class SharedPreferences {
    public void writemaindata(Context mContext,String data){
        android.content.SharedPreferences sp=mContext.getSharedPreferences("'iptv'",Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor ed= sp.edit();
        ed.putString("main",data);
        ed.apply();
    }
    public  String getmaindata(Context mContext){
        android.content.SharedPreferences sp=mContext.getSharedPreferences("'iptv'",Context.MODE_PRIVATE);

       return sp.getString("main","");
    }
    public  void writeportion(Context mContext,float proportion){
        android.content.SharedPreferences sp=mContext.getSharedPreferences("'iptv'",Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor ed= sp.edit();
        ed.putFloat("proportion",proportion);
        ed.apply();

    }
    public  Float getportion(Context mContext){
        android.content.SharedPreferences sp=mContext.getSharedPreferences("'iptv'",Context.MODE_PRIVATE);

        return sp.getFloat("proportion",1);
    }

    public  void writewelcombg(Context mContext,String str){
        android.content.SharedPreferences sp=mContext.getSharedPreferences("'iptv'",Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor ed= sp.edit();
        ed.putString("welbg",str);
        ed.apply();

    }
    public  String getwelcombg(Context mContext){
        android.content.SharedPreferences sp=mContext.getSharedPreferences("'iptv'",Context.MODE_PRIVATE);

        return sp.getString("welbg","");
    }
}
