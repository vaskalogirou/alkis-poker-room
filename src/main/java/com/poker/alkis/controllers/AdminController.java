package com.poker.alkis.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.apache.commons.lang3.StringUtils;

import com.poker.alkis.entities.Player;
import com.poker.alkis.entities.PokerSession;
import com.poker.alkis.entities.Result;
import com.poker.alkis.exceptions.UnauthorizedException;
import com.poker.alkis.helper.Constants;
import com.poker.alkis.repos.PokerSessionRepo;
import com.poker.alkis.repos.ResultRepo;
import com.poker.alkis.services.PlayerService;

@RestController
@RequestMapping("admin")
public class AdminController {
    
    private final PokerSessionRepo pokerSessionRepo;
    
    private final PlayerService playerService;
    
    private final ResultRepo resultRepo;
    
    public AdminController(final PokerSessionRepo pokerSessionRepo, final PlayerService playerService, final ResultRepo resultRepo) {
        this.pokerSessionRepo = pokerSessionRepo;
        this.playerService = playerService;
        this.resultRepo = resultRepo;
    }
    
    @GetMapping(value = "process")
    public String process(@RequestParam String password) {
        
        if (!password.equals(Constants.PASSWORD)) {
            throw new UnauthorizedException("Wrong password");
        }
        
        return "all seems good!";
    }
    
    private void readPlayersFromNotesAndUpdatePokerSessions(List<PokerSession> pokerSessions) {
        pokerSessions.stream()
                     .filter(pokerSession -> StringUtils.isNotBlank(pokerSession.getNotes()))
                     .forEach(pokerSession -> {
                         String notes = pokerSession.getNotes().trim();
                         String[] tokens = notes.split(":");
                         String playerName = tokens[0];
                         Integer cashIn = Integer.valueOf(tokens[1]);
                         Integer cashOut = Integer.valueOf(tokens[2]);
                         
                         Player player = playerService.getOrCreatePlayer(playerName);
                         
                         Result result = new Result(player, pokerSession, cashIn, cashOut);
                         pokerSession.getResults().add(result);
                         
                         pokerSession.setNotes(null);
                         pokerSessionRepo.saveAndFlush(pokerSession);
                     });
    }
    
    private void deleteResultsOfPlayersThatDidNotPlay() {
        List<Result> results = resultRepo.findAll();
        for (Result result : results) {
            if (result.getCashIn().equals(0)) {
                resultRepo.delete(result);
            }
        }
    }
    
}
