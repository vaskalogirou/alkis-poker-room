package com.poker.alkis.services;

import java.util.List;

import com.poker.alkis.dtos.PlayerDto;
import com.poker.alkis.entities.Player;

public interface PlayerService {
    
    List<PlayerDto> getAll();
    
    PlayerDto update(PlayerDto playerDto);
    
    Player getOrCreatePlayer(String name);
    
}
