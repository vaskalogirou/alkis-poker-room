package com.poker.alkis.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EarningsDto {

	@JsonProperty("player_name")
	private String playerName;
	
	@JsonProperty("net_earnings")
	private Integer netEarnings;
	
	private String color;
}
