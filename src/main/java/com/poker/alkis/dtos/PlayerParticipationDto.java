package com.poker.alkis.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class PlayerParticipationDto {
    private String name;
    private int numberOfGames;
    private double percentage;

    public PlayerParticipationDto(String name, int numberOfGames) {
        this.name = name;
        this.numberOfGames = numberOfGames;
    }
}
