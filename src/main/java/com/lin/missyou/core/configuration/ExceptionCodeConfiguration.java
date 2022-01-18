package com.lin.missyou.core.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@PropertySource(value="classpath:config/exception-code.properties")
@ConfigurationProperties(prefix = "lin")
@Component // 需添加该注解，不然SpringBoot无法管理
public class ExceptionCodeConfiguration {
    // 需生成getter和setter不然无法注入
    private Map<Integer,String> codes = new HashMap<>();

    public String getMessage(int code){
        String message = codes.get(code);
        return message;
    }

    public Map<Integer, String> getCodes() {
        return codes;
    }

    public void setCodes(Map<Integer, String> codes) {
        this.codes = codes;
    }
}
