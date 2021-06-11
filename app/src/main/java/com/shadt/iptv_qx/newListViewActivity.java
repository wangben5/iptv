package com.shadt.iptv_qx;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
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
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.ListViewTV;
import com.open.androidtvwidget.view.MainUpView;
import com.shadt.iptv_qx.Application.BaseApplication;
import com.shadt.iptv_qx.adapter.ListAdapter;
import com.shadt.iptv_qx.model.PageInfo2;
import com.shadt.iptv_qx.model.newlistInfo;
import com.shadt.iptv_qx.utils.JsonUtils;
import com.shadt.iptv_qx.utils.MyLog;
import com.shadt.iptv_qx.utils.ScaleUtils;
import com.shadt.iptv_qx.utils.Url;
import com.shadt.iptv_qx.utils.Util;
import com.shadt.iptv_qx.widget.TestWebView;
import com.shadt.iptv_qx.widget.media.IjkVideoView;
import com.wang.avi.AVLoadingIndicatorView;
import java.util.List;
import tv.danmaku.ijk.media.player.IMediaPlayer;



public class newListViewActivity extends Activity {

	private static final String TAG = BaseApplication.TAG;

	private List<String> data;

	private LayoutInflater mInflater;


	private Context mContext = newListViewActivity.this;

	newlistInfo list_data;

	RelativeLayout rela_top, rela_left;
	int index = 1, size = 10;

	boolean ismore = true;
	AVLoadingIndicatorView list_load, AVload;
	boolean is_bottom = false, is_top = true;
	int videowidth = 0;


	public int type = -1; //0表示web/1表示视频/-1表示未初始化
	public float webheight = 0;
	public int webscroheight = 30;

	PageInfo2.PageDataBean mContent;


	private final int HIND_CONTROL = 0;
	private final int UPDATE_TIME = 1;
	private final int SYSTEMTIME = 2;
	private final int SHOW_SPEED = 3;
	private final int CANCLEEXIT = 4;
	private final int SEEK_TO = 5;
	private final int HIDE_LOADING = 66;

	private Util utils = new Util();
	private boolean controlIsShowing = false;
	//记录步数
	private int count = 0;


	@RequiresApi(api = Build.VERSION_CODES.M)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listnews);
		TextView mtitle = findViewById(R.id.title);
		Bundle bundle = getIntent().getBundleExtra("bd");
		mContent = new PageInfo2.PageDataBean();
		if (bundle != null) {
			mContent = (PageInfo2.PageDataBean) bundle.getSerializable("content");
			mtitle.setText(mContent.getText());
		} else {
//			mtitle.setText("电视");
		}


		this.mInflater = LayoutInflater.from(getApplicationContext());

		list_load = findViewById(R.id.list_load);

		initLeft();
		initwebview();
		initview_video();

		rela_top = findViewById(R.id.rela_top);
		rela_left = findViewById(R.id.rela_left);

		handler.handleMessage(handler.obtainMessage());


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(5000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    View rootview = newListViewActivity.this.getWindow().getDecorView();
//                    View focusView = rootview.findFocus();
//                    Log.i(BaseApplication.TAG, focusView == null ? "当前无焦点" : focusView.toString());
//                }
//            }
//        }).start();

//fjyx.chinashadt.com//Interface/Recodrd/listRecod1.do?key=YXXWWYXNEWS&pageNum=1&getSize=4

//				get_newlist(Url.newsurl+mContent.getTarget()+"&pageNum="+index+"&getSize="+size);
		get_newlist(Url.newsurl+mContent.getTarget()+"&pageNum="+index+"&getSize="+size);
//		get_newlist("http://www.shcnxwxczx.com/Interface/Recodrd/listRecod2.do?key=HomePageGDXW&pageNum=1&getSize=20");

	}

	int mposition = 0;
	int selectindex = 0;
	ListAdapter madapter;
	ImageView video_thumbs;
	RelativeLayout rela_video;
	ImageView iv_bg; //黑色背景
	LinearLayout right_load;

	private MainUpView mainUpView1;
	private ListViewTV listView;
	private View mOldView;

	public void initLeft() {
		listView = (ListViewTV) findViewById(R.id.listview);

		// 注意这里，需要使用 RecyclerViewBridge 的移动边框 Bridge.

		mainUpView1 = (MainUpView) findViewById(R.id.mainUpView1);
		// 默认是 OpenEff...，建议使用 NoDraw... ...
		mainUpView1.setEffectBridge(new EffectNoDrawBridge());
		EffectNoDrawBridge bridget = (EffectNoDrawBridge) mainUpView1.getEffectBridge();
		mainUpView1.setUpRectResource(R.drawable.white_light_10); // 设置移动边框的图片.
		mainUpView1.setDrawUpRectPadding(new Rect(20, 20, 20, 20)); // 边框图片设置间距.
		bridget.setTranDurAnimTime(200);
		listView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View view, boolean b) {
				if (b) {

				} else {
					mainUpView1.setVisibility(View.GONE);
				}
			}
		});

