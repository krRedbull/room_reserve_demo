package com.syjun.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Properties;

@Configuration
public class FreeMarkerConfig {
    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer(){
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("classpath:/templates/");
        configurer.setDefaultEncoding("UTF-8");

        Properties settingProps = new Properties();
        settingProps.setProperty("default_encoding", "UTF-8");
        settingProps.setProperty("number_format", "####");
        settingProps.setProperty("date_format", "yy-MM-dd");
        settingProps.setProperty("time_format", "HH:mm:ss");
        settingProps.setProperty("datetime_format", "yy-MM-dd HH:mm:ss");
        settingProps.setProperty("cache_storage", "strong:20, soft:250");
        settingProps.setProperty("template_update_delay", "0");
        settingProps.setProperty("template_exception_handler", "html_debug");
        configurer.setFreemarkerSettings(settingProps);

        return configurer;
    }

    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver(){
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();

        viewResolver.setCache(false);
        viewResolver.setCacheUnresolved(false);
        viewResolver.setCacheLimit(1);
        viewResolver.setContentType("text/html; charset=utf-8");
        viewResolver.setExposeSpringMacroHelpers(true);
        viewResolver.setExposeContextBeansAsAttributes(true);
        viewResolver.setSuffix(".ftl");
        viewResolver.setOrder(1);

        return viewResolver;
    }
}
