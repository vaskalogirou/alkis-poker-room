package com.poker.alkis.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.integration.ftp.session.FtpRemoteFileTemplate;

import org.apache.commons.net.ftp.FTPClient;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class FtpConfiguration {
    
    private final FtpProperties ftpProperties;
    
    @Bean
    public DefaultFtpSessionFactory ftpSessionFactory() {
        DefaultFtpSessionFactory sf = new DefaultFtpSessionFactory();
        sf.setHost(ftpProperties.getHost());
        sf.setUsername(ftpProperties.getUsername());
        sf.setPassword(ftpProperties.getPassword());
        sf.setConnectTimeout(ftpProperties.getConnectTimeout());
        sf.setDefaultTimeout(ftpProperties.getDefaultTimeout());
        sf.setDataTimeout(ftpProperties.getDataTimeout());
        sf.setClientMode(FTPClient.PASSIVE_LOCAL_DATA_CONNECTION_MODE);
        
        return sf;
    }
    
    @Bean
    public FtpRemoteFileTemplate template(DefaultFtpSessionFactory sf) {
        FtpRemoteFileTemplate template = new FtpRemoteFileTemplate(sf);
        template.setRemoteDirectoryExpression(new LiteralExpression(ftpProperties.getDirectory()));
        return template;
    }
    
}
