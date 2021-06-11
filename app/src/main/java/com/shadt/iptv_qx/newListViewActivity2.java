package com.shadt.iptv_qx;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.shadt.iptv_qx.Application.BaseApplication;
import com.shadt.iptv_qx.adapter.RecycHAdapter;
import com.shadt.iptv_qx.model.Visualization;
import com.shadt.iptv_qx.model.newlistInfo;
import com.shadt.iptv_qx.utils.JsonUtils;
import com.shadt.iptv_qx.utils.MyLog;
import com.shadt.iptv_qx.utils.Url;
import com.shadt.iptv_qx.widget.RecyclerCoverFlow;
import com.shadt.iptv_qx.widget.TestWebView;
import com.wang.avi.AVLoadingIndicatorView;
import java.util.ArrayList;
import java.util.List;


public class newListViewActivity2 extends BaseActivity {




	Visualization.DataBean.ContentBean mContent;
	Context mContext = newListViewActivity2.this;
	private AVLoadingIndicatorView AVload;
	public int RECYCLER_DISMISS=3;
	public int RECYCLER_SHOW=4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recycylist_h_view);
		AVload=findViewById(R.id.AVload);
		AVload.setIndicator("BallSpinFadeLoaderIndicator");
		Bundle bundle = getIntent().getBundleExtra("bd");
		  mContent = new Visualization.DataBean.ContentBean();
		if (bundle!=null){
			mContent= (Visualization.DataBean.ContentBean) bundle.getSerializable("content");
			Log.v(BaseApplication.TAG,">>>>>>>>>>>>>>>"+mContent.getInterfaceX());
		}

		recycler_coverflow = (RecyclerCoverFlow) findViewById(R.id.recycler_coverflow);
		recycler_coverflow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				webView.loadUrl(list_data.getData().get(sel_pot).getRecordJumpUrl()+"&onapp=yes");
			}
		});
		init_center();

	}



	RecyclerCoverFlow recycler_coverflow;
	RecycHAdapter demoAdapter1;
	int sel_pot = 0;
	Animation animation;
	final List<String> title = new ArrayList<String>();
	final List<String> img = new ArrayList<String>();
	public TestWebView webView;
	public float webheight=0;
	public  int webscroheight=30;
	boolean is_bottom=false,is_top=true,ispress=false;

	public void init_center() {




		MyLog.v("size:" + size);



		webView=findViewById(R.id.web);

		WebSettings settings = webView.getSettings();
		//支持javascript
		settings.setJavaScriptEnabled(true);
		//设置可以支持缩放
		settings.setSupportZoom(true);
		//设置出现缩放工具
		settings.setBuiltInZoomControls(true);
		//扩大比例的缩放
		settings.setUseWideViewPort(true);
		//自适应屏幕
		settings.setTextZoom(150);
		settings.setLoadWithOverviewMode(true);
		webView.setWebViewClient(new WebViewClient(){
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				AVload.hide();
 			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				AVload.show();
			}
		});
		webView.setOnCustomScroolChangeListener(new TestWebView.ScrollInterface() {
			@Override
			public void onSChanged(int l, int t, int oldl, int oldt) {
				//WebView的总高度
				float webViewContentHeight=webView.getContentHeight() * webView.getScale();
				//WebView的现高度
				float webViewCurrentHeight=(webView.getHeight() + webView.getScrollY());
				webheight=webViewContentHeight;

				System.out.println("webViewContentHeight="+webViewContentHeight);

				if (webView.getScrollY()==0){

					is_top=true;
					is_bottom=false;
				}else{
					is_top=false;
				}
				if ((webViewContentHeight-webViewCurrentHeight) == 0) {
					System.out.println("WebView滑动到了底端");

					is_bottom=true;
					is_top=false;
				}else{
					is_bottom=false;
				}
			}
		});
		get_newlist(Url.newsurl+mContent.getInterfaceX()+"&pageNum="+index+"&getSize="+size);

		animation = AnimationUtils.loadAnimation(newListViewActivity2.this, R.anim.reward_launcher);

