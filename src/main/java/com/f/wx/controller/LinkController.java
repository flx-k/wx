package com.f.wx.controller;

import com.f.wx.dto.MessageXML;
import com.f.wx.service.WxService;
import com.f.wx.util.CheckUtil;
import com.f.wx.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
public class LinkController {
    @Value("${wx.tocken}")
    private String token ;
    @Autowired
    private WxService wxService;

    @GetMapping(value = "link")
    public String link(@RequestParam(value = "signature") String signature,
                        @RequestParam(value = "timestamp") String timestamp,
                        @RequestParam(value = "nonce") String nonce,
                        @RequestParam(value = "echostr") String echostr) {
        if (CheckUtil.checkSignature(signature, timestamp, nonce,token)) {
            return echostr;
        }
        return null;
    }

    @PostMapping(value = "link")
    public String processMsg(HttpServletRequest request) throws IOException {
        Gson g=new Gson();
        MessageXML messageXML=g.fromJson(g.toJson(HttpUtil.request2Map(request)),MessageXML.class);
        return wxService.wxResponse(messageXML);
    }
}
