package com.f.wx.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wx")
public class WxConfig {
    public static String APPID;
    public static String APPSECRET;
    public static String tocken;
    public static String access_token_url;

    public static String getAPPID() {
        return APPID;
    }

    public static void setAPPID(String APPID) {
        WxConfig.APPID = APPID;
    }

    public static String getAPPSECRET() {
        return APPSECRET;
    }

    public static void setAPPSECRET(String APPSECRET) {
        WxConfig.APPSECRET = APPSECRET;
    }

    public static String getTocken() {
        return tocken;
    }

    public static void setTocken(String tocken) {
        WxConfig.tocken = tocken;
    }

    public static String getAccess_token_url() {
        return access_token_url;
    }

    public static void setAccess_token_url(String access_token_url) {
        WxConfig.access_token_url = access_token_url;
    }
}
