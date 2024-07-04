package com.poker.alkis.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "ftp")
@Data
public class FtpProperties {
    
    private String host;
    
    private String username;
    
    private String password;
    
    private String directory;
    
    private int connectTimeout;
    
    private int defaultTimeout;
    
    private int dataTimeout;
    
}
