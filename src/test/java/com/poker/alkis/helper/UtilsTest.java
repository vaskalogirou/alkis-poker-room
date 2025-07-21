package com.poker.alkis.helper;

import com.poker.alkis.dtos.PlayerParticipationDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void fakerReturnsNonNullSingleton() {
        var faker1 = Utils.faker();
        var faker2 = Utils.faker();
        assertNotNull(faker1);
        assertSame(faker1, faker2, "Utils.faker() should always return the same instance");
    }

    @Test
    void loadPercentagesCalculatesCorrectly() {
        List<PlayerParticipationDto> participations = Arrays.asList(new PlayerParticipationDto("Alice", 5),
                                                                    new PlayerParticipationDto("Bob", 10));
        int totalGames = 20;

        Utils.loadPercentages(participations, totalGames);
        assertEquals(25.0, participations.get(0).getPercentage());
        assertEquals(50.0, participations.get(1).getPercentage());
    }

    @Test
    void loadPercentagesHandlesEmptyList() {
        List<PlayerParticipationDto> participations = new ArrayList<>();

        Utils.loadPercentages(participations, 10);
        assertTrue(participations.isEmpty());
    }

    @Test
    void loadPercentagesHandlesZeroTotalGames() {
        List<PlayerParticipationDto> participations = List.of(new PlayerParticipationDto("Alice", 5));

        assertThrows(ArithmeticException.class, () -> Utils.loadPercentages(participations, 0));
    }

    @Test
    void sortByGamesSortsDescending() {
        PlayerParticipationDto p1 = new PlayerParticipationDto("Alice", 5);
        PlayerParticipationDto p2 = new PlayerParticipationDto("Bob", 10);
        PlayerParticipationDto p3 = new PlayerParticipationDto("Carol", 7);

        List<PlayerParticipationDto> participations = new ArrayList<>(Arrays.asList(p1, p2, p3));
        Utils.sortByGames(participations);
        assertEquals("Bob", participations.get(0).getName());
        assertEquals("Carol", participations.get(1).getName());
        assertEquals("Alice", participations.get(2).getName());
    }

    @Test
    void sortByGamesHandlesEmptyList() {
        List<PlayerParticipationDto> participations = new ArrayList<>();
        Utils.sortByGames(participations);
        assertTrue(participations.isEmpty());
    }

    @Test
    void sortByGamesHandlesSingleElement() {
        PlayerParticipationDto p1 = new PlayerParticipationDto("Alice", 5);
        List<PlayerParticipationDto> participations = new ArrayList<>(List.of(p1));

        Utils.sortByGames(participations);
        assertEquals(1, participations.size());
        assertEquals("Alice", participations.getFirst().getName());
    }

    @Test
    void sortByGamesHandlesEqualGames() {
        PlayerParticipationDto p1 = new PlayerParticipationDto("Alice", 5);
        PlayerParticipationDto p2 = new PlayerParticipationDto("Bob", 5);
        List<PlayerParticipationDto> participations = new ArrayList<>(Arrays.asList(p1, p2));

        Utils.sortByGames(participations);
        // Order is not guaranteed, but both should still be present
        assertTrue(participations.contains(p1));
        assertTrue(participations.contains(p2));
    }
}