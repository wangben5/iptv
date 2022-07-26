package com.shadt.iptv_qx.widget;

import android.content.Context;

import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 类名：ScrollTextView
 * 描述：自动滚动的TextView,跑马灯效果
 * 创建人：Yun.Lei on 2017年6月21日
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ScrollTextView extends TextView {
    public ScrollTextView(Context context) {
        super(context);
        initView();
    }

    public ScrollTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setMarqueeRepeatLimit(-1);
        setSingleLine(true);
    }

    public ScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
