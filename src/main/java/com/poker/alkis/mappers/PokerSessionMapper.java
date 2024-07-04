package com.poker.alkis.mappers;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.poker.alkis.dtos.PokerSessionDto;
import com.poker.alkis.entities.PokerSession;

@Mapper(componentModel = "spring", uses = {
    PlayerMapper.class,
    ResultMapper.class
})
public interface PokerSessionMapper {
    
    @Mapping(target = "pokerDate", ignore = true)
    @Mapping(target = "ordinal", ignore = true)
    PokerSessionDto toDto(PokerSession pokerSession);
    
    List<PokerSessionDto> toDtos(List<PokerSession> pokerSessions);
    
    PokerSession toEntity(PokerSessionDto pokerSessionDto);
    
    List<PokerSession> toEntities(List<PokerSessionDto> pokerSessionDtos);
    
    @AfterMapping
    default void setPokerDateAndSortResults(PokerSession pokerSession, @MappingTarget PokerSessionDto pokerSessionDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        
        pokerSessionDto.setPokerDate(pokerSession.getPokerDate().format(formatter));
        
        pokerSessionDto.getResults().sort(Comparator.comparing(res -> res.getPlayer().getName()));
    }
    
    @AfterMapping
    default void setOrdinal(@MappingTarget List<PokerSessionDto> pokerSessionDtos) {
        int count = pokerSessionDtos.size();
        for (PokerSessionDto pokerSessionDto : pokerSessionDtos) {
            pokerSessionDto.setOrdinal(count--);
        }
    }
    
}
