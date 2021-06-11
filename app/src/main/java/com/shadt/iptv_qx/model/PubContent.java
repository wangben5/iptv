package com.shadt.iptv_qx.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PubContent implements Serializable {


    /**
     * type : logo
     * imgSrc : http://192.168.0.88:8080/img/uploadFiles/2020-12-14/c2624bf7a25b41e3bf6af5f7fb4b395e.jpg
     * abscissa : 0
     * ordinate : 0
     * width : 160
     * height : 134
     * targetType : openvisualization
     * target :
     * color : #fff
     * fontSize : 24
     * backgroundColor :
     * backgroundImage : http://192.168.2.15/img/uploadFiles/2020-12-25/3ac56b057ddf4e6fbc4e6ae024f731fb.png
     * borderSize : 0
     * borderRadius : 0
     * borderColor : #000000
     * sourceType : interface
     * interface : http://192.168.2.15:7090/Record/listRecordByseq?seq=210&recordCount=5
     * sourceKey :
     * sourceNum : 1
     * listData : [{"createtime":"2020-01-12T05:53:43.000+0000","channelseq":210,"isno":"11129374","updatetime":"2020-01-12T05:53:43.000+0000","title":"邹平看点1","platForm":"xinwenzixun","seq":11129374,"uniqueID":"374e1067-a99b-4798-9ae0-63a9b56d67e2","dataCrosshead":"测试标题图","RecordJumpUrl":"-897424"},{"createtime":"2019-05-08T06:38:47.000+0000","channelseq":210,"isno":"11127888","updatetime":"2019-05-08T06:38:47.000+0000","title":"邹平看点1","platForm":"xinwenzixun","seq":11127888,"uniqueID":"d7eff3a5-995e-4306-8fd3-048b79332b39","dataCrosshead":""}]
     * gap : 10
     * rate : 5000
     * mould : yyyy-MM-dd
     * align : left
     * iconPose : top
     * iconWidth : 30
     * iconHeight : 30
     * navData : [{"channelid":415,"npName":"首页","icon":{"value":"http://192.168.2.15/img/uploadFiles/2020-12-28/0aeb12eaf91443bba820f373de68edff.png","show":false},"checked":true},{"channelid":421,"npName":"版式设计","icon":{"value":"http://192.168.2.15/img/uploadFiles/2020-12-28/57eb3721bba2459ea2a69a5ded7c89a0.png","show":false},"checked":true},{"channelid":416,"npName":"敏捷迭代","icon":{"value":"http://192.168.2.15/img/uploadFiles/2020-12-28/fcd20de22a124f85aca0edb70c03a9f2.png","show":true},"checked":true},{"channelid":440,"npName":"淘宝前端团队","icon":{"value":"http://192.168.2.15/img/uploadFiles/2020-12-28/890fbbe1ec1b4a58842457a7e21dff01.png","show":true},"checked":true},{"channelid":417,"npName":"出版社","icon":{"value":null,"show":false},"checked":true},{"channelid":418,"npName":"新时代文明实践","icon":{"value":null,"show":false},"checked":true},{"channelid":449,"npName":"红色历史","icon":{"value":null,"show":false},"checked":true},{"channelid":452,"npName":"哲学思考","icon":{"value":null,"show":false},"checked":true},{"channelid":415,"npName":"首页","icon":{"value":"http://192.168.2.15/img/uploadFiles/2020-12-28/0aeb12eaf91443bba820f373de68edff.png","show":false},"checked":true}]
     * istop : 0
     */

    private String type;
    private String imgSrc;
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
    private int gap;
    private int rate;
    private String mould;
    private String align;
    private String iconPose;
    private int iconWidth;
    private int iconHeight;
    private int istop;





    private List<ListDataBean> listData;
    private List<NavDataBean> navData;


    private int pageNum; //页面索引



    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
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

    public String getMould() {
        return mould;
    }

    public void setMould(String mould) {
        this.mould = mould;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getIconPose() {
        return iconPose;
    }

    public void setIconPose(String iconPose) {
        this.iconPose = iconPose;
    }

    public int getIconWidth() {
        return iconWidth;
    }

    public void setIconWidth(int iconWidth) {
        this.iconWidth = iconWidth;
    }

    public int getIconHeight() {
        return iconHeight;
    }

    public void setIconHeight(int iconHeight) {
        this.iconHeight = iconHeight;
    }

    public int getIstop() {
        return istop;
    }

    public void setIstop(int istop) {
        this.istop = istop;
    }

    public List<ListDataBean> getListData() {
        return listData;
    }

    public void setListData(List<ListDataBean> listData) {
        this.listData = listData;
    }

    public List<NavDataBean> getNavData() {
        return navData;
    }

    public void setNavData(List<NavDataBean> navData) {
        this.navData = navData;
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

    public static class NavDataBean implements Serializable{
        /**
         * channelid : 415
         * npName : 首页
         * icon : {"value":"http://192.168.2.15/img/uploadFiles/2020-12-28/0aeb12eaf91443bba820f373de68edff.png","show":false}
         * checked : true
         */

        private String channelid;
        private String npName;
        private IconBean icon;
        private boolean checked;

        public String getChannelid() {
            return channelid;
        }

        public void setChannelid(String channelid) {
            this.channelid = channelid;
        }

        public String getNpName() {
            return npName;
        }

        public void setNpName(String npName) {
            this.npName = npName;
        }

        public IconBean getIcon() {
            return icon;
        }

        public void setIcon(IconBean icon) {
            this.icon = icon;
        }

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        public static class IconBean implements Serializable{
            /**
             * value : http://192.168.2.15/img/uploadFiles/2020-12-28/0aeb12eaf91443bba820f373de68edff.png
             * show : false
             */

            private String value;
            private boolean show;


            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public boolean isShow() {
                return show;
            }

            public void setShow(boolean show) {
                this.show = show;
            }
        }
    }

    @Override
    public String toString() {
        return "PubContent{" +
                "type='" + type + '\'' +
                ", imgSrc='" + imgSrc + '\'' +
                ", abscissa=" + abscissa +
                ", ordinate=" + ordinate +
                ", width=" + width +
                ", height=" + height +
                ", targetType='" + targetType + '\'' +
                ", target='" + target + '\'' +
                ", color='" + color + '\'' +
                ", fontSize=" + fontSize +
                ", backgroundColor='" + backgroundColor + '\'' +
                ", backgroundImage='" + backgroundImage + '\'' +
                ", borderSize=" + borderSize +
                ", borderRadius=" + borderRadius +
                ", borderColor='" + borderColor + '\'' +
                ", sourceType='" + sourceType + '\'' +
                ", interfaceX='" + interfaceX + '\'' +
                ", sourceKey='" + sourceKey + '\'' +
                ", sourceNum='" + sourceNum + '\'' +
                ", gap=" + gap +
                ", rate=" + rate +
                ", mould='" + mould + '\'' +
                ", align='" + align + '\'' +
                ", iconPose='" + iconPose + '\'' +
                ", iconWidth=" + iconWidth +
                ", iconHeight=" + iconHeight +
                ", istop=" + istop +
                ", listData=" + listData +
                ", navData=" + navData +
                '}';
    }
}
