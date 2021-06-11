//package com.shadt.cn;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import com.shadt.media.util.NetUtils;
//import com.shadt.media.util.Util;
//import com.shadt.media_jn.R;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.AlertDialog.Builder;
//import android.app.Dialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.res.Configuration;
//import android.graphics.drawable.BitmapDrawable;
//import android.media.MediaPlayer;
//import android.media.MediaPlayer.OnCompletionListener;
//import android.media.MediaPlayer.OnPreparedListener;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.KeyEvent;
//import android.view.LayoutInflater;
//import android.view.TextureView;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup.LayoutParams;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ImageButton;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.PopupWindow;
//import android.widget.ProgressBar;
//import android.widget.SeekBar;
//import android.widget.SeekBar.OnSeekBarChangeListener;
//import android.widget.TextView;
//import android.widget.Toast;
//import android.widget.VideoView;
//
//
///**
// * @author wangliang
// * @see VidoeView播放器
// */
//@SuppressLint("NewApi")
//public class VideoPlayerActivity extends Activity {
//
//	private final static int VIDEO_LODING_SPEED =1001; //下载速度
//	private final static int VIDEO_UPDATE_TIME =1002 ; //当前播放进度
//
//	private VideoView video;
//	private String path_temp;// 影片uri
//	private String history_position = "";// 影片上一次播放位置
//	private ProgressBar progressBar;
//	private ImageButton pause;
//	private TextView time_label;
//	private boolean isPause = false;
//	private int old_duration;
//	private final Handler handler = new Handler();
//	private Runnable runnable;
//	private LinearLayout seekbar_layout;
//	private SeekBar seekBar;
//	private TextView currentTime;
//	private TextView totalTime;
//	private int totalTimeLaber;// 片长
//	private boolean isPressDown = false;
//	private boolean mDragging = false;
//	private int SEEKBAR_LABEL = 0;// 无操作3秒后隐藏seekbar
//	Dialog dialog;
//	private ArrayList<String> urllist;
//	private int mPlayerNum = 0 ; //当前播放第几个
//	private LinearLayout mTimeMessage;
//	private TextView cacheT;
//	private TextView mSystemTime;
//	private TextView mDownLoding;
//	private String mLodingSpeed;  //下载速度
//
//	private String mNettype=""; //当前网络类型
//
//	Handler mHandler =new Handler(){
//		@Override
//		public void handleMessage(Message msg) {
//			// TODO Auto-generated method stub
//			super.handleMessage(msg);
//
//			switch (msg.what) {
//			case VIDEO_LODING_SPEED://视频下载速度
//				if(NetUtils.isConnected(VideoPlayerActivity.this)){
//					mNettype =NetUtils.getNetworkType(VideoPlayerActivity.this);
//
//					DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm");
//					String time=dateFormat.format(new Date());
//					mSystemTime.setText("当前时间  "+time);
//
//				}else{
//
//				}
//
//			    mDownLoding.setText("网络状态  "+mNettype+"  "+mLodingSpeed);
//
//				break;
//			case VIDEO_UPDATE_TIME://当前播放进度
//				String s ="";
//				if(video.isPlaying()) {
//					int position = video.getCurrentPosition();
//					int mMax = video.getDuration();
//					mMax = 0 == mMax ? 1 : mMax;
//					double playpercent = position * 100.00 / mMax * 1.0;
//					int i = position / 1000;
//					int hour = i / (60 * 60);
//					int minute = i / 60 % 60;
//					int second = i % 60;
//					s += String.format("播放位置  %02d:%02d:%02d [%.2f%%]", hour, minute, second, playpercent);
//				}else{
//					s += "视频当前未播放!";
//				}
//				cacheT.setText(s);
//				break;
//
//			default:
//				break;
//			}
//
//
//
//		}
//	};
//
//
//
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//
//
//		Intent intent = this.getIntent();
//
//		if (intent != null) {
//			urllist =intent.getStringArrayListExtra("paths");
//			for(int i =0 ;i<urllist.size() ;i++){
//				Log.i("BLUE", "播放地址："+urllist.get(i));
//			}
//			if (urllist ==null && urllist.size() <= 0) {
//				finish();
//			}
//
//		} else {
//
//			finish();
//		}
//
//		path_temp = urllist.get(mPlayerNum);
//
////		path_temp = intent .getStringExtra("path");
//
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);
//		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//
//		setContentView(R.layout.activity_videoview);
//
//		// 初始化数据库
////		DBAdapter.createOrOpeanData(this);
////		history_position = queryHistoryPosition();
//
//		/*时间信息*/
//		mTimeMessage =(LinearLayout) findViewById(R.id.timemessage);
//		cacheT = (TextView) findViewById(R.id.cacheT); //缓存提示
//		mSystemTime =(TextView) findViewById(R.id.systemtime); //系统时间提示
//		mDownLoding =(TextView) findViewById(R.id.lodingspeed); //下载速度提示
//
//		mTimeMessage.setVisibility(View.GONE);
//
//
//		video = (VideoView) findViewById(R.id.videoView);
//		seekbar_layout = (LinearLayout) findViewById(R.id.seekbar_layout);
//		progressBar = (ProgressBar) findViewById(R.id.progressBar);
//		pause = (ImageButton) findViewById(R.id.pause);
//		time_label = (TextView) findViewById(R.id.time_label);
//		currentTime = (TextView) findViewById(R.id.current_time);
//		totalTime = (TextView) findViewById(R.id.total_time);
//		seekBar = (SeekBar) findViewById(R.id.mediacontroller_progress);
//		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
//			@Override
//			public void onStartTrackingTouch(SeekBar bar) {
//				// TODO Auto-generated method stub
//				mDragging = true;
//			}
//
//			@Override
//			public void onStopTrackingTouch(SeekBar bar) {
//				// TODO Auto-generated method stub
//				mDragging = false;
//				setProgress();
//			}
//
//			@Override
//			public void onProgressChanged(SeekBar bar, int progress,
//					boolean fromuser) {
//				// TODO Auto-generated method stub
//				if (!fromuser) {
//					return;
//				}
//				long duration = video.getDuration();
//				long newposition = (duration * progress) / 1000L;
//				video.seekTo((int) newposition);
//				if (currentTime != null) {
//					currentTime.setText(Util.secToTime((int) newposition));
//				}
//				if (time_label != null) {
//					time_label.setText(Util.secToTime((int) newposition));
//				}
//			}
//		});
//		seekBar.setMax(1000);
//
//		video.setVideoURI(Uri.parse(path_temp));
//		video.setOnPreparedListener(new OnPreparedListener() {
//
//			@Override
//			public void onPrepared(MediaPlayer arg0) {
//				// TODO Auto-generated method stub
//				handler.post(runnable);
//				totalTimeLaber = video.getDuration() / 1000;
//				totalTime.setText(Util.secToTime(totalTimeLaber));
//				seekbar_layout.setVisibility(View.VISIBLE);
//				progressBar.setVisibility(View.GONE);
//			}
//		});
//		video.setOnCompletionListener(new OnCompletionListener() {
//
//			@Override
//			public void onCompletion(MediaPlayer arg0) {
//				// TODO Auto-generated method stub
//				// seekbar_layout.setVisibility(View.VISIBLE);
//				// progressBar.setVisibility(View.GONE);
//				if (null != video && video.isPlaying()) {
//					video.pause();
//				}
////				finish();
//				mPlayerNum ++;
//				if(mPlayerNum < urllist.size()){
//
//
//					progressBar.setVisibility(View.GONE);
//					handler.removeCallbacks(runnable);
//
//					final Dialog dialog =new Dialog(VideoPlayerActivity.this, R.style.dialog);
//					View view =LayoutInflater.from(VideoPlayerActivity.this).inflate(R.layout.dialog, null);
//					Button leftBtn =(Button) view.findViewById(R.id.bt1);
//					Button rightBtn =(Button) view.findViewById(R.id.bt2);
//
//					dialog.setContentView(view);
//					dialog.show();
//					leftBtn.setOnClickListener(new OnClickListener() {
//
//						@Override
//						public void onClick(View arg0) {
//							// TODO Auto-generated method stub
//							dialog.cancel();
//							video.setVideoURI(Uri.parse(urllist.get(mPlayerNum)));
//							Log.i("OTH", "当前节目地址："+urllist.get(mPlayerNum));
//							video.start();
//							progressBar.setVisibility(View.VISIBLE);
//						}
//					});
//					rightBtn.setOnClickListener(new OnClickListener() {
//
//						@Override
//						public void onClick(View arg0) {
//							// TODO Auto-generated method stub
//							dialog.cancel();
//							finish();
//						}
//					});
//
//
//				}else{
//					finish();
//				}
//
//
//
//
//			}
//		});
//		video.start();
//		// 是否接上次位置播放
////		if (!history_position.equals("")) {
////			dialog = new Dialog(MainActivity.this, R.style.MyDialog);
////			dialog.setContentView(R.layout.show_dialog);
////			// View
////			// view=LayoutInflater.from(MainActivity.this).inflate(R.layout.show_dialog,
////			// null);
////			Button cancel = (Button) dialog
////					.findViewById(R.id.dialog_button_cancel);
////			Button ok = (Button) dialog.findViewById(R.id.dialog_button_ok);
////			cancel.setOnClickListener(new OnClickListener() {
////
////				@Override
////				public void onClick(View arg0) {
////					// TODO Auto-generated method stub
////					dialog.cancel();
////				}
////			});
////			ok.setOnClickListener(new OnClickListener() {
////
////				@Override
////				public void onClick(View arg0) {
////					// TODO Auto-generated method stub
////					video.seekTo(Integer.parseInt(history_position));
////					dialog.cancel();
////				}
////			});
////			// dialog.set
////			dialog.show();
////		} else {
////			video.start();
////		}
//		video.requestFocus();
//
//		runnable = new Runnable() {
//			public void run() {
//				if (old_duration == video.getCurrentPosition() && !isPressDown) {
//					progressBar.setVisibility(View.VISIBLE);
//				} else {
//					progressBar.setVisibility(View.GONE);
//				}
//				setProgress();
//
//				mHandler.sendEmptyMessage(VIDEO_UPDATE_TIME);
//
//				// 无操作5秒后隐藏seekbar
//				SEEKBAR_LABEL++;
//				if (SEEKBAR_LABEL > 5) {
//					hideSeekBar();
//				}
//				handler.postDelayed(runnable, 1000);
//			}
//
//		};
//
//		new Timer().schedule(task, 1000, 4000); // 1s后启动任务，每4s执行一次
//
//	}
//
//
//	/*计时器*/
//	TimerTask task = new TimerTask() {
//		@Override
//	    public void run() {
//	    	mLodingSpeed =NetUtils.lodingSpeed();
//	    	mHandler.sendEmptyMessage(VIDEO_LODING_SPEED);
//	    }
//	};
//
//
//	public int setProgress() {
//		if (mDragging) {
//			return 0;
//		}
//		int position = video.getCurrentPosition();
//		old_duration = position;
//		int duration = video.getDuration();
//		if (seekBar != null) {
//			if (duration > 0) {
//				// use long to avoid overflow
//				long pos = 1000L * position / duration;
//				seekBar.setProgress((int) pos);
//			}
//			// int percent = video.getBufferPercentage();
//			// seekBar.setSecondaryProgress(percent * 10);
//		}
//		if (totalTime != null) {
//			totalTime.setText(Util.secToTime(duration / 1000));
//		}
//		if (currentTime != null) {
//			currentTime.setText(Util.secToTime(position / 1000));
//		}
//		if (time_label != null) {
//			time_label.setText(Util.secToTime(position / 1000));
//		}
//		return position;
//	}
//
//	/**
//	 * 显示播放进度条.
//	 */
//	public void showSeekBar() {
//		seekbar_layout.setVisibility(View.VISIBLE);
////		mTimeMessage.setVisibility(View.VISIBLE);
//	}
//
//	/**
//	 * 隐藏播放进度条.
//	 */
//	public void hideSeekBar() {
//		seekbar_layout.setVisibility(View.GONE);
////		mTimeMessage.setVisibility(View.GONE);
//	}
//
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		// TODO Auto-generated method stub
//
//		switch (keyCode) {
//		case KeyEvent.KEYCODE_MENU:
//			// int y = getWindowManager().getDefaultDisplay().getHeight();
//			// int x = getWindowManager().getDefaultDisplay().getWidth() / 4;
//			// showPopupWindow(0, 0);
//			break;
//		case KeyEvent.KEYCODE_DPAD_LEFT:
//			SEEKBAR_LABEL = 0;
//			showSeekBar();
//			isPressDown = true;
//			progressBar.setVisibility(View.GONE);
//			time_label.setVisibility(View.VISIBLE);
//			pause.setVisibility(View.VISIBLE);
//			pause.setBackgroundResource(R.drawable.left_btn);
//			int pos_left = video.getCurrentPosition();
//			pos_left -= 15000;
//			if (pos_left < 0) {
//				pos_left = 0;
//			}
//			video.seekTo(pos_left);
//			setProgress();
//			break;
//		case KeyEvent.KEYCODE_DPAD_RIGHT:
//			SEEKBAR_LABEL = 0;
//			showSeekBar();
//			isPressDown = true;
//			progressBar.setVisibility(View.GONE);
//			time_label.setVisibility(View.VISIBLE);
//			pause.setVisibility(View.VISIBLE);
//			pause.setBackgroundResource(R.drawable.right_btn);
//			int pos_right = video.getCurrentPosition();
//			pos_right += 25000;
//			if (pos_right > video.getDuration()) {
//				pos_right = video.getDuration();
//			}
//			video.seekTo(pos_right);
//			setProgress();
//			break;
//		case KeyEvent.KEYCODE_DPAD_CENTER:
//			SEEKBAR_LABEL = 0;
//			showSeekBar();
//			isPressDown = true;
//			if (null != video) {
//				if (video.isPlaying()) {
//					isPause = true;
//					video.pause();
//					handler.removeCallbacks(runnable);
//					progressBar.setVisibility(View.GONE);
//					pause.setVisibility(View.VISIBLE);
//					time_label.setVisibility(View.VISIBLE);
//					pause.setBackgroundResource(R.drawable.pause_btn);
//				} else {
//					isPause = false;
//					video.start();
//					handler.postDelayed(runnable, 1000);
//					pause.setVisibility(View.GONE);
//					time_label.setVisibility(View.GONE);
//				}
//			}
//			break;
//		case KeyEvent.KEYCODE_DPAD_DOWN:
//			SEEKBAR_LABEL = 0;
//			showSeekBar();
//			isPressDown = true;
//			break;
//		case KeyEvent.KEYCODE_DPAD_UP:
//			SEEKBAR_LABEL = 0;
//			showSeekBar();
//			isPressDown = true;
//			break;
//		default:
//			break;
//		}
//		return super.onKeyDown(keyCode, event);
//	}
//
//	@Override
//	public boolean onKeyUp(int keyCode, KeyEvent event) {
//		// TODO Auto-generated method stub
//		SEEKBAR_LABEL++;
//		isPressDown = false;
//		if (isPause) {
//			pause.setBackgroundResource(R.drawable.pause_btn);
//			pause.setVisibility(View.VISIBLE);
//			time_label.setVisibility(View.VISIBLE);
//		} else {
//			pause.setVisibility(View.GONE);
//			time_label.setVisibility(View.GONE);
//		}
//		return super.onKeyUp(keyCode, event);
//	}
//
//
//
//	@Override
//	protected void onPause() {
//		// TODO Auto-generated method stub
//		if (null != video && video.isPlaying()) {
//			video.pause();
//		}
////		insertCurrentPosition(video.getCurrentPosition() + "");
//		super.onPause();
//	}
//
//	@Override
//	protected void onStop() {
//		// TODO Auto-generated method stub
//		super.onStop();
//	}
//
//	/**设置播放器的宽高比**/
////	private void changeSurfaceSize() {
////		// get screen size
////		int dw = getWindow().getDecorView().getWidth();
////		int dh = getWindow().getDecorView().getHeight();
////
////		// getWindow().getDecorView() doesn't always take orientation into
////		// account, we have to correct the values
////		boolean isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
////		if (dw > dh && isPortrait || dw < dh && !isPortrait) {
////			int d = dw;
////			dw = dh;
////			dh = d;
////		}
////		if (dw * dh == 0)
////			return;
////		// compute the aspect ratio
////		double ar, vw;
////		double density = (double) mSarNum / (double) mSarDen;
////		if (density == 1.0) {
////			/* No indication about the density, assuming 1:1 */
////			vw = mVideoWidth;
////			ar = (double) mVideoWidth / (double) mVideoHeight;
////		} else {
////			/* Use the specified aspect ratio */
////			vw = mVideoWidth * density;
////			ar = vw / mVideoHeight;
////		}
////
////		// compute the display aspect ratio
////		double dar = (double) dw / (double) dh;
////
////		// // calculate aspect ratio
////		// double ar = (double) mVideoWidth / (double) mVideoHeight;
////		// // calculate display aspect ratio
////		// double dar = (double) dw / (double) dh;
////
////		switch (mCurrentSize) {
////		case SURFACE_BEST_FIT: //最适合
////			mTextShowInfo.setText(R.string.video_player_best_fit);
////			if (dar < ar)
////				dh = (int) (dw / ar);
////			else
////				dw = (int) (dh * ar);
////			break;
////		case SURFACE_FIT_HORIZONTAL: //横向最适
////			mTextShowInfo.setText(R.string.video_player_fit_horizontal);
////			dh = (int) (dw / ar);
////			break;
////		case SURFACE_FIT_VERTICAL: //纵向最适
////			mTextShowInfo.setText(R.string.video_player_fit_vertical);
////			dw = (int) (dh * ar);
////			break;
////		case SURFACE_FILL: //全屏显示
////			mTextShowInfo.setText(R.string.video_player_fill);
////
////			break;
////		case SURFACE_16_9: //16:9
////			mTextShowInfo.setText(R.string.video_player_16x9);
////			ar = 16.0 / 9.0;
////			if (dar < ar)
////				dh = (int) (dw / ar);
////			else
////				dw = (int) (dh * ar);
////			break;
////		case SURFACE_4_3: //4:3
////			mTextShowInfo.setText(R.string.video_player_4x3);
////			ar = 4.0 / 3.0;
////			if (dar < ar)
////				dh = (int) (dw / ar);
////			else
////				dw = (int) (dh * ar);
////			break;
////		case SURFACE_ORIGINAL:   //原样
////			mTextShowInfo.setText(R.string.video_player_original);
////			dh = mVideoHeight;
////			dw = mVideoWidth;
////			break;
////		}
////
////		surfaceHolder.setFixedSize(mVideoWidth, mVideoHeight);
////		LayoutParams lp = surfaceView.getLayoutParams();
////		lp.width = dw;
////		lp.height = dh;
////		surfaceView.setLayoutParams(lp);
////		surfaceView.invalidate();
////	}
//
//
//
//
//
//
//
//}
