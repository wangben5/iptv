package com.shadt.iptv_qx;


import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.shadt.iptv_qx.Application.BaseApplication;
import com.shadt.iptv_qx.model.Configs;
import com.shadt.iptv_qx.model.MainInfo2;
import com.shadt.iptv_qx.model.PubContent;
import com.shadt.iptv_qx.utils.FontSize;
import com.shadt.iptv_qx.utils.JsonUtils;
import com.shadt.iptv_qx.utils.MyLog;
import com.shadt.iptv_qx.utils.SharedPreferences;
import com.shadt.iptv_qx.utils.Url;
import com.wang.avi.AVLoadingIndicatorView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class WelcomeActivity extends BaseActivity {


    Context mContext = WelcomeActivity.this;
    AVLoadingIndicatorView AVload;
    ImageView iv_bg;
    SharedPreferences mshare = new SharedPreferences();
    public static RequestOptions requestOptions = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
//    String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606193022949&di=0bb13b06ab199ae48f1b1367a1673937&imgtype=0&src=http%3A%2F%2Fp0.qhmsg.com%2Ft0148c9cc1ab45fa390.jpg";
    String iv_bg_url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vagetable_activity);

        iv_bg = findViewById(R.id.iv_bg);

      String wel=  mshare.getwelcombg(mContext);
        if (TextUtils.isEmpty(wel)) {

        } else {
            MyLog.v("用缓存图片");
            Glide.with(mContext)
                    .load(wel)
                    .preload();
            Glide.with(mContext).load(wel).apply(requestOptions).transition(withCrossFade().crossFade()).into(iv_bg);
        }
        AVload = findViewById(R.id.AVload);
        AVload.setIndicator("BallSpinFadeLoaderIndicator");
        getWifiMac(mContext);

        get_newlist(Url.getBaseInfo + "aaa");
        MyLog.v(">"+Url.getBaseInfo+ "aaa");

    }

    int INTENT = 0;
    int INITVIEW = 1;
    Handler mhandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == INTENT) {
                Intent it;
                AVload.hide();
                if (navigation_postion != -1 && arrPubContent.get(navigation_postion).getNavData().size() > 1) {
                    it = new Intent(mContext, MainActivity.class);
                } else {
                    it = new Intent(mContext, MainActivity_Visual.class);
                    it.putExtra("index", navigation_postion);
                }
                it.putExtra("bg", iv_bg_url);
                it.putExtra("data", (Serializable) arrPubContent);
                startActivity(it);
                finish();
            } else if (msg.what == INITVIEW) {
                initview();
            }
        }
    };

    private void initview() {

        mhandler.sendEmptyMessageDelayed(INTENT, 1000);
    }


    PubContent mPubContent = new PubContent();
    Configs mConfigs = new Configs();
    List<PubContent> arrPubContent = new ArrayList<>();
    public int navigation_postion = -1;

    public void get_newlist(String posturl) {


        RequestParams params = new RequestParams();

        HttpUtils httpUtils = new HttpUtils();

        httpUtils.send(HttpRequest.HttpMethod.GET, posturl, params, new RequestCallBack<String>() {

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                // TODO Auto-generated method stub
                MyLog.v("获取失败");
            }

            @Override
            public void onSuccess(ResponseInfo<String> arg0) {
                // TODO Auto-generated method stub
                String str = arg0.result;

                MainInfo2 mainInfo2 = JsonUtils.getModel(str, MainInfo2.class);
                MyLog.v("获取成功" + str);
                if (mainInfo2.getCode() == 200) {

                    arrPubContent = JsonUtils.getListModel(mainInfo2.getData().getUnits(), PubContent.class);
                    for (int i = 0; i < arrPubContent.size(); i++) {
                        if (arrPubContent.get(i).getType().equals("navigation")) {
                            navigation_postion = i;
                        }
                    }
                    MyLog.v("arrPubContent" + arrPubContent.size());
                    mConfigs = JsonUtils.getModel(mainInfo2.getData().getConfig(), Configs.class);
                    if (mConfigs.getBackground().getValue() != null) {
                        iv_bg_url = mConfigs.getBackground().getValue();
                        if (!mshare.getwelcombg(mContext).equals(iv_bg_url)) {
                            Glide.with(mContext)
                                    .load(mConfigs.getBackground().getValue())
                                    .preload();
                            mshare.writewelcombg(mContext, mConfigs.getBackground().getValue());
                            Glide.with(mContext).load(iv_bg_url).apply(requestOptions).transition(withCrossFade()).into(iv_bg);
                            mhandler.sendEmptyMessageDelayed(INITVIEW, 2000);
                            return;
                        } else {

                        }
                    }
                    MyLog.v(mConfigs.getBackground().getValue());
                    mhandler.sendEmptyMessageDelayed(INITVIEW, 1000);
                } else {
                    Toast.makeText(mContext, "数据有误", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }


    public static String getWifiMac(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String mac = info.getMacAddress();
        Log.e(BaseApplication.TAG, "wifi mac : " + mac);
        return mac;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:

                break;
        }
        return super.onKeyDown(keyCode, event);
    }

}
