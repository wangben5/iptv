package com.shadt.iptv_qx.adapter;

import com.shadt.iptv_qx.R;
import com.open.androidtvwidget.leanback.mode.OpenPresenter;



import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GridViewHolder extends OpenPresenter.ViewHolder {
	
	public ImageView iv; 
	public TextView tv;
	public TextView head_tv;
	LinearLayout line;
	public GridViewHolder(View itemView) {
		super(itemView);
		iv = (ImageView)itemView.findViewById(R.id.img);
		tv = (TextView)itemView.findViewById(R.id.tv);
		line=itemView.findViewById(R.id.line);
	}

}
