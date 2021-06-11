package com.shadt.iptv_qx.mode;

/**
 * 用于Leanback 的数据测试.
 * Created by hailongqiu on 2016/8/25.
 */
public class Movie {
    private int mRes;
    private String mTitle;

    public Movie(int res, String title) {
        this.mRes = res;
        this.mTitle = title;
    }

    public int getRes() {
        return this.mRes;
    }

    public String getTitle() {
        return this.mTitle;
    }

}
