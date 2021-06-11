package com.shadt.iptv_qx.utils;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.shadt.iptv_qx.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.open.androidtvwidget.view.FrameMainLayout;
import com.open.androidtvwidget.view.ReflectItemView;
import com.shadt.iptv_qx.Application.BaseApplication;
import com.shadt.iptv_qx.adapter.ListAdapter_CreatView;
import com.shadt.iptv_qx.adapter.ListwithTextAdapter;
import com.shadt.iptv_qx.adapter.RecycBannerAdapter;
import com.shadt.iptv_qx.adapter.RecycNewsAdapter;
import com.shadt.iptv_qx.model.PageInfo2;
import com.shadt.iptv_qx.model.PubContent;
import com.shadt.iptv_qx.model.newlistInfo;
import com.shadt.iptv_qx.widget.ScrollTextView;
import com.shadt.iptv_qx.widget.media.IjkVideoView;
import java.util.ArrayList;
import java.util.List;
import tv.danmaku.ijk.media.player.IMediaPlayer;


public class Create_views {
    public static float proportion = BaseApplication.proportion;


    public static View create_main_video(final Context mContext, FrameMainLayout page1_main_lay, final int title_position, final int view_position, final PageInfo2.PageDataBean mData) {
        //view_position   表示 图片位置，title_position在第几个栏目下
        //最外层
        float w = mData.getWidth(), h = mData.getHeight(), t = mData.getOrdinate(), l = mData.getAbscissa();


        w = (int) (w / proportion);
        h = (int) (h / proportion);
        t = (int) (t / proportion);
        l = (int) (l / proportion);
        final ReflectItemView reflectItemView = new ReflectItemView(mContext);
        reflectItemView.setReflection(false);
        reflectItemView.setId(view_position);
//        reflectItemView.setId(Integer.parseInt(title_position+""+view_position));
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
//        final RelativeLayout rl=view.findViewById(R.id.Rl_tv);
//        final TextView tv=view.findViewById(R.id.tv);
//        tv.setText(mData.getText());
//        reflectItemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if (b){
//                    rl.setVisibility(View.VISIBLE);
//                }else{
//                    rl.setVisibility(View.GONE);
//                }
//            }
//        });

        final IjkVideoView videoView = view.findViewById(R.id.live_videoView);
        final RelativeLayout ll_loading = view.findViewById(R.id.ll_loading);
        videoView.setNeedPlay(true);

        videoView.setOnCompletionListener(new IMediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(IMediaPlayer mediaPlayer) {
                videoView.seekTo(0);
                videoView.start();
            }
        });

        videoView.setOnErrorListener(new IMediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(IMediaPlayer mediaPlayer, int i, int i1) {
                Toast.makeText(mContext, "IPTV播放出错", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        String url = "";
        Log.e("TAG", "playUrl----" + url);
        videoView.setVideoPath(mData.getVideoSrc());

        videoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer mediaPlayer) {
                ll_loading.setVisibility(View.GONE);
            }
        });

