package com.f.wx.util;

import com.alibaba.fastjson.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HttpUtil {
    public static JSONObject post(String url,JSONObject jsonObject){
            RestTemplate client = new RestTemplate();
            JSONObject json = client.postForEntity(url, jsonObject, JSONObject.class).getBody();
            return json;
    }
    public static JSONObject get(String url){
        RestTemplate client = new RestTemplate();
        JSONObject json = client.getForEntity(url, JSONObject.class).getBody();
        return json;
    }

    public static Map request2Map(HttpServletRequest request) throws IOException {
        SAXReader reader=new SAXReader();
        Document document;
        try {
            Map<String,String> map=new HashMap<String, String>();
            document = reader.read(request.getInputStream());
            Element rootElm=document.getRootElement();
            for(Iterator it = rootElm.elementIterator(); it.hasNext();){
                Element e=(Element) it.next();
                map.put(e.getName(), e.getText());
            }
            return map;
        } catch (DocumentException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return null;
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return null;
        }
    }



    public static void main(String[] args) {
//        JSONObject postData = new JSONObject();
//        postData.put("nameKet","a");
//        post("http://192.168.1.234:8080/api/new/user_page/list/all/all",postData);
        JSONObject jsonObject=get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxc519bd2bc20412d3&secret=f3ec125001bd0a16f8736a57988fb9b2");
        System.out.println(jsonObject);
    }
}
