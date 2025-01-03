package com.poker.alkis.controllers;

import com.poker.alkis.dtos.EarningsDto;
import com.poker.alkis.enums.PlayerListType;
import com.poker.alkis.helper.Constants;
import com.poker.alkis.services.EarningsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("earnings")
public class EarningsController {

    private final EarningsService earningsService;

    @Autowired
    public EarningsController(final EarningsService earningsService) {
        this.earningsService = earningsService;
    }

    @GetMapping
    public List<EarningsDto> getEarnings(@RequestParam(defaultValue = "ACTIVE") PlayerListType type) {
        return earningsService.getEarnings(type, Constants.CURRENT_SEASON);
    }

}
