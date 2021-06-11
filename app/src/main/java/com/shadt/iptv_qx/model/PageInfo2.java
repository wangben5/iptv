package com.shadt.iptv_qx.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PageInfo2 implements Serializable {

    /**
     * pageInfo : {"background":{"value":"","repeat":"no-repeat","position":"center center","size":"contain","color":"#00ff00"},"font":{"color":"#000000","size":"14"}}
     * pageData : [{"type":"image","text":"今日侯马","imgSrc":"","txtPose":"imgTxtIn","txtHeight":50,"istop":0,"abscissa":1340,"ordinate":150,"width":366,"height":50,"targetType":"openvisualization","target":"","color":"#fff","fontSize":26,"backgroundColor":"#4977f1","backgroundImage":"","borderSize":0,"borderRadius":0,"borderColor":"#000000"},{"type":"list","sourceType":"interface","interface":"http://192.168.2.15:7090/Record/listRecordByseq?seq=210&amp;recordCount=5","sourceKey":"","sourceNum":"1","mould":"sm","listData":[{"createtime":"2020-01-12T05:53:43.000+0000","channelseq":210,"isno":"11129374","updatetime":"2020-01-12T05:53:43.000+0000","title":"邹平看点1","platForm":"xinwenzixun","seq":11129374,"uniqueID":"374e1067-a99b-4798-9ae0-63a9b56d67e2","dataCrosshead":"测试标题图","RecordJumpUrl":"-897424"},{"createtime":"2019-05-08T06:38:47.000+0000","channelseq":210,"isno":"11127888","updatetime":"2019-05-08T06:38:47.000+0000","title":"邹平看点1","platForm":"xinwenzixun","seq":11127888,"uniqueID":"d7eff3a5-995e-4306-8fd3-048b79332b39","dataCrosshead":""}],"thumbW":100,"thumbH":80,"gap":10,"rate":"5000","istop":0,"abscissa":1340,"ordinate":210,"width":366,"height":356,"targetType":"openvisualization","target":"","color":"#fff","fontSize":24,"backgroundColor":"","backgroundImage":"","borderSize":0,"borderRadius":0,"borderColor":"#000000"},{"type":"video","videoSrc":"http://cdn.video.chinashadt.com/26a5c8cdvodsh1257065451/0c4fbfd15285890805580668414/8azTAmvjdE8A.mp4","text":"侯马电视台新闻综合频道","txtPose":"imgTxtIn","txtHeight":38,"istop":0,"abscissa":590,"ordinate":150,"width":740,"height":416,"targetType":"openvisualization","target":"","color":"#fff","fontSize":24,"backgroundColor":"","backgroundImage":"","borderSize":0,"borderRadius":0,"borderColor":"#000000"},{"type":"image","text":"新时代文明实践","imgSrc":"http://fjwysdb.chinashadt.com:8040/cmspic/IPTV/wenmingshijian.png","txtPose":"imgTxtIn","txtHeight":30,"istop":0,"abscissa":214,"ordinate":150,"width":366,"height":203,"targetType":"openvisualization","target":"","color":"#fff","fontSize":24,"backgroundColor":"","backgroundImage":"","borderSize":0,"borderRadius":0,"borderColor":"#000000"},{"type":"image","text":"智慧侯马","imgSrc":"http://fjwysdb.chinashadt.com:8040/cmspic/IPTV/zhihuihouma.png","txtPose":"imgTxtIn","txtHeight":30,"istop":0,"abscissa":214,"ordinate":363,"width":366,"height":203,"targetType":"openvisualization","target":"","color":"#fff","fontSize":24,"backgroundColor":"","backgroundImage":"","borderSize":0,"borderRadius":0,"borderColor":"#000000"},{"type":"image","text":"明星企业","imgSrc":"http://fjwysdb.chinashadt.com:8040/cmspic/IPTV/mingxingqiye.png","txtPose":"imgTxtIn","txtHeight":30,"istop":0,"abscissa":214,"ordinate":576,"width":366,"height":203,"targetType":"openvisualization","target":"","color":"#fff","fontSize":24,"backgroundColor":"","backgroundImage":"","borderSize":0,"borderRadius":0,"borderColor":"#000000"},{"type":"image","text":"魅力乡镇","imgSrc":"http://fjwysdb.chinashadt.com:8040/cmspic/IPTV/meilixiangzhen.png","txtPose":"imgTxtIn","txtHeight":30,"istop":0,"abscissa":590,"ordinate":576,"width":366,"height":203,"targetType":"openvisualization","target":"","color":"#fff","fontSize":24,"backgroundColor":"","backgroundImage":"","borderSize":0,"borderRadius":0,"borderColor":"#000000"},{"type":"image","text":"醉美侯马","imgSrc":"http://fjwysdb.chinashadt.com:8040/cmspic/IPTV/zuimeihouma.png","txtPose":"imgTxtIn","txtHeight":30,"istop":0,"abscissa":965,"ordinate":576,"width":366,"height":203,"targetType":"openvisualization","target":"","color":"#fff","fontSize":24,"backgroundColor":"","backgroundImage":"","borderSize":0,"borderRadius":0,"borderColor":"#000000"},{"type":"image","text":"生活服务","imgSrc":"http://fjwysdb.chinashadt.com:8040/cmspic/IPTV/shenghuofuwu.png","txtPose":"imgTxtIn","txtHeight":30,"istop":0,"abscissa":1340,"ordinate":576,"width":366,"height":203,"targetType":"openvisualization","target":"","color":"#fff","fontSize":24,"backgroundColor":"","backgroundImage":"","borderSize":0,"borderRadius":0,"borderColor":"#000000"},{"type":"image","text":"美食","imgSrc":"http://fjwysdb.chinashadt.com:8040/cmspic/IPTV/meishi_icon.png","txtPose":"imgTxtIn","txtHeight":30,"istop":0,"abscissa":450,"ordinate":810,"width":90,"height":120,"targetType":"openvisualization","target":"","color":"#fff","fontSize":24,"backgroundColor":"","backgroundImage":"","borderSize":0,"borderRadius":0,"borderColor":"#000000"},{"type":"image","text":"酒店","imgSrc":"http://fjwysdb.chinashadt.com:8040/cmspic/IPTV/jiudian_icon.png","txtPose":"imgTxtIn","txtHeight":30,"istop":0,"abscissa":682,"ordinate":810,"width":90,"height":120,"targetType":"openvisualization","target":"","color":"#fff","fontSize":24,"backgroundColor":"","backgroundImage":"","borderSize":0,"borderRadius":0,"borderColor":"#000000"},{"type":"image","text":"购物","imgSrc":"http://fjwysdb.chinashadt.com:8040/cmspic/IPTV/gouwu_icon.png","txtPose":"imgTxtIn","txtHeight":25,"istop":0,"abscissa":915,"ordinate":810,"width":90,"height":120,"targetType":"openvisualization","target":"","color":"#fff","fontSize":24,"backgroundColor":"","backgroundImage":"","borderSize":0,"borderRadius":0,"borderColor":"#000000"},{"type":"image","text":"景点","imgSrc":"http://fjwysdb.chinashadt.com:8040/cmspic/IPTV/jingdian_icon.png","txtPose":"imgTxtIn","txtHeight":30,"istop":0,"abscissa":1147,"ordinate":810,"width":90,"height":120,"targetType":"openvisualization","target":"","color":"#fff","fontSize":24,"backgroundColor":"","backgroundImage":"","borderSize":0,"borderRadius":0,"borderColor":"#000000"},{"type":"image","text":"娱乐","imgSrc":"http://fjwysdb.chinashadt.com:8040/cmspic/IPTV/yule_icon.png","txtPose":"imgTxtIn","txtHeight":30,"istop":0,"abscissa":1380,"ordinate":810,"width":90,"height":120,"targetType":"openvisualization","target":"","color":"#fff","fontSize":24,"backgroundColor":"","backgroundImage":"","borderSize":0,"borderRadius":0,"borderColor":"#000000"}]
     */

    private PageInfoBean pageInfo;
    private List<PageDataBean> pageData;

    public PageInfoBean getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfoBean pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<PageDataBean> getPageData() {
        return pageData;
    }

    public void setPageData(List<PageDataBean> pageData) {
        this.pageData = pageData;
    }

    public static class PageInfoBean implements Serializable{
        /**
         * background : {"value":"","repeat":"no-repeat","position":"center center","size":"contain","color":"#00ff00"}
         * font : {"color":"#000000","size":"14"}
         */

        private BackgroundBean background;
        private FontBean font;

        public BackgroundBean getBackground() {
            return background;
        }

        public void setBackground(BackgroundBean background) {
            this.background = background;
        }

        public FontBean getFont() {
            return font;
        }

        public void setFont(FontBean font) {
            this.font = font;
        }

        public static class BackgroundBean implements Serializable{
            /**
             * value :
             * repeat : no-repeat
             * position : center center
             * size : contain
             * color : #00ff00
             */

            private String value;
            private String repeat;
            private String position;
            private String size;
            private String color;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getRepeat() {
                return repeat;
            }

            public void setRepeat(String repeat) {
                this.repeat = repeat;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }

        public static class FontBean implements Serializable{
            /**
             * color : #000000
             * size : 14
             */

            private String color;
            private String size;

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }
        }
    }

    public static class PageDataBean implements Serializable{
        /**
         * type : image
         * text : 今日侯马
         * imgSrc :
         * txtPose : imgTxtIn
         * txtHeight : 50
         * istop : 0
         * abscissa : 1340
         * ordinate : 150
         * width : 366
         * height : 50
         * targetType : openvisualization
         * target :
         * color : #fff
         * fontSize : 26
         * backgroundColor : #4977f1
         * backgroundImage :
         * borderSize : 0
         * borderRadius : 0
         * borderColor : #000000
         * sourceType : interface
         * interface : http://192.168.2.15:7090/Record/listRecordByseq?seq=210&amp;recordCount=5
         * sourceKey :
         * sourceNum : 1
         * mould : sm
         * listData : [{"createtime":"2020-01-12T05:53:43.000+0000","channelseq":210,"isno":"11129374","updatetime":"2020-01-12T05:53:43.000+0000","title":"邹平看点1","platForm":"xinwenzixun","seq":11129374,"uniqueID":"374e1067-a99b-4798-9ae0-63a9b56d67e2","dataCrosshead":"测试标题图","RecordJumpUrl":"-897424"},{"createtime":"2019-05-08T06:38:47.000+0000","channelseq":210,"isno":"11127888","updatetime":"2019-05-08T06:38:47.000+0000","title":"邹平看点1","platForm":"xinwenzixun","seq":11127888,"uniqueID":"d7eff3a5-995e-4306-8fd3-048b79332b39","dataCrosshead":""}]
         * thumbW : 100
         * thumbH : 80
         * gap : 10
         * rate : 5000
         * videoSrc : http://cdn.video.chinashadt.com/26a5c8cdvodsh1257065451/0c4fbfd15285890805580668414/8azTAmvjdE8A.mp4
         */

        private String type;
        private String text;
        private String imgSrc;
        private String txtPose;
        private int txtHeight;
        private int istop;
        private int abscissa;
        private int ordinate;
        private int width;
        private int height;
        private String targetType;
        private String target;
        private String color;
        private int fontSize;
        private String backgroundColor;
        private String backgroundImage;
        private int borderSize;
        private int borderRadius;
        private String borderColor;
        private String sourceType;
        @SerializedName("interface")
        private String interfaceX;
        private String sourceKey;
        private String sourceNum;
        private String mould;
        private int thumbW;
        private int thumbH;
        private int gap;
        private int rate;
        private String videoSrc;
        private List<ListDataBean> listData;
        private int pageNum; //页面索引

        private String align; //文字对齐方式


        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public String getAlign() {
            return align;
        }

        public void setAlign(String align) {
            this.align = align;
        }



        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getImgSrc() {
            return imgSrc;
        }

        public void setImgSrc(String imgSrc) {
            this.imgSrc = imgSrc;
        }

        public String getTxtPose() {
            return txtPose;
        }

        public void setTxtPose(String txtPose) {
            this.txtPose = txtPose;
        }

        public int getTxtHeight() {
            return txtHeight;
        }

        public void setTxtHeight(int txtHeight) {
            this.txtHeight = txtHeight;
        }

        public int getIstop() {
            return istop;
        }

        public void setIstop(int istop) {
            this.istop = istop;
        }

        public int getAbscissa() {
            return abscissa;
        }

        public void setAbscissa(int abscissa) {
            this.abscissa = abscissa;
        }

        public int getOrdinate() {
            return ordinate;
        }

        public void setOrdinate(int ordinate) {
            this.ordinate = ordinate;
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

        public String getTargetType() {
            return targetType;
        }

        public void setTargetType(String targetType) {
            this.targetType = targetType;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getFontSize() {
            return fontSize;
        }

        public void setFontSize(int fontSize) {
            this.fontSize = fontSize;
        }

        public String getBackgroundColor() {
            return backgroundColor;
        }

        public void setBackgroundColor(String backgroundColor) {
            this.backgroundColor = backgroundColor;
        }

        public String getBackgroundImage() {
            return backgroundImage;
        }

        public void setBackgroundImage(String backgroundImage) {
            this.backgroundImage = backgroundImage;
        }

        public int getBorderSize() {
            return borderSize;
        }

        public void setBorderSize(int borderSize) {
            this.borderSize = borderSize;
        }

        public int getBorderRadius() {
            return borderRadius;
        }

        public void setBorderRadius(int borderRadius) {
            this.borderRadius = borderRadius;
        }

        public String getBorderColor() {
            return borderColor;
        }

        public void setBorderColor(String borderColor) {
            this.borderColor = borderColor;
        }

        public String getSourceType() {
            return sourceType;
        }

        public void setSourceType(String sourceType) {
            this.sourceType = sourceType;
        }

        public String getInterfaceX() {
            return interfaceX;
        }

        public void setInterfaceX(String interfaceX) {
            this.interfaceX = interfaceX;
        }

        public String getSourceKey() {
            return sourceKey;
        }

        public void setSourceKey(String sourceKey) {
            this.sourceKey = sourceKey;
        }

        public String getSourceNum() {
            return sourceNum;
        }

        public void setSourceNum(String sourceNum) {
            this.sourceNum = sourceNum;
        }

        public String getMould() {
            return mould;
        }

        public void setMould(String mould) {
            this.mould = mould;
        }

        public int getThumbW() {
            return thumbW;
        }

        public void setThumbW(int thumbW) {
            this.thumbW = thumbW;
        }

        public int getThumbH() {
            return thumbH;
        }

        public void setThumbH(int thumbH) {
            this.thumbH = thumbH;
        }

        public int getGap() {
            return gap;
        }

        public void setGap(int gap) {
            this.gap = gap;
        }

        public int getRate() {
            return rate;
        }

        public void setRate(int rate) {
            this.rate = rate;
        }

        public String getVideoSrc() {
            return videoSrc;
        }

        public void setVideoSrc(String videoSrc) {
            this.videoSrc = videoSrc;
        }

        public List<ListDataBean> getListData() {
            return listData;
        }

        public void setListData(List<ListDataBean> listData) {
            this.listData = listData;
        }

        public static class ListDataBean implements Serializable{
            /**
             * createtime : 2020-01-12T05:53:43.000+0000
             * channelseq : 210
             * isno : 11129374
             * updatetime : 2020-01-12T05:53:43.000+0000
             * title : 邹平看点1
             * platForm : xinwenzixun
             * seq : 11129374
             * uniqueID : 374e1067-a99b-4798-9ae0-63a9b56d67e2
             * dataCrosshead : 测试标题图
             * RecordJumpUrl : -897424
             */

            private String createtime;
            private int channelseq;
            private String isno;
            private String updatetime;
            private String title;
            private String platForm;
            private int seq;
            private String uniqueID;
            private String dataCrosshead;
            private String RecordJumpUrl;

            private String img;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public int getChannelseq() {
                return channelseq;
            }

            public void setChannelseq(int channelseq) {
                this.channelseq = channelseq;
            }

            public String getIsno() {
                return isno;
            }

            public void setIsno(String isno) {
                this.isno = isno;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPlatForm() {
                return platForm;
            }

            public void setPlatForm(String platForm) {
                this.platForm = platForm;
            }

            public int getSeq() {
                return seq;
            }

            public void setSeq(int seq) {
                this.seq = seq;
            }

            public String getUniqueID() {
                return uniqueID;
            }

            public void setUniqueID(String uniqueID) {
                this.uniqueID = uniqueID;
            }

            public String getDataCrosshead() {
                return dataCrosshead;
            }

            public void setDataCrosshead(String dataCrosshead) {
                this.dataCrosshead = dataCrosshead;
            }

            public String getRecordJumpUrl() {
                return RecordJumpUrl;
            }

            public void setRecordJumpUrl(String RecordJumpUrl) {
                this.RecordJumpUrl = RecordJumpUrl;
            }
        }
    }
}
