package com.shadt.iptv_qx;


import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.shadt.iptv_qx.utils.Util;
import com.shadt.iptv_qx.widget.media.IjkVideoView;
import tv.danmaku.ijk.media.player.IMediaPlayer;


public class IjkPlayerActivity extends BaseActivity {


    Context mContext = IjkPlayerActivity.this;


    private final int HIND_CONTROL = 0;
    private final int UPDATE_TIME = 1;
    private final int SYSTEMTIME = 2;
    private final int SHOW_SPEED = 3;
    private final int CANCLEEXIT = 4;
    private final int SEEK_TO = 5;

    private Util utils = new Util();
    private boolean controlIsShowing = false;
    //记录步数
    private int count = 0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_SPEED:
                    String speed = utils.getNetSpeed(IjkPlayerActivity.this);
                    tvLoading.setText(speed);
                    handler.removeMessages(SHOW_SPEED);
                    sendEmptyMessageDelayed(SHOW_SPEED, 1000);
                    break;
                case HIND_CONTROL:
                    handler.removeMessages(HIND_CONTROL);
                    rlController.setVisibility(View.GONE);
                    controlIsShowing = false;
                    break;
                case UPDATE_TIME://视频当前时间更新
                    //得到当前视频的播放进度
                    int currentPosition = (int) videoView.getCurrentPosition();
                    //设置到SeekBar中
                    Log.e("TAG", "UPDATE_TIME--" + "UPDATE_TIME");
                    seekBarTime.setProgress(currentPosition);
                    seekBarTime.setSecondaryProgress((int) (videoView.getBufferPercentage() * (videoView.getDuration() / 100)));
                    tvControllerOnTime.setText(utils.stringForTime(currentPosition));
                    handler.removeMessages(UPDATE_TIME);
                    handler.sendEmptyMessageDelayed(UPDATE_TIME, 1000);
                    break;
                case SYSTEMTIME:

                    handler.sendEmptyMessageDelayed(SYSTEMTIME, 1000);
                    break;
                case CANCLEEXIT:
                    waitExit = true;
                    break;
                case SEEK_TO:
                    state.setVisibility(View.GONE);
                    videoView.seekTo(progress);
                    break;
            }
        }
    };


    private int progress = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ijk);
        initview();
        init();
    }
    TextView tvControllerName;

    TextView tvControllerOnTime;
    SeekBar seekBarTime;
    TextView tvAllTime;
    TextView tvLoading;
    ImageView state;
    RelativeLayout rlController;

    RelativeLayout ll_loading;

    public void initview(){
        videoView = findViewById(R.id.live_videoView);
        tvControllerName=findViewById(R.id.tv_controller_name);

        tvControllerOnTime=findViewById(R.id.tv_controller_on_time);
        seekBarTime=findViewById(R.id.seekBar_time);
        tvAllTime=findViewById(R.id.tv_all_time);
        tvLoading=findViewById(R.id.tv_loading);
        state=findViewById(R.id.iv_state);
        rlController=findViewById(R.id.rl_controller);

        ll_loading=findViewById(R.id.ll_loading);
    }
    IjkVideoView videoView;
    public void init() {

        handler.sendEmptyMessage(SYSTEMTIME);
        handler.sendEmptyMessage(SHOW_SPEED);
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
                Toast.makeText(IjkPlayerActivity.this, "IPTV-V-002 播放出错", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        String url="d";
        url="http://cdn.video.chinashadt.com/26a5c8cdvodsh1257065451/734fdd705285890807381409293/CHPw1jtPwaAA.mp4";

        Log.e("TAG", "playUrl----" + url);
        videoView.setVideoPath(url);

        videoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer mediaPlayer) {
//                mediaPlayer.start();
//                ll_loading.setVisibility(View.GONE);
//                int duration = (int) videoView.getDuration();
//                seekBarTime.setMax(duration);
//                tvAllTime.setText(utils.stringForTime(duration));
//                handler.sendEmptyMessage(UPDATE_TIME);
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
                            state.setVisibility(View.GONE);
                            ll_loading.setVisibility(View.VISIBLE);
                            break;
                        //卡顿结束后,进入此case
                        case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                            handler.sendEmptyMessageDelayed(UPDATE_TIME, 1000);
                            handler.sendEmptyMessageDelayed(HIND_CONTROL, 1000);
                            state.setVisibility(View.GONE);
                            ll_loading.setVisibility(View.GONE);
                    }
                    return true;
                }

            });
        }
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        Log.e("TAG", "keydown---" + keyCode);
        if(event.getAction() == KeyEvent.ACTION_UP) {
            return super.dispatchKeyEvent(event);
        }
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_ENTER:
                handler.removeMessages(HIND_CONTROL);
                if (videoView.isPlaying()) {
                    videoView.pause();
                    state.setVisibility(View.VISIBLE);
                    state.setBackgroundResource(R.drawable.icon_player_pause_bg);
                    rlController.setVisibility(View.VISIBLE);
                } else {
                    videoView.start();
                    rlController.setVisibility(View.GONE);
                    handler.sendEmptyMessageAtTime(HIND_CONTROL, 3000);
                }
                return true;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
            case KeyEvent.KEYCODE_DPAD_LEFT:
                Log.e("TAG", "keydown---" + "right|left");
                handler.removeMessages(UPDATE_TIME);
                handler.removeMessages(HIND_CONTROL);
                state.setVisibility(View.VISIBLE);
                setVideoPositionByKey(keyCode);
                rlController.setVisibility(View.VISIBLE);
                handler.removeMessages(SEEK_TO);
                handler.sendEmptyMessageDelayed(SEEK_TO, 500);
                Log.e("TAG", "progress---" + progress);
                seekBarTime.setProgress(progress);
                tvControllerOnTime.setText(utils.stringForTime(seekBarTime.getProgress()));
                return true;
            case KeyEvent.KEYCODE_BACK:
                exit();
                return true;
        }
        return super.dispatchKeyEvent(event);
    }

    /**
     * 快进，快退
     */
    public void setVideoPositionByKey(int keyCode) {

        if (!videoView.isPlaying()) {
            videoView.start();
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            forward();
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            rewind();
        }
    }

    /**
     * 快进 15s
     */
    private void forward() {
        state.setBackgroundResource(R.drawable.forward);
        int seek = seekBarTime.getProgress();
        progress = seek + 5000;
        if (this.progress > videoView.getDuration()) {
            this.progress = (int) videoView.getDuration() - 2000;
        } else if (this.progress < 0) {
            this.progress = 0;
        }
    }

    /**
     * 快退 15s
     */
    private void rewind() {
        state.setBackgroundResource(R.drawable.rewind);
        int seek = seekBarTime.getProgress();
        progress = seek - 5000;
        if (progress < 0) {
            progress = 0;
        }
    }

    private boolean waitExit = true;

    /**
     * 退出方法
     */
    private void exit() {
        if (waitExit) {
            waitExit = false;
            Toast.makeText(mContext, "在按一次返回键退出", Toast.LENGTH_SHORT).show();
            handler.sendEmptyMessageDelayed(CANCLEEXIT, 2000);
        } else {
            finish();
        }
    }
    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        videoView.stopPlayback();



        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        videoView.start();
        videoView.seekTo(tempTime);
        Log.e("TAG", "onRestart----" + tempTime);
    }

    private int tempTime;

    @Override
    protected void onPause() {
        tempTime = videoView.getCurrentPosition();
        videoView.pause();
        super.onPause();
    }



}
