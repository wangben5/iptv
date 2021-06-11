//package com.shadt.cn;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.v4.view.PagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.KeyEvent;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.shadt.iptv_qx.R;
//import com.shadt.cn.Application.BaseApplication;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//public class ViewPagerActivity extends Activity {
//    private ViewPager mViewPaper;
//    private List<ImageView> images;
//    private List<View> dots;
//    private Handler mHandler = new Handler() {
//        public void handleMessage(android.os.Message msg) {
//            mViewPaper.setCurrentItem(currentItem);
//        }
//    };
//    private int currentItem;
//    //记录上一次点的位置
//    private int oldPosition = 0;
//    private TextView title;
//    private ViewPagerAdapter adapter;
//    private ScheduledExecutorService scheduledExecutorService;
//    //存放图片的id
//    private int[] imageIds = new int[]{
//            R.drawable.bg,
//            R.drawable.bg,
//            R.drawable.bg,
//            R.drawable.bg,
//            R.drawable.bg
//    };
//    //存放图片的标题
//    private String[] titles = new String[]{
//            "挑战者联盟，薛之谦又来辣",
//            "老九门，又是李易峰这个傻叉",
//            "红色通道，再看刘烨英俊潇洒",
//            "神犬小七，小七居然是只猪",
//            "灭罪师，鬼知道这是什么剧"
//    };
//    private ImageView rightImgView;
//    private ImageView rightImgView1;
//    private ImageView rightImgView2;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.view_listview_img);
//        //定义mViewPaper旁边的控件
//        rightImgView = new ImageView(this);
//        rightImgView = (ImageView) findViewById(R.id.rightImgView);
////        rightImgView.setImageResource(R.drawable.right);
//        rightImgView1 = new ImageView(this);
//        rightImgView1 = (ImageView) findViewById(R.id.rightImgView1);
////        rightImgView1.setImageResource(R.drawable.right);
//        rightImgView2 = new ImageView(this);
//        rightImgView2 = (ImageView) findViewById(R.id.rightImgView3);
//        rightImgView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(5000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    View rootview = ViewPagerActivity.this.getWindow().getDecorView();
//                    View focusView = rootview.findFocus();
//                    Log.i(BaseApplication.TAG, focusView == null ? "当前无焦点" : focusView.toString());
//                }
//            }
//        }).start();
//        mViewPaper = (ViewPager) findViewById(R.id.vp);
//        //显示的图片
//        images = new ArrayList<ImageView>();
//        for (int i = 0; i < imageIds.length; i++) {
//            ImageView imageView = new ImageView(this);
//            imageView.setImageResource(imageIds[i]);
//            images.add(imageView);
//        }
//        //显示的小点
//        dots = new ArrayList<View>();
//        dots.add(findViewById(R.id.dot_0));
//        dots.add(findViewById(R.id.dot_1));
//        dots.add(findViewById(R.id.dot_2));
//        dots.add(findViewById(R.id.dot_3));
//        dots.add(findViewById(R.id.dot_4));
//
//        title = (TextView) findViewById(R.id.title);
//        title.setText(titles[0]);
//
//        mViewPaper.setAdapter(adapter = new ViewPagerAdapter(this, images));
//
//
//        //禁止手指控制滑动
//        mViewPaper.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });
//
//        mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//            @Override
//            public void onPageSelected(int position) {
//                title.setText(titles[position]);
////                dots.get(position).setBackgroundResource(R.drawable.dot_focused);
////                dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
//
//                oldPosition = position;
//                currentItem = position;
//            }
//
//            @Override
//            public void onPageScrolled(int arg0, float arg1, int arg2) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int arg0) {
//
//            }
//        });
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        // 产生一个ExecutorService对象，这个对象只有一个线程可用来执行任务，若任务多于一个，任务将按先后顺序执行。
//        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//        scheduledExecutorService.scheduleWithFixedDelay(new ViewPageTask(), 4,4, TimeUnit.SECONDS);
//    }
//
//    private class ViewPageTask implements Runnable {
//        @Override
//        public void run() {
//            currentItem = (currentItem + 1) % imageIds.length;
//            mHandler.sendEmptyMessage(0);
//        }
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if (scheduledExecutorService != null) {
//            scheduledExecutorService.shutdown();
//            scheduledExecutorService = null;
//        }
//    }
//
//
//    public class ViewPagerAdapter extends PagerAdapter {
//        private List<ImageView> images;
//        private Context mContext;
//
//        public ViewPagerAdapter(Context context, List<ImageView> img) {
//            this.mContext = context;
//            this.images = img;
//        }
//
//        //返回要滑动的VIew的个数
//        @Override
//        public int getCount() {
//            return images.size();
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view == object;
//        }
//
//        //从当前container中删除指定位置（position）的View;
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView(images.get(position));
//        }
//
//        //做了两件事，第一：将当前视图添加到container中，第二：返回当前View
//        @Override
//        public Object instantiateItem(ViewGroup container, final int position) {
//            container.addView(images.get(position));
//            ImageView imageView = images.get(position);
//            imageView.setBackgroundResource(R.drawable.newbg);
//
//            imageView.setOnKeyListener(new View.OnKeyListener() {
//                @Override
//                public boolean onKey(View v, int keyCode, KeyEvent event) {
//                    if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT && event.getAction() == KeyEvent.ACTION_DOWN) {
//                        rightImgView2.requestFocusFromTouch();
//                        rightImgView2.requestFocus();
//                        return true;
//                    }
//                    if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT && event.getAction() == KeyEvent.ACTION_DOWN) {
//                        rightImgView.requestFocusFromTouch();
//                        rightImgView.requestFocus();
//                        return true;
//                    }
//                    return false;
//                }
//            });
//            imageView.setFocusable(true);
//            imageView.setFocusableInTouchMode(true);
//            imageView.setClickable(true);
//            imageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(mContext, "点击了第" + "" + position + "张图片", Toast.LENGTH_LONG).show();
//                }
//            });
//            return imageView;
//        }
//    }
//
//}