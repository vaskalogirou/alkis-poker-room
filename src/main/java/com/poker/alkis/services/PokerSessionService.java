package com.poker.alkis.services;

import com.poker.alkis.dtos.PokerSessionDto;
import com.poker.alkis.enums.Season;

import java.util.List;

public interface PokerSessionService {

    List<PokerSessionDto> getAllBySeason(Season season);

    PokerSessionDto create(PokerSessionDto pokerSessionDto);

    void delete(Long pokerSessionId);
}
