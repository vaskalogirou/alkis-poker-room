package com.poker.alkis.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poker.alkis.entities.PokerSession;

public interface PokerSessionRepo extends JpaRepository<PokerSession, Long> {

}
