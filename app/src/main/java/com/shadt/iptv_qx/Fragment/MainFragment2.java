package com.shadt.iptv_qx.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.shadt.iptv_qx.R;
import com.open.androidtvwidget.view.FrameMainLayout;
import com.shadt.iptv_qx.utils.Create_views;
import com.shadt.iptv_qx.utils.MyLog;


public class MainFragment2 extends Fragment {
    public static MainFragment2 newInstance(int pageType,String posturl) {

        MainFragment2 f = new MainFragment2();
        Bundle b = new Bundle();

        b.putInt("pageType", pageType);
        b.putString("posturl", posturl);
        f.setArguments(b);
        return f;
    }

    Context mContext;
    View view;
    FrameMainLayout page1_main_lay;
    View first_view;
    float proportion=0;
//    Banner banner;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.test_page1, null);
        mContext = getActivity();

        initData();
        page1_main_lay = (FrameMainLayout) view.findViewById(R.id.page1_main_lay);
//        banner=(Banner) view.findViewById(R.id.banner);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        if (screenWidth==1920){
            proportion=1;
        }else if(screenWidth==1280){
            proportion=1.5f;
        }
//        banner=(Banner) view.findViewById(R.id.banner);
        String url = "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1598251131&di=54b500f8798f80fb2f176b24834171b2&src=http://a3.att.hudong.com/14/75/01300000164186121366756803686.jpg";
//        first_view = Create_views.create_main_view(mContext, page1_main_lay, pageType, 0, true, 840, 394, 96, 204, url,proportion);
//        Create_views.create_main_banner(mContext, page1_main_lay, pageType, 1, false, 404, 246, 96, 630,proportion,0);
//        Create_views.create_main_news(mContext, page1_main_lay, pageType, 2, true, 512, 672, 1412, 204, url,proportion,5000,posturl);
//        Create_views.create_main_view(mContext, page1_main_lay, pageType, 3, true, 412, 672, 968, 204, url,proportion);
//        Create_views.create_main_view(mContext, page1_main_lay, pageType, 4, false, 404, 246, 532, 630, url,proportion);
//            Create_views.create_main_view(mContext,page1_main_lay,i,5,true,412,672,1858,204,url);

        MyLog.v("chuangjian");
//        initBanner();
        return view;
    }

    int pageType = 0;
    String posturl="";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {

            pageType = args.getInt("pageType");
            posturl = args.getString("posturl");
        }

    }
    private void initData() {
//        mData.add(new BannerItem(R.drawable.banner_1, ""));
//        mData.add(new BannerItem(R.drawable.banner_2, ""));
//        mData.add(new BannerItem(R.drawable.vagetable_item_img, ""));
//        mData.add(new BannerItem(R.drawable.vagetable_item_img, ""));
//        mData.add(new BannerItem(R.drawable.vagetable_item_img, ""));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Create_views.myhandler();

    }

    @Override
    public void setUserVisibleHint(final boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
       /* new Handler().post(new Runnable() {
            @Override
            public void run() {*/

//        if (isVisibleToUser == true) {
//            if (first_view != null) {
//
//
//                if (MainActivity.is_real_top == true) {
//
//                    MainActivity.is_real_top = false;
//                    Log.v("shadt", "1111111111111111");
//                } else {
//                    Log.v("shadt", "eeeeee");
//                    first_view.requestFocus();
//                }
//
//            } else{
//                Log.v("shadt", "空的啊");
////                first_view.requestFocus();
//            }
//
//        }

        /*    }
        });*/

    }

}

