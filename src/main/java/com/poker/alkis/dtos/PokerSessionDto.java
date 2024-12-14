package com.poker.alkis.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.poker.alkis.enums.Season;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PokerSessionDto {

    private Long id;

    private int ordinal;

    private PlayerDto host;

    private String notes;

    private Season season;

    @JsonProperty(value = "poker_date")
    private String pokerDate;

    private List<ResultDto> results = new ArrayList<>();
}
