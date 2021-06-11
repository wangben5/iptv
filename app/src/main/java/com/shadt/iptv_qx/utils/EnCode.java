package com.shadt.iptv_qx.utils;



import java.net.URLEncoder;

public class EnCode {
    public static String toURLEncoded(String paramString) {
        if (paramString == null || paramString.equals("")) {

            return "";
        }

        try
        {
            String str = new String(paramString.getBytes(), "UTF-8");
            str = URLEncoder.encode(str, "UTF-8");

            return str;
        }
        catch (Exception localException)
        {

        }

        return "";
    }
}
