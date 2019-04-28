package com.dbstudy.springboot.manager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Autowired
    private HandlerInterceptor handlerInterceptor;
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/index").setViewName("login");
    }

    //拦截器的路径配置
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.handlerInterceptor).addPathPatterns("/**")
                                                            .excludePathPatterns("/login")
                                                            .excludePathPatterns("/index")
                                                            .excludePathPatterns("/user/login")
                                                            .excludePathPatterns("/css/**")
                                                            .excludePathPatterns("/images/**")
                                                            .excludePathPatterns("/js/**");
    }
    /**
     * 配置静态访问资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //第一个方法设定路径前缀
        //第二个方法设置路径资源
        //registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/");
    }
}