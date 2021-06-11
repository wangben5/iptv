package com.shadt.iptv_qx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.open.androidtvwidget.utils.OPENLOG;

import com.shadt.iptv_qx.model.VagetablInfo;
import com.shadt.iptv_qx.utils.JsonUtils;
import com.shadt.iptv_qx.utils.MyLog;
import com.shadt.iptv_qx.widget.RecyclerCoverFlow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class VagetableActivity extends Activity {


    Context mContext = VagetableActivity.this;

    String title = "";
    //    SpeechHelper mSpeechHelper;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vagetable_activity);
        sp = getSharedPreferences("tv_data", 0);
        sel_pot = getIntent().getIntExtra("position", 0);
        mQuestionAndAnsMob = (VagetablInfo) getIntent().getSerializableExtra("content");
        MyLog.v(">>>>>>>>>>>>>>>>>>>>>.oncrate" + sel_pot);
//        startts("为您找到"+level_three.getData().size()+"条数据");
        init();

    }

    @Override
    protected void onNewIntent(Intent intent) {


        int postion = sel_pot % mQuestionAndAnsMob.getData().size();

        int intent_position = 0;


        sel_pot = sel_pot - postion + intent_position;

        recycler_coverflow.smoothScrollToPosition(sel_pot);
        postion = sel_pot % mQuestionAndAnsMob.getData().size();
        caregoryName = "【商品类别】" + mQuestionAndAnsMob.getData().get(postion).getCaregoryName();
        unit = "【计量单位】" + mQuestionAndAnsMob.getData().get(postion).getUnit();
        price = "" + mQuestionAndAnsMob.getData().get(postion).getPrice() + "元/Kg";
        memberPrice = "【会员价格】" + mQuestionAndAnsMob.getData().get(postion).getMemberPrice() + "元/Kg";

        sellerName = "【商家】" + mQuestionAndAnsMob.getData().get(postion).getSellerName();
        updateTime = "【上架时间】" + getDateToString(mQuestionAndAnsMob.getData().get(postion).getUpdateTime());

        super.onNewIntent(intent);
    }

     RecyclerCoverFlow recycler_coverflow;

    String content = "(-)、查询内容\n纳税人通过网络查询代缴单位为个人所得税代缴申报的信息。\n（二）、查询条件\n身份信息已经核准,即已持本人有效证照到税务机关进行了核准,bqi并且身份信息填写完整,即已在'个人信息平台’系统中完成信息登记。\n（三）、查询步骤\n1.登录个人所得税服务管理信息系统网站（http://shadt.com);\n2.点击'个人纳税信息查询,进入'个人纳税信息'页面',点击进入;\n3.选择证照类型,输入证照号码、密码、验证码,点击登录;\n4、登录成功后出现'纳税信息查询'页面,选择年度进行查询。";
    int sel_pot = 2004;
    Animation animation;

    public void init() {

        recycler_coverflow = (RecyclerCoverFlow) findViewById(R.id.recycler_coverflow);

        mQuestionAndAnsMob=new VagetablInfo();
        mQuestionAndAnsMob.setMessage("111");
        mQuestionAndAnsMob.setStatus("1");
        List<VagetablInfo.DataBean> mlst=new ArrayList<>();

        for (int i=0;i<10;i++){
            VagetablInfo.DataBean md=new VagetablInfo.DataBean();
            md.setCategoryId(i);
            md.setGoodsName("ddd"+i);
            mlst.add(md);
        }
        mQuestionAndAnsMob.setData(mlst);
        init_center();
        MyLog.v("seize"+mQuestionAndAnsMob);
    }

    public void init_center() {


        final List<String> title = new ArrayList<String>();
        final List<String> img = new ArrayList<String>();
        int size = mQuestionAndAnsMob.getData().size();
        if (size % 2 == 0) {
            sel_pot = mQuestionAndAnsMob.getData().size() * 500 + sel_pot;
        } else {
            sel_pot = mQuestionAndAnsMob.getData().size() * 500 + sel_pot;
        }
        for (int i = 0; i < size; i++) {
            title.add("" + mQuestionAndAnsMob.getData().get(i).getGoodsName());
            img.add("");
        }

        animation = AnimationUtils.loadAnimation(VagetableActivity.this, R.anim.reward_launcher);

        recycler_coverflow.setAlphaItem(true);
        recycler_coverflow.setIntervalRatio(0.55f);

        recycler_coverflow.smoothScrollToPosition(sel_pot);
        recycler_coverflow.setAnimation(animation);

        postion = sel_pot % mQuestionAndAnsMob.getData().size();
        caregoryName = "【商品类别】" + mQuestionAndAnsMob.getData().get(postion).getCaregoryName();
        unit = "【计量单位】" + mQuestionAndAnsMob.getData().get(postion).getUnit();
        price = "" + mQuestionAndAnsMob.getData().get(postion).getPrice() + "元/Kg";
        memberPrice = "【会员价格】" + mQuestionAndAnsMob.getData().get(postion).getMemberPrice() + "元/Kg";

        sellerName = "【商家】" + mQuestionAndAnsMob.getData().get(postion).getSellerName();
        updateTime = "【上架时间】" + getDateToString(mQuestionAndAnsMob.getData().get(postion).getUpdateTime());

//        post_getinfo("0", level_three.getData().get(sel_pot % level_three.getData().size()).getTitle());
    }

    String caregoryName, unit, price, memberPrice, allowWeigh, sellerName, updateTime;

    int postion = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {

            case KeyEvent.KEYCODE_ENTER:     //确定键enter
               /* mhander.removeMessages(0);
                is_read=false;
                stoptts();*/


                break;


            case KeyEvent.KEYCODE_DPAD_DOWN:   //向下键

//                *//**//*    实际开发中有时候会触发两次，所以要判断一下按下时触发 ，松开按键时不触发
//                 *    exp:KeyEvent.ACTION_UP
//                 *//**//*


                Log.v("shadt", "ACTION_DOWN");
                break;

            case KeyEvent.KEYCODE_DPAD_UP:   //向上键
                Log.v("shadt", "上");


                break;


            case KeyEvent.KEYCODE_DPAD_LEFT: //向左键
                Log.v("shadt", "向左键");
                if (mQuestionAndAnsMob.getData().size() != 0) {

                    sel_pot = sel_pot - 1;
                    recycler_coverflow.smoothScrollToPosition(sel_pot);

                    postion = sel_pot % mQuestionAndAnsMob.getData().size();
                    caregoryName = "【商品类别】" + mQuestionAndAnsMob.getData().get(postion).getCaregoryName();
                    unit = "【计量单位】" + mQuestionAndAnsMob.getData().get(postion).getUnit();
                    price = "" + mQuestionAndAnsMob.getData().get(postion).getPrice() + "元/Kg";
                    memberPrice = "【会员价格】" + mQuestionAndAnsMob.getData().get(postion).getMemberPrice() + "元/Kg";

                    sellerName = "【商家】" + mQuestionAndAnsMob.getData().get(postion).getSellerName();
                    updateTime = "【上架时间】" + getDateToString(mQuestionAndAnsMob.getData().get(postion).getUpdateTime());


//                mSpeechHelper.stopTTS();

                }
                break;

            case KeyEvent.KEYCODE_DPAD_RIGHT:  //向右键
                if (mQuestionAndAnsMob.getData().size() != 0) {


                    sel_pot = sel_pot + 1;
                    recycler_coverflow.smoothScrollToPosition(sel_pot);
                    postion = sel_pot % mQuestionAndAnsMob.getData().size();
                    caregoryName = "【商品类别】" + mQuestionAndAnsMob.getData().get(postion).getCaregoryName();
                    unit = "【计量单位】" + mQuestionAndAnsMob.getData().get(postion).getUnit();
                    price = "" + mQuestionAndAnsMob.getData().get(postion).getPrice() + "元/Kg";
                    memberPrice = "【会员价格】" + mQuestionAndAnsMob.getData().get(postion).getMemberPrice() + "元/Kg";

                    sellerName = "【商家】" + mQuestionAndAnsMob.getData().get(postion).getSellerName();
                    updateTime = "【上架时间】" + getDateToString(mQuestionAndAnsMob.getData().get(postion).getUpdateTime());



//                mSpeechHelper.stopTTS();
                }
                break;
            case KeyEvent.KEYCODE_BACK:
                MyLog.v("返回");

//                mhander.sendEmptyMessageDelayed(3,1000);

                break;

            default:
                break;
        }

        return super.onKeyDown(keyCode, event);

    }


    VagetablInfo mQuestionAndAnsMob;
