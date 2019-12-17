package com.f.wx.controller;

import com.f.wx.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class Test {

    @Autowired
    WxService wxUtil;
    @RequestMapping(value = "test",method= RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response){
        return "";
    }
}
