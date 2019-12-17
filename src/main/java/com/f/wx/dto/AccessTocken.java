package com.f.wx.dto;


public class AccessTocken {
    public static String tocken;
    public static long timestamp;
    public static void refresh(String tocken2){
        AccessTocken.tocken=tocken2;
        AccessTocken.timestamp=System.currentTimeMillis();
    }
}
