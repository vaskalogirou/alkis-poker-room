package com.poker.alkis.services;

import com.poker.alkis.dtos.EarningsDto;
import com.poker.alkis.entities.Player;
import com.poker.alkis.entities.PokerSession;
import com.poker.alkis.enums.PlayerListType;
import com.poker.alkis.enums.Season;
import com.poker.alkis.repos.PlayerRepo;
import com.poker.alkis.repos.PokerSessionRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class EarningsServiceImpl implements EarningsService {

    private final PokerSessionRepo pokerSessionRepo;

    private final PlayerRepo playerRepo;

    private final HelperService helperService;

    public EarningsServiceImpl(final PokerSessionRepo pokerSessionRepo,
                               final PlayerRepo playerRepo,
                               final HelperService helperService) {
        this.pokerSessionRepo = pokerSessionRepo;
        this.playerRepo = playerRepo;
        this.helperService = helperService;
    }

    @Override
    public List<EarningsDto> getEarnings(PlayerListType playerListType) {
//        List<PokerSession> pokerSessions = pokerSessionRepo.findAll();
        List<PokerSession> pokerSessions = pokerSessionRepo.findAllBySeason(Season.SEASON_2025_A);

        List<Player> players;
        if (PlayerListType.ACTIVE.equals(playerListType)) {
            players = playerRepo.findByActiveTrue();
        } else if (PlayerListType.ALL.equals(playerListType)) {
            players = playerRepo.findAll();
        } else if (PlayerListType.RECENTLY_ACTIVE.equals(playerListType)) {
            players = new ArrayList<>();
        } else {
            players = new ArrayList<>();
        }

        players.sort(Comparator.comparing(Player::getName));

        List<EarningsDto> earnings = new ArrayList<>();

        for (Player player : players) {
            int sum = helperService.calculateSum(pokerSessions, player);
            earnings.add(new EarningsDto(player.getName(), sum, player.getColor()));
        }

        return earnings;
    }

}
