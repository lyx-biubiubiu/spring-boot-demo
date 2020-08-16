package com.lyx.config;

//import com.lyx.component.LoginHandlerInterceptor;
import com.lyx.component.LoginHandlerInterceptor;
import com.lyx.component.MyLocaleResolver;
import com.lyx.filter.MyFilter;
import com.lyx.listener.MyListener;
import com.lyx.servlet.MyServlet;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;
import java.util.Arrays;

@Configuration
public class MyWebConfig extends WebMvcConfigurerAdapter {

    /**
     * 设置视图映射
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        super.addViewControllers(registry);
        registry.addViewController("/lyx").setViewName("success");

    }

    /**
     * 将组件注册到容器
     * @return
     */
    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter webMvcConfigurerAdapter = new WebMvcConfigurerAdapter(){
            /**
             * 视图跳转
             * @param registry
             */
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");

            }

            /**
             * 注册拦截器
             * @param registry
             */
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/index.html","/","/user/login");
            }

        };
        return webMvcConfigurerAdapter;
    }

    /**
     * 国际化
     * @return
     */
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

    /**
     * 注册Servlet组件
     * @return
     */
    @Bean
    public ServletRegistrationBean myServlet(){
        ServletRegistrationBean<MyServlet> myServletServletRegistrationBean = new ServletRegistrationBean<>(new MyServlet(),"/myServlet");
        return myServletServletRegistrationBean;
    }

    /**
     * 注册Filter过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean myFilter(){
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>(new MyFilter());
        filterFilterRegistrationBean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));
        return filterFilterRegistrationBean;
    }


    /**
     * 注册listener监听器
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean myListener(){
        ServletListenerRegistrationBean<MyListener> myListenerServletListenerRegistrationBean = new ServletListenerRegistrationBean<>(new MyListener());
        return myListenerServletListenerRegistrationBean;
    }





}