//		recycler_coverflow.setAlphaItem(true);
		recycler_coverflow.setIntervalRatio(0.7f);
		recycler_coverflow.setAnimation(animation);
		sel_pot=0;


	}

	private static double DOUBLE_CLICK_TIME = 0L;
	private boolean isLongPressKey;
	private boolean lockLongPressKey;//判断长按还是
	private boolean isDoublePressKey;//判断是否快速点击
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		switch (keyCode) {
			case KeyEvent.KEYCODE_MENU:
				if (recycler_coverflow.getVisibility()==View.GONE) {
					Log.v(BaseApplication.TAG, "菜单键111111");
					mhandler.sendEmptyMessageDelayed(RECYCLER_SHOW, 500);
				}
				Log.v(BaseApplication.TAG, "菜单键"+recycler_coverflow.getVisibility());
				break;
			case KeyEvent.KEYCODE_ENTER:     //确定键enter

				Log.v(BaseApplication.TAG, "确定键enter");

				break;


			case KeyEvent.KEYCODE_DPAD_DOWN:   //向下键
				if (recycler_coverflow.getVisibility()==View.VISIBLE){
					mhandler.sendEmptyMessageDelayed(RECYCLER_DISMISS,500);
				}

				if (webheight==0.0){
					webView.setScrollY(webView.getScrollY()+webscroheight);
				}else{
					if (is_bottom==false){
						if (  webView.getScrollY()+webscroheight+webView.getHeight()<webheight){

							webView.setScrollY(webView.getScrollY()+webscroheight);
						}else{
//							webView.setScrollY((int)webheight);

						}
					}

				}
				Log.v(BaseApplication.TAG, "ACTION_DOWN");
				break;

			case KeyEvent.KEYCODE_DPAD_UP:   //向上键
				if (is_top==true){

				}else{
					webView.setScrollY(webView.getScrollY()-webscroheight);
				}

				break;


			case KeyEvent.KEYCODE_DPAD_LEFT: //向左键
				Log.v(BaseApplication.TAG, "向左键");

//
//				if (event.getRepeatCount() == 0) {
//					event.startTracking();
//					isLongPressKey = false;
//					if ((System.currentTimeMillis() - DOUBLE_CLICK_TIME) > 100) {
//
//						isDoublePressKey = false;
//
//					} else {
//						isDoublePressKey = true;
//
//					}
//					DOUBLE_CLICK_TIME = System.currentTimeMillis();
//					return true;
//				} else {
//
//					isLongPressKey = true;
//					return true;
//				}
				sel_pot = sel_pot - 1;
					if (sel_pot < 0) {
						sel_pot = 0;
					}
					recycler_coverflow.smoothScrollToPosition(sel_pot);
				break;
			case KeyEvent.KEYCODE_DPAD_RIGHT:  //向右键
				move_left_right_item_data();

//                initData3();

//				if (event.getRepeatCount() == 0) {
//					event.startTracking();
//					isLongPressKey = false;
//					if ((System.currentTimeMillis() - DOUBLE_CLICK_TIME) > 600) {
//
//						isDoublePressKey = false;
//
//					} else {
//						isDoublePressKey = true;
//
//					}
//					DOUBLE_CLICK_TIME = System.currentTimeMillis();
//					return true;
//				} else {
//
//					isLongPressKey = true;
//					return true;
//				}
break;



			default:
				break;
		}

		return super.onKeyDown(keyCode, event);

	}
	public boolean isloadmore=false;
//	@Override
//
////	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
////		switch (keyCode) {
////			case KeyEvent.KEYCODE_DPAD_DOWN:
////				lockLongPressKey = true;
////				return true;
////		}
////		return super.onKeyDown(keyCode, event);
////	}
//
//	int mKeyTimes = 0;//用来按下的上下键长按执行的次数int mKeyRunTimes=0;


