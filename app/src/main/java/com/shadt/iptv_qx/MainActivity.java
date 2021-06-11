package com.shadt.iptv_qx;

import android.animation.Animator;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalFocusChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.Glide;
import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.bridge.OpenEffectBridge;
import com.open.androidtvwidget.utils.OPENLOG;
import com.open.androidtvwidget.view.MainUpView;
import com.open.androidtvwidget.view.SmoothHorizontalScrollView;
import com.owen.tab.TvTabLayout;
import com.shadt.iptv_qx.Application.BaseApplication;
import com.shadt.iptv_qx.Fragment.MainFragment;
import com.shadt.iptv_qx.model.PubContent;
import com.shadt.iptv_qx.utils.Create_views;
import com.shadt.iptv_qx.utils.SharedPreferences;
import com.shadt.iptv_qx.utils.Url;
import com.shadt.iptv_qx.widget.CustomDialog;
import java.util.ArrayList;
import java.util.List;


/**
 * ViewPager demo：
 * 注意标题栏和viewpager的焦点控制.(在XML布局中控制了, ids)
 * 移动边框的问题也需要注意.
 *
 * @author hailongqiu
 */
public class MainActivity extends FragmentActivity {

    ViewPager viewpager;


    // 移动边框.
    MainUpView mainUpView1;
    EffectNoDrawBridge mEffectNoDrawBridge;
    OpenEffectBridge mOpenEffectBridge;
    View mNewFocus;
    View mOldView;
    RelativeLayout vp_main_lay;

    SharedPreferences sp = new SharedPreferences();

