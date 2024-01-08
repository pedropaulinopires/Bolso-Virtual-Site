package com.bolsovirtual.br.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Optional;

@Service
public class CookieService {

    private static final String USER_AUTHENTICATED = "UserAutentication";

    public static void setCookie(HttpServletResponse response, String value, int timeLife) throws UnsupportedEncodingException {
        Cookie cookie = new Cookie(URLEncoder.encode(USER_AUTHENTICATED, "UTF-8"), value);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(timeLife);
        response.addCookie(cookie);
    }

    public static String getCookie(HttpServletRequest request) throws UnsupportedEncodingException {
        String value = Optional.ofNullable(request.getCookies()).flatMap(cookies -> Arrays.stream(cookies)
                .filter(cookie -> USER_AUTHENTICATED.equals(cookie.getName())).findAny()).map(e -> e.getValue()).orElse(null);
        value = URLDecoder.decode(value, "UTF-8");
        return value;
    }

}
