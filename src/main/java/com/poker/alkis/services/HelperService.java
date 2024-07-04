package com.poker.alkis.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poker.alkis.entities.Player;
import com.poker.alkis.entities.PokerSession;
import com.poker.alkis.entities.Result;

@Service
public class HelperService {
    
    public int calculateSum(List<PokerSession> pokerSessions, Player player) {
        int sum = 0;
        for (PokerSession pokerSession : pokerSessions) {
            for (Result result : pokerSession.getResults()) {
                if (result.getPlayer().getName().equals(player.getName())) {
                    sum = sum + result.getCashOut() - result.getCashIn();
                }
            }
        }
        
        return sum;
    }
    
}
