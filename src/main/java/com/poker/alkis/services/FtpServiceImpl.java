package com.poker.alkis.services;

import org.springframework.integration.file.FileHeaders;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.ftp.session.FtpRemoteFileTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class FtpServiceImpl implements FtpService {
    
    private final FtpRemoteFileTemplate template;
    
    public FtpServiceImpl(final FtpRemoteFileTemplate template) {
        this.template = template;
    }
    
    @Override
    public String uploadString(final String content, final String fileName) {
        Message<String> message = MessageBuilder.withPayload(content).setHeader(FileHeaders.FILENAME, fileName).build();
        
        return template.send(message, FileExistsMode.REPLACE);
    }
    
}
