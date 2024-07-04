package com.poker.alkis.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;

import com.poker.alkis.AlkisPokerRoomApplicationTests;
import com.poker.alkis.dtos.PlayerDto;
import com.poker.alkis.entities.Player;
import com.poker.alkis.repos.PlayerRepo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerServiceTest extends AlkisPokerRoomApplicationTests {
    
    @Autowired
    private PlayerService playerService;
    
    @Autowired
    private PlayerRepo playerRepo;
    
    @Test
    void getOrCreatePlayer() {
        
        String newPlayerName = "foofootos";
        
        var newPlayer = playerRepo.findByName(newPlayerName);
        assertNull(newPlayer);
        
        newPlayer = playerService.getOrCreatePlayer(newPlayerName);
        assertNotNull(newPlayer);
        assertNotNull(newPlayer.getId());
        assertNotNull(newPlayer.getColor());
        assertFalse(newPlayer.isActive());
        
        Player samePlayer = playerService.getOrCreatePlayer(newPlayerName);
        assertEquals(newPlayer, samePlayer);
    }
    
}
