package com.f.wx.config;

import com.f.wx.service.WxService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

@Component
public class CustomWxAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private WxService wxService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String code=authentication.getName();
        Map map=wxService.get_user_info(code);
        System.out.println(new Gson().toJson(map));
        return new UsernamePasswordAuthenticationToken(map.get("openId"),map.get("neckname"),new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
