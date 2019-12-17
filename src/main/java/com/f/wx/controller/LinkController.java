package com.f.wx.controller;

import com.f.wx.dto.MessageXML;
import com.f.wx.service.WxService;
import com.f.wx.util.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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
    public String processMsg(@RequestBody MessageXML messageXML) {
        return wxService.wxResponse(messageXML);
    }
}
