package com.shadt.iptv_qx.adapter;

import android.content.Context;
import android.os.Handler;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shadt.iptv_qx.R;
import com.open.androidtvwidget.BuildConfig;
import com.shadt.iptv_qx.Application.BaseApplication;
import com.shadt.iptv_qx.model.newlistInfo;
import com.shadt.iptv_qx.utils.Url;

import java.util.Random;


/**
 * Created by chensuilun on 2016/11/15.
 */
public class RecycHAdapter extends RecyclerView.Adapter<RecycHAdapter.ViewHolder> implements View.OnClickListener {
    public static final int VIEW_TYPE_IMAGE = 0;
    public static final int VIEW_TYPE_TEXT = 1;
    private static final String TAG = "RecycAdapter";

    private int mType = VIEW_TYPE_IMAGE;
    private OnItemClickListener mOnItemClickListener;

    Context mContext;
    newlistInfo list;
    public RecycHAdapter(newlistInfo list, Context mContext) {
        this.list = list;

        this.mContext = mContext;

    }


    private static final Random RANDOM = new Random();



    private int selectedIndex=-1;        //记录当前选中的条目索引

    public void setSelectedIndex(int position) {
        this.selectedIndex = position;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                notifyDataSetChanged();

            }
        }, 1);
    }

    public RecycHAdapter setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
        return this;
    }

    @Override
    public int getItemViewType(int position) {
        return mType;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, "onCreateViewHolder: type:" + viewType);
        }
        View v;

        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_demo, parent, false);

        v.setOnClickListener(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int width = mContext.getResources().getDisplayMetrics().widthPixels;
        params.width = (int)(width / 4.5);
        params.height = width / 7;
        v.setLayoutParams(params);
        return new ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onBindViewHolder: position:" + position);
        }


//        holder.item_tv_position.setText(""+(position % mItems.size()+record1));
        holder.text.setText(list.getData().get(position).getTitle());
        holder.text.setSelected(true);


            Glide.with(mContext).load(list.getData().get(position).getImg().startsWith("http")==true?list.getData().get(position).getImg(): Url.headurl+list.getData().get(position).getImg()).apply(BaseApplication.requestOptions).into((ImageView) holder.iv);


        if (selectedIndex == position) {

            holder.cv.setBackgroundResource(R.drawable.bk4);
        } else {                                                            //非选中状态
             holder.cv.setBackgroundResource(R.color.transparent);

        }
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.getData().size();
    }

    @Override
    public void onClick(final View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    /**
     * @author chensuilun
     */
    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text,item_tv_position;
        public RelativeLayout rl_bk;
        ImageView iv;
        public CardView cv;
        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.item_tv_title);
            item_tv_position = (TextView) itemView.findViewById(R.id.item_tv_position);
            rl_bk = (RelativeLayout) itemView.findViewById(R.id.rl_bk);
            iv = (ImageView) itemView.findViewById(R.id.img);
            cv=(CardView)itemView.findViewById(R.id.Cv);
        }
    }

    /**
     * @author chensuilun
     */
    public interface OnItemClickListener {

        void onItemClick(View view, int position);

    }

}