//    private List<QuestionAndAnsMob.RelatedAnswersBean> relatedAnswers;

    public void post_getinfo() {
//        String content="我想办居住证";


        RequestParams params = new RequestParams();

        HttpUtils httpUtils = new HttpUtils();

        String post_url = "http://oa.joinval.com/VegeMarket/getBestGoods";
        MyLog.v(post_url);
        httpUtils.send(HttpRequest.HttpMethod.POST, post_url, params, new RequestCallBack<String>() {

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                // TODO Auto-generated method stub
                OPENLOG.V("失败：" + arg1.toString());


            }

            @Override
            public void onSuccess(ResponseInfo<String> arg0) {
                // TODO Auto-generated method stub
                String str = arg0.result;
                MyLog.v("成功：" + str);
                mQuestionAndAnsMob = JsonUtils.getModel(str, VagetablInfo.class);//
                if (mQuestionAndAnsMob.getStatus().equals("0000")) {
                    init_center();
                } else {

                }

            }
        });
    }

    String readcontent;


    Runnable runable_start = new Runnable() {

        @Override
        public void run() {
            // TODO
            // 在这里进行 http request.网络请求相关操作
            Looper.prepare();
            try {
                MyLog.v("没有走吗");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Looper.loop();
        }
    };

    public static String getDateToString(long milSecond) {
        String pattern = "yyyy-MM-dd";
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }


}
