package com.f.wx.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name=authentication.getName();
        String pasw=authentication.getCredentials().toString();
        System.out.println("name:"+name);
        System.out.println("pasw:"+pasw);

        return new UsernamePasswordAuthenticationToken(name,pasw,new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
