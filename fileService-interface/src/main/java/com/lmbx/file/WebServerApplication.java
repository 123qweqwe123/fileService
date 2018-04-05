package com.lmbx.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
* @author qianyanan
* @date 2018年4月2日 
* Description:
*/
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class WebServerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WebServerApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebServerApplication.class);
    }
}
