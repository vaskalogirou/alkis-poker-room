package com.poker.alkis.services;

import java.util.List;

import com.poker.alkis.dtos.EarningsDto;
import com.poker.alkis.enums.PlayerListType;

public interface EarningsService {
    
    List<EarningsDto> getEarnings(PlayerListType playerListType);
    
}
