package com.f.wx.util;

import com.f.wx.dto.MessageXML;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamer;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;


public class XmlUtil {
    public static Object toBean(Class<?> clazz, String xml) {
        Object xmlObject = null;
        XStream xstream = new XStream();
        xstream.processAnnotations(clazz);
        xstream.autodetectAnnotations(true);
        xmlObject = xstream.fromXML(xml);
        return xmlObject;
    }

    public static Object toBean(Class<?> clazz, HttpServletRequest request) {
        BufferedReader rd = null;
        try {
            rd = request.getReader();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("获取BufferedReader 失败");
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            String line = null;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("读取数据失败");
            return null;
        }
        return toXML(clazz, stringBuffer.toString());
    }

    public static String toXML(Class<?> clazz, Object obj) {
        String xmlObject = null;
        XStream xstream = new XStream();
        xstream.processAnnotations(clazz);
        xstream.autodetectAnnotations(true);
        xmlObject = xstream.toXML(obj);
        return xmlObject;
    }

    public static String toXML(Class<?> clazz, String obj) {
        String xmlObject = null;
        XStream xstream = new XStream();
        xstream.processAnnotations(clazz);
        xstream.autodetectAnnotations(true);
        xmlObject = xstream.toXML(obj);
        return xmlObject;
    }

    public static void main(String[] args) {
//        MessageXML messageXML=new MessageXML();
//        messageXML.setFromUserName("asdsad");
//        System.out.println(toXML(MessageXML.class,messageXML));
        MessageXML messageXML1 = (MessageXML) toBean(MessageXML.class, "<xml>\n" +
                "  <FromUserName>asdsad</FromUserName>\n" +
                "</xml>");
        System.out.println(messageXML1.getFromUserName());
    }


}