//
		listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

				if (view != null) {
					if (listView.getCount() >= 10) {
						if (listView.getCount() != 0 && position >= (listView.getCount() - 2)) {
							if (isloadmore == false) {
								index++;
								get_newlist(Url.newsurl + mContent.getInterfaceX() + "&pageNum=" + index + "&getSize=" + size);
							}
						}
					}
					view.bringToFront();
					mainUpView1.setFocusView(view, mOldView, 1.1f);
					mOldView = view;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		//			// item 单击事件处理.
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
				if (mposition == position) {
					return;
				}
				mposition = position;
				if (!TextUtils.isEmpty(madapter.mlist().getData().get(0).getVideoUrl())){
					handler.sendEmptyMessageDelayed(PLAYVIDEO, 800);
				} else {
					handler.sendEmptyMessageDelayed(PLAYWEB, 800);
				}
				madapter.setSelectItem(position);
			}
		});


	}

	private final int PLAYVIDEO = 1001, PLAYWEB = 1002, INIT_LIST = 1003;
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {
				case PLAYVIDEO:
					showvideo();
					break;
				case PLAYWEB:
					showweb();
					break;
				case INIT_LIST:
					init_result();
					break;
				case SHOW_SPEED:
					String speed = utils.getNetSpeed(newListViewActivity.this);
					tvLoading.setText(speed);
					handler.removeMessages(SHOW_SPEED);
					sendEmptyMessageDelayed(SHOW_SPEED, 1000);
					break;
				case HIND_CONTROL:
					handler.removeMessages(HIND_CONTROL);
					if (isfull) {
						rlController.setVisibility(View.GONE);
					} else {
						rlController.setVisibility(View.VISIBLE);
					}

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
				case HIDE_LOADING:
					list_load.setVisibility(View.GONE);
					break;
			}
		}
	};


	IjkVideoView videoView;
	TextView tvControllerName;
	TextView tvControllerOnTime;
	SeekBar seekBarTime;
	TextView tvAllTime;
	TextView tvLoading;
	ImageView state;
	RelativeLayout rlController;

	RelativeLayout ll_loading, Rl, rela_bottom_tv, rela_right;
	TextView tv_bottom_title,tv_dianzan;
	LinearLayout line_bottom_seekbar, line_controller_top, line_dianzan;
	ImageView iv_state;
	boolean isfull = false;

	public void initview_video() {
		iv_state = findViewById(R.id.iv_state);
		tv_dianzan=findViewById(R.id.tv_dianzan);
		line_bottom_seekbar = findViewById(R.id.line_bottom_seekbar);
		rela_right = findViewById(R.id.rela_right);
		line_controller_top = findViewById(R.id.line_controller_top);
		tv_bottom_title = findViewById(R.id.tv_bottom_title);
		tv_bottom_title.setTextSize(BaseApplication.fontsize);
		Rl = findViewById(R.id.Rl);
		rela_bottom_tv = findViewById(R.id.rela_bottom_tv);
		rela_video = findViewById(R.id.rela_video);
		videoView = findViewById(R.id.live_videoView);
		tvControllerName = findViewById(R.id.tv_controller_name);

		tvControllerOnTime = findViewById(R.id.tv_controller_on_time);
		seekBarTime = findViewById(R.id.seekBar_time);
		tvAllTime = findViewById(R.id.tv_all_time);
		tvLoading = findViewById(R.id.tv_loading);
		state = findViewById(R.id.iv_state);
		line_dianzan = findViewById(R.id.line_dianzan);
		rlController = findViewById(R.id.rl_controller);

		ll_loading = findViewById(R.id.ll_loading);
		rlController.setVisibility(View.VISIBLE);

		line_controller_top.setVisibility(View.GONE);
		iv_state.setVisibility(View.INVISIBLE);
		Rl.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				setvideofull();
			}
		});
		Rl.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View view, boolean b) {
				if (b) {
					Rl.setBackgroundColor(Color.parseColor("#ffffff"));
					rela_bottom_tv.setBackgroundColor(Color.parseColor("#ffffff"));
					tv_bottom_title.setTextColor(getResources().getColor(R.color.black));

				} else {
					Rl.setBackgroundColor(Color.parseColor("#00000000"));
					rela_bottom_tv.setBackgroundColor(getResources().getColor(R.color.gray));
					tv_bottom_title.setTextColor(getResources().getColor(R.color.white));
					mainUpView1.setVisibility(View.VISIBLE);
				}
			}
		});
	}
	private TestWebView webView;
	private LinearLayout line_web_load;
	RelativeLayout rela_web,Rl_web;
	private  void initwebview(){
		rela_web=findViewById(R.id.rela_web);
		webView=findViewById(R.id.web);
		line_web_load=findViewById(R.id.line_web_load);
		line_web_load.setVisibility(View.GONE);

		Rl_web=findViewById(R.id.Rl_web);
		webView.setOnCustomScroolChangeListener(new TestWebView.ScrollInterface() {
			@Override
			public void onSChanged(int l, int t, int oldl, int oldt) {
				//WebView的总高度
				float webViewContentHeight=webView.getContentHeight() * webView.getScale();
				//WebView的现高度
				float webViewCurrentHeight=(webView.getHeight() + webView.getScrollY());
				webheight=webViewContentHeight;
				Log.v(TAG, webView.getScrollY()+" webView.getScrollY()"+webView.getHeight() +"WebView滑动到了底端"+webViewCurrentHeight);


				if (webView.getScrollY()==0){
					Log.v(TAG,"到顶了");
					is_top=true;
					is_bottom=false;
				}else{
					is_top=false;
				}
				if ((webViewContentHeight-webViewCurrentHeight) == 0) {
					is_bottom=true;
					is_top=false;
				}else{
					is_bottom=false;
				}
			}
		});
		webView.setWebViewClient(new WebViewClient(){
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				line_web_load.setVisibility(View.VISIBLE);
//				webView.setFocusableInTouchMode(true);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				line_web_load.setVisibility(View.GONE);
			}
		});
		Rl_web.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View view, boolean b) {


				if (b) {
//                    Rl_web.setBackgroundColor(Color.parseColor("#ffffff"));
					Rl_web.setBackgroundResource(R.drawable.bk4);
				} else {
					Rl_web.setBackgroundColor(Color.parseColor("#00000000"));
					mainUpView1.setVisibility(View.VISIBLE);
				}

			}
		});

	}
	private  void showweb(){
		type=0;
		if (videoView.isPlaying()){
			videoView.stopPlayback();
		}
		rela_web.setVisibility(View.VISIBLE);
		webView.setVisibility(View.VISIBLE);

		rela_video.setVisibility(View.GONE);
		line_dianzan.setVisibility(View.GONE);
		webView.loadUrl(madapter.mlist().getData().get(mposition).getRecordJumpUrl()+"&onapp=yes");

	}
	public void showvideo() {
		tvControllerName.setText(madapter.mlist().getData().get(mposition).getTitle());
		tv_bottom_title.setText(madapter.mlist().getData().get(mposition).getTitle());
		tv_dianzan.setText(""+madapter.mlist().getData().get(mposition).getTraffic());
		rela_web.setVisibility(View.GONE);
		handler.sendEmptyMessage(SYSTEMTIME);
		handler.sendEmptyMessage(SHOW_SPEED);
		rela_video.setVisibility(View.VISIBLE);
		line_dianzan.setVisibility(View.VISIBLE);
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
				Toast.makeText(newListViewActivity.this, "IPTV-V-002 播放出错", Toast.LENGTH_SHORT).show();
				return true;
			}
		});
		String url = "d";
