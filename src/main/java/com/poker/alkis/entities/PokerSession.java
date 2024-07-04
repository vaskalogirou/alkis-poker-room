package com.poker.alkis.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "poker_session")
public class PokerSession {
    
    public PokerSession() {
    }
    
    public PokerSession(Player host, LocalDate pokerDate, String notes) {
        this.host = host;
        this.pokerDate = pokerDate;
        this.notes = notes;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "poker_session_id_seq")
    @SequenceGenerator(name = "poker_session_id_seq", sequenceName = "poker_session_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "host_id", referencedColumnName = "id")
    private Player host;
    
    @OneToMany(mappedBy = "pokerSession", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Result> results = new HashSet<>();
    
    @Column(name = "poker_date")
    private LocalDate pokerDate;
    
    private String notes;
    
}
