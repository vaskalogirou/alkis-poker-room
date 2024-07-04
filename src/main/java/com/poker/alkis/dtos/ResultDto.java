package com.poker.alkis.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ResultDto {
	private PlayerDto player;

	@JsonProperty(value = "cash_in")
	private Integer cashIn;

	@JsonProperty(value = "cash_out")
	private Integer cashOut;

	private Integer total;
}
