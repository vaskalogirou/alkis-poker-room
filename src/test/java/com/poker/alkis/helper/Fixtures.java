package com.poker.alkis.helper;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poker.alkis.entities.Player;
import com.poker.alkis.entities.PokerSession;
import com.poker.alkis.repos.PlayerRepo;
import com.poker.alkis.repos.PokerSessionRepo;
import com.poker.alkis.services.PlayerService;

@Component
public class Fixtures {
    
    @Autowired
    private PlayerService playerService;
    
    @Autowired
    private PokerSessionRepo pokerSessionRepo;
    
    public PokerSession buildAndSavePokerSession() {
        Player host = playerService.getOrCreatePlayer("host");
        PokerSession pokerSession = new PokerSession(host, LocalDate.now().plusYears(1), "just a poker session");
        
        return pokerSessionRepo.saveAndFlush(pokerSession);
    }
    
}
