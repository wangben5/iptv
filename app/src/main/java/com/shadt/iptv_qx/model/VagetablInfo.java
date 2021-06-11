package com.shadt.iptv_qx.model;

import java.io.Serializable;
import java.util.List;

public class VagetablInfo implements Serializable {


    /**
     * data : [{"memberPrice":5.63,"marketNo":15,"sellerName":"蔬菜-美天配送17 29","updateTime":1528063396000,"unit":"克","createTime":1528063396000,"price":5.8,"goodsNumber":"093800","caregoryName":"蔬菜","id":3392,"allowWeigh":1,"goodsName":"丝瓜","barcode":"","categoryId":66},{"memberPrice":7.57,"marketNo":15,"sellerName":"蔬菜-美天配送17 29","updateTime":1527288589000,"unit":"克","createTime":1527288589000,"price":7.8,"goodsNumber":"093514","caregoryName":"蔬菜","id":5959,"allowWeigh":1,"goodsName":"薄皮椒","barcode":"","categoryId":66},{"memberPrice":2.72,"marketNo":15,"sellerName":"蔬菜-美天配送17 29","updateTime":1531484577000,"unit":"克","createTime":1531484577000,"price":2.8,"goodsNumber":"094323","caregoryName":"蔬菜","id":3447,"allowWeigh":1,"goodsName":"本地南瓜","barcode":"","categoryId":66},{"memberPrice":3.4,"marketNo":15,"sellerName":"蔬菜-美天配送17 29","updateTime":1527464875000,"unit":"克","createTime":1527462203000,"price":3.5,"goodsNumber":"093525","caregoryName":"蔬菜","id":3382,"allowWeigh":1,"goodsName":"莴笋","barcode":"","categoryId":66},{"memberPrice":2.23,"marketNo":15,"sellerName":"蔬菜-美天配送17 29","updateTime":1528714715000,"unit":"克","createTime":1528714715000,"price":2.3,"goodsNumber":"093907","caregoryName":"蔬菜","id":3419,"allowWeigh":1,"goodsName":"大白菜","barcode":"","categoryId":66},{"memberPrice":2.72,"marketNo":15,"sellerName":"蔬菜-王庭峰24 16,蔬菜-美天配送17 29","updateTime":1524132212000,"unit":"克","createTime":1509982904000,"price":2.8,"goodsNumber":"068119","caregoryName":"蔬菜","id":3273,"allowWeigh":1,"goodsName":"红洋葱","barcode":"","categoryId":66},{"memberPrice":3.69,"marketNo":15,"sellerName":"蔬菜-美天配送17 29","updateTime":1528063210000,"unit":"克","createTime":1528063210000,"price":3.8,"goodsNumber":"093798","caregoryName":"蔬菜","id":3390,"allowWeigh":1,"goodsName":"本地黄瓜","barcode":"","categoryId":66},{"memberPrice":3.69,"marketNo":15,"sellerName":"蔬菜-美天配送17 29","updateTime":1524738763000,"unit":"克","createTime":1524738763000,"price":3.8,"goodsNumber":"093020","caregoryName":"蔬菜","id":3350,"allowWeigh":1,"goodsName":"牛心菜","barcode":"","categoryId":66},{"memberPrice":1.94,"marketNo":15,"sellerName":"蔬菜-美天配送17 29","updateTime":1528063666000,"unit":"克","createTime":1528063666000,"price":2,"goodsNumber":"093805","caregoryName":"蔬菜","id":3397,"allowWeigh":1,"goodsName":"长萝卜","barcode":"","categoryId":66},{"memberPrice":2.43,"marketNo":15,"sellerName":"蔬菜-王庭峰24 16,蔬菜-美天配送17 29","updateTime":1524201320000,"unit":"克","createTime":1509982904000,"price":2.5,"goodsNumber":"068182","caregoryName":"蔬菜","id":3304,"allowWeigh":1,"goodsName":"青菜","barcode":"","categoryId":66},{"memberPrice":2.72,"marketNo":15,"sellerName":"蔬菜-美天配送17 29","updateTime":1528245308000,"unit":"克","createTime":1528245308000,"price":2.8,"goodsNumber":"093833","caregoryName":"蔬菜","id":3414,"allowWeigh":1,"goodsName":"胡萝卜","barcode":"","categoryId":66},{"memberPrice":3.69,"marketNo":15,"sellerName":"蔬菜-美天配送17 29","updateTime":1533119863000,"unit":"克","createTime":1533119863000,"price":3.8,"goodsNumber":"094656","caregoryName":"蔬菜","id":3458,"allowWeigh":1,"goodsName":"冬瓜","barcode":"","categoryId":66},{"memberPrice":7.76,"marketNo":15,"sellerName":"蔬菜-美天配送17 29,蔬菜-王庭峰24 16","updateTime":1528063637000,"unit":"包","createTime":1528063637000,"price":8,"goodsNumber":"093804","caregoryName":"蔬菜","id":3396,"allowWeigh":1,"goodsName":"娃娃菜（包）","barcode":"","categoryId":66},{"memberPrice":5.34,"marketNo":15,"sellerName":"蔬菜-美天配送17 29","updateTime":1524822969000,"unit":"克","createTime":1524822969000,"price":5.5,"goodsNumber":"093119","caregoryName":"蔬菜","id":3359,"allowWeigh":1,"goodsName":"番茄","barcode":"","categoryId":66},{"memberPrice":3.4,"marketNo":15,"sellerName":"蔬菜-美天配送17 29","updateTime":1528065053000,"unit":"克","createTime":1528065053000,"price":3.5,"goodsNumber":"093809","caregoryName":"蔬菜","id":3401,"allowWeigh":1,"goodsName":"土豆","barcode":"","categoryId":66}]
     * message :
     * status : 0000
     */

    private String message;
    private String status;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements  Serializable{
        /**
         * memberPrice : 5.63
         * marketNo : 15
         * sellerName : 蔬菜-美天配送17 29
         * updateTime : 1528063396000
         * unit : 克
         * createTime : 1528063396000
         * price : 5.8
         * goodsNumber : 093800
         * caregoryName : 蔬菜
         * id : 3392
         * allowWeigh : 1
         * goodsName : 丝瓜
         * barcode :
         * categoryId : 66
         */

        private double memberPrice;
        private int marketNo;
        private String sellerName;
        private long updateTime;
        private String unit;
        private long createTime;
        private double price;
        private String goodsNumber;
        private String caregoryName;
        private int id;
        private int allowWeigh;
        private String goodsName;
        private String barcode;
        private int categoryId;

        public double getMemberPrice() {
            return memberPrice;
        }

        public void setMemberPrice(double memberPrice) {
            this.memberPrice = memberPrice;
        }

        public int getMarketNo() {
            return marketNo;
        }

        public void setMarketNo(int marketNo) {
            this.marketNo = marketNo;
        }

        public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getGoodsNumber() {
            return goodsNumber;
        }

        public void setGoodsNumber(String goodsNumber) {
            this.goodsNumber = goodsNumber;
        }

        public String getCaregoryName() {
            return caregoryName;
        }

        public void setCaregoryName(String caregoryName) {
            this.caregoryName = caregoryName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAllowWeigh() {
            return allowWeigh;
        }

        public void setAllowWeigh(int allowWeigh) {
            this.allowWeigh = allowWeigh;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getBarcode() {
            return barcode;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }
    }
}
