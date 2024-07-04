package com.poker.alkis.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
