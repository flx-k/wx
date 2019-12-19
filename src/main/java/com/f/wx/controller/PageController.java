package com.f.wx.controller;

import com.f.wx.service.WxService;
import com.f.wx.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
public class PageController {
    @Autowired
    private WxService wxService;
    @RequestMapping(value = {"/index","/index.html","/"})
    public String login(HashMap<String,Object> map, Model model){
        model.addAttribute("text1", "hello world");
        String clientId=StringUtil.buildId();
        map.put("clientId", clientId);
        map.put("qrcodeUrl", wxService.getQrcodeUrl(wxService.url_qrcode_userinfo+"/"+clientId));
        return "index.html";
    }
}
