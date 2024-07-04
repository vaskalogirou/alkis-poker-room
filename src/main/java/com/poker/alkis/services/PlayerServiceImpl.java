package com.poker.alkis.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import com.poker.alkis.dtos.PlayerDto;
import com.poker.alkis.entities.Player;
import com.poker.alkis.helper.Utils;
import com.poker.alkis.mappers.PlayerMapper;
import com.poker.alkis.repos.PlayerRepo;

@Service
public class PlayerServiceImpl implements PlayerService {
    
    private final PlayerRepo playerRepo;
    
    private final PlayerMapper playerMapper;
    
    public PlayerServiceImpl(final PlayerRepo playerRepo, final PlayerMapper playerMapper) {
        this.playerRepo = playerRepo;
        this.playerMapper = playerMapper;
    }
    
    @Override
    public List<PlayerDto> getAll() {
        List<Player> players = playerRepo.findAll();
        
        return playerMapper.toDtos(players);
    }
    
    @Override
    public PlayerDto update(final PlayerDto playerDto) {
        Player player = playerRepo.findByName(playerDto.getName());
        if (player == null) {
            throw new EntityNotFoundException("Player with name " + playerDto.getName() + " does not exist");
        }
        
        player.setActive(playerDto.isActive());
        player.setColor(playerDto.getColor());
        
        playerRepo.saveAndFlush(player);
        
        return playerMapper.toDto(player);
    }
    
    @Override
    public Player getOrCreatePlayer(final String name) {
        Player player = playerRepo.findByName(name);
        if (player == null) {
            player = playerRepo.saveAndFlush(new Player(name, Utils.faker().color().hex(), Boolean.FALSE));
        }
        return player;
    }
    
}