    List<PubContent> arrPubContent = new ArrayList<>();
    ImageView iv_bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_viewpager);

        arrPubContent = (List<PubContent>) getIntent().getSerializableExtra("data");
        String bg_url = getIntent().getStringExtra("bg");
        iv_bg = findViewById(R.id.iv_bg);
        Glide.with(mContext).load(bg_url).into(iv_bg);
        mhandler.sendEmptyMessage(INIT_DATA);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(5000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    View rootview = MainActivity.this.getWindow().getDecorView();
//                    View focusView = rootview.findFocus();
//                    Log.i(BaseApplication.TAG, focusView == null ? "当前无焦点" : focusView.toString());
//                }
//            }
//        }).start();

    }

    private int INIT_DATA = 1;

    Handler mhandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == INIT_DATA) {
                init_data();
            }
        }
    };

    public void init_data() {
        float density = getResources().getDisplayMetrics().density;
        vp_main_lay = findViewById(R.id.vp_main_lay);
        ImageView iv_bg = findViewById(R.id.iv_bg);

        sp.getmaindata(mContext);


        RectF rectF = null;
        if (BaseApplication.proportion == 1) {
            rectF = new RectF(getDimension(R.dimen.w_10) * density, getDimension(R.dimen.h_10) * density,
                    getDimension(R.dimen.w_10) * density, getDimension(R.dimen.h_10) * density);
        } else {
            rectF = new RectF(getDimension(R.dimen.w_20) * density, getDimension(R.dimen.h_20) * density,
                    getDimension(R.dimen.w_20) * density, getDimension(R.dimen.h_20) * density);
        }


        mainUpView1 = (MainUpView) findViewById(R.id.mainUpView1);
        mEffectNoDrawBridge = new EffectNoDrawBridge();
        mainUpView1.setEffectBridge(mEffectNoDrawBridge);
        mEffectNoDrawBridge.setUpRectResource(R.drawable.white_light_10); // 设置移动边框图片.

        mEffectNoDrawBridge.setDrawUpRectPadding(rectF);
        mEffectNoDrawBridge.setVisibleWidget(true);
//        PubContent mp=new PubContent();
//        mp.setType("back");
//        mp.setImgSrc("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fku.90sjimg.com%2Felement_origin_min_pic%2F16%2F12%2F27%2Ffae736a7f01aced4548168b6caed42ab.jpg&refer=http%3A%2F%2Fku.90sjimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1611737663&t=c630af649337b31dbe045ea10553f755");
//        mp.setOrdinate(100);
//        mp.setAbscissa(200);
//        mp.setWidth(100);
//        mp.setHeight(100);
//        arrPubContent.add(mp);
        for (int i = 0; i < arrPubContent.size(); i++) {
            if (arrPubContent.get(i).getType().equals("navigation")) {
                if (arrPubContent.get(i).getNavData().size() > 1) {
                    init_view_viewpager(arrPubContent.get(i));
                }
            } else if (arrPubContent.get(i).getType().equals("time")) {
                if (!TextUtils.isEmpty("" + arrPubContent.get(i).getWidth()) || !TextUtils.isEmpty("" + arrPubContent.get(i).getHeight()) ||
                        !TextUtils.isEmpty("" + arrPubContent.get(i).getAbscissa()) || !TextUtils.isEmpty("" + arrPubContent.get(i).getOrdinate())) {
                    Create_views.create_time(mContext, vp_main_lay, arrPubContent.get(i));
                } else {
                    Toast.makeText(mContext, "时间控件数据出现问题", Toast.LENGTH_SHORT).show();
                }
            } else if (arrPubContent.get(i).getType().equals("logo")) {
                if (!TextUtils.isEmpty("" + arrPubContent.get(i).getWidth()) || !TextUtils.isEmpty("" + arrPubContent.get(i).getHeight()) ||
                        !TextUtils.isEmpty("" + arrPubContent.get(i).getAbscissa()) || !TextUtils.isEmpty("" + arrPubContent.get(i).getOrdinate())) {
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
                create_back(mContext, vp_main_lay, arrPubContent.get(i));
            }
        }

    }

    List<Fragment> fragments = new ArrayList<>();
    TvTabLayout mTabLayout;
    private SparseArray<Fragment> mFragments = new SparseArray<>();

    public void init_view_viewpager(PubContent data) {


        mTabLayout = findViewById(R.id.tablayout1);

        mTabLayout.setDefaultHeight((int) (data.getHeight() / BaseApplication.proportion));
//        mTabLayout.setBackgroundColor(Util.setbgcolor(data.getBackgroundColor()));
        if (data.getOrdinate() > 500) {
            mTabLayout.setNextFocusUpId(0);
        } else {
            mTabLayout.setNextFocusDownId(0);
        }
//        mTabLayout.setBackgroundColor(getResources().getColor(R.color.black));
        mTabLayout.setNextFocusLeftId(R.id.tablayout1);
        mTabLayout.setNextFocusRightId(R.id.tablayout1);


//        mTabLayout.setTabTextSelectedScaleValue((float) 1.1);
        RelativeLayout.LayoutParams tablp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, (int) (data.getHeight() / BaseApplication.proportion));
        tablp.setMargins(0, (int) (data.getOrdinate() / BaseApplication.proportion), 0, 0);

        tablp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        List<PubContent.NavDataBean> itemdata = data.getNavData();
        String iconpose=data.getIconPose();
        iconpose="top";
        mTabLayout.setLayoutParams(tablp);
        for (int i = 0; i < itemdata.size(); i++) {

            fragments.add(MainFragment.newInstance(i, itemdata.get(i).getChannelid()));
            View view = null;

            LinearLayout.LayoutParams rl_iv = new LinearLayout.LayoutParams((int) (data.getIconWidth() / BaseApplication.proportion), (int) (data.getIconWidth() / BaseApplication.proportion));

            if (itemdata.get(i).getIcon().getValue() != null) {

                if (itemdata.get(i).getIcon().isShow() == true) {

                    if (iconpose.equals("right")) {
                        view = LayoutInflater.from(this).inflate(R.layout.item_tab_lefttv_rightimg, null);
                    } else if (iconpose.equals("left")) {
                        view = LayoutInflater.from(this).inflate(R.layout.item_tab_leftimg_righttv, null);
                    } else if (iconpose.equals("down")) {
                        view = LayoutInflater.from(this).inflate(R.layout.item_tab_toptv_downimg, null);
                    } else {
                        //默认顶部
                        view = LayoutInflater.from(this).inflate(R.layout.item_tab_topimg_downtv, null);
                    }
                    ImageView iv = view.findViewById(R.id.iv);
                    iv.setLayoutParams(rl_iv);
                    Glide.with(mContext).load(itemdata.get(i).getIcon().getValue()).apply(BaseApplication.requestOptions).into((ImageView) iv);
                } else {
                    view = LayoutInflater.from(this).inflate(R.layout.item_tab_text, null);
                }
            }else{
                view = LayoutInflater.from(this).inflate(R.layout.item_tab_text, null);
            }
            mTabLayout.addTab(mTabLayout.newTab().setCustomView(view), i == 0);
            final TextView tv = view.findViewById(R.id.tv);

//                StateListDrawable bg = new StateListDrawable();
//                Drawable normal = getResources().getDrawable(R.color.transparent);
//
//
//                GradientDrawable drawable = new GradientDrawable();
//                drawable.setShape(GradientDrawable.RECTANGLE);
//
//                drawable.setCornerRadius(35);
//                if (i==0){
//                    drawable.setColor(Color.parseColor("#ff1234"));
//                }else{
//                    drawable.setColor(Color.parseColor("#f78912"));
//                }
//                bg.addState(new int[]{android.R.attr.state_selected}, drawable);
//
//                bg.addState(new int[]{}, normal);
//                  tv.setPadding(10,5,10,5);
//                tv.setBackground(bg);

//            tv.setTextColor(Util.setfontcolor(data.getColor()));
            tv.setTextSize(BaseApplication.fontsize);
            tv.setText(itemdata.get(i).getNpName());

        }

        viewpager = (ViewPager) findViewById(R.id.viewpager);
        viewpager.setVisibility(View.VISIBLE);


        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);

            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public int getItemPosition(@NonNull Object object) {
                return POSITION_NONE;
            }

            ///////////////////////////////////////////////////////////////////////////
            // 应以这样的方式来持有fragment的引用 start
            ///////////////////////////////////////////////////////////////////////////
            @Override
            public @NonNull
            Object instantiateItem(@NonNull ViewGroup container, int position) {
                Fragment f = (Fragment) super.instantiateItem(container, position);
                mFragments.put(position, f);
                return f;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                super.destroyItem(container, position, object);
                mFragments.remove(position);
            }
            ///////////////////////////////////////////////////////////////////////////
            // 应以这样的方式来持有fragment的引用 end
            ///////////////////////////////////////////////////////////////////////////
        });


        // 全局焦点监听. (这里只是demo，为了方便这样写，你可以不这样写)

        viewpager.setOffscreenPageLimit(6);

        // 初始化.
        viewpager.setCurrentItem(0);
        viewpager.getViewTreeObserver().addOnGlobalFocusChangeListener(new OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {


                // 判断 : 避免焦点框跑到标题栏. (只是demo，你自己处理逻辑)
                // 你也可以让标题栏放大，有移动边框.

                float scale = 1.0f;

                if (newFocus != null && !(newFocus instanceof TvTabLayout)) {


                    if (newFocus instanceof ListView) {

                        mNewFocus = null;
                        mOldView = null;
                        mainUpView1.setUnFocusView(oldFocus);
                        mEffectNoDrawBridge.setVisibleWidget(true);
                    } else if (newFocus instanceof SmoothHorizontalScrollView) {

                        mNewFocus = null;
                        mOldView = null;
                        mainUpView1.setUnFocusView(oldFocus);
                        mEffectNoDrawBridge.setVisibleWidget(true);
                    } else {
                        mNewFocus = newFocus;
                        mOldView = oldFocus;
                        mEffectNoDrawBridge.setVisibleWidget(false);
                        if ((int) newFocus.getTag() == 0) {
                            scale = 1f;
                        } else {
                            scale = 1f;
                        }

                        mainUpView1.setFocusView(newFocus, oldFocus, scale);
                        // 让被挡住的焦点控件在前面.
                        newFocus.bringToFront();
                    }

                    is_viewpager_scrool = true;

                } else { // 标题栏处理.
                    is_viewpager_scrool = false;
                    mNewFocus = null;
                    mOldView = null;
                    mainUpView1.setUnFocusView(oldFocus);

                    mEffectNoDrawBridge.setVisibleWidget(true);
                }
            }
        });
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


                mTabLayout.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE: // viewpager 滚动结束.


                        mainUpView1.setFocusView(mNewFocus, mOldView, 1f);

                        // 监听动画事件.
                        mEffectNoDrawBridge.setOnAnimatorListener(new OpenEffectBridge.NewAnimatorListener() {
                            @Override
                            public void onAnimationStart(OpenEffectBridge bridge, View view, Animator animation) {
                                bridge.setVisibleWidget(false);
                            }

                            @Override
                            public void onAnimationEnd(OpenEffectBridge bridge, View view, Animator animation) {
                                // 动画结束的时候恢复原来的时间. (这里只是DEMO)
                                mEffectNoDrawBridge.setTranDurAnimTime(OpenEffectBridge.DEFAULT_TRAN_DUR_ANIM);
                            }
                        });
                        // 让被挡住的焦点控件在前面.
                        if (mNewFocus != null)
                            mNewFocus.bringToFront();

                        OPENLOG.D("SCROLL_STATE_IDLE");
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        OPENLOG.D("SCROLL_STATE_DRAGGING");
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING: // viewPager开始滚动.

                        mEffectNoDrawBridge.clearAnimator(); // 清除之前的动画.
                        mEffectNoDrawBridge.setTranDurAnimTime(0); // 避免边框从其它地方跑出来.
                        OPENLOG.D("SCROLL_STATE_SETTLING");
                        break;
                }
            }
        });

        mTabLayout.addOnTabSelectedListener(new TvTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TvTabLayout.Tab tab) {

                viewpager.setCurrentItem(tab.getPosition(), true);


            }

            @Override
            public void onTabUnselected(TvTabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TvTabLayout.Tab tab) {

            }
        });
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


    public static boolean is_viewpager_scrool = false;


    Context mContext = MainActivity.this;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Create_views.myhandler();
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                exit();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    private float getDimension(int id) {
        return getResources().getDimension(id);
    }

}
