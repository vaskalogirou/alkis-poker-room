package com.poker.alkis.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.poker.alkis.dtos.PlayerDto;
import com.poker.alkis.entities.Player;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    
    PlayerDto toDto(Player player);
    
    List<PlayerDto> toDtos(List<Player> players);
    
    Player toEntity(PlayerDto playerDto);
    
    List<Player> toEntities(List<PlayerDto> playerDtos);
    
}
