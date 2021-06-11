package com.shadt.iptv_qx.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Visualization implements Serializable {

    /**
     * data : [{"viewid":"news","target":"opennews","content":{"img":"11","backgroundimg":"点击页面图片背景，没有就是默认","title":"新闻联播","interface":"http://fjyx.chinashadt.com//Interface/Recodrd/listRecod1.do?key=YXXWWYXNEWS","pageNum":2,"getSize":10,"weburl":"网页地址","packname":"com.shadt.demo","activity":"com.shadt.MainActivity"},"style":{"mould":"listSmImgL","width":480,"height":720,"x":120,"y":120,"istop":true,"fontsize":28,"fontcolor":"#fff000","bgcolor":"#ffffffff","refreshtime":5000,"showtitle":true}},{"viewid":"banner","target":"opennews","content":{"img":"11","backgroundimg":"点击页面图片背景，没有就是默认","title":"轮播图新闻","interface":"http://fjyx.chinashadt.com/Interface/Recodrd/listRecod1.do?key=YXXWWYXNEWS","pageNum":1,"getSize":10,"weburl":"网页地址","packname":"com.shadt.demo","activity":"com.shadt.MainActivity"},"style":{"mould":"swipeScroll","width":780,"height":450,"x":620,"y":120,"istop":true,"fontsize":28,"fontcolor":"#fff000","bgcolor":"#ffffffff","refreshtime":5000,"showtitle":true}},{"viewid":"image","target":"opennews","content":{"img":"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&amp;quality=100&amp;size=b4000_4000&amp;sec=1598251131&amp;di=54b500f8798f80fb2f176b24834171b2&amp;src=http://a3.att.hudong.com/14/75/01300000164186121366756803686.jpg","backgroundimg":"点击页面图片背景，没有就是默认","title":"侯马旅游","interface":"","weburl":"网页地址","packname":"com.shadt.demo","activity":"com.shadt.MainActivity"},"style":{"mould":"ImgTxtIn","width":380,"height":450,"x":1420,"y":120,"istop":true,"fontsize":28,"fontcolor":"#fff000","bgcolor":"#ffffffff","showtitle":true}},{"viewid":"image","target":"opennews","content":{"img":"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&amp;quality=100&amp;size=b4000_4000&amp;sec=1598251131&amp;di=54b500f8798f80fb2f176b24834171b2&amp;src=http://a3.att.hudong.com/14/75/01300000164186121366756803686.jpg","backgroundimg":"点击页面图片背景，没有就是默认","title":"疫情防控","interface":"请求接口","weburl":"网页地址","packname":"com.shadt.demo","activity":"com.shadt.MainActivity"},"style":{"mould":"ImgTxtIn","width":380,"height":250,"x":620,"y":590,"istop":false,"fontsize":28,"fontcolor":"#fff000","bgcolor":"#ffffffff","showtitle":true}},{"viewid":"image","target":"opennews","content":{"img":"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&amp;quality=100&amp;size=b4000_4000&amp;sec=1598251131&amp;di=54b500f8798f80fb2f176b24834171b2&amp;src=http://a3.att.hudong.com/14/75/01300000164186121366756803686.jpg","backgroundimg":"点击页面图片背景，没有就是默认","title":"游山西读历史","interface":"请求接口","weburl":"网页地址","packname":"com.shadt.demo","activity":"com.shadt.MainActivity"},"style":{"mould":1,"width":380,"height":250,"x":1020,"y":590,"istop":true,"fontsize":28,"fontcolor":"#fff000","bgcolor":"#ffffffff","showtitle":true}},{"viewid":"image","target":"opennews","content":{"img":"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&amp;quality=100&amp;size=b4000_4000&amp;sec=1598251131&amp;di=54b500f8798f80fb2f176b24834171b2&amp;src=http://a3.att.hudong.com/14/75/01300000164186121366756803686.jpg","backgroundimg":"点击页面图片背景，没有就是默认","title":"脱贫攻坚","interface":"请求接口","weburl":"网页地址","packname":"com.shadt.demo","activity":"com.shadt.MainActivity"},"style":{"mould":"ImgTxtIn","width":380,"height":250,"x":1420,"y":590,"istop":false,"fontsize":28,"fontcolor":"#fff000","bgcolor":"#ffffffff","showtitle":true}},{"viewid":"image","target":"opennews","content":{"img":"1","backgroundimg":"点击页面图片背景，没有就是默认","title":"推荐","interface":"请求接口","weburl":"网页地址","packname":"com.shadt.demo","activity":"com.shadt.MainActivity"},"style":{"mould":"ImgTxtOut","width":92,"height":90,"x":250,"y":915,"istop":false,"fontsize":28,"fontcolor":"#fff000","bgcolor":"#ffffffff","showtitle":true}},{"viewid":"image","target":"opennews","content":{"img":"1","backgroundimg":"点击页面图片背景，没有就是默认","title":"文明实践","interface":"请求接口","weburl":"网页地址","packname":"com.shadt.demo","activity":"com.shadt.MainActivity"},"style":{"mould":"ImgTxtOut","width":92,"height":90,"x":517,"y":915,"istop":false,"fontsize":28,"fontcolor":"#fff000","bgcolor":"#ffffffff","showtitle":true}},{"viewid":"image","target":"opennews","content":{"img":"1","backgroundimg":"点击页面图片背景，没有就是默认","title":"视频点播","interface":"请求接口","weburl":"网页地址","packname":"com.shadt.demo","activity":"com.shadt.MainActivity"},"style":{"mould":"ImgTxtOut","width":92,"height":90,"x":784,"y":915,"istop":false,"fontsize":28,"fontcolor":"#fff000","bgcolor":"#ffffffff","showtitle":true}},{"viewid":"image","target":"opennews","content":{"img":"1","backgroundimg":"点击页面图片背景，没有就是默认","title":"智慧医疗","interface":"请求接口","weburl":"网页地址","packname":"com.shadt.demo","activity":"com.shadt.MainActivity"},"style":{"mould":"ImgTxtOut","width":92,"height":90,"x":1051,"y":915,"istop":false,"fontsize":28,"fontcolor":"#fff000","bgcolor":"#ffffffff","showtitle":true}},{"viewid":"image","target":"opennews","content":{"img":"1","backgroundimg":"点击页面图片背景，没有就是默认","title":"智慧党建","interface":"请求接口","weburl":"网页地址","packname":"com.shadt.demo","activity":"com.shadt.MainActivity"},"style":{"mould":"ImgTxtOut","width":92,"height":90,"x":1318,"y":915,"istop":false,"fontsize":28,"fontcolor":"#fff000","bgcolor":"#ffffffff","showtitle":true}},{"viewid":"image","target":"opennews","content":{"img":"1","backgroundimg":"点击页面图片背景，没有就是默认","title":"便民服务","interface":"请求接口","weburl":"网页地址","packname":"com.shadt.demo","activity":"com.shadt.MainActivity","showtitle":true},"style":{"mould":"ImgTxtOut","width":92,"height":90,"x":1585,"y":915,"istop":false,"fontsize":28,"fontcolor":"#fff000","bgcolor":"#ffffffff","showtitle":true}}]
     * code : 200
     * msg : 请求成功
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * viewid : news
         * target : opennews
         * content : {"img":"11","backgroundimg":"点击页面图片背景，没有就是默认","title":"新闻联播","interface":"http://fjyx.chinashadt.com//Interface/Recodrd/listRecod1.do?key=YXXWWYXNEWS","pageNum":2,"getSize":10,"weburl":"网页地址","packname":"com.shadt.demo","activity":"com.shadt.MainActivity"}
         * style : {"mould":"listSmImgL","width":480,"height":720,"x":120,"y":120,"istop":true,"fontsize":28,"fontcolor":"#fff000","bgcolor":"#ffffffff","refreshtime":5000,"showtitle":true}
         */

        private String viewid;
        private String target;
        private ContentBean content;
        private StyleBean style;

        public String getViewid() {
            return viewid;
        }

        public void setViewid(String viewid) {
            this.viewid = viewid;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public ContentBean getContent() {
            return content;
        }

        public void setContent(ContentBean content) {
            this.content = content;
        }

        public StyleBean getStyle() {
            return style;
        }

        public void setStyle(StyleBean style) {
            this.style = style;
        }

        public static class ContentBean implements Serializable{
            /**
             * img : 11
             * backgroundimg : 点击页面图片背景，没有就是默认
             * title : 新闻联播
             * interface : http://fjyx.chinashadt.com//Interface/Recodrd/listRecod1.do?key=YXXWWYXNEWS
             * pageNum : 2
             * getSize : 10
             * weburl : 网页地址
             * packname : com.shadt.demo
             * activity : com.shadt.MainActivity
             */

            private String img;
            private String backgroundimg;
            private String title;
            @SerializedName("interface")
            private String interfaceX;
            private int pageNum;
            private int getSize;
            private String weburl;
            private String packname;
            private String activity;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getBackgroundimg() {
                return backgroundimg;
            }

            public void setBackgroundimg(String backgroundimg) {
                this.backgroundimg = backgroundimg;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getInterfaceX() {
                return interfaceX;
            }

            public void setInterfaceX(String interfaceX) {
                this.interfaceX = interfaceX;
            }

            public int getPageNum() {
                return pageNum;
            }

            public void setPageNum(int pageNum) {
                this.pageNum = pageNum;
            }

            public int getGetSize() {
                return getSize;
            }

            public void setGetSize(int getSize) {
                this.getSize = getSize;
            }

            public String getWeburl() {
                return weburl;
            }

            public void setWeburl(String weburl) {
                this.weburl = weburl;
            }

            public String getPackname() {
                return packname;
            }

            public void setPackname(String packname) {
                this.packname = packname;
            }

            public String getActivity() {
                return activity;
            }

            public void setActivity(String activity) {
                this.activity = activity;
            }
        }

        public static class StyleBean implements Serializable{
            /**
             * mould : listSmImgL
             * width : 480
             * height : 720
             * x : 120
             * y : 120
             * istop : true
             * fontsize : 28
             * fontcolor : #fff000
             * bgcolor : #ffffffff
             * refreshtime : 5000
             * showtitle : true
             */

            private String mould;
            private int width;
            private int height;
            private int x;
            private int y;
            private boolean istop;
            private int fontsize;
            private String fontcolor;
            private String bgcolor;
            private int refreshtime;
            private boolean showtitle;

            public String getMould() {
                return mould;
            }

            public void setMould(String mould) {
                this.mould = mould;
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

            public boolean isIstop() {
                return istop;
            }

            public void setIstop(boolean istop) {
                this.istop = istop;
            }

            public int getFontsize() {
                return fontsize;
            }

            public void setFontsize(int fontsize) {
                this.fontsize = fontsize;
            }

            public String getFontcolor() {
                return fontcolor;
            }

            public void setFontcolor(String fontcolor) {
                this.fontcolor = fontcolor;
            }

            public String getBgcolor() {
                return bgcolor;
            }

            public void setBgcolor(String bgcolor) {
                this.bgcolor = bgcolor;
            }

            public int getRefreshtime() {
                return refreshtime;
            }

            public void setRefreshtime(int refreshtime) {
                this.refreshtime = refreshtime;
            }

            public boolean isShowtitle() {
                return showtitle;
            }

            public void setShowtitle(boolean showtitle) {
                this.showtitle = showtitle;
            }
        }
    }
}