//		url = "http://cdn.video.chinashadt.com/26a5c8cdvodsh1257065451/734fdd705285890807381409293/CHPw1jtPwaAA.mp4";
		url =madapter.mlist().getData().get(mposition).getVideoUrl();

		Log.e("TAG", "playUrl----" + url);
		videoView.setVideoPath(url);

		videoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
			@Override
			public void onPrepared(IMediaPlayer mediaPlayer) {
				mediaPlayer.start();
				ll_loading.setVisibility(View.GONE);
				int duration = (int) videoView.getDuration();
				seekBarTime.setMax(duration);
				tvAllTime.setText(utils.stringForTime(duration));
				handler.sendEmptyMessage(UPDATE_TIME);
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

	RelativeLayout.LayoutParams rl_videso;
	LinearLayout.LayoutParams line_right;

	private void setvideofull() {
		if (isfull) {
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
		} else {
			isfull = true;
			line_right = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
			rl_videso = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
			rl_videso.setMargins(0, 0, 0, 0);
			line_right.setMargins(0, 0, 0, 0);

			rela_left.setVisibility(View.GONE);
			rela_top.setVisibility(View.GONE);
			rela_bottom_tv.setVisibility(View.GONE);
			line_dianzan.setVisibility(View.GONE);
			videoView.setLayoutParams(rl_videso);
			rlController.setLayoutParams(rl_videso);
			rela_video.setLayoutParams(rl_videso);


			line_right = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
			rela_right.setLayoutParams(line_right);

			tvControllerOnTime.setVisibility(View.VISIBLE);
			tvAllTime.setVisibility(View.VISIBLE);
			line_controller_top.setVisibility(View.VISIBLE);
			RelativeLayout.LayoutParams rl_line_bottom_seekbar = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, ScaleUtils.px2dip(mContext, 100));
			rl_line_bottom_seekbar.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			line_bottom_seekbar.setLayoutParams(rl_line_bottom_seekbar);
			Rl.setPadding(0, 0, 0, 0);
			handler.sendEmptyMessageAtTime(HIND_CONTROL, 3000);
		}

	}

	private void setvideoNormal() {
		isfull = false;
		line_right = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		rl_videso = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		rl_videso.setMargins(0, 0, 0, 0);
		line_right.setMargins(0, 0, 0, 0);

		rela_left.setVisibility(View.VISIBLE);
		rela_top.setVisibility(View.VISIBLE);
		rela_bottom_tv.setVisibility(View.VISIBLE);
		line_dianzan.setVisibility(View.VISIBLE);
		line_controller_top.setVisibility(View.GONE);
		tvControllerOnTime.setVisibility(View.GONE);
		tvAllTime.setVisibility(View.GONE);
		RelativeLayout.LayoutParams lpvideoView = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		lpvideoView.setMargins(0, 0, 0, ScaleUtils.px2dip(mContext, 60));
		videoView.setLayoutParams(lpvideoView);

		rl_videso.setMargins(0, 0, ScaleUtils.dip2px(mContext, 40), ScaleUtils.dip2px(mContext, 60));

		rl_videso.setMargins(0, 0, ScaleUtils.dip2px(mContext, 40), ScaleUtils.dip2px(mContext, 60));
		RelativeLayout.LayoutParams lprlController = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		lprlController.setMargins(0, 0, 0, ScaleUtils.px2dip(mContext, 60));
		rlController.setLayoutParams(lprlController);
		rlController.setVisibility(View.VISIBLE);
		rl_videso.setMargins(0, 0, ScaleUtils.px2dip(mContext, 40), ScaleUtils.px2dip(mContext, 130));
		rela_video.setLayoutParams(rl_videso);


		line_right.setMargins(ScaleUtils.px2dip(mContext, 20), 0, 0, 0);
		rela_right.setLayoutParams(line_right);
		int s = ScaleUtils.dip2px(mContext, 1);

		Rl.setPadding(s, s, s, s);

		state.setVisibility(View.GONE);
		RelativeLayout.LayoutParams rl_line_bottom_seekbar = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, ScaleUtils.px2dip(mContext, 2));
		rl_line_bottom_seekbar.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		line_bottom_seekbar.setLayoutParams(rl_line_bottom_seekbar);
	}



	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		switch (keyCode) {
			case KeyEvent.KEYCODE_DPAD_CENTER:


				Log.v("wangliang", "点了确认件");

				break;

			case KeyEvent.KEYCODE_BACK:
				if (isfull == true) {
					isfull = false;
					setvideoNormal();
					if (!videoView.isPlaying()) {
						videoView.start();
						rlController.setVisibility(View.VISIBLE);
					}
					return false;
				} else {
					finish();

				}
				break;
			case KeyEvent.KEYCODE_DPAD_DOWN:
				if (Rl_web.hasFocus()){
					if (webheight==0.0){
						webView.setScrollY(webView.getScrollY()+webscroheight);
					}else{
						if (is_bottom==false){
							if (  webView.getScrollY()+webscroheight+webView.getHeight()<webheight){
								Log.v(TAG,webView.getScrollY()+webscroheight+">>11111111>>>"+webheight);
								webView.setScrollY(webView.getScrollY()+webscroheight);
							}else{
//							webView.setScrollY((int)webheight);
								Log.v(TAG,"DDDDDDDDDD");
							}
						}

					}
				}

				break;
			case KeyEvent.KEYCODE_DPAD_UP:
				if (Rl_web.hasFocus()){
					if (is_top==true){

					}else{
						webView.setScrollY(webView.getScrollY()-webscroheight);
					}
				}

				break;
			case KeyEvent.KEYCODE_DPAD_RIGHT:


				break;

			case KeyEvent.KEYCODE_DPAD_LEFT:


				break;
		}
		return super.onKeyDown(keyCode, event);
	}


	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		int keyCode = event.getKeyCode();
		Log.e("TAG", "keydown---" + keyCode);
		if (event.getAction() == KeyEvent.ACTION_UP) {
			return super.dispatchKeyEvent(event);
		}
		switch (keyCode) {


			case KeyEvent.KEYCODE_DPAD_RIGHT:
			case KeyEvent.KEYCODE_DPAD_LEFT:
				Log.e("TAG", "keydown---" + "right|left");
				if (isfull) {
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

				} else {
					return false;
				}
//            case KeyEvent.KEYCODE_BACK:
//                exit();
//                return true;
		}
		return super.dispatchKeyEvent(event);
	}


	boolean isloadmore = false;

	public void get_newlist(String posturl) {
		list_load.setVisibility(View.VISIBLE);
		Log.v("wangliang", "请求接口" + posturl);
		RequestParams params = new RequestParams();
		isloadmore = true;
		HttpUtils httpUtils = new HttpUtils();

		httpUtils.send(HttpRequest.HttpMethod.GET, posturl, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				handler.sendEmptyMessageDelayed(HIDE_LOADING,800);
				isloadmore = false;
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				String str = arg0.result;
				isloadmore = false;
				handler.sendEmptyMessageDelayed(HIDE_LOADING,800);
				Log.v("wangliang", "请求数据返回数据" + str);
				try {
					list_data = JsonUtils.getModel(str, newlistInfo.class);
				} catch (Error e) {
					return;
				}

				if (list_data == null) {
					Log.v("wangliang", "请求数据为空");
					return;
				}
				if (list_data.getReturnCode() != 0) {
					Log.v("wangliang", "请求数据出错");
					return;
				}
				if (list_data != null && list_data.getData().size() == size) {
					Log.v("wangliang", "请求数据》》" + ismore);
					ismore = true;
				} else {

					ismore = false;
					Log.v("wangliang", "没有更多了" + ismore);
				}

				handler.sendEmptyMessageDelayed(INIT_LIST, 800);

				//                //相当于定时器，每隔2s执行一次该线程

			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		handler.removeMessages(INIT_LIST);
		handler.removeMessages(PLAYWEB);
		handler.removeMessages(PLAYVIDEO);
		handler.removeMessages(CANCLEEXIT);
	}

	public void init_result() {
		if (madapter != null) {
			Log.v("wangliang", "添加数据");
			madapter.addData(list_data);
//		mFocusHandler.sendEmptyMessageDelayed(10, 100);
		} else {
			madapter = new ListAdapter(mContext, list_data, 1);


			madapter.setAlp(listView.getLastVisiblePosition());
			listView.setAdapter(madapter);
			if (!TextUtils.isEmpty(madapter.mlist().getData().get(0).getVideoUrl())){

				handler.sendEmptyMessageDelayed(PLAYVIDEO, 800);
			}else{


				handler.sendEmptyMessageDelayed(PLAYWEB,800);
			}
			listView.setDefualtSelect(0);
			madapter.setSelectItem(0);

			listView.setOnScrollListener(new AbsListView.OnScrollListener() {
				@Override
				public void onScrollStateChanged(AbsListView absListView, int i) {
					MyLog.v(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + i);
				}

				@Override
				public void onScroll(AbsListView absListView, int i, int i1, int i2) {
					if (ismore == false) {
						MyLog.v("没有更多了啊》》》" + i);
						return;
					} else {
						MyLog.v("加载更多大大得读》》》" + i);
						if (listView.getCount() != 0 && selectindex >= (listView.getCount() - 2)) {
							if (isloadmore == false) {
								index++;

								get_newlist(Url.newsurl + mContent.getInterfaceX() + "&pageNum=" + index + "&getSize=" + size);
							}

						}
					}

				}
			});
		}
	}

	private int progress = 0;

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

}
