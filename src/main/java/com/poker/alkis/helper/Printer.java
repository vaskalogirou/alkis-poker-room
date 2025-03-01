package com.poker.alkis.helper;

import com.poker.alkis.dtos.PlayerParticipationDto;
import org.apache.commons.lang3.StringUtils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

public class Printer {

    public static String buildParticipationsString(List<PlayerParticipationDto> participations) {

        DecimalFormat df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.CEILING);
        StringBuilder result = new StringBuilder();
        for (PlayerParticipationDto participation : participations) {
            result.append("\n").append(buildSingleParticipationString(participation, df));
        }
        return result.toString();
    }

    private static String buildSingleParticipationString(PlayerParticipationDto participation, DecimalFormat df) {
        String name = StringUtils.rightPad(participation.getName(), 15, Constants.SPACE);
        String numOfGames = StringUtils.rightPad(String.valueOf(participation.getNumberOfGames()), 7, Constants.SPACE);
        String percentage = StringUtils.rightPad(df.format(participation.getPercentage()).concat("%"), 10, Constants.SPACE);

        return name.concat(numOfGames).concat(percentage);
    }
}
