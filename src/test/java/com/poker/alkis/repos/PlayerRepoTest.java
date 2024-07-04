package com.poker.alkis.repos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.javafaker.Color;
import com.github.javafaker.Faker;

import com.poker.alkis.AlkisPokerRoomApplicationTests;
import com.poker.alkis.entities.Player;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerRepoTest extends AlkisPokerRoomApplicationTests {
    
    @Autowired
    private PlayerRepo playerRepo;
    
    @Test
    void savePlayerWorksCorrectly() {
        String vasilis = "vasilis";
        Player player = new Player(vasilis, "yellow", Boolean.TRUE);
        
        playerRepo.saveAndFlush(player);
        
        Player retrieved = playerRepo.findById(player.getId()).get();
        
        assertEquals(vasilis, retrieved.getName());
        assertEquals("yellow", retrieved.getColor());
        assertTrue(retrieved.isActive());
        
        retrieved = playerRepo.findByName(vasilis);
        assertEquals(vasilis, retrieved.getName());
    }
    
}
