package com.poker.alkis.repos;

import com.poker.alkis.entities.PokerSession;
import com.poker.alkis.enums.Season;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PokerSessionRepo extends JpaRepository<PokerSession, Long> {
    List<PokerSession> findAllBySeason(Season season);
}
