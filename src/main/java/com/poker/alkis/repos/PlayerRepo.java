package com.poker.alkis.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poker.alkis.entities.Player;

public interface PlayerRepo extends JpaRepository<Player, Long> {

	Player findByName(String name);
	
	List<Player> findByActiveTrue();

}
