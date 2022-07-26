package com.shadt.iptv_qx;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalFocusChangeListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.bridge.OpenEffectBridge;
import com.open.androidtvwidget.view.FrameMainLayout;
import com.open.androidtvwidget.view.MainUpView;
import com.open.androidtvwidget.view.ReflectItemView;
import com.open.androidtvwidget.view.SmoothHorizontalScrollView;
import com.shadt.iptv_qx.Application.BaseApplication;
import com.shadt.iptv_qx.model.PageInfo;
import com.shadt.iptv_qx.model.PageInfo2;
import com.shadt.iptv_qx.model.PubContent;
import com.shadt.iptv_qx.utils.Create_views;
import com.shadt.iptv_qx.utils.JsonUtils;
import com.shadt.iptv_qx.utils.JumpUtils;
import com.shadt.iptv_qx.utils.MyLog;
import com.shadt.iptv_qx.utils.Url;
import com.shadt.iptv_qx.widget.CustomDialog;
import com.shadt.iptv_qx.widget.media.IjkVideoView;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * ViewPager demo：
 * 注意标题栏和viewpager的焦点控制.(在XML布局中控制了, ids)
 * 移动边框的问题也需要注意.
 *
 * @author hailongqiu
 */
public class MainActivity_Visual extends BaseActivity {


    // 移动边框.
    MainUpView mainUpView1;
    OpenEffectBridge mOpenEffectBridge;
    Context mContext = MainActivity_Visual.this;


    AVLoadingIndicatorView AVload;


    List<PubContent> arrPubContent = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_visual);
        //

        arrPubContent = (List<PubContent>) getIntent().getSerializableExtra("data");
        int index = getIntent().getIntExtra("index", 0);


        AVload = findViewById(R.id.AVload);
        AVload.setIndicator("BallSpinFadeLoaderIndicator");


