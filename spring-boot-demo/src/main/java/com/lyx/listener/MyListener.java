package com.lyx.listener;

import javax.servlet.*;
import java.io.IOException;

public class MyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("MyListener开始执行");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("MyListener执行完毕");
    }
}
