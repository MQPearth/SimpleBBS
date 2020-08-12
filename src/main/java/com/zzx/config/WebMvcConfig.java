package com.zzx.config;


import com.zzx.filter.DownloadFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private FileUploadProperteis fileUploadProperteis;

    @Autowired
    private DownloadFilter downloadFilter;


    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File file = new File(fileUploadProperteis.getUploadFolder());
        if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            if (!file.exists())
                file.mkdirs();
            registry.addResourceHandler(fileUploadProperteis.getStaticAccessPath())
                    .addResourceLocations("file:" + fileUploadProperteis.getUploadFolder() + "/");

        } else {
            file = new File("/file");
            if (!file.exists())
                file.mkdirs();
            registry.addResourceHandler(fileUploadProperteis.getStaticAccessPath())
                    .addResourceLocations("file:/file");
        }
    }


    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(downloadFilter);

        //设置过滤器拦截请求
        List<String> urls = new ArrayList<>();
        urls.add("/file/*");
        registrationBean.setUrlPatterns(urls);

        return registrationBean;
    }


}
