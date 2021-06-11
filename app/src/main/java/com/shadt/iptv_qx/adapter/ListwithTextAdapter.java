package com.shadt.iptv_qx.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shadt.iptv_qx.R;
import com.shadt.iptv_qx.Application.BaseApplication;
import com.shadt.iptv_qx.model.newlistInfo;
import com.shadt.iptv_qx.utils.FontSize;
import com.shadt.iptv_qx.utils.MyLog;

public class ListwithTextAdapter extends BaseAdapter {
    private  Context mContext;
    private newlistInfo list;
    private float height;
    private int fontsize;
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
    public ListwithTextAdapter(Context mContext, newlistInfo list, int target,float height,int fontsize){

            this.mContext=mContext;
            this.list=list;
            this.target=target;
            this.height=height;
            this.fontsize=fontsize;

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


                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_listview_text, null);
				holder.title = (TextView) convertView.findViewById(R.id.tv);

                holder.line=(LinearLayout)convertView.findViewById(R.id.line);
                //0表示Create_view创建的list,1表示newlistview里面的

                LinearLayout.LayoutParams line=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int)height/4);

                holder.line.setLayoutParams(line);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (selectItem==position){
                holder.title.setTextColor(Color.parseColor("#808080"));
             }else{
                holder.title.setTextColor(Color.parseColor("#ffffff"));
            }



//                final AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
//                alphaAnimation.setDuration(800);
//                convertView.startAnimation(alphaAnimation);

             holder.title.setText(list.getData().get(position).getTitle());


            holder.title.setTextSize(TypedValue.COMPLEX_UNIT_SP,(FontSize.px2sp(mContext,(fontsize/proportion))));
            return convertView;
        }

        public class ViewHolder {
            public TextView title;

            public LinearLayout line;
        }
    }


