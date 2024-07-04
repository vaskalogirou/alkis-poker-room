package com.poker.alkis.repos;

import com.poker.alkis.services.PlayerService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import com.poker.alkis.AlkisPokerRoomApplicationTests;
import com.poker.alkis.entities.Player;
import com.poker.alkis.entities.PokerSession;
import com.poker.alkis.entities.Result;
import com.poker.alkis.helper.Fixtures;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PokerSessionRepoTest extends AlkisPokerRoomApplicationTests {
    
    @Autowired
    private PokerSessionRepo pokerSessionRepo;
    
    @Autowired
    private Fixtures fixtures;
    
    @Autowired
    private PlayerService playerService;
    
    @Test
    void createPokerSessionSavesAndUpdatesCorrectly() {
        Player host = playerService.getOrCreatePlayer("vasilis");
        
        PokerSession pokerSession = new PokerSession(host, LocalDate.now().plusYears(1), "test notes");
        
        Player alkis = playerService.getOrCreatePlayer("alkis");
        Result resultAlkis = new Result(alkis, pokerSession, 20, 100);
        Result resultVasilis = new Result(host, pokerSession, 20, 10);
        
        pokerSession.getResults().add(resultAlkis);
        pokerSession.getResults().add(resultVasilis);
        
        pokerSessionRepo.saveAndFlush(pokerSession);
        
        PokerSession retrieved = pokerSessionRepo.findById(pokerSession.getId()).orElse(new PokerSession());
        
        assertEquals("vasilis", retrieved.getHost().getName());
        
        Set<Result> results = retrieved.getResults();
        assertEquals(2, results.size());
        
        Result resultAlkisRetrieved = results.stream()
                                             .filter(r -> "alkis".equals(r.getPlayer().getName()))
                                             .findFirst()
                                             .orElse(new Result());
        assertNotNull(resultAlkisRetrieved.getId());
        assertEquals(20, resultAlkisRetrieved.getCashIn());
        assertEquals(100, resultAlkisRetrieved.getCashOut());
        
        Result resultVasilisRetrieved = results.stream()
                                               .filter(r -> "vasilis".equals(r.getPlayer().getName()))
                                               .findFirst()
                                               .orElse(new Result());
        assertNotNull(resultVasilisRetrieved.getId());
        assertEquals(20, resultVasilisRetrieved.getCashIn());
        assertEquals(10, resultVasilisRetrieved.getCashOut());
        
        assertEquals("test notes", retrieved.getNotes());
        
        Player rami = playerService.getOrCreatePlayer("rami");
        Result resultRami = new Result(rami, pokerSession, 20, 50);
        retrieved.getResults().add(resultRami);
        
        pokerSessionRepo.saveAndFlush(pokerSession);
        
        retrieved = pokerSessionRepo.findById(pokerSession.getId()).orElse(new PokerSession());
        Set<Result> resultsAfterUpdate = retrieved.getResults();
        assertEquals(3, resultsAfterUpdate.size());
        
        Optional<Result> ramiOpt = resultsAfterUpdate.stream().filter(r -> r.getPlayer().getName().equals("rami")).findFirst();
        assertTrue(ramiOpt.isPresent());
    }
    
}
