package com.zzx.utils;

import javax.servlet.http.Cookie;
import java.util.Map;

public class CookieUtils {
    public static Cookie findCookie(Cookie[] cookies, String name) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }
}
