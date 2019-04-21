package com.ybj.news_website.interceptor;


import com.ybj.news_website.redis.JedisService;
import com.ybj.news_website.util.RedisKeyUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tuzhenyu on 17-7-20.
 * @author tuzhenyu
 */
@Component
public class PassportInterceptor implements HandlerInterceptor {

    @Autowired
    private JedisService jedisService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {


        String uri = httpServletRequest.getServletPath();
        String uriKey = RedisKeyUntil.getClickCountKey(uri);
        jedisService.incr(uriKey);
        return true;
    }
}
