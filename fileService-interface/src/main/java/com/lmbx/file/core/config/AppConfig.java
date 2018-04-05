package com.lmbx.file.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


/**
* @author qianyanan
* @date 2018年4月2日 
* Description:
*/
@Configuration
@PropertySource(value = "classpath:/messages.properties", encoding = "UTF-8")
public class AppConfig {

}
