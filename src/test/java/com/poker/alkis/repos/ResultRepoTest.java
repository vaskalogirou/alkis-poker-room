package com.poker.alkis.repos;

import com.poker.alkis.AlkisPokerRoomApplicationTests;
import com.poker.alkis.entities.Player;
import com.poker.alkis.entities.PokerSession;
import com.poker.alkis.entities.Result;
import com.poker.alkis.enums.Season;
import com.poker.alkis.helper.Fixtures;
import com.poker.alkis.services.PlayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResultRepoTest extends AlkisPokerRoomApplicationTests {

    @Autowired
    private ResultRepo resultRepo;

    @Autowired
    private Fixtures fixtures;

    @Autowired
    private PlayerService playerService;

    @Test
    void saveResultWorksCorrectly() {
        Player player = playerService.getOrCreatePlayer("vasilis");

        PokerSession pokerSession = fixtures.buildAndSavePokerSession();

        Result result = new Result(player, pokerSession, 20, 100);

        resultRepo.saveAndFlush(result);

        Result retrieved = resultRepo.findById(result.getId()).orElse(new Result());

        assertEquals("vasilis", retrieved.getPlayer().getName());
        assertEquals(LocalDate.now().plusYears(1), retrieved.getPokerSession().getPokerDate());
        assertEquals(Season.SEASON_2025_A, retrieved.getPokerSession().getSeason());
    }

}