//            new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(5000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    View rootview = MainActivity_Visual.this.getWindow().getDecorView();
//                    View focusView = rootview.findFocus();
//                    Log.i(BaseApplication.TAG, focusView == null ? "当前无焦点" : focusView.toString());
//                }
//            }
//        }).start();

        MyLog.v("请求接口" + Url.getVisualInfo + arrPubContent.get(index).getNavData().get(0).getChannelid());
        if (arrPubContent.get(index).getNavData().size() > 0) {
            get_newlist(Url.getVisualInfo + arrPubContent.get(index).getNavData().get(0).getChannelid());
        } else {
            MyLog.v("idweikong");
        }

    }

    CustomDialog.Builder builder = new CustomDialog.Builder(this);

    public void exit() {

        builder.setPositiveButton("再看一会",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        // 设置你的操作事项
                    }
                });
        builder.setNegativeButton("退出",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        // 设置你的操作事项
                        finish();
                    }
                });
        builder.create().show();
    }

    public void init_view() {

        SmoothHorizontalScrollView hscroll_view = (SmoothHorizontalScrollView) findViewById(R.id.test_hscroll);
        hscroll_view.setFadingEdge((int) getDimension(R.dimen.w_100)); // 滚动窗口也需要适配.

        vp_main_lay = findViewById(R.id.vp_main_lay);
        mainUpView1 = (MainUpView) findViewById(R.id.mainUpView1);

        mOpenEffectBridge = (OpenEffectBridge) mainUpView1.getEffectBridge();


        main_lay11 = (FrameMainLayout) findViewById(R.id.page1_main_lay);
        main_lay11.getViewTreeObserver().addOnGlobalFocusChangeListener(new OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(final View oldFocus, final View newFocus) {
                if (newFocus != null) {
                    newFocus.bringToFront(); // 防止放大的view被压在下面. (建议使用MainLayout)

                    float scale = 1.0f;

                    if (newFocus.getTag() != null) {
                        if ((int) newFocus.getTag() == 1) {
                            scale = 1f;
                            mainUpView1.setFocusView(newFocus, mOldFocus, scale);
                            mOldFocus = newFocus; // 4.3以下需要自己保存.
                        } else {
                            mainUpView1.setFocusView(newFocus, mOldFocus, scale);
                            mOldFocus = newFocus; // 4.3以下需要自己保存.
                        }
                    }
                }
            }
        });
        switchNoDrawBridgeVersion();
    }

    RelativeLayout vp_main_lay;
    View mOldFocus; // 4.3以下版本需要自己保存.
    View first_view = null;
    FrameMainLayout main_lay11;
    int pageType = 0;
    boolean haslogo = false, hastime = false, hasback = false;

    private void initAllTitleBar() {

        init_view();


        for (int i = 0; i < arrPubContent.size(); i++) {
            if (arrPubContent.get(i).getType().equals("time")) {
                if (!TextUtils.isEmpty("" + arrPubContent.get(i).getWidth()) || !TextUtils.isEmpty("" + arrPubContent.get(i).getHeight()) ||
                        !TextUtils.isEmpty("" + arrPubContent.get(i).getAbscissa()) || !TextUtils.isEmpty("" + arrPubContent.get(i).getOrdinate())) {
                    hastime = true;
                    Create_views.create_time(mContext, vp_main_lay, arrPubContent.get(i));

                } else {
                    Toast.makeText(mContext, "时间控件数据出现问题", Toast.LENGTH_SHORT).show();
                }
            } else if (arrPubContent.get(i).getType().equals("logo")) {
                if (!TextUtils.isEmpty("" + arrPubContent.get(i).getWidth()) || !TextUtils.isEmpty("" + arrPubContent.get(i).getHeight()) ||
                        !TextUtils.isEmpty("" + arrPubContent.get(i).getAbscissa()) || !TextUtils.isEmpty("" + arrPubContent.get(i).getOrdinate())) {

                    haslogo = true;
                    Create_views.create_logo(mContext, vp_main_lay, arrPubContent.get(i));
                } else {
                    Toast.makeText(mContext, "图标控件数据出现问题", Toast.LENGTH_SHORT).show();
                }
            } else if (arrPubContent.get(i).getType().equals("notice")) {
                if (!TextUtils.isEmpty("" + arrPubContent.get(i).getWidth()) || !TextUtils.isEmpty("" + arrPubContent.get(i).getHeight()) ||
                        !TextUtils.isEmpty("" + arrPubContent.get(i).getAbscissa()) || !TextUtils.isEmpty("" + arrPubContent.get(i).getOrdinate())) {


                    Create_views.create_scroll_text(mContext, vp_main_lay, arrPubContent.get(i));

                } else {
                    Toast.makeText(mContext, "通知控件数据出现问题", Toast.LENGTH_SHORT).show();
                }
            } else if (arrPubContent.get(i).getType().equals("back")) {
                hasback = true;
                create_back(mContext, vp_main_lay, arrPubContent.get(i));
            }
        }


        String posturl = "http://fjyx.chinashadt.com//Interface/Recodrd/listRecod1.do?key=YXXWWYXNEWS&pageNum=2&getSize=10";


        if (mPageInfo2.getPageData() == null) {
            return;
        }
        if (mPageInfo2.getPageData().size() != 0) {
            for (int i = 0; i < mPageInfo2.getPageData().size(); i++) {
                if (mPageInfo2.getPageData().get(i).getType().equals("image")) {
                    if (hasfirstview == false) {
                        hasfirstview = true;
                        first_view = Create_views.create_main_Img(mContext, main_lay11, pageType, i, mPageInfo2.getPageData().get(i));
                    } else {
                        Create_views.create_main_Img(mContext, main_lay11, pageType, i, mPageInfo2.getPageData().get(i));
                    }
                } else if (mPageInfo2.getPageData().get(i).getType().equals("list")) {
                    if (hasfirstview == false) {
                        hasfirstview = true;
                        first_view = Create_views.create_main_news2(mContext, main_lay11, pageType, i, mPageInfo2.getPageData().get(i));
                    } else {
                        Create_views.create_main_news2(mContext, main_lay11, pageType, i, mPageInfo2.getPageData().get(i));
                    }
                } else if (mPageInfo2.getPageData().get(i).getType().equals("swipe")) {
                    if (hasfirstview == false) {
                        hasfirstview = true;
                        first_view = Create_views.create_main_banner(mContext, main_lay11, pageType, i, mPageInfo2.getPageData().get(i));
                    } else {
                        Create_views.create_main_banner(mContext, main_lay11, pageType, i, mPageInfo2.getPageData().get(i));
                    }
                } else if (mPageInfo2.getPageData().get(i).getType().equals("video")) {
                     videoIndex = i;
                    initvideo();
                    if (hasfirstview == false) {
                        hasfirstview = true;

                        first_view = mIjkVideoView;
                    }
                    hasvideo = true;

                } else if (mPageInfo2.getPageData().get(i).getType().equals("time")) {
                    if (!hastime) {
                        Create_views.create_time2(mContext, main_lay11, mPageInfo2.getPageData().get(i));
                    }
                } else if (mPageInfo2.getPageData().get(i).getType().equals("scrolltext")) {
                    Create_views.create_scroll_text2(mContext, main_lay11, mPageInfo2.getPageData().get(i));
                } else if (mPageInfo2.getPageData().get(i).getType().equals("logo")) {
                    if (!haslogo) {
                        Create_views.create_logo2(mContext, main_lay11, mPageInfo2.getPageData().get(i));
                    }
                } else if (mPageInfo2.getPageData().get(i).getType().equals("text")) {
                    Create_views.create_Text(mContext, main_lay11, mPageInfo2.getPageData().get(i));
                } else if (arrPubContent.get(i).getType().equals("back")) {
                    if (hasback == false) {
                        create_back(mContext, vp_main_lay, arrPubContent.get(i));
                    }
                }

            }
        }
        mhandler.sendEmptyMessage(REUSET_FOUCSE);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (hasvideo) {
            ll_loading.setVisibility(View.VISIBLE);
            mhandler.sendEmptyMessageDelayed(INITVIDEO, 3000);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        MyLog.v("onpause>>>>>>>>>>>>>>>>>>>>");
        if (hasvideo == true) {
            duration = mIjkVideoView.getCurrentPosition();
            mhandler.removeMessages(INITVIDEO);
            mhandler.removeMessages(PALYVIDEO);
            if (mIjkVideoView == null) {
                return;
            }
            if (mIjkVideoView.isPlaying()) {
                mIjkVideoView.stopPlayback();
//                mIjkVideoView.release(true);

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (hasvideo == true) {
            mIjkVideoView.release(true);


        }
    }

    private int INITVIDEO = 111;
    private int PALYVIDEO = 0;
    private int INIT_DATA = 1;
    private int REUSET_FOUCSE = 2;
    Handler mhandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == INIT_DATA) {
                initAllTitleBar();
            } else if (msg.what == REUSET_FOUCSE) {
                AVload.hide();

                first_view.requestFocus();
            } else if (msg.what == INITVIDEO) {
                if (hasvideo == true) {


                    if (mIjkVideoView != null) {

                        if (mIjkVideoView.isPlaying()) {

                        } else {
                            player(mPageInfo2.getPageData().get(videoIndex).getVideoSrc());
                        }
                    }
                }
            } else if (msg.what == PALYVIDEO) {
                mIjkVideoView.start();
            }
        }
    };


    private float getDimension(int id) {
        return getResources().getDimension(id);
    }

    private void switchNoDrawBridgeVersion() {
        float density = getResources().getDisplayMetrics().density;

        final EffectNoDrawBridge effectNoDrawBridge = new EffectNoDrawBridge();


        RectF rectf = null;
        if (BaseApplication.proportion == 1) {

            rectf = new RectF(getDimension(R.dimen.w_10) * density, getDimension(R.dimen.h_10) * density,
                    getDimension(R.dimen.w_10) * density, getDimension(R.dimen.h_10) * density);
        } else {

            rectf = new RectF(getDimension(R.dimen.w_20) * density, getDimension(R.dimen.h_20) * density,
                    getDimension(R.dimen.w_20) * density, getDimension(R.dimen.h_20) * density);
        }
        effectNoDrawBridge.setTranDurAnimTime(200);
//        effectNoDrawBridge.setDrawUpRectPadding(rectf);
        mainUpView1.setEffectBridge(effectNoDrawBridge); // 4.3以下版本边框移动.

        mainUpView1.setUpRectResource(R.drawable.white_light_10); // 设置移动边框的图片.
        mainUpView1.setDrawUpRectPadding(rectf); // 边框图片设置间距.
    }


    PageInfo2 mPageInfo2;
