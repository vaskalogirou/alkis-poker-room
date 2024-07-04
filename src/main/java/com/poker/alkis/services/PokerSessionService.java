package com.poker.alkis.services;

import java.util.List;

import com.poker.alkis.dtos.PokerSessionDto;

public interface PokerSessionService {
    
    List<PokerSessionDto> getAll();
    
    PokerSessionDto create(PokerSessionDto pokerSessionDto);
    
    void delete(Long pokerSessionId);
}
