package com.poker.alkis.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.poker.alkis.dtos.EarningsDto;
import com.poker.alkis.dtos.PokerSessionDto;
import com.poker.alkis.enums.PlayerListType;
import com.poker.alkis.exceptions.UnauthorizedException;
import com.poker.alkis.helper.Constants;
import com.poker.alkis.services.EarningsService;
import com.poker.alkis.services.FtpService;
import com.poker.alkis.services.PokerSessionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("ftp")
@RequiredArgsConstructor
public class FtpCommunicationController {
    
    private final EarningsService earningsService;
    
    private final PokerSessionService pokerSessionService;
    
    private final FtpService ftpService;
    
    private final ObjectMapper objectMapper;
    
    @GetMapping(value = "send")
    public String sendToFtp(@RequestParam String password) {
        if (!password.equals(Constants.PASSWORD)) {
            throw new UnauthorizedException("Wrong password");
        }
        
        List<EarningsDto> earningsActive = earningsService.getEarnings(PlayerListType.ACTIVE);
        List<EarningsDto> earningsAll = earningsService.getEarnings(PlayerListType.ALL);
        
        List<PokerSessionDto> pokerSessions = pokerSessionService.getAll();
        String content;
        String result;
        try {
            String pokerSessionsString = objectMapper.writeValueAsString(pokerSessions);
            String earningsActiveStr = objectMapper.writeValueAsString(earningsActive);
            String earningsAllStr = objectMapper.writeValueAsString(earningsAll);
            
            content = "var pokerData = " + pokerSessionsString + ";" + System.lineSeparator();
            content += "var earningsActive = " + earningsActiveStr + ";" + System.lineSeparator();
            content += "var earningsAll = " + earningsAllStr + ";";
            
            result = ftpService.uploadString(content, "poker_data.js");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        
        return result;
    }
    
}
