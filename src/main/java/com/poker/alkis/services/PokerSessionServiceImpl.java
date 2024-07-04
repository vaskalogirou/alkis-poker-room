package com.poker.alkis.services;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.poker.alkis.dtos.PokerSessionDto;
import com.poker.alkis.dtos.ResultDto;
import com.poker.alkis.entities.Player;
import com.poker.alkis.entities.PokerSession;
import com.poker.alkis.entities.Result;
import com.poker.alkis.mappers.PokerSessionMapper;
import com.poker.alkis.repos.PlayerRepo;
import com.poker.alkis.repos.PokerSessionRepo;

@Service
public class PokerSessionServiceImpl implements PokerSessionService {
    
    private final PokerSessionRepo pokerSessionRepo;
    
    private final PokerSessionMapper pokerSessionMapper;
    
    private final PlayerRepo playerRepo;
    
    public PokerSessionServiceImpl(
        final PokerSessionRepo pokerSessionRepo,
        final PokerSessionMapper pokerSessionMapper,
        final PlayerRepo playerRepo) {
        
        this.pokerSessionRepo = pokerSessionRepo;
        this.pokerSessionMapper = pokerSessionMapper;
        this.playerRepo = playerRepo;
    }
    
    @Override
    public List<PokerSessionDto> getAll() {
        List<PokerSession> pokerSessions = pokerSessionRepo.findAll();
        
        for (PokerSession pokerSession : pokerSessions) {
            Set<Result> results = pokerSession.getResults();
            
            Integer totalIn = results.stream().mapToInt(Result::getCashIn).sum();
            Integer totalOut = results.stream().mapToInt(Result::getCashOut).sum();
            
            Player dummyPlayer = new Player("|_ total _|", "black", Boolean.FALSE);
            Result result = new Result(dummyPlayer, pokerSession, totalIn, totalOut);
            
            results.add(result);
        }
        
        pokerSessions.sort(Comparator.comparing(r -> ((PokerSession) r).getPokerDate()).reversed());
        
        return pokerSessionMapper.toDtos(pokerSessions);
    }
    
    @Override
    public PokerSessionDto create(final PokerSessionDto pokerSessionDto) {
        Player host = playerRepo.findByName(pokerSessionDto.getHost().getName());
        
        PokerSession pokerSession = new PokerSession();
        pokerSession.setHost(host);
        
        pokerSession.setPokerDate(LocalDate.parse(pokerSessionDto.getPokerDate()));
        pokerSession.setNotes(pokerSessionDto.getNotes());
        
        for (ResultDto resultDto : pokerSessionDto.getResults()) {
            if (resultDto.getCashIn().equals(0)) {
                continue;
            }
            Result result = new Result();
            
            Player player = playerRepo.findByName(resultDto.getPlayer().getName());
            result.setPlayer(player);
            result.setCashIn(resultDto.getCashIn());
            result.setCashOut(resultDto.getCashOut());
            result.setPokerSession(pokerSession);
            
            pokerSession.getResults().add(result);
        }
        
        pokerSessionRepo.saveAndFlush(pokerSession);
        
        return pokerSessionMapper.toDto(pokerSession);
    }
    
    @Override
    public void delete(final Long pokerSessionId) {
        PokerSession pokerSessionToDelete = pokerSessionRepo.getById(pokerSessionId);
        
        pokerSessionRepo.delete(pokerSessionToDelete);
    }
    
}
