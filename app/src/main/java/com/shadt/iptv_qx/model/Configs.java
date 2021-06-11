package com.shadt.iptv_qx.model;

import java.io.Serializable;

public class Configs implements Serializable {


    /**
     * appName : 山西融媒
     * appCode : 10001
     * data_version : 111
     * background : {"value":"http://192.168.0.88:8080/img/uploadFiles/2020-12-07/967512138fc64ee0b23ba292866672d6.jpg","repeat":"repeat","position":"center center","size":"cover","color":"blue"}
     * refreshtime : 5000
     * font : {"color":"#ffffff","size":12}
     * screenSlide : true
     * supportTouch : true
     */

    private String appName;
    private int appCode;
    private int data_version;
    private BackgroundBean background;
    private int refreshtime;
    private FontBean font;
    private boolean screenSlide;
    private boolean supportTouch;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getAppCode() {
        return appCode;
    }

    public void setAppCode(int appCode) {
        this.appCode = appCode;
    }

    public int getData_version() {
        return data_version;
    }

    public void setData_version(int data_version) {
        this.data_version = data_version;
    }

    public BackgroundBean getBackground() {
        return background;
    }

    public void setBackground(BackgroundBean background) {
        this.background = background;
    }

    public int getRefreshtime() {
        return refreshtime;
    }

    public void setRefreshtime(int refreshtime) {
        this.refreshtime = refreshtime;
    }

    public FontBean getFont() {
        return font;
    }

    public void setFont(FontBean font) {
        this.font = font;
    }

    public boolean isScreenSlide() {
        return screenSlide;
    }

    public void setScreenSlide(boolean screenSlide) {
        this.screenSlide = screenSlide;
    }

    public boolean isSupportTouch() {
        return supportTouch;
    }

    public void setSupportTouch(boolean supportTouch) {
        this.supportTouch = supportTouch;
    }

    public static class BackgroundBean {
        /**
         * value : http://192.168.0.88:8080/img/uploadFiles/2020-12-07/967512138fc64ee0b23ba292866672d6.jpg
         * repeat : repeat
         * position : center center
         * size : cover
         * color : blue
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

    public static class FontBean {
        /**
         * color : #ffffff
         * size : 12
         */

        private String color;
        private int size;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }
}
