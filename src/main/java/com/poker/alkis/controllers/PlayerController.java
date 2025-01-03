package com.poker.alkis.controllers;

import com.poker.alkis.dtos.PlayerDto;
import com.poker.alkis.exceptions.UnauthorizedException;
import com.poker.alkis.helper.Constants;
import com.poker.alkis.services.PlayerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
