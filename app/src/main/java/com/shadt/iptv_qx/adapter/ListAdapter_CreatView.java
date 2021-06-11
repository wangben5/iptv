package com.shadt.iptv_qx.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shadt.iptv_qx.Application.BaseApplication;
import com.shadt.iptv_qx.R;
import com.shadt.iptv_qx.model.PageInfo2;
import com.shadt.iptv_qx.model.newlistInfo;
import com.shadt.iptv_qx.utils.FontSize;
import com.shadt.iptv_qx.utils.MyLog;
import com.shadt.iptv_qx.utils.Url;
import com.shadt.iptv_qx.utils.Util;

public class ListAdapter_CreatView extends BaseAdapter {
    private  Context mContext;
    private newlistInfo list;
    private int selectItem = -1;
    private int lastitem = -1;
    private  int target=0;   //0表示Create_view创建的list,1表示newlistview里面的
    private float proportion= BaseApplication.proportion;
    private int img_w;
    private int img_h;
    private int fontsize;
    private String fontcolor;
    private int gap;//间隔底部
    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
        notifyDataSetChanged();
    }
    boolean isfocus=false;
    public void setfocus(boolean isfocus){
        this.isfocus=isfocus;
    }
    public void addData(newlistInfo list) {
        for (int i=0;i<list.getData().size();i++){
            this.list.getData().add(list.getData().get(i));
        }
         notifyDataSetChanged();
    }
    public newlistInfo mlist(){
        return this.list;
    }
    public void setAlp(int lastitem){
        this.lastitem=lastitem;
        notifyDataSetChanged();
    }

    public ListAdapter_CreatView(Context mContext, newlistInfo list, int target, PageInfo2.PageDataBean mData){

            this.mContext=mContext;
            this.list=list;
            this.target=target;
            this.img_h=mData.getThumbH();
            this.img_w=mData.getThumbW();
            this.fontsize=mData.getFontSize();
            this.gap=mData.getGap();
            this.fontcolor=mData.getColor();
         }
        @Override
        public int getCount() {
            return list.getData().size();
        }

        @Override
        public Object getItem(int position) {
            return list.getData().get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                LinearLayout.LayoutParams lp ;
//                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(230, 130);
//                FrameLayout.LayoutParams linelp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);


                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_listview, null);
				holder.title = (TextView) convertView.findViewById(R.id.tv);
                holder.iv = (ImageView) convertView.findViewById(R.id.img);
                holder.line=(LinearLayout)convertView.findViewById(R.id.line);
                //0表示Create_view创建的list,1表示newlistview里面的

                        lp = new LinearLayout.LayoutParams((int)(img_w/proportion), (int)(img_h/proportion));
                        lp.setMargins(10,(int)(gap/proportion),10,0);

                    holder.title.setTextColor(Color.parseColor("#FFFFFF"));

                holder.iv.setLayoutParams(lp);
                holder.title.setLayoutParams(lp);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            holder.title.setTextColor(Util.setfontcolor(fontcolor));
            holder.title.setText(list.getData().get(position).getTitle());
            Glide.with(mContext).load(list.getData().get(position).getImg().startsWith("http")==true?list.getData().get(position).getImg(): Url.headurl+list.getData().get(position).getImg()).apply(BaseApplication.requestOptions).into((ImageView) holder.iv);

            holder.title.setTextSize(TypedValue.COMPLEX_UNIT_SP,(FontSize.px2sp(mContext,(fontsize/proportion))));
            return convertView;
        }

        public class ViewHolder {
            public TextView title;
            public ImageView iv;
            public LinearLayout line;
        }
    }


