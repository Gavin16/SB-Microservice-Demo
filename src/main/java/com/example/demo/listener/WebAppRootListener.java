package com.example.demo.listener;

import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Title: demo
 * @Package com.example.demo.listener
 * @Description: ${todo}
 * @author: 80002748
 * @date 2018/3/6 14:11
 */
@Component
public class WebAppRootListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        WebUtils.setWebAppRootSystemProperty(event.getServletContext());
        System.out.println("set WebAppRootSystemProperty");
    }

    public void contextDestroyed(ServletContextEvent event) {
        WebUtils.removeWebAppRootSystemProperty(event.getServletContext());
        System.out.println("remove WebAppRootSystemProperty");
    }
}
