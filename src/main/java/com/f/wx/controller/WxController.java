package com.f.wx.controller;

import com.alibaba.fastjson.JSONObject;
import com.f.wx.service.WebSocketService;
import com.f.wx.service.WxService;
import com.f.wx.util.CheckUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WxController {
    @Autowired
    private WxService wxService;
    @GetMapping(value = "/login/_wx/{clientId}")
    public String link(@RequestParam(value = "code") String code,
                       @RequestParam(value = "state") String state,
                       @PathVariable("clientId") String clientId) {
        try {
//            Map map=wxService.get_user_info(code);
            Map<String,String> map=new HashMap<>();
            map.put("type","wx_login");
            map.put("code",code);
            map.put("data","授权成功，正在登录");
            WebSocketService.sendInfo(new Gson().toJson(map),clientId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "user";
    }
    @GetMapping(value = "/login/_qrcode_used/{clientId}")
    public void link(@PathVariable("clientId") String clientId, HttpServletResponse response) {
        String url=wxService.getQrcodeUrl(wxService.url_qrcode_userinfo+"/"+clientId);
        try {
            Map<String,String> map=new HashMap<>();
            map.put("type","qrcode");
            map.put("data","已扫描，等待授权");
            WebSocketService.sendInfo(new Gson().toJson(map),clientId);
            System.out.println("forward:"+url);
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
