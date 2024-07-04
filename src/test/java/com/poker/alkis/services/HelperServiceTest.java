package com.poker.alkis.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;

import com.poker.alkis.AlkisPokerRoomApplicationTests;
import com.poker.alkis.entities.Player;
import com.poker.alkis.entities.PokerSession;
import com.poker.alkis.helper.TestObjectBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HelperServiceTest extends AlkisPokerRoomApplicationTests {
    
    @Autowired
    private HelperService helperService;
    
    @Test
    void calculateSum() {
        int sumA = helperService.calculateSum(new ArrayList<>(), new Player());
        assertEquals(0, sumA);
        
        List<PokerSession> pokerSessions = TestObjectBuilder.buildPokerSessions();
        
        int sumB = helperService.calculateSum(pokerSessions, new Player("alkis", "yellow", Boolean.TRUE));
        assertEquals(80, sumB);
        
        int sumC = helperService.calculateSum(pokerSessions, new Player("tasos", "yellow", Boolean.TRUE));
        assertEquals(-40, sumC);
    }
    
}
