package com.shadt.iptv_qx.adapter;

import android.content.Context;


import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shadt.iptv_qx.R;
import com.shadt.iptv_qx.Application.BaseApplication;
import com.shadt.iptv_qx.model.newlistInfo;

public class RecycBannerAdapter extends RecyclerView.Adapter<RecycBannerAdapter.VH> {
    Context mContext;
    newlistInfo mlist;
    float height=0,width=0;
    public RecycBannerAdapter(Context mContext, newlistInfo mlist,float height) {
        this.mlist=mlist;
        this.mContext=mContext;
        this.height=height;
    }
        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new VH(LayoutInflater.from(mContext).inflate(R.layout.item_banner_rv,viewGroup,false));
        }

        @Override
        public void onBindViewHolder(@NonNull VH viewHolder, int i) {
//          viewHolder.tv.setText(datas.get(i % datas.size()));

                                Glide.with(mContext).load(mlist.getData().get(i % mlist.getData().size()).getImg()).apply(BaseApplication.requestOptions).into((ImageView) viewHolder.iv);
                            viewHolder.tv.setText(mlist.getData().get(i % mlist.getData().size()).getTitle());
        }

        @Override
        public int getItemCount() {
            return Integer.MAX_VALUE;
        }

        class VH extends RecyclerView.ViewHolder{
          ImageView iv;
          TextView tv;
          RelativeLayout Rl,Rl_tv;
            public VH(@NonNull View itemView) {
                super(itemView);
                iv = itemView.findViewById(R.id.iv);
                tv=itemView.findViewById(R.id.tv);
                Rl=itemView.findViewById(R.id.Rl);
                Rl_tv=itemView.findViewById(R.id.Rl_tv);
                RelativeLayout.LayoutParams   lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,(int)height);
                Rl.setLayoutParams(lp);
                tv.setTextSize(BaseApplication.fontsize);
                tv.setGravity(Gravity.CENTER);
                if (BaseApplication.proportion==1.5f){
                 RelativeLayout.LayoutParams   fl_tv = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 40);
                    fl_tv.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    Rl_tv.setLayoutParams(fl_tv);
                }else{
                    RelativeLayout.LayoutParams   fl_tv = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 60);
                    fl_tv.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    Rl_tv.setLayoutParams(fl_tv);
                }
            }
        }
    }