//    String url = "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1598251131&di=54b500f8798f80fb2f176b24834171b2&src=http://a3.att.hudong.com/14/75/01300000164186121366756803686.jpg";

    public void get_newlist(String posturl) {

        Log.v("wangliang", "请求接口" + posturl);
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
                PageInfo mInfo = JsonUtils.getModel(str, PageInfo.class);
                MyLog.v("content" + mInfo.getData().getIptvChannelJson());

                if (mInfo.getCode() == 200) {
                    mPageInfo2 = JsonUtils.getModel(mInfo.getData().getIptvChannelJson(), PageInfo2.class);
                    MyLog.v(mPageInfo2.getPageData().size() + "");
                    mhandler.sendEmptyMessageDelayed(INIT_DATA, 1000);
                }

                //                //相当于定时器，每隔2s执行一次该线程

            }
        });
    }


    private RelativeLayout ll_loading;
    public float proportion = BaseApplication.proportion;
    public boolean hasvideo = false;
    public boolean hasfirstview = false;
    private int videoIndex = -1; //视频控件索引
    private IjkVideoView mIjkVideoView;
    private int duration = 0;

    public void initvideo() {
        final PageInfo2.PageDataBean mData = mPageInfo2.getPageData().get(videoIndex);
        float w = mData.getWidth(), h = mData.getHeight(), t = mData.getOrdinate(), l = mData.getAbscissa();

        w = (int) (w / proportion);
        h = (int) (h / proportion);
        t = (int) (t / proportion);
        l = (int) (l / proportion);
        final ReflectItemView reflectItemView = new ReflectItemView(mContext);
        reflectItemView.setReflection(false);
        reflectItemView.setId(videoIndex);
        FrameLayout.LayoutParams fl = new FrameLayout.LayoutParams((int) w, (int) h);
        fl.setMargins((int) l, (int) t, 0, 0);
        reflectItemView.setFocusable(true);

        //里面FraFrameLayout
        FrameLayout mFralayout = new FrameLayout(mContext);
        FrameLayout.LayoutParams fl_f = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        //图片
//        VideoView mVideoView = new VideoView(mContext);


        RelativeLayout.LayoutParams lv = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);


        View view = LayoutInflater.from(mContext).inflate(R.layout.item_video, null);


        mIjkVideoView = view.findViewById(R.id.live_videoView);
        ll_loading = view.findViewById(R.id.ll_loading);

        player(mData.getVideoSrc());
        mFralayout.addView(view, lv);

        reflectItemView.setTag(0);
        reflectItemView.addView(mFralayout, fl_f);
        main_lay11.addView(reflectItemView, fl);

        reflectItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JumpUtils.Jump(mContext, mData, mData.getTargetType());
            }
        });
    }

    public void player(String url) {
        mIjkVideoView.setNeedPlay(true);

        mIjkVideoView.setOnCompletionListener(new IMediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(IMediaPlayer mediaPlayer) {
                mIjkVideoView.seekTo(0);

            }
        });

        mIjkVideoView.setOnErrorListener(new IMediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(IMediaPlayer mediaPlayer, int i, int i1) {
                Toast.makeText(mContext, "IPTV播放出错", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        mIjkVideoView.setVideoPath(url);

        mIjkVideoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer mediaPlayer) {
                ll_loading.setVisibility(View.GONE);

                if (duration != 0) {
                    mIjkVideoView.seekTo(duration);
                }
                mhandler.sendEmptyMessageDelayed(PALYVIDEO, 1000);
            }
        });

        //监听视频是否有卡顿,只有在Android4.2版本以上才有
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            mIjkVideoView.setOnInfoListener(new IMediaPlayer.OnInfoListener() {
                @Override
                public boolean onInfo(IMediaPlayer mediaPlayer, int what, int extra) {
                    switch (what) {
                        //出现卡顿,或拖动进度条引起的卡顿,进入此case
                        case MediaPlayer.MEDIA_INFO_BUFFERING_START:

                            ll_loading.setVisibility(View.VISIBLE);
                            break;
                        //卡顿结束后,进入此case
                        case MediaPlayer.MEDIA_INFO_BUFFERING_END:

                            ll_loading.setVisibility(View.GONE);
                    }
                    return true;
                }

            });
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {


            case KeyEvent.KEYCODE_BACK:
                exit();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void create_back(final Context mContext, RelativeLayout page1_main_lay, PubContent mData) {
        //view_position   表示 图片位置，title_position在第几个栏目下
        //最外层

        float w = mData.getWidth(), h = mData.getHeight(), t = mData.getOrdinate(), l = mData.getAbscissa(), proportion = BaseApplication.proportion;
        w = w / proportion;
        h = h / proportion;
        t = t / proportion;
        l = l / proportion;

        RelativeLayout.LayoutParams fl = new RelativeLayout.LayoutParams((int) w, (int) h);
        fl.setMargins((int) l, (int) t, 0, 0);

        ImageView iv = new ImageView(mContext);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);

        iv.setFocusable(false);
        iv.setFocusableInTouchMode(false);

        Glide.with(mContext).load(mData.getImgSrc().startsWith("http") == true ? mData.getImgSrc() : Url.Img_name + mData.getImgSrc()).into((ImageView) iv);

        page1_main_lay.addView(iv, fl);


        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exit();
            }
        });
    }
}
