package com.f.wx.service;

import ch.qos.logback.core.joran.spi.XMLUtil;
import com.alibaba.fastjson.JSONObject;
import com.f.wx.dto.AccessTocken;
import com.f.wx.dto.MessageXML;
import com.f.wx.util.HttpUtil;
import com.f.wx.util.XmlUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

@Service
public class WxService {
    @Value("${wx.APPID}")
    public String APPID;
    @Value("${wx.APPSECRET}")
    public String APPSECRET;
    @Value("${wx.tocken}")
    public String tocken;
    @Value("${wx.access_token_url}")
    public String access_token_url;

    @Value("${wx.web_access_token_url}")
    public String web_access_token_url;

    @Value("${wx.user_info_url}")
    public String user_info_url;

    @Value("${wx.qrcode_url}")
    public String qrcode_url;
    @Value("${wx.url_qrcode_userinfo}")
    public String url_qrcode_userinfo;


    public String getQrcodeUrl(String url) {
        try {
            String url2 = URLEncoder.encode(url, "utf-8");
            url = url2;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return qrcode_url.replace("APPID", APPID)
                .replace("APPSECRET", APPSECRET)
                .replace("REDIRECT_URI", url)
                .replace("SCOPE", "snsapi_userinfo")
                .replace("STATE", "login_by_wx");
    }

    private void refresh_ACCESS_TOKEN() {
        long now = System.currentTimeMillis();
        if (AccessTocken.timestamp < (now + 7100 * 1000)) {
            Map jsonObject = HttpUtil.get(access_token_url.replace("APPID", APPID)
                    .replace("APPSECRET", APPSECRET));
            if (jsonObject.containsKey("access_token")) {
                AccessTocken.refresh((String) jsonObject.get("access_token"));
            }
        }
    }

    /**
     * 网页授权,获取web access_tocken 和  openId
     */
    public Map get_web_access_tocken(String CODE) {
        Map jsonObject = HttpUtil.get(web_access_token_url.replace("APPID", APPID)
                .replace("APPSECRET", APPSECRET)
                .replace("CODE", CODE));
//        String webAccessTocken=null;
//        String openId=null;
//        if(jsonObject.containsKey("access_token")){
//            webAccessTocken=jsonObject.getString("access_token");
//            openId=jsonObject.getString("openid");
//        }
        return jsonObject;
    }

//    public String get_user_info(String WEB_ACCESS_TOKEN, String OPENID) {
//        JSONObject jsonObject = HttpUtil.get(user_info_url.replace("ACCESS_TOKEN", WEB_ACCESS_TOKEN)
//                .replace("OPENID", OPENID));
//        return null;
//    }

    public Map get_user_info(String CODE) {
        Map jsonObject = HttpUtil.get(
                web_access_token_url.replace("APPID", APPID)
                    .replace("APPSECRET", APPSECRET)
                    .replace("CODE", CODE));
        String WEB_ACCESS_TOKEN = null;
        String OPENID = null;
        if (jsonObject.containsKey("access_token")) {
            WEB_ACCESS_TOKEN = (String) jsonObject.get("access_token");
            OPENID = (String) jsonObject.get("openid");
        }
        return HttpUtil.get(user_info_url.replace("ACCESS_TOKEN", WEB_ACCESS_TOKEN)
                .replace("OPENID", OPENID));
    }


    /**
     * 微信返回的消息，用户操作
     */
    public String wxResponse(MessageXML messageXML) {
        refresh_ACCESS_TOKEN();
        //消息
        if (null == messageXML.getEvent()) {
            return XmlUtil.toXML(MessageXML.class, MessageXML.buildTxtMsg(messageXML.getToUserName(), messageXML.getFromUserName(), messageXML.getContent()));
        } else {
            //事件
            //1.关注
            if (messageXML.getEvent().equalsIgnoreCase("subscribe")) {
                return subscribe(messageXML);
            }
            //2.取消关注
            if (messageXML.getEvent().equalsIgnoreCase("unsubscribe")) {
                return unsubscribe(messageXML);
            }
            //3.已关注
            if (messageXML.getEvent().equalsIgnoreCase("SCAN")) {
                return XmlUtil.toXML(MessageXML.class, MessageXML.buildTxtMsg(messageXML.getToUserName(), messageXML.getFromUserName(), "已经关注"));
            }
            //4.自定义菜单CLICK
            if (messageXML.getEvent().equalsIgnoreCase("CLICK")) {

            }

        }
        return null;
    }

    /**
     * 关注
     */
    private String subscribe(MessageXML messageXML) {
        return XmlUtil.toXML(MessageXML.class, MessageXML.buildTxtMsg(messageXML.getToUserName(), messageXML.getFromUserName(), "谢谢关注"));
    }

    /**
     * 取消关注
     */
    private String unsubscribe(MessageXML messageXML) {
        return XmlUtil.toXML(MessageXML.class, MessageXML.buildTxtMsg(messageXML.getToUserName(), messageXML.getFromUserName(), "再见"));
    }

    public static void main(String[] args) {
        long now = System.currentTimeMillis();
        Date date = new Date(now + 7200 * 1000);
        System.out.println(date.toString());
    }

}
