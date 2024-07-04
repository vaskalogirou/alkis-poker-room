package com.poker.alkis.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poker.alkis.entities.Result;

public interface ResultRepo extends JpaRepository<Result, Long> {

}
