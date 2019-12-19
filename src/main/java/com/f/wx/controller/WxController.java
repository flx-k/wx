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

            WebSocketService.sendInfo(new Gson().toJson(map),clientId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "user";
    }
}
