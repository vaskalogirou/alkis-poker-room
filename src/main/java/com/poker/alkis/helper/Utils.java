package com.poker.alkis.helper;

import com.poker.alkis.dtos.PlayerParticipationDto;
import net.datafaker.Faker;

import java.util.Comparator;
import java.util.List;

public class Utils {

    private static final Faker FAKER = new Faker();

    public static Faker faker() {
        return FAKER;
    }

    public static void loadPercentages(List<PlayerParticipationDto> participations, int totalNumberOfGames) {
        if (totalNumberOfGames == 0) {
            throw new ArithmeticException("Cannot calculate percentages: totalNumberOfGames is zero.");
        }
        participations.forEach(p -> p.setPercentage((double) p.getNumberOfGames() * 100 / totalNumberOfGames));
    }

    public static void sortByGames(List<PlayerParticipationDto> participations) {
        participations.sort(Comparator.comparing(PlayerParticipationDto::getNumberOfGames).reversed());
    }

}
