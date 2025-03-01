package com.poker.alkis.controllers;

import com.poker.alkis.dtos.PlayerParticipationDto;
import com.poker.alkis.dtos.PokerSessionDto;
import com.poker.alkis.dtos.ResultDto;
import com.poker.alkis.enums.Season;
import com.poker.alkis.helper.Printer;
import com.poker.alkis.helper.Utils;
import com.poker.alkis.services.PokerSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("participations")
public class ParticipationsController {

    private final PokerSessionService pokerSessionService;

    @Autowired
    public ParticipationsController(final PokerSessionService pokerSessionService) {
        this.pokerSessionService = pokerSessionService;
    }

    @GetMapping
    public String getPlayerParticipations(@RequestParam(defaultValue = "SEASON_2025_A") Season season) {
        List<PokerSessionDto> sessionDtos = pokerSessionService.getAllBySeason(season);

        List<PlayerParticipationDto> participations = new ArrayList<>();

        for (PokerSessionDto sessionDto : sessionDtos) {
            for (ResultDto resultDto : sessionDto.getResults()) {
                var name = resultDto.getPlayer().getName();
                var part = participations.stream()
                                         .filter(p -> p.getName().equalsIgnoreCase(name))
                                         .findFirst();

                if (part.isPresent()) {
                    part.get().setNumberOfGames(part.get().getNumberOfGames() + 1);
                } else {
                    participations.add(new PlayerParticipationDto(name, 1));
                }
            }
        }

        Utils.loadPercentages(participations, sessionDtos.size());
        Utils.sortByGames(participations);

        System.out.println(Printer.buildParticipationsString(participations));

        return "participations printed on standard output";
    }

}
