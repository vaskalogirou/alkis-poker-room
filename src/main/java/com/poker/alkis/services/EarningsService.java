package com.poker.alkis.services;

import com.poker.alkis.dtos.EarningsDto;
import com.poker.alkis.enums.PlayerListType;
import com.poker.alkis.enums.Season;

import java.util.List;

public interface EarningsService {

    List<EarningsDto> getEarnings(PlayerListType playerListType, Season season);

}
