package com.shadt.iptv_qx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shadt.iptv_qx.R;
import com.open.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.open.androidtvwidget.leanback.mode.OpenPresenter;
import com.shadt.iptv_qx.Application.BaseApplication;
import com.shadt.iptv_qx.model.newlistInfo;

/**
 * 测试.
 * Created by hailongqiu on 2016/8/24.
 */
public class RecyclerViewPresenter extends OpenPresenter {

    private  newlistInfo mlist;
    private GeneralAdapter mAdapter;
    private Context mContext;
    private int mposition=-1;

    private float proportion= BaseApplication.proportion;
    private int img_w;
    private int img_h;
    private int fontsize;
    public RecyclerViewPresenter(Context mContext,newlistInfo mlist, int img_w,int img_h,int fontsize) {
       this.mlist=mlist;
       this.mContext=mContext;
        this.img_h=img_h;
        this.img_w=img_w;
        this.fontsize=fontsize;
    }

    @Override
    public void setAdapter(GeneralAdapter adapter) {
        this.mAdapter = adapter;
    }

    /**
     * 用于数据加载更多测试.
     */
    public void addDatas(newlistInfo list) {
        for (int i=0;i<list.getData().size();i++){
            this.mlist.getData().add(list.getData().get(i));
        }

        this.mAdapter.notifyDataSetChanged();
    }
    public newlistInfo mlist(){
        return this.mlist;
    }

    @Override
    public int getItemCount() {
        return mlist.getData().size();
    }
    public void setfouces(int position){
        this.mposition=position;
        mAdapter.notifyDataSetChanged();
    }
    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        GridViewHolder gridViewHolder = (GridViewHolder) viewHolder;
        TextView textView = (TextView) gridViewHolder.tv;
        ImageView iv=(ImageView)  gridViewHolder.iv;
        textView.setText( mlist.getData().get(position).getTitle());
        LinearLayout.LayoutParams      lp = new LinearLayout.LayoutParams((int)(img_w/proportion), (int)(img_h/proportion));
//        lp.setMargins(10,10,10,0);
        Glide.with(mContext).load(mlist.getData().get(position).getImg()).apply(BaseApplication.requestOptions).into((ImageView) iv);
        iv.setLayoutParams(lp);

    }

}
