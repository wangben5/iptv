package com.shadt.iptv_qx.adapter;

import android.content.Context;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shadt.iptv_qx.R;
import com.shadt.iptv_qx.Application.BaseApplication;
import com.shadt.iptv_qx.model.newlistInfo;

public class RecycNewsAdapter extends RecyclerView.Adapter<RecycNewsAdapter.VH> {
    Context mContext;
    newlistInfo mlist;
    float height=0,width=0;
    public RecycNewsAdapter(Context mContext, newlistInfo mlist, float width, float height) {
        this.mlist=mlist;
        this.mContext=mContext;
        this.height=height;
        this.width=width;
    }
        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new VH(LayoutInflater.from(mContext).inflate(R.layout.item_rv,viewGroup,false));
        }

        @Override
        public void onBindViewHolder(@NonNull VH viewHolder, int i) {
//          viewHolder.tv.setText(datas.get(i % datas.size()));
                                Glide.with(mContext).load(mlist.getData().get(i).getImg()).apply(BaseApplication.requestOptions).into((ImageView) viewHolder.iv);

        }

        @Override
        public int getItemCount() {
            return mlist.getData().size();
        }

        class VH extends RecyclerView.ViewHolder{
          ImageView iv;
            public VH(@NonNull View itemView) {
                super(itemView);
                iv = itemView.findViewById(R.id.iv);
             LinearLayout.LayoutParams   lp = new LinearLayout.LayoutParams((int)width,(int) height);
             iv.setLayoutParams(lp);
            }
        }
    }