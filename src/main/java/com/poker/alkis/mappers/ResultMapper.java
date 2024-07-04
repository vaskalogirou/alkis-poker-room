package com.poker.alkis.mappers;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.poker.alkis.dtos.ResultDto;
import com.poker.alkis.entities.Result;

@Mapper(componentModel = "spring", uses = PlayerMapper.class)
public interface ResultMapper {
    
    @Mapping(target = "total", ignore = true)
    ResultDto toDto(Result result);
    
    List<ResultDto> toDtos(List<Result> results);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pokerSession", ignore = true)
    Result toEntity(ResultDto resultDto);
    
    List<Result> toEntities(List<ResultDto> resultDtos);
    
    @AfterMapping
    default void setTotal(Result result, @MappingTarget ResultDto resultDto) {
        if (result.getCashOut() != null && result.getCashIn() != null) {
            resultDto.setTotal(result.getCashOut() - result.getCashIn());
        }
    }
    
}
