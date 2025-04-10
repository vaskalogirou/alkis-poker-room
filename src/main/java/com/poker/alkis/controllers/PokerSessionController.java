package com.poker.alkis.controllers;

import com.poker.alkis.dtos.PokerSessionDto;
import com.poker.alkis.enums.Season;
import com.poker.alkis.exceptions.UnauthorizedException;
import com.poker.alkis.helper.Constants;
import com.poker.alkis.services.PokerSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("poker-sessions")
public class PokerSessionController {

    private final PokerSessionService pokerSessionService;

    @Autowired
    public PokerSessionController(final PokerSessionService pokerSessionService) {
        this.pokerSessionService = pokerSessionService;
    }

    @GetMapping
    public List<PokerSessionDto> getPokerSessions(@RequestParam(defaultValue = "SEASON_2025_B") Season season) {
        return pokerSessionService.getAllBySeason(season);
    }

    @PostMapping(consumes = Constants.CONTENT_TYPE_APPLICATION_JSON)
    public ResponseEntity<PokerSessionDto> create(@RequestBody PokerSessionDto pokerSessionDto,
                                                  @RequestParam String password) {

        if (!password.equals(Constants.PASSWORD)) {
            throw new UnauthorizedException("Wrong password");
        }

        PokerSessionDto created = pokerSessionService.create(pokerSessionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping(value = "/{pokerSessionId}")
    public ResponseEntity<PokerSessionDto> delete(@PathVariable Long pokerSessionId,
                                                  @RequestParam String password) {

        if (!password.equals(Constants.PASSWORD)) {
            throw new UnauthorizedException("Wrong password");
        }

        pokerSessionService.delete(pokerSessionId);

        return ResponseEntity.noContent().build();
    }

}