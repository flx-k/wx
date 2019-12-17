package com.f.wx.util;

import com.f.wx.dto.MessageXML;
import com.thoughtworks.xstream.XStream;


public class XmlUtil {
    public static Object toBean(Class<?> clazz, String xml) {
        Object xmlObject = null;
        XStream xstream = new XStream();
        xstream.processAnnotations(clazz);
        xstream.autodetectAnnotations(true);
        xmlObject= xstream.fromXML(xml);
        return xmlObject;
    }
    public static String toXML(Class<?> clazz,Object obj) {
        String xmlObject = null;
        XStream xstream = new XStream();
        xstream.processAnnotations(clazz);
        xstream.autodetectAnnotations(true);
        xmlObject= xstream.toXML(obj);
        return xmlObject;
    }

    public static void main(String[] args) {
//        MessageXML messageXML=new MessageXML();
//        messageXML.setFromUserName("asdsad");
//        System.out.println(toXML(MessageXML.class,messageXML));
        MessageXML messageXML1= (MessageXML) toBean(MessageXML.class,"<xml>\n" +
                "  <FromUserName>asdsad</FromUserName>\n" +
                "</xml>");
        System.out.println(messageXML1.getFromUserName());
    }


}
