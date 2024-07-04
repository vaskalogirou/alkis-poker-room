package com.poker.alkis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poker.alkis.dtos.PokerSessionDto;
import com.poker.alkis.exceptions.UnauthorizedException;
import com.poker.alkis.helper.Constants;
import com.poker.alkis.services.PokerSessionService;

@RestController
@RequestMapping("poker-sessions")
public class PokerSessionController {
    
    private final PokerSessionService pokerSessionService;
    
    @Autowired
    public PokerSessionController(final PokerSessionService pokerSessionService) {
        this.pokerSessionService = pokerSessionService;
    }
    
    @GetMapping
    public List<PokerSessionDto> getPokerSessions() {
        return pokerSessionService.getAll();
    }
    
    @PostMapping(consumes = Constants.CONTENT_TYPE_APPLICATION_JSON)
    public ResponseEntity<PokerSessionDto> create(
        @RequestBody PokerSessionDto pokerSessionDto,
        @RequestParam String password) {
        
        if (!password.equals(Constants.PASSWORD)) {
            throw new UnauthorizedException("Wrong password");
        }
        
        PokerSessionDto created = pokerSessionService.create(pokerSessionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @DeleteMapping(value = "/{pokerSessionId}")
    public ResponseEntity<PokerSessionDto> delete(
        @PathVariable Long pokerSessionId,
        @RequestParam String password) {
        
        if (!password.equals(Constants.PASSWORD)) {
            throw new UnauthorizedException("Wrong password");
        }
        
        pokerSessionService.delete(pokerSessionId);
        
        return ResponseEntity.noContent().build();
    }
    
}