package com.poker.alkis.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "player")
public class Player {
    
    public Player() {
    }
    
    public Player(@NotNull String name, String color, boolean active) {
        this.name = name;
        this.color = color;
        this.active = active;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_id_seq")
    @SequenceGenerator(name = "player_id_seq", sequenceName = "player_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "name", nullable = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String name;
    
    @Column(name = "color")
    @Size(min = 1, max = 50)
    private String color;
    
    @Column(name = "active")
    private boolean active;
    
}
