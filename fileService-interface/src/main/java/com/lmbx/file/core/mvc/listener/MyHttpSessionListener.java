package com.lmbx.file.core.mvc.listener;

import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;


@Component
public class MyHttpSessionListener implements HttpSessionListener, ApplicationContextAware {

    private static Map<String, String> sessionId2LogId = Maps.newConcurrentMap();

    public static void bindSessionIdWithLoginLogId(String sessionId, String loginLogId) {
        sessionId2LogId.put(sessionId, loginLogId);
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String sessionId = se.getSession().getId();
        String loginLogId = sessionId2LogId.get(sessionId);
        //UserService userService = SpringContextHolder.getBean(UserService.class);
        //userService.createLogoutLog(loginLogId);
        sessionId2LogId.remove(sessionId);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (applicationContext instanceof WebApplicationContext) {
            ((WebApplicationContext) applicationContext).getServletContext().addListener(this);
        } else {
            // Either throw an exception or fail gracefully, up to you
            throw new RuntimeException("Must be inside a web application context");
        }
    }
}
