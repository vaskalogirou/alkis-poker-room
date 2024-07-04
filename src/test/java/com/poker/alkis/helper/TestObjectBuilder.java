package com.poker.alkis.helper;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.poker.alkis.entities.Player;
import com.poker.alkis.entities.PokerSession;
import com.poker.alkis.entities.Result;

public class TestObjectBuilder {
    
    public static List<PokerSession> buildPokerSessions() {
        Player alkis = new Player("alkis", "yellow", Boolean.TRUE);
        Player tasos = new Player("tasos", "blue", Boolean.TRUE);
        
        PokerSession pokerSessionA = new PokerSession(alkis, LocalDate.now(), "");
        Result firstResultA = new Result(alkis, pokerSessionA, 20, 40);
        Result secondResultA = new Result(tasos, pokerSessionA, 40, 0);
        pokerSessionA.setResults(Set.of(firstResultA, secondResultA));
        
        PokerSession pokerSessionB = new PokerSession(alkis, LocalDate.now().minusDays(7), "");
        Result firstResultB = new Result(alkis, pokerSessionB, 20, 80);
        Result secondResultB = new Result(tasos, pokerSessionB, 40, 40);
        pokerSessionB.setResults(Set.of(firstResultB, secondResultB));
        
        return List.of(pokerSessionA, pokerSessionB);
    }
    
}
