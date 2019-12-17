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

import java.util.Date;

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

    private void refresh_ACCESS_TOKEN(){
        long now=System.currentTimeMillis();
        if(AccessTocken.timestamp<(now+7100*1000)){
            JSONObject jsonObject= HttpUtil.get(access_token_url.replace("APPID",APPID)
                    .replace("APPSECRET",APPSECRET));
            if(jsonObject.containsKey("access_token")){
                AccessTocken.refresh(jsonObject.getString("access_token"));
            }
        }

    }


    /**
     *  微信返回的消息，用户操作
     */
    public String wxResponse(MessageXML messageXML){
        refresh_ACCESS_TOKEN();
        //消息
        if (null==messageXML.getEvent()){
            return XmlUtil.toXML(MessageXML.class,MessageXML.buildTxtMsg(messageXML.getToUserName(),messageXML.getFromUserName(),messageXML.getContent()));
        }else{
            //事件
            //1.关注
            if(messageXML.getEvent().equalsIgnoreCase("subscribe")){
                return subscribe(messageXML);
            }
            //2.取消关注
            if(messageXML.getEvent().equalsIgnoreCase("unsubscribe")){
                return unsubscribe(messageXML);
            }
            //3.已关注
            if(messageXML.getEvent().equalsIgnoreCase("SCAN")){
                return XmlUtil.toXML(MessageXML.class,MessageXML.buildTxtMsg(messageXML.getToUserName(),messageXML.getFromUserName(),"已经关注"));
            }
            //4.自定义菜单CLICK
            if(messageXML.getEvent().equalsIgnoreCase("CLICK")){

            }

        }
        return null;
    }

    /**
     *  关注
     */
    private String subscribe(MessageXML messageXML){
        return XmlUtil.toXML(MessageXML.class,MessageXML.buildTxtMsg(messageXML.getToUserName(),messageXML.getFromUserName(),"谢谢关注"));
    }

    /**
     * 取消关注
     */
    private String unsubscribe(MessageXML messageXML){
        return XmlUtil.toXML(MessageXML.class,MessageXML.buildTxtMsg(messageXML.getToUserName(),messageXML.getFromUserName(),"再见"));
    }

    public static void main(String[] args) {
        long now=System.currentTimeMillis();
        Date date=new Date(now+7200*1000);
        System.out.println(date.toString());
    }

}
