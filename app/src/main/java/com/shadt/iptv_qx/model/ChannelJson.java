package com.shadt.iptv_qx.model;

import java.io.Serializable;
import java.util.List;

public class ChannelJson implements Serializable {


    private List<TabContentBean> tab_content;

    public List<TabContentBean> getTab_content() {
        return tab_content;
    }

    public void setTab_content(List<TabContentBean> tab_content) {
        this.tab_content = tab_content;
    }

    public static class TabContentBean implements Serializable{
        /**
         * title : 推荐
         * id : 360
         * img : icon_fuwu.png
         */

        private String title;
        private String id;
        private String img;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
