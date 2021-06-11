package com.shadt.iptv_qx.Fragment;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.open.androidtvwidget.view.ReflectItemView;
import com.shadt.iptv_qx.Application.BaseApplication;
import com.shadt.iptv_qx.MainActivity;
import com.shadt.iptv_qx.R;
import com.open.androidtvwidget.view.FrameMainLayout;
import com.shadt.iptv_qx.model.PageInfo;
import com.shadt.iptv_qx.model.PageInfo2;
import com.shadt.iptv_qx.utils.Create_views;
import com.shadt.iptv_qx.utils.JsonUtils;
import com.shadt.iptv_qx.utils.JumpUtils;
import com.shadt.iptv_qx.utils.MyLog;
import com.shadt.iptv_qx.utils.Url;
import com.shadt.iptv_qx.widget.media.IjkVideoView;
import com.wang.avi.AVLoadingIndicatorView;
import java.util.HashMap;
import java.util.Map;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class MainFragment extends LazyLoadFragment2 {
    public static MainFragment newInstance(int pageType, String posturl) {
        MainFragment f = new MainFragment();
        Bundle b = new Bundle();
        b.putInt("pageType", pageType);
        b.putString("posturl", posturl);
        f.setArguments(b);
        return f;
    }


    Context mContext;

    FrameMainLayout main_lay11;
    View first_view;


    private AVLoadingIndicatorView AVload;

    boolean haslogo = true, hastime = true;



    private void loadData() {
        mContext = getActivity();
        MyLog.v(posturl + "创建页面" + pageType);
        main_lay11 = (FrameMainLayout) view.findViewById(R.id.page1_main_lay);
        AVload = view.findViewById(R.id.mAVload);
        AVload.setVisibility(View.VISIBLE);
        get_newlist(Url.getVisualInfo + posturl);
    }

    Map<String, Object> mMap = new HashMap<>();

    public void initview() {

        main_lay11.setVisibility(View.VISIBLE);
        AVload.setVisibility(View.GONE);
        if (mPageInfo2 == null) {
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
                    //目前还是图片
                    if (hasfirstview == false) {
                        hasfirstview = true;
                        initvideo();
//                        mIjkVideoView =  (IjkVideoView) Create_views.create_main_video(mContext,main_lay11, pageType, i, mPageInfo2.getPageData().get(i));
                        first_view = mIjkVideoView;
                    } else {
//                        mIjkVideoView =  (IjkVideoView) Create_views.create_main_video(mContext,main_lay11, pageType, i, mPageInfo2.getPageData().get(i));
                        initvideo();
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
                }
            }


        }
        handler.sendEmptyMessage(REUSET_FOUCSE);
    }

    private int INITVIDEO = 111;
    private int REUSET_FOUCSE = 222;
    private int PALYVIDEO = 333;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                initview();
            } else if (msg.what == REUSET_FOUCSE) {
                if (MainActivity.is_viewpager_scrool == true) {
                    if (first_view != null) {
                        first_view.requestFocus();
                    }
                    MainActivity.is_viewpager_scrool = false;
                    MyLog.v("是是不是应该获取焦点啊");
                }

            } else if (msg.what == INITVIDEO) {
                if (hasvideo == true) {


                    if (mIjkVideoView != null) {

                        if (mIjkVideoView.isPlaying()) {

                        } else {
                            player(mPageInfo2.getPageData().get(videoIndex).getVideoSrc());
                        }
                    }
                }
            }else if(msg.what==PALYVIDEO){
                mIjkVideoView.start();
            }
        }
    };
    int pageType = 0;
    String posturl = "";


    private View view;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.test_page1, null);
        return view;
    }

    boolean isfirsttime = true;

    @Override
    protected void onFirst() {
        super.onFirst();

        assert getArguments() != null;
        pageType = getArguments().getInt("pageType");
        posturl = getArguments().getString("posturl");
        MyLog.v("第一次加载啊" + pageType);
        loadData();
        isfirsttime = true;
    }

    @Override
    protected void onInVisible() {
        super.onInVisible();
        MyLog.v("不可见的时候" + pageType);
        isfirsttime = false;
        if (hasvideo == true) {
            duration=mIjkVideoView.getCurrentPosition();
            handler.removeMessages(INITVIDEO);
            handler.removeMessages(PALYVIDEO);
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
    public void onDestroy() {
        super.onDestroy();
        if (mIjkVideoView != null) {
            mIjkVideoView.release(true);
        }
    }
    @Override
    protected void onVisible() {
        super.onVisible();
        MyLog.v("可见的时候播放啊");
        if (hasvideo){
            ll_loading.setVisibility(View.VISIBLE);
            handler.sendEmptyMessageDelayed(INITVIDEO, 3000);
        }

    }
    private int INIT_DATA = 1;
    PageInfo2 mPageInfo2;

    public void get_newlist(String posturl) {

        MyLog.v("请求接口" + posturl);
        RequestParams params = new RequestParams();
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, posturl, params, new RequestCallBack<String>() {

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onSuccess(ResponseInfo<String> arg0) {
                // TODO Auto-generated method stub
                String str = arg0.result;
                PageInfo mInfo = JsonUtils.getModel(str, PageInfo.class);
                if (mInfo.getCode() == 200) {
                    mPageInfo2 = JsonUtils.getModel(mInfo.getData().getIptvChannelJson(), PageInfo2.class);
                    handler.sendEmptyMessageDelayed(INIT_DATA, 500);
                }

            }
        });
    }

    public float proportion = BaseApplication.proportion;
    private RelativeLayout ll_loading;
    public boolean hasvideo = false;
    public boolean hasfirstview = false;
    private IjkVideoView mIjkVideoView;
    private int duration=0;
    private int videoIndex = -1; //视频控件索引
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
    public void player(String url){
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

                if (duration!=0){
                    mIjkVideoView.seekTo(duration);
                }
                handler.sendEmptyMessageDelayed(PALYVIDEO, 2000);
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
}

