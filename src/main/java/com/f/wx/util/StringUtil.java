package com.f.wx.util;

import java.util.UUID;

public class StringUtil {
    public static String buildId(){
        return UUID.randomUUID().toString();
    }
}