//	// @Override
//	public boolean onKeyUp(int keyCode, KeyEvent event) {
//		if (event.getAction() == KeyEvent.ACTION_UP && isDoublePressKey) {
//			mKeyTimes++;
//		}
//		switch (keyCode) {
//			case KeyEvent.KEYCODE_DPAD_RIGHT:
//				if (lockLongPressKey) {
//
//					// TODO: 2017/6/11 加载数据
//					isLongPressKey = false;
//					lockLongPressKey = false;
//
//
//					move_left_right_item_data();
//				} else {
//					if (!isLongPressKey) {
////                        Log.d("shadt", "这里面是快速点击" );
//						if (isDoublePressKey) {
//							//任务延时加载
//							//         Log.d("shadt", "这里会重复走几次，快速点击" );
//						} else {
//							// TODO: 2017/6/11 加载数据
////                            Log.d("shadt", "这里会重复走几次，这里走第一次" );
//
//							move_left_right_item_data();
//						}
//					} else {
//						//这是长按只走一次
//
//						move_left_right_item_data();
//					}
//
//				}
//				return true;
//			case KeyEvent.KEYCODE_DPAD_LEFT:
//				if (lockLongPressKey) {
//
//					// TODO: 2017/6/11 加载数据
//					isLongPressKey = false;
//					lockLongPressKey = false;
//
//					sel_pot = sel_pot - 1;
//					if (sel_pot < 0) {
//						sel_pot = 0;
//					}
//					recycler_coverflow.smoothScrollToPosition(sel_pot);
////                    move_left_right_item_data();
//				} else {
//					if (!isLongPressKey) {
////                        Log.d("shadt", "这里面是快速点击" );
//						if (isDoublePressKey) {
//							//任务延时加载
//							//         Log.d("shadt", "这里会重复走几次，快速点击" );
//						} else {
//							// TODO: 2017/6/11 加载数据
////                            Log.d("shadt", "这里会重复走几次，这里走第一次" );
//							sel_pot = sel_pot - 1;
//							if (sel_pot < 0) {
//								sel_pot = 0;
//							}
//							recycler_coverflow.smoothScrollToPosition(sel_pot);
//						}
//					} else {
//						//这是长按只走一次
//						sel_pot = sel_pot - 1;
//						if (sel_pot < 0) {
//							sel_pot = 0;
//						}
//						recycler_coverflow.smoothScrollToPosition(sel_pot);
//					}
//
//				}
//				return true;
//		}
//
//		return super.onKeyUp(keyCode, event);
//	}

	private void move_left_right_item_data() {
		if (isloadmore==true){
			Log.v(BaseApplication.TAG, "别慌加载更多呢");

		}else{

			sel_pot = sel_pot + 1;
			if (ismore==false){
				if (sel_pot>=demoAdapter1.getItemCount()){
					sel_pot = sel_pot - 1;

					MyLog.v( "？？？？？？？？？？？？？？？"+sel_pot);
					return;
				}else{
					recycler_coverflow.smoothScrollToPosition(sel_pot);
				}

			}else{
				if (sel_pot>=demoAdapter1.getItemCount()-2){

					isloadmore=true;
					MyLog.v( "加载更多,目前没有什么更好的办法，重新绑定");
//                  demoAdapter1.addData(mContext);
					index++;
					get_newlist(Url.newsurl+mContent.getInterfaceX()+"&pageNum="+index+"&getSize="+size);


				}else{
					isloadmore=false;
				}
			}



				recycler_coverflow.smoothScrollToPosition(sel_pot);



		}
	}


	newlistInfo list_data=new newlistInfo();
	boolean ismore=true;
	int index=1,size=30;

	public  void get_newlist(String posturl) {
		AVload.setVisibility(View.VISIBLE);
		MyLog.v("请求接口"+posturl);
		RequestParams params = new RequestParams();
		isloadmore=true;
		HttpUtils httpUtils = new HttpUtils();

		httpUtils.send(HttpRequest.HttpMethod.GET, posturl, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				AVload.setVisibility(View.GONE);
				isloadmore=false;
			}
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				String str = arg0.result;
				isloadmore=false;
				AVload.setVisibility(View.GONE);
				newlistInfo mList=new newlistInfo();
				MyLog.v("请求数据返回数据"+str);
				try {

					mList=  JsonUtils.getModel(str, newlistInfo.class);
				}catch (Error e){
					return;
				}

				if (mList==null){
					ismore=false;
					MyLog.v( "请求数据为空");
					return;
				}
				if (mList.getReturnCode()!=0){
					MyLog.v( "请求数据出错");
					ismore=false;
					return;
				}

				if (mList!=null && mList.getData().size()==size){
					MyLog.v("请求数据》》"+ismore);
					ismore=true;
				}else{

					ismore=false;
					MyLog.v("没有更多了"+ismore);
				}
				isloadmore=false;
				if (demoAdapter1!=null){

						Log.v("wangliang","第二次添加");
						for (int i=0;i<mList.getData().size();i++){
							list_data.getData().add(mList.getData().get(i));
						}
					MyLog.v( "第二次"+list_data.getData().size());
//					demoAdapter1=null;
//
//					demoAdapter1 = new RecycHAdapter(list_data, mContext);
//
//						mhandler.sendEmptyMessage(1);

				}else{
					list_data=mList;
					MyLog.v("第一次为空啊"+list_data.getData().size());
					demoAdapter1 = new RecycHAdapter(list_data, mContext);
					recycler_coverflow.setAdapter(demoAdapter1);
					demoAdapter1.setOnItemClickListener(new RecycHAdapter.OnItemClickListener() {
						@Override
						public void onItemClick(View view, int position) {
							recycler_coverflow.smoothScrollToPosition(position);
						}
					});
				}
				recycler_coverflow.smoothScrollToPosition(sel_pot);
				webView.loadUrl(list_data.getData().get(sel_pot).getRecordJumpUrl()+"&onapp=yes");


			}
		});
	}


Handler mhandler=new Handler(){
	@Override
	public void handleMessage(@NonNull Message msg) {
		super.handleMessage(msg);
		  if(msg.what==RECYCLER_DISMISS){
//			animation = AnimationUtils.loadAnimation(mContext, R.anim.push_bottom_out);
//			recycler_coverflow.setAnimation(animation);
			recycler_coverflow.setVisibility(View.GONE);
		}else if(msg.what==RECYCLER_SHOW){
			recycler_coverflow.setVisibility(View.VISIBLE);
			animation = AnimationUtils.loadAnimation(mContext, R.anim.reward_launcher);
			recycler_coverflow.setAnimation(animation);
		}
	}
};





}
