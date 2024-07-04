package com.poker.alkis.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "result")
@NoArgsConstructor
public class Result {
    
    public Result(@NotNull Player player, PokerSession pokerSession, Integer cashIn, Integer cashOut) {
        this.player = player;
        this.pokerSession = pokerSession;
        this.cashIn = cashIn;
        this.cashOut = cashOut;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "result_id_seq")
    @SequenceGenerator(name = "result_id_seq", sequenceName = "result_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "player_id")
    @NotNull
    private Player player;
    
    @JoinColumn(name = "poker_session_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PokerSession pokerSession;
    
    @Column(name = "cash_in")
    private Integer cashIn;
    
    @Column(name = "cash_out")
    private Integer cashOut;
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Result result = (Result) o;
        return id != null && Objects.equals(id, result.id);
    }
    
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
    
}
