package com.f.wx.util;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpUtil {
    public static RestTemplate client;

    static {
        client = new RestTemplate();
        if (null == client || ObjectUtils.isEmpty(client.getMessageConverters())) {

        } else {
            List<HttpMessageConverter<?>> messageConverters = client.getMessageConverters();
            for (int i = 0; i < messageConverters.size(); i++) {
                HttpMessageConverter<?> httpMessageConverter = messageConverters.get(i);
                if (httpMessageConverter.getClass().equals(StringHttpMessageConverter.class)) {
                    messageConverters.set(i, new StringHttpMessageConverter(StandardCharsets.UTF_8));
                }
            }
        }
    }

    public static JSONObject post(String url, JSONObject jsonObject) {
        JSONObject json = client.postForEntity(url, jsonObject, JSONObject.class).getBody();
        return json;
    }

    public static void setRestTemplateEncode(RestTemplate restTemplate, Charset charset) {
        if (null == restTemplate || ObjectUtils.isEmpty(restTemplate.getMessageConverters())) {
            return;
        }

        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        for (int i = 0; i < messageConverters.size(); i++) {
            HttpMessageConverter<?> httpMessageConverter = messageConverters.get(i);
            if (httpMessageConverter.getClass().equals(StringHttpMessageConverter.class)) {
                messageConverters.set(i, new StringHttpMessageConverter(charset));
            }
        }
    }

    public static Map get(String url) {
        String str = client.getForEntity(url, String.class).getBody();
        return new Gson().fromJson(str, Map.class);
    }

    public static Map request2Map(HttpServletRequest request) throws IOException {
        SAXReader reader = new SAXReader();
        Document document;
        try {
            Map<String, String> map = new HashMap<String, String>();
            document = reader.read(request.getInputStream());
            Element rootElm = document.getRootElement();
            for (Iterator it = rootElm.elementIterator(); it.hasNext(); ) {
                Element e = (Element) it.next();
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
//        JSONObject jsonObject=get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxc519bd2bc20412d3&secret=f3ec125001bd0a16f8736a57988fb9b2");
//        System.out.println(jsonObject);
    }
}
