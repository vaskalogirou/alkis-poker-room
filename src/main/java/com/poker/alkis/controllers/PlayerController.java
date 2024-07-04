package com.poker.alkis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poker.alkis.dtos.PlayerDto;
import com.poker.alkis.exceptions.UnauthorizedException;
import com.poker.alkis.helper.Constants;
import com.poker.alkis.services.PlayerService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("players")
public class PlayerController {
    
    private final PlayerService playerService;
    
    @Autowired
    public PlayerController(final PlayerService playerService) {
        this.playerService = playerService;
    }
    
    @GetMapping
    public List<PlayerDto> getPlayers() {
        return playerService.getAll();
    }
    
    @PutMapping
    public PlayerDto update(@Valid @RequestBody final PlayerDto playerDto, @RequestParam String password) {
        if (!password.equals(Constants.PASSWORD)) {
            throw new UnauthorizedException("Wrong password");
        }
        
        return playerService.update(playerDto);
    }
    
}
