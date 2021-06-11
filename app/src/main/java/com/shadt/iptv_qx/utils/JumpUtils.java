package com.shadt.iptv_qx.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.shadt.iptv_qx.IjkPlayerActivity;
import com.shadt.iptv_qx.VisualizationActivity;
import com.shadt.iptv_qx.WebActivity;
import com.shadt.iptv_qx.model.PageInfo2;
import com.shadt.iptv_qx.newListViewActivity;
import com.shadt.iptv_qx.newListViewActivity2;

public class JumpUtils {
    public static void Jump(Context mContext, PageInfo2.PageDataBean mContent, String target) {
        if(target==null || target.equals("")){
            Toast.makeText(mContext,"暂未开放该功能",Toast.LENGTH_SHORT).show();
            return;
        }
        if (target.equals("openweb")) {
            OpenWeb(mContext, "" + mContent.getTarget());
        } else if (target.equals("openvisualization")) {
            if(mContent.getTarget().equals("")){
                Toast.makeText(mContext,"暂未开放该功能",Toast.LENGTH_SHORT).show();
                return;
            }
            Intent it = new Intent(mContext, VisualizationActivity.class);
            it.putExtra("id", mContent.getTarget());
            mContext.startActivity(it);
            //OpenNews(mContext,mContent);
        } else if (target.equals("opennews")) {
            OpenNews(mContext, mContent);
        } else if (target.equals("OpenGallery")) {
            OpenGallery(mContext, mContent);
        } else if (target.equals("openapk")) {
            if (!TextUtils.isEmpty(mContent.getTarget())){
                OpenApk(mContext,mContent.getTarget());
            }
        } else if (target.equals("playvideo")) {
            OpenVideoPlayer(mContext, "");
        } else if (target.equals("return")) {

        }
    }

    public static void OpenNews(Context mContext, PageInfo2.PageDataBean mContent) {
        MyLog.v("打开新闻页面" + mContent.getInterfaceX());
        Intent it = new Intent(mContext, newListViewActivity.class);
        Bundle bd = new Bundle();
        bd.putSerializable("content", mContent);
        it.putExtra("bd", bd);
        mContext.startActivity(it);
    }

    public static void OpenGallery(Context mContext, PageInfo2.PageDataBean mContent) {
        MyLog.v("打开新闻页面" + mContent.getInterfaceX());
        Intent it = new Intent(mContext, newListViewActivity2.class);
        Bundle bd = new Bundle();
        bd.putSerializable("content", mContent);
        it.putExtra("bd", bd);
        mContext.startActivity(it);
    }

    public static void OpenApkWithActivity(Context mContext, String packname, String Activity) {
        if (checkPackInfo(mContext, packname)) {
            MyLog.v("应用安装了");
        } else {
            Toast.makeText(mContext, "没有发现该应用，请先下载安装", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(Intent.ACTION_MAIN);
            /**知道要跳转应用的包命与目标Activity*/
        ComponentName componentName = null;
        if (!TextUtils.isEmpty(Activity))
            componentName = new ComponentName(packname, Activity);
        else {
            MyLog.v("跳转出错参数不对");
            return;
        }

        intent.setComponent(componentName);
        intent.putExtra("", "");//这里Intent传值
        mContext.startActivity(intent);
    }

    public static void OpenApk(Context mContext, String packname) {
        if (checkPackInfo(mContext, packname)) {
            MyLog.v("应用安装了");
        } else {
            Toast.makeText(mContext, "没有发现该应用，请先下载安装", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(packname);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    public static void OpenApkforUrl(Context mContext, String url) {
        Intent intent = new Intent();
        intent.setData(Uri.parse(url));
        intent.putExtra("", "");//这里Intent当然也可传递参数,但是一般情况下都会放到上面的URL中进行传递
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    public static void OpenWeb(Context mContext, String weburl) {
        Intent intent = new Intent(mContext, WebActivity.class);
        MyLog.v("打开网友" + weburl);
        intent.putExtra("url", weburl);//这里Intent当然也可传递参数,但是一般情况下都会放到上面的URL中进行传递
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    public static void OpenVideoPlayer(Context mContext, String url) {
        Intent intent = new Intent(mContext, IjkPlayerActivity.class);
        MyLog.v("视频地址" + url);
        intent.putExtra("url", url);//这里Intent当然也可传递参数,但是一般情况下都会放到上面的URL中进行传递
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    private static boolean checkPackInfo(Context mContext, String packname) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = mContext.getPackageManager().getPackageInfo(packname, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }
}
