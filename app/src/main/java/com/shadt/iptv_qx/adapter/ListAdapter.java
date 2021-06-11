package com.shadt.iptv_qx.adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.shadt.iptv_qx.R;
import com.shadt.iptv_qx.Application.BaseApplication;
import com.shadt.iptv_qx.model.newlistInfo;
import com.shadt.iptv_qx.utils.MyLog;
import com.shadt.iptv_qx.utils.Url;

public class ListAdapter  extends BaseAdapter {
    private  Context mContext;
    private newlistInfo list;
    private int selectItem = -1;
    private int lastitem = -1;
    private  int target=0;   //0表示Create_view创建的list,1表示newlistview里面的
    private float proportion=BaseApplication.proportion;

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
        notifyDataSetChanged();
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
    public  ListAdapter(Context mContext, newlistInfo list,int target){

            this.mContext=mContext;
            this.list=list;
            this.target=target;

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
                if (target==0){
//                    if (TextUtils.isEmpty(list.getData().get(position).getImg())){
//                        lp = new LinearLayout.LayoutParams(0, 0);
//                        lp.setMargins(10,20,10,0);
//                    }else{


                    if (proportion==1){
                        lp = new LinearLayout.LayoutParams(160, 106);
                        lp.setMargins(10,20,10,0);
                    }else{

                        lp = new LinearLayout.LayoutParams((int)(160/proportion), (int)(106/proportion));
                        lp.setMargins(10,10,10,0);
//                    }
                    }
                    holder.title.setTextColor(Color.parseColor("#FFFFFF"));

                    holder.iv.setLayoutParams(lp);
                }else{

                     holder.title.setTextColor(Color.parseColor("#ffffff"));

                }
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (selectItem==position){
                holder.line.setBackgroundColor(Color.parseColor("#FF0000"));
             }else{
                holder.line.setBackgroundColor(Color.parseColor("#00000000"));
            }

                if (lastitem==position ){
                    convertView.setAlpha(0.2f);

                }else{
                    convertView.setAlpha(1f);
                }

            if (selectItem==-1){
                final AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
                alphaAnimation.setDuration(800);
                convertView.startAnimation(alphaAnimation);
            }

            holder.title.setText(list.getData().get(position).getTitle());

            Glide.with(mContext).load(list.getData().get(position).getImg().startsWith("http")==true?list.getData().get(position).getImg(): Url.headurl+list.getData().get(position).getImg()).apply(BaseApplication.requestOptions).into((ImageView) holder.iv);

            holder.title.setTextSize(BaseApplication.fontsize_news);

            return convertView;
        }

        public class ViewHolder {
            public TextView title;
            public ImageView iv;
            public LinearLayout line;
        }
    }


