package com.shadt.iptv_qx.model;

import java.io.Serializable;
import java.util.List;

public class MainInfo implements Serializable {


    /**
     * public_content : {"appname":"山西IPTV","app_code":1,"app_version":"1.0.0","data_version":"1.0.0","logo":{"img":"https://timgsa.baidu.com/timg?image&amp;quality=80&amp;size=b9999_10000&amp;sec=1598959096284&amp;di=500a4b1a174d3caf0b289c2e8935619a&amp;imgtype=0&amp;src=http%3A%2F%2Fimage.uisdc.com%2Fwp-content%2Fuploads%2F2013%2F10%2F1K23IC5-15.jpg","width":470,"height":45,"x":120,"y":30},"time":{"width":400,"height":120,"x":1450,"y":40},"tab":{"x":20,"y":20},"background":"https://timgsa.baidu.com/timg?image&amp;quality=80&amp;size=b9999_10000&amp;sec=1598953907305&amp;di=82934cce83a1b56b716b37ab4d387e85&amp;imgtype=0&amp;src=http%3A%2F%2Fa0.att.hudong.com%2F56%2F12%2F01300000164151121576126282411.jpg","scrolltext":{"x":1500,"y":20,"id":"http://www.shcnxwxczx.com/Interface/Recodrd/listRecod1.do?key=HomePageGDXW&amp;pageNum=1&amp;getSize=7","show":false,"refreshtime":5000}}
     * tab_content : [{"title":"首页","id":"http://www.shcnxwxczx.com/Interface/Recodrd/listRecod1.do?key=HomePageGDXW&amp;pageNum=1&amp;getSize=7"},{"title":"服务","id":"http://www.shcnxwxczx.com/Interface/Recodrd/listRecod1.do?key=HomePageGDXW&amp;pageNum=2&amp;getSize=7"}]
     */

    private PublicContentBean public_content;
    private List<TabContentBean> tab_content;

    public PublicContentBean getPublic_content() {
        return public_content;
    }

    public void setPublic_content(PublicContentBean public_content) {
        this.public_content = public_content;
    }

    public List<TabContentBean> getTab_content() {
        return tab_content;
    }

    public void setTab_content(List<TabContentBean> tab_content) {
        this.tab_content = tab_content;
    }

    public static class PublicContentBean implements Serializable {
        /**
         * appname : 山西IPTV
         * app_code : 1
         * app_version : 1.0.0
         * data_version : 1.0.0
         * logo : {"img":"https://timgsa.baidu.com/timg?image&amp;quality=80&amp;size=b9999_10000&amp;sec=1598959096284&amp;di=500a4b1a174d3caf0b289c2e8935619a&amp;imgtype=0&amp;src=http%3A%2F%2Fimage.uisdc.com%2Fwp-content%2Fuploads%2F2013%2F10%2F1K23IC5-15.jpg","width":470,"height":45,"x":120,"y":30}
         * time : {"width":400,"height":120,"x":1450,"y":40}
         * tab : {"x":20,"y":20}
         * background : https://timgsa.baidu.com/timg?image&amp;quality=80&amp;size=b9999_10000&amp;sec=1598953907305&amp;di=82934cce83a1b56b716b37ab4d387e85&amp;imgtype=0&amp;src=http%3A%2F%2Fa0.att.hudong.com%2F56%2F12%2F01300000164151121576126282411.jpg
         * scrolltext : {"x":1500,"y":20,"id":"http://www.shcnxwxczx.com/Interface/Recodrd/listRecod1.do?key=HomePageGDXW&amp;pageNum=1&amp;getSize=7","show":false,"refreshtime":5000}
         */

        private String appname;
        private int app_code;
        private String app_version;
        private String data_version;
        private LogoBean logo;
        private TimeBean time;
        private TabBean tab;
        private String background;
        private ScrolltextBean scrolltext;

        public String getAppname() {
            return appname;
        }

        public void setAppname(String appname) {
            this.appname = appname;
        }

        public int getApp_code() {
            return app_code;
        }

        public void setApp_code(int app_code) {
            this.app_code = app_code;
        }

        public String getApp_version() {
            return app_version;
        }

        public void setApp_version(String app_version) {
            this.app_version = app_version;
        }

        public String getData_version() {
            return data_version;
        }

        public void setData_version(String data_version) {
            this.data_version = data_version;
        }

        public LogoBean getLogo() {
            return logo;
        }

        public void setLogo(LogoBean logo) {
            this.logo = logo;
        }

        public TimeBean getTime() {
            return time;
        }

        public void setTime(TimeBean time) {
            this.time = time;
        }

        public TabBean getTab() {
            return tab;
        }

        public void setTab(TabBean tab) {
            this.tab = tab;
        }

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }

        public ScrolltextBean getScrolltext() {
            return scrolltext;
        }

        public void setScrolltext(ScrolltextBean scrolltext) {
            this.scrolltext = scrolltext;
        }

        public static class LogoBean implements Serializable{
            /**
             * img : https://timgsa.baidu.com/timg?image&amp;quality=80&amp;size=b9999_10000&amp;sec=1598959096284&amp;di=500a4b1a174d3caf0b289c2e8935619a&amp;imgtype=0&amp;src=http%3A%2F%2Fimage.uisdc.com%2Fwp-content%2Fuploads%2F2013%2F10%2F1K23IC5-15.jpg
             * width : 470
             * height : 45
             * x : 120
             * y : 30
             */

            private String img;
            private int width;
            private int height;
            private int x;
            private int y;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getX() {
                return x;
            }

            public void setX(int x) {
                this.x = x;
            }

            public int getY() {
                return y;
            }

            public void setY(int y) {
                this.y = y;
            }
        }

        public static class TimeBean implements Serializable{
            /**
             * width : 400
             * height : 120
             * x : 1450
             * y : 40
             */

            private int width;
            private int height;
            private int x;
            private int y;

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getX() {
                return x;
            }

            public void setX(int x) {
                this.x = x;
            }

            public int getY() {
                return y;
            }

            public void setY(int y) {
                this.y = y;
            }
        }

        public static class TabBean implements Serializable{
            /**
             * x : 20
             * y : 20
             */

            private int x;
            private int y;

            public int getX() {
                return x;
            }

            public void setX(int x) {
                this.x = x;
            }

            public int getY() {
                return y;
            }

            public void setY(int y) {
                this.y = y;
            }
        }

        public static class ScrolltextBean implements Serializable{
            /**
             * x : 1500
             * y : 20
             * id : http://www.shcnxwxczx.com/Interface/Recodrd/listRecod1.do?key=HomePageGDXW&amp;pageNum=1&amp;getSize=7
             * show : false
             * refreshtime : 5000
             */

            private int x;
            private int y;
            private String id;
            private boolean show;
            private int refreshtime;

            public int getX() {
                return x;
            }

            public void setX(int x) {
                this.x = x;
            }

            public int getY() {
                return y;
            }

            public void setY(int y) {
                this.y = y;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public boolean isShow() {
                return show;
            }

            public void setShow(boolean show) {
                this.show = show;
            }

            public int getRefreshtime() {
                return refreshtime;
            }

            public void setRefreshtime(int refreshtime) {
                this.refreshtime = refreshtime;
            }
        }
    }

    public static class TabContentBean implements Serializable{
        /**
         * title : 首页
         * id : http://www.shcnxwxczx.com/Interface/Recodrd/listRecod1.do?key=HomePageGDXW&amp;pageNum=1&amp;getSize=7
         */

        private String title;
        private String id;

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
    }
}