package com.poker.alkis.dtos;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PokerSessionDto {

	private Long id;

	private int ordinal;

	private PlayerDto host;

	private String notes;

	@JsonProperty(value = "poker_date")
	private String pokerDate;

	private List<ResultDto> results = new ArrayList<>();
}