        //监听视频是否有卡顿,只有在Android4.2版本以上才有
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            videoView.setOnInfoListener(new IMediaPlayer.OnInfoListener() {
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

        mFralayout.addView(view, lv);

        reflectItemView.setTag(0);
        reflectItemView.addView(mFralayout, fl_f);
        page1_main_lay.addView(reflectItemView, fl);
        nextupid(reflectItemView, title_position, true);


        reflectItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JumpUtils.Jump(mContext, mData, mData.getTargetType());
            }
        });

        return videoView;
    }


    public static View create_main_news2(final Context mContext, FrameMainLayout page1_main_lay, final int title_position, final int view_position, final PageInfo2.PageDataBean mData) {
        //style   1表示左图又list     0表示只有list    tag==0  表示选中不会放大   1=放大
        float w = mData.getWidth(), h = mData.getHeight(), t = mData.getOrdinate(), l = mData.getAbscissa();

        String mould = mData.getMould();

        w = w / proportion;
        h = h / proportion;
        t = t / proportion;
        l = l / proportion;
        //最外层
        ReflectItemView reflectItemView = new ReflectItemView(mContext);
        reflectItemView.setReflection(false);
        reflectItemView.setId(view_position);
        FrameLayout.LayoutParams fl = new FrameLayout.LayoutParams((int) w, (int) h);
        fl.setMargins((int) l, (int) t, 0, 0);
        reflectItemView.setFocusable(true);

        LinearLayout mline = new LinearLayout(mContext);
        LinearLayout.LayoutParams line = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mline.setOrientation(LinearLayout.VERTICAL);

        LinearLayout ml = new LinearLayout(mContext);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mline.setOrientation(LinearLayout.HORIZONTAL);
        View view = null;
//        mould = "listSmImgL";

        MyLog.v("mould"+mould);
        if (TextUtils.isEmpty(mould)) {
            mould = "listSmImgL";
        } else {

        }
        if (mould.equals("listLgImgL")) {
            view = LayoutInflater.from(mContext).inflate(R.layout.view_listview_img, null);
            MyLog.v("新闻左大图右边list");
            if (mData.getListData() != null) {
                get_newlist3(mContext, view, mData, 0);
            } else {
                get_newlist3(mContext, view, mData, 1);
            }

        } else if (mould.equals("listSmImgL")) {
            MyLog.v("新闻list");
            view = LayoutInflater.from(mContext).inflate(R.layout.view_list, null);
            RelativeLayout rl_titleBar = view.findViewById(R.id.rl_titleBar);
            TextView tv = view.findViewById(R.id.title_news);
            if (mData.getText() != null) {
                rl_titleBar.setVisibility(View.VISIBLE);
            } else {
                rl_titleBar.setVisibility(View.GONE);
            }
            tv.setText(mData.getText());
            mline.setBackgroundColor(Util.setbgcolor(mData.getBackgroundColor()));
            if (mData.getListData() != null) {
                get_newlist2(mContext, view, mData, true);
            } else {
                get_newlist2(mContext, view, mData, false);
            }
        }
        ml.addView(view, lp);
        mline.addView(ml, line);
        reflectItemView.addView(mline, line);
        reflectItemView.setTag(0);
        page1_main_lay.addView(reflectItemView, fl);

        reflectItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumpUtils.Jump(mContext, mData, mData.getTargetType());
            }
        });
        return reflectItemView;
    }

    public static Runnable runnable;
    public static Handler mHandler = new Handler();

    public static boolean idDestory(Activity mActivity) {
        if (mActivity == null || mActivity.isFinishing() || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && mActivity.isDestroyed())) {
            return true;
        } else {
            return false;
        }
    }

    public static View create_main_banner(final Context mContext, FrameMainLayout page1_main_lay, final int title_position, final int view_position, final PageInfo2.PageDataBean mData) {
        //view_position   表示 图片位置，title_position在第几个栏目下
        //最外层
        float w = mData.getWidth(), h = mData.getHeight(), t = mData.getOrdinate(), l = mData.getAbscissa();

        String mould = mData.getMould();

        w = w / proportion;
        h = h / proportion;
        t = t / proportion;
        l = l / proportion;
        ReflectItemView reflectItemView = new ReflectItemView(mContext);
        reflectItemView.setReflection(false);
        reflectItemView.setId(view_position);
        FrameLayout.LayoutParams fl = new FrameLayout.LayoutParams((int) w, (int) h);
        fl.setMargins((int) l, (int) t, 0, 0);
        reflectItemView.setFocusable(true);
        //里面FraFrameLayout
        FrameLayout mFralayout = new FrameLayout(mContext);
        mFralayout.setFocusable(false);
        reflectItemView.setFocusableInTouchMode(false);
        FrameLayout.LayoutParams fl_f = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);

        if (TextUtils.isEmpty(mould) || !mould.equals("swipeScrollUD") && !mould.equals("swipeScrollLR") && !mould.equals("swipeGradients")) {
            mould = "swipeScrollLR";
        }
        boolean local = false;
        // swipeGradients  渐变               swipeScroll   上下滚动
        if (mould.equals("swipeScrollLR") || mould.equals("swipeScrollUD")) {
            //swipeScrollUD 上下滑动    swipeScrollLR 左右
            View view = LayoutInflater.from(mContext).inflate(R.layout.banner_style0, null);
            if (mData.getListData() != null && mData.getListData().size() > 0) {
                local = true;

            }

            mFralayout.addView(view, fl_f);
            get_banner(mContext, view, mould, mData, local);
        } else if (mould.equals("swipeGradients")) {
            //图片
            View view = LayoutInflater.from(mContext).inflate(R.layout.banner_style1, null);
            if (mData.getListData() != null && mData.getListData().size() > 0) {
                local = true;
            }

            mFralayout.addView(view, fl_f);
            get_banner(mContext, view, mould, mData, local);
        }

        reflectItemView.setTag(0);
        reflectItemView.addView(mFralayout, fl_f);
        page1_main_lay.addView(reflectItemView, fl);


        //相当于定时器，每隔2s执行一次该线程


        reflectItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumpUtils.Jump(mContext, mData, mData.getTargetType());
            }
        });

        return reflectItemView;
    }

    public static void nextupid(View reflectItemView, int title_position, boolean is_top) {
//        if (is_top == true) {
//            if (title_position == 0) {
//                reflectItemView.setNextFocusUpId(R.id.title_bar1);
//            } else if (title_position == 1) {
//                reflectItemView.setNextFocusUpId(R.id.title_bar2);
//            } else if (title_position == 2) {
//                reflectItemView.setNextFocusUpId(R.id.title_bar3);
//            } else if (title_position == 3) {
//                reflectItemView.setNextFocusUpId(R.id.title_bar4);
//            } else if (title_position == 4) {
//                reflectItemView.setNextFocusUpId(R.id.title_bar5);
//            } else if (title_position == 5) {
//                reflectItemView.setNextFocusUpId(R.id.title_bar6);
//            } else if (title_position == 6) {
//                reflectItemView.setNextFocusUpId(R.id.title_bar7);
//            }
//        }
    }

    public static void myhandler() {
        //报错
        mHandler.removeCallbacks(runnable);
    }

    public static View create_main_Img(final Context mContext, FrameMainLayout page1_main_lay, final int title_position, final int view_position, final PageInfo2.PageDataBean mData) {
        //view_position   表示 图片位置，title_position在第几个栏目下
        //最外层

        float w = mData.getWidth(), h = mData.getHeight(), t = mData.getOrdinate(), l = mData.getAbscissa();
        String mould = mData.getTxtPose();


        w = w / proportion;
        h = h / proportion;
        t = t / proportion;
        l = l / proportion;


        final ReflectItemView reflectItemView = new ReflectItemView(mContext);
        reflectItemView.setReflection(false);
        reflectItemView.setId(view_position);

         reflectItemView.setFocusable(true);
        reflectItemView.setFocusableInTouchMode(true);
        FrameLayout mFralayout = new FrameLayout(mContext);


        if (TextUtils.isEmpty(mould)) {
            mould = "imgTxtIn";
        }
        if (mould.equals("imgTxtIn")) {
            //文字和图片底部对齐
            FrameLayout.LayoutParams fl = new FrameLayout.LayoutParams((int) w, (int) h);
            fl.setMargins((int) l, (int) t, 0, 0);
            FrameLayout.LayoutParams fl_f = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            //图片
            ImageView iv = new ImageView(mContext);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);

            Glide.with(mContext).load(mData.getImgSrc().startsWith("http") ? mData.getImgSrc() : Url.Img_name + mData.getImgSrc()).apply(BaseApplication.requestOptions).into((ImageView) iv);

            mFralayout.addView(iv, fl_f);

            if (mData.getText() == "") {

            } else {
                //文字底部
                TextView tv = new TextView(mContext);
                tv.setText(mData.getText());
                tv.setMarqueeRepeatLimit(Integer.MAX_VALUE);

                tv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                tv.setSingleLine();

                tv.setHorizontallyScrolling(true);
                FrameLayout.LayoutParams fl_tv;


                fl_tv = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,(int)(mData.getTxtHeight()/proportion));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,(FontSize.px2sp(mContext,(mData.getFontSize()/proportion))));
                tv.setBackgroundResource(R.drawable.title_bg);
                tv.setTextColor(Util.setfontcolor(mData.getColor()));
                tv.setGravity(Gravity.CENTER);
                fl_tv.gravity = Gravity.BOTTOM;
                mFralayout.addView(tv, fl_tv);
            }

            reflectItemView.addView(mFralayout, fl_f);
            page1_main_lay.addView(reflectItemView, fl);
        } else {
            //文字在图片下面
            FrameLayout.LayoutParams fl = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            fl.setMargins((int) l, (int) t, 0, 0);
            LinearLayout mly = new LinearLayout(mContext);

            mly.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams lllp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            LinearLayout.LayoutParams iv_lp = new LinearLayout.LayoutParams((int) w, (int) h);
            iv_lp.gravity = Gravity.CENTER;
//          FrameLayout.LayoutParams iv_lp=new FrameLayout.LayoutParams((int)w,(int)h);
            lllp.gravity = Gravity.CENTER;
            ImageView iv = new ImageView(mContext);
            iv.setFocusableInTouchMode(false);
            iv.setFocusable(false);
//            iv.setScaleType(ImageView.ScaleType.FIT_XY);
//          iv.setImageResource(R.drawable.mainview_tuijian);
//            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setLayoutParams(iv_lp);
            iv_lp.setMargins(0, 0, 0, 10);
            mly.addView(iv);
            //只是绘制左上角和右上角圆角
//            RequestOptions options = new RequestOptions().bitmapTransform(new RoundedCorners(30));//图片圆角为30

            Glide.with(mContext).load(mData.getImgSrc().startsWith("http") ? mData.getImgSrc() : Url.Img_name + mData.getImgSrc()).apply(BaseApplication.requestOptions).into((ImageView) iv);

            if (mData.getText() == "") {

            } else {
                TextView tv = new TextView(mContext);
                tv.setText(mData.getText());
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,(FontSize.px2sp(mContext,(mData.getFontSize()/proportion))));
                tv.setTextColor(Util.setfontcolor(mData.getColor()));
                tv.setGravity(Gravity.CENTER);
                mly.addView(tv, lllp);
            }

            reflectItemView.addView(mly, lllp);

            page1_main_lay.addView(reflectItemView, fl);

        }
        reflectItemView.setTag(1);

        reflectItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumpUtils.Jump(mContext, mData, mData.getTargetType());
            }
        });
        return reflectItemView;
    }


    public static View create_time(final Context mContext, RelativeLayout page1_main_lay, PubContent mData) {
        //view_position   表示 图片位置，title_position在第几个栏目下
        //最外层
        float w = mData.getWidth(), h = mData.getHeight(), t = mData.getOrdinate(), l = mData.getAbscissa();
        MyLog.v("数据" + mData.toString());
        w = w / proportion;
        h = h / proportion;
        t = t / proportion;
        l = l / proportion;

        RelativeLayout.LayoutParams fl = new RelativeLayout.LayoutParams((int) w, (int) h);
        fl.setMargins((int) l, (int) t, 0, 0);

        View view = LayoutInflater.from(mContext).inflate(R.layout.view_time, null);

        TextClock tv_date = view.findViewById(R.id.tv_date);
        tv_date.setFormat24Hour(mData.getMould());
        tv_date.setFormat12Hour(mData.getMould());
        if (mData.getAlign() != null) {
            if (mData.getAlign().equals("right")) {
                tv_date.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            } else if (mData.getAlign().equals("left")) {
                tv_date.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            } else {
                tv_date.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
        } else {
            tv_date.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }

        tv_date.setTextSize(TypedValue.COMPLEX_UNIT_SP,(FontSize.px2sp(mContext,(mData.getFontSize()/proportion))));
        tv_date.setTextColor(Util.setfontcolor(mData.getColor()));
        page1_main_lay.addView(view, fl);

        return page1_main_lay;
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static View create_time2(final Context mContext, FrameMainLayout page1_main_lay, final PageInfo2.PageDataBean mData) {
        //view_position   表示 图片位置，title_position在第几个栏目下
        //最外层
        float w = mData.getWidth(), h = mData.getHeight(), t = mData.getOrdinate(), l = mData.getAbscissa();

        w = w / proportion;
        h = h / proportion;
        t = t / proportion;
        l = l / proportion;

        FrameMainLayout.LayoutParams fl = new FrameMainLayout.LayoutParams((int) w, (int) h);
        fl.setMargins((int) l, (int) t, 0, 0);

        View view = LayoutInflater.from(mContext).inflate(R.layout.view_time, null);
        TextClock tv_date = view.findViewById(R.id.tv_date);
        tv_date.setFormat24Hour(mData.getMould());
        tv_date.setFormat12Hour(mData.getMould());
        if (mData.getAlign() != null) {
            if (mData.getAlign().equals("right")) {
                tv_date.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            } else if (mData.getAlign().equals("left")) {
                tv_date.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            } else {
                tv_date.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
        } else {
            tv_date.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }


        tv_date.setTextSize(TypedValue.COMPLEX_UNIT_SP,(FontSize.px2sp(mContext,(mData.getFontSize()/proportion))));

        page1_main_lay.addView(view, fl);

        tv_date.setTextColor(Util.setfontcolor(mData.getColor()));
        tv_date.setBackgroundColor(Util.setbgcolor(mData.getBackgroundColor()));
        return page1_main_lay;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static View create_Text(final Context mContext, FrameMainLayout page1_main_lay, final PageInfo2.PageDataBean mData) {
        //view_position   表示 图片位置，title_position在第几个栏目下
        float w = mData.getWidth(), h = mData.getHeight(), t = mData.getOrdinate(), l = mData.getAbscissa();

        w = w / proportion;
        h = h / proportion;
        t = t / proportion;
        l = l / proportion;

        FrameMainLayout.LayoutParams fl = new FrameMainLayout.LayoutParams((int) w, (int) h);
        fl.setMargins((int) l, (int) t, 0, 0);


        TextView tv = new TextView(mContext);
        tv.setText(mData.getText() != null ? mData.getText() : "");
        tv.setTextColor(Util.setfontcolor(mData.getColor()));


        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,(FontSize.px2sp(mContext,(mData.getFontSize()/proportion))));
        if (mData.getAlign() != null) {
            if (mData.getAlign().equals("flex-end")) {
                tv.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            } else if (mData.getAlign().equals("flex-start")) {
                tv.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            } else {
                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
        } else {
            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }
        tv.setGravity(Gravity.CENTER);


        tv.setBackgroundColor(Util.setbgcolor(mData.getBackgroundColor()));
        page1_main_lay.addView(tv, fl);

        return page1_main_lay;

    }

    public static View create_logo(final Context mContext, RelativeLayout page1_main_lay, PubContent mData) {
        //view_position   表示 图片位置，title_position在第几个栏目下
        //最外层

        float w = mData.getWidth(), h = mData.getHeight(), t = mData.getOrdinate(), l = mData.getAbscissa();

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


        return page1_main_lay;
    }

    public static View create_logo2(final Context mContext, FrameMainLayout page1_main_lay, final PageInfo2.PageDataBean mData) {
        //view_position   表示 图片位置，title_position在第几个栏目下
        //最外层
        float w = mData.getWidth(), h = mData.getHeight(), t = mData.getOrdinate(), l = mData.getAbscissa();


        w = w / proportion;
        h = h / proportion;
        t = t / proportion;
        l = l / proportion;

        FrameMainLayout.LayoutParams fl = new FrameMainLayout.LayoutParams((int) w, (int) h);
        fl.setMargins((int) l, (int) t, 0, 0);

        ImageView iv = new ImageView(mContext);
//        iv.setImageResource(R.drawable.mainview_tuijian);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);

        iv.setFocusable(false);
        iv.setFocusableInTouchMode(false);

//        iv.setImageResource(R.drawable.logo_top);
        Glide.with(mContext).load(mData.getImgSrc().startsWith("http") == true ? mData.getImgSrc() : Url.Img_name + mData.getImgSrc()).into((ImageView) iv);
        page1_main_lay.addView(iv, fl);


        return page1_main_lay;
    }


    private static void TvAnimation(final TextView tv) {

        TranslateAnimation downTranslateAnimation = new TranslateAnimation(0, 0, 0, -40);
        downTranslateAnimation.setDuration(500);
        downTranslateAnimation.setFillAfter(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(500);

        AnimationSet animationSetOut = new AnimationSet(true);
        animationSetOut.addAnimation(downTranslateAnimation);
        animationSetOut.addAnimation(alphaAnimation);
        tv.startAnimation(animationSetOut);

        animationSetOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                int iii = TVADPOSITION % mList_Tv_Ad.size();
                tv.setText(mList_Tv_Ad.get(iii));
                topTranslateAnimation(tv);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    private static void topTranslateAnimation(TextView tv) {

        TranslateAnimation topTranslateAnimation = new TranslateAnimation(0, 0, tv.getHeight(), 0);
        topTranslateAnimation.setDuration(500);
        topTranslateAnimation.setFillAfter(true);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(500);


        AnimationSet animationSetIn = new AnimationSet(true);
        animationSetIn.addAnimation(topTranslateAnimation);
        animationSetIn.addAnimation(alphaAnimation);

        tv.startAnimation(animationSetIn);


    }

    public static void get_newlist2(final Context mContext, final View view, final PageInfo2.PageDataBean mData, final boolean local) {


        String posturl = mData.getInterfaceX() +"?key="+mData.getSourceKey()+ "&pageNum=" + (mData.getPageNum() == 0 ? 1 : mData.getPageNum()) + "&getSize=" + (mData.getSourceNum() == null ? "5" : mData.getSourceNum());
        final int refreshtime = (mData.getRate() == 0 ? 5000 : mData.getRate());
        String title = mData.getText();

        final ListView mlist = (ListView) view.findViewById(R.id.mlist);
        final View[] mOldView = new View[1];
        LinearLayout.LayoutParams line = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mlist.setLayoutParams(line);
        TextView title_news = view.findViewById(R.id.title_news);
        title_news.setText("" + title);
//        mlist.setDividerHeight(10);
        final ListAdapter_CreatView[] madapter = new ListAdapter_CreatView[1];

        mlist.setSelector(R.color.transparent);
        mlist.setFocusable(false);
        final int[] showviewitem_num = {0};
        final int[] firstitem_num = {0};
        mlist.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    mlist.setSelector(R.color.red);
                    if (madapter[0] != null) {
                        madapter[0].setfocus(true);
                    }
                    if (madapter[0].getCount() >= 4) {
                        if (runnable != null) {

                            mHandler.removeCallbacks(runnable);
                        }
                    }

                } else {
                    if (madapter[0] != null) {
                        madapter[0].setfocus(false);
                    }
                    if (madapter[0].getCount() >= 4) {
                        if (runnable != null) {
                            runnable.run();
                        }
                    }
                    mlist.setSelector(R.color.white_50);
                }
            }
        });

        mlist.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                showviewitem_num[0] = i1;
                firstitem_num[0] = i;
            }
        });
        if (mData.getListData().size() > 0) {
            initlistdata2(mContext, "", view, madapter, mData, mlist, local, refreshtime);
            return;
        }

        RequestParams params = new RequestParams();

        HttpUtils httpUtils = new HttpUtils();

        MyLog.v("请求接口" + Url.newsurl + posturl);
        httpUtils.send(HttpRequest.HttpMethod.GET, Url.newsurl + posturl, params, new RequestCallBack<String>() {

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onSuccess(ResponseInfo<String> arg0) {
                // TODO Auto-generated method stub
                String str = arg0.result;
                MyLog.v("这是获取的" + str);
                initlistdata2(mContext, str, view, madapter, mData, mlist, local, refreshtime);
            }
        });
    }

    public static void initlistdata2(final Context mContext, String result, final View view, final ListAdapter_CreatView[] madapter, final PageInfo2.PageDataBean mData, final ListView mlist, final boolean local, final int refreshtime) {


        newlistInfo list = new newlistInfo();
        if (local == false) {

            list = JsonUtils.getModel(result, newlistInfo.class);
            if (list == null || list.getReturnCode() != 0) {
                MyLog.v("可视化创建获取list");
                return;
            }
        } else {

            List<newlistInfo.DataBean> mlistdata = new ArrayList<>();
            for (int i = 0; i < mData.getListData().size(); i++) {
                newlistInfo.DataBean bean = new newlistInfo.DataBean();
                bean.setTitle(mData.getListData().get(i).getTitle());
                bean.setImg(mData.getListData().get(i).getImg());
                mlistdata.add(bean);
            }
            list.setData(mlistdata);

        }


        madapter[0] = new ListAdapter_CreatView(mContext, list, 0,mData);
        final int[] scrollby = {0};
        mlist.setAdapter(madapter[0]);
        if (list.getData().size() >= 4) {

            final AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
            alphaAnimation.setDuration(800);
//                mlist.setSelection(0);
            view.startAnimation(alphaAnimation);
             final int[] i = {0};
            final newlistInfo finalList = list;
            runnable = new Runnable() {
                @Override
                public void run() {
                    i[0]++;
                    scrollby[0] = scrollby[0] +mData.getThumbH()+10;
                    if (idDestory((Activity) mContext)) {
                        MyLog.v("结束了");
                        return;
                    }

                    if (i[0] >= finalList.getData().size()) {
                        i[0] = 0;
                        scrollby[0]=0;
                    }
//                    mlist.setSelection(i[0]);
//                    MyLog.v("scrollby[0]"+scrollby[0]);
//                    mlist.smoothScrollBy(scrollby[0],800);
                        mlist.setSelection(i[0]);
                      mHandler.postDelayed(this, refreshtime);                             //相当于定时器，每隔2s执行一次该线程
                }
            };
            mHandler.postDelayed(runnable, refreshtime);
        }
    }
    public static void get_newlist3(final Context mContext, final View view, final PageInfo2.PageDataBean mData, final int source) {

        String posturl = mData.getInterfaceX() +"?key="+mData.getSourceKey()+ "&pageNum=" + (mData.getPageNum() == 0 ? 1 : mData.getPageNum()) + "&getSize=" + (mData.getSourceNum() == null ? "5" : mData.getSourceNum());
        final int refreshtime = (mData.getRate() == 0 ? 5000 : mData.getRate());
        float w = mData.getThumbW(), h = mData.getHeight();
        final float width = w / proportion;
        final float height = h / proportion;
//        ImageView   iv_bg  =view.findViewById(R.id.iv_list_bg);
        final RecyclerView rv = view.findViewById(R.id.rv);


        rv.setFocusable(false);
        rv.setFocusableInTouchMode(false);

        final ListView mlist = (ListView) view.findViewById(R.id.mlist);
        LinearLayout.LayoutParams line = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mlist.setLayoutParams(line);
         mlist.setSelector(R.color.transparent);
        mlist.setBackgroundColor(Util.setbgcolor(mData.getBackgroundColor()));
        mlist.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {

                } else {

                }
            }
        });
        mlist.setFocusable(false);
        final int[] showviewitem_num = {0};
        final int[] firstitem_num = {0};
        mlist.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                MyLog.v(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + i);
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                showviewitem_num[0] = i1;
                firstitem_num[0] = i;

            }
        });
        if (source == 0) {
            initlistdata3(mContext, "", width, height, view, rv, mlist, refreshtime, source, mData);
            return;
        }
        RequestParams params = new RequestParams();

        HttpUtils httpUtils = new HttpUtils();
        MyLog.v("获取新闻" + posturl);
        httpUtils.send(HttpRequest.HttpMethod.GET,   posturl, params, new RequestCallBack<String>() {

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onSuccess(ResponseInfo<String> arg0) {
                // TODO Auto-generated method stub
                String str = arg0.result;

                initlistdata3(mContext, str, width, height, view, rv, mlist, refreshtime, source, mData);

            }
        });
    }

    public static void initlistdata3(final Context mContext, String result, float width, final float height, View view, final RecyclerView rv, final ListView mlist, final int refreshtime, int source, PageInfo2.PageDataBean mData) {
        newlistInfo list = null;
        if (source == 1) {

            list = JsonUtils.getModel(result, newlistInfo.class);
        } else {

            list=new newlistInfo();
            List<newlistInfo.DataBean> mlistdata = new ArrayList<>();
            for (int i = 0; i < mData.getListData().size(); i++) {
                newlistInfo.DataBean bean = new newlistInfo.DataBean();
                bean.setTitle(mData.getListData().get(i).getTitle());
                bean.setImg(mData.getListData().get(i).getImg());
                mlistdata.add(bean);
            }
            list.setData(mlistdata);
        }

        final ListwithTextAdapter madapter = new ListwithTextAdapter(mContext, list, 0, height,mData.getFontSize());
        final DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        rv.setLayoutManager(linearLayoutManager);
        new PagerSnapHelper().attachToRecyclerView(rv); //一次滑动一页

        rv.setAdapter(new RecycNewsAdapter(mContext, list, width, height));

        if (list.getReturnCode() != 0) {
            return;
        }
        mlist.setAdapter(madapter);
        if (list.getData().size() >= 3) {
            final AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
            alphaAnimation.setDuration(800);
            madapter.setSelectItem(0);
            view.startAnimation(alphaAnimation);

            final int[] i = {0};

//                //相当于定时器，每隔2s执行一次该线程
            final newlistInfo finalList = list;
            runnable = new Runnable() {


                @Override
                public void run() {
                    i[0]++;
                    if (idDestory((Activity) mContext)) {
                        MyLog.v("结束了");
                        return;
                    }
                    if (i[0] >= finalList.getData().size()) {
                        i[0] = 0;

                        rv.smoothScrollToPosition(0);
                    } else {

                        rv.smoothScrollBy(0, (int) height, decelerateInterpolator);
                    }
                    mlist.setSelection(i[0]);
                    madapter.setSelectItem(i[0]);
                    mHandler.postDelayed(this, refreshtime);                             //相当于定时器，每隔2s执行一次该线程
                }
            };
            mHandler.postDelayed(runnable, refreshtime);
        }

    }

    public static void get_banner(final Context mContext, final View view, final String mould, final PageInfo2.PageDataBean mData, boolean local) {
        //local  true 表示本地数据   ,false  表示请求接口数据
        String posturl = mData.getInterfaceX() +"?key="+mData.getSourceKey()+ "&pageNum=" + (mData.getPageNum() == 0 ? 1 : mData.getPageNum()) + "&getSize=" + (mData.getSourceNum() == null ? "5" : mData.getSourceNum());
        final int refreshtime = (mData.getRate() == 0 ? 5000 : mData.getRate());
        float w = mData.getWidth(), h = mData.getHeight();
        final float width = w / proportion;
        final float height = h / proportion;
        RecyclerView rv = null;
        TextView recyc_tv = null;
//        view.setVisibility(View.GONE);

        ImageView iv_item = null;
        TextView tv_item = null;
        RelativeLayout Rl_tv = null;
        recyc_tv = view.findViewById(R.id.recyc_tv);

        recyc_tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,(FontSize.px2sp(mContext,(mData.getFontSize()/proportion))));
        if (mould.equals("swipeScrollLR") || mould.equals("swipeScrollUD")) {

            rv = view.findViewById(R.id.banner_rv);
            rv.setFocusable(false);
            rv.setFocusableInTouchMode(false);

        } else {

            iv_item = view.findViewById(R.id.iv);
            tv_item = view.findViewById(R.id.tv);
            tv_item.setTextSize(TypedValue.COMPLEX_UNIT_SP,(FontSize.px2sp(mContext,(mData.getFontSize()/proportion))));

            Rl_tv = view.findViewById(R.id.Rl_tv);
            if (BaseApplication.proportion == 1.5f) {
                RelativeLayout.LayoutParams fl_tv = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 40);
                fl_tv.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                Rl_tv.setLayoutParams(fl_tv);
            } else {
                RelativeLayout.LayoutParams fl_tv = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 60);
                fl_tv.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                Rl_tv.setLayoutParams(fl_tv);
            }

        }

        RequestParams params = new RequestParams();

        HttpUtils httpUtils = new HttpUtils();

        final RecyclerView finalRv = rv;
        final TextView tvindex = recyc_tv;
        final TextView tvtitle = tv_item;
        final ImageView Iv_bg = iv_item;

        if (local == true) {

            newlistInfo list = new newlistInfo();
            List<newlistInfo.DataBean> mlistdata = new ArrayList<>();
            for (int i = 0; i < mData.getListData().size(); i++) {
                newlistInfo.DataBean bean = new newlistInfo.DataBean();
                bean.setTitle(mData.getListData().get(i).getTitle());
                bean.setImg(mData.getListData().get(i).getImg());
                mlistdata.add(bean);
            }
            list.setData(mlistdata);
            initBannerData(mContext, mould, finalRv, list, (int) height, (int) width, tvtitle, Iv_bg, tvindex, refreshtime);
            return;
        }
        httpUtils.send(HttpRequest.HttpMethod.GET,  posturl, params, new RequestCallBack<String>() {

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                // TODO Auto-generated method stub
//                tvindex.setVisibility(View.GONE);
            }

            @Override
            public void onSuccess(ResponseInfo<String> arg0) {
                // TODO Auto-generated method stub
                String str = arg0.result;
                MyLog.i("获取轮播图新闻：" + str);
                final newlistInfo list = JsonUtils.getModel(str, newlistInfo.class);
                if (TextUtils.isEmpty(str) || list.getReturnCode() != 0) {
                    return;
                }
                initBannerData(mContext, mould, finalRv, list, (int) height, (int) width, tvtitle, Iv_bg, tvindex, refreshtime);
                //相当于定时器，每隔2s执行一次该线程
            }
        });
    }


    public static void initBannerData(final Context mContext, final String mould, final RecyclerView finalRv, final newlistInfo list, final int height, final int width,
                                      final TextView tvtitle, final ImageView Iv_bg, final TextView tvindex, final int refreshtime) {
        final AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        final DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator();
        final int[] position = {0};
        MyLog.i(mould + "list" + list.getData().size());
        if (mould.equals("swipeScrollLR")) {

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            finalRv.setLayoutManager(linearLayoutManager);
            new PagerSnapHelper().attachToRecyclerView(finalRv); //一次滑动一页
            finalRv.setAdapter(new RecycBannerAdapter(mContext, list, height));
        } else if (mould.equals("swipeScrollUD")) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            finalRv.setLayoutManager(linearLayoutManager);
            new PagerSnapHelper().attachToRecyclerView(finalRv); //一次滑动一页
            finalRv.setAdapter(new RecycBannerAdapter(mContext, list, height));
        } else {
            MyLog.i("list>>>>>>>>>>>>>" + list.getData().get(position[0]).getTitle());
            tvtitle.setText(list.getData().get(position[0]).getTitle());
            Glide.with(mContext).load(list.getData().get(position[0]).getImg()).apply(BaseApplication.requestOptions).into((ImageView) Iv_bg);
            alphaAnimation.setDuration(800);
            Iv_bg.startAnimation(alphaAnimation);
        }


        //相当于定时器，每隔2s执行一次该线程
        runnable = new Runnable() {


            @Override
            public void run() {

                position[0]++;

                if (idDestory((Activity) mContext)) {
                    MyLog.v("结束了");
                    return;
                }
                if (position[0] >= list.getData().size()) {

                    position[0] = 0;
                }
                if (mould.equals("swipeScrollLR")) {

                    finalRv.smoothScrollBy((int) width, 0, decelerateInterpolator);

                } else if (mould.equals("swipeScrollUD")) {

                    finalRv.smoothScrollBy(0, (int) height, decelerateInterpolator);

                } else {

                    tvtitle.setText(list.getData().get(position[0]).getTitle());
                    Glide.with(mContext).load(list.getData().get(position[0]).getImg()).apply(BaseApplication.requestOptions).into((ImageView) Iv_bg);

                    Iv_bg.startAnimation(alphaAnimation);
                }
                tvindex.setText("" + (position[0] + 1) + "/" + list.getData().size());

                mHandler.postDelayed(this, refreshtime);                             //相当于定时器，每隔2s执行一次该线程
            }
        };
        tvindex.setText("" + (position[0] + 1) + "/" + list.getData().size());
        mHandler.postDelayed(runnable, refreshtime);
    }


    public static View create_scroll_text2(final Context mContext, FrameMainLayout page1_main_lay, final PageInfo2.PageDataBean mData) {
        //view_position   表示 图片位置，title_position在第几个栏目下
        //最外层
        float w = mData.getWidth(), h = mData.getHeight(), t = mData.getOrdinate(), l = mData.getAbscissa();


        w = w / proportion;
        h = h / proportion;
        t = t / proportion;
        l = l / proportion;
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        w = dm.widthPixels;
        FrameMainLayout.LayoutParams fl = new FrameMainLayout.LayoutParams((int) w, (int) h);
        fl.setMargins((int) 0, (int) t, 0, 0);


        String mould = mData.getMould();
        if (mould == null) {
            mould = "leftright";
        }

        if (mould.equals("leftright")) {

            View view = LayoutInflater.from(mContext).inflate(R.layout.scrolltext_leftright, null);


            ScrollTextView mScrollTextView = view.findViewById(R.id.scroltv);

            mScrollTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,(FontSize.px2sp(mContext,(mData.getFontSize()/proportion))));
            mScrollTextView.setTextColor(Util.setfontcolor(mData.getColor()));

            if (mData.getListData() != null && mData.getListData().size() > 0) {


                get_notice(mContext,null,mScrollTextView,mould,mData,true);
            }else{

                get_notice(mContext,null,mScrollTextView,mould,mData,false);
            }
            page1_main_lay.addView(view, fl);
        } else if (mould.equals("updown")) {
            final TextView tv = new TextView(mContext);
            tv.setTextColor(Util.setfontcolor(mData.getColor()));

            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,(FontSize.px2sp(mContext,(mData.getFontSize()/proportion))));
            tv.setGravity(Gravity.CENTER);

            if (mData.getListData() != null && mData.getListData().size() > 0) {
                for (int i = 0; i < mData.getListData().size(); i++) {
                    mList_Tv_Ad.add(mData.getListData().get(i).getTitle());
                }
                get_notice(mContext,tv,null,mould,mData,true);
            }else{
                get_notice(mContext,tv,null,mould,mData,false);
            }
            page1_main_lay.addView(tv, fl);
        }

        return page1_main_lay;
    }


    public static View create_scroll_text(final Context mContext, RelativeLayout page1_main_lay,final PubContent mData) {
        //view_position   表示 图片位置，title_position在第几个栏目下
        //最外层

        float t = mData.getOrdinate() / proportion;

        RelativeLayout.LayoutParams fl = new RelativeLayout.LayoutParams((int) RelativeLayout.LayoutParams.MATCH_PARENT, 100);
        fl.setMargins((int) 0, (int) t, 0, 0);
        String mould = mData.getMould();
        mould="updown";
        if (mould == null) {
            mould = "leftright";
        }
        PageInfo2.PageDataBean data=new PageInfo2.PageDataBean();
        if (mould.equals("leftright")) {


            View view = LayoutInflater.from(mContext).inflate(R.layout.scrolltext_leftright, null);


            ScrollTextView mScrollTextView = view.findViewById(R.id.scroltv);
            mScrollTextView.setTextSize(Util.setfontsize(mContext, mData.getFontSize()));
            mScrollTextView.setTextColor(Util.setfontcolor(mData.getColor()));

            if (mData.getListData() != null && mData.getListData().size() > 0) {

                List<PageInfo2.PageDataBean.ListDataBean> listdata=new ArrayList<PageInfo2.PageDataBean.ListDataBean>();
                for (int i=0;i<mData.getListData().size();i++){
                    PageInfo2.PageDataBean.ListDataBean itemdata=new PageInfo2.PageDataBean.ListDataBean();
                    itemdata.setTitle(mData.getListData().get(i).getTitle());
                    listdata.add(itemdata);
                }
                data.setListData(listdata);
                get_notice(mContext,null,mScrollTextView,mould,data,true);
            }else{
                data.setInterfaceX(mData.getInterfaceX());
                data.setPageNum(mData.getPageNum());
                data.setSourceNum(mData.getSourceNum());
                data.setRate(mData.getRate());
                get_notice(mContext,null,mScrollTextView,mould,data,false);
            }

            page1_main_lay.addView(view, fl);
        } else {

            final TextView tv = new TextView(mContext);
            tv.setTextColor(Util.setfontcolor(mData.getColor()));
            tv.setTextSize(Util.setfontsize(mContext, mData.getFontSize()));

            tv.setGravity(Gravity.CENTER);
            data.setRate(mData.getRate());
            if (mData.getListData() != null && mData.getListData().size() > 0) {
                for (int i = 0; i < mData.getListData().size(); i++) {
                    mList_Tv_Ad.add(mData.getListData().get(i).getTitle());
                }
                get_notice(mContext,tv,null,mould,data,true);
            }else{
                data.setInterfaceX(mData.getInterfaceX());
                data.setPageNum(mData.getPageNum());
                data.setSourceNum(mData.getSourceNum());
                get_notice(mContext,tv,null,mould,data,false);
            }

            page1_main_lay.addView(tv, fl);
        }

        return page1_main_lay;
    }


    private static int TVADWITH = 111;
    private static int TVADPOSITION = 0;
    private static ArrayList<String> mList_Tv_Ad = new ArrayList<>();

    public static void setNoticeData_updown(final Context mContext, final TextView tv,final int refreshtime) {



            runnable = new Runnable() {
                @Override
                public void run() {

                    if (idDestory((Activity) mContext)) {
                        MyLog.v("结束了");
                        return;
                    }
                    TVADPOSITION++;
                    TvAnimation(tv);
                    mHandler.postDelayed(this, refreshtime);                             //相当于定时器，每隔2s执行一次该线程
                }
            };
            mHandler.postDelayed(runnable, refreshtime);
            TvAnimation(tv);
    }

    public static void setNoticeData_leftright(final Context mContext, final ScrollTextView mScrollTextView, PubContent mData) {
        String str="",mtext="";
        if (proportion == 1) {
            str = "                                                                                                                                                   " +
                    "                                                       ";
        } else {
            str = "                                                                                                                                                                       " +
                    "                                               ";
        }

        if (mData.getListData() != null && mData.getListData().size() > 0) {
            for (int i = 0; i < mData.getListData().size(); i++) {
                mtext = mtext + str + mData.getListData().get(i).getTitle();
            }
        }else{

        }

        mScrollTextView.setText(mtext);

    }

    public static void get_notice(final Context mContext, final TextView tv, final ScrollTextView mScrollTextView,final String mould, final PageInfo2.PageDataBean mData,final boolean local) {
        //local  true 表示本地数据   ,false  表示请求接口数据

        String posturl = mData.getInterfaceX() +"?key="+mData.getSourceKey()+ "&pageNum=" + (mData.getPageNum() == 0 ? 1 : mData.getPageNum()) + "&getSize=" + (mData.getSourceNum() == null ? "5" : mData.getSourceNum());
//        posturl="http://fjwysdb.chinashadt.com:8040/Interface/Recodrd/listRecod2.do?key=ECEPXIBQUORZSAET&pageNum=1&getSize=10";
        String str="";
         String mtext = "";

        if (mould.equals("leftright")){
            if (local==true){
                if (proportion == 1) {
                    str = "                                                                                                                                                   " +
                            "                                                       ";
                } else {
                    str = "                                                                                                                                                                       " +
                            "                                               ";
                }

                if (mData.getListData() != null && mData.getListData().size() > 0) {
                    for (int i = 0; i < mData.getListData().size(); i++) {
                        mtext = mtext + str + mData.getListData().get(i).getTitle();
                    }
                }

                mScrollTextView.setText(mtext);
                return;
            }

        }else if(mould.equals("updown")){
            if (local==true){
                int refreshtime = (mData.getRate() == 0 ? 5000 : mData.getRate());
                setNoticeData_updown(mContext,tv,refreshtime);
                return;
            }

        }

        HttpUtils httpUtils = new HttpUtils();



        httpUtils.send(HttpRequest.HttpMethod.GET,  posturl, null, new RequestCallBack<String>() {

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                // TODO Auto-generated method stub
//                tvindex.setVisibility(View.GONE);
            }

            @Override
            public void onSuccess(ResponseInfo<String> arg0) {
                // TODO Auto-generated method stub
                String result = arg0.result;
                MyLog.i("获取轮播图新闻：" + result);
                final newlistInfo list = JsonUtils.getModel(result, newlistInfo.class);
                if (TextUtils.isEmpty(result) || list.getReturnCode() != 0) {
                    return;
                }
                if (mould.equals("updown")){
                    for (int i=0;i<list.getData().size();i++){
                        mList_Tv_Ad.add(list.getData().get(i).getTitle());
                    }
                    int refreshtime = (mData.getRate() == 0 ? 5000 : mData.getRate());
                    setNoticeData_updown(mContext,tv,refreshtime);
                }else{
                    String str="";
                    String mtext = "";
                    if (proportion == 1) {
                        str = "                                                                                                                                                   " +
                                "                                                       ";
                    } else {
                        str = "                                                                                                                                                                       " +
                                "                                               ";
                    }
                    for (int i=0;i<list.getData().size();i++){
                        mtext = mtext + str +list.getData().get(i).getTitle();
                    }
                    mScrollTextView.setText(mtext);
                }
//                initBannerData(mContext, mould, finalRv, list, (int) height, (int) width, tvtitle, Iv_bg, tvindex, refreshtime);
                //相当于定时器，每隔2s执行一次该线程
            }
        });
    }
}
