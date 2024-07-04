package com.poker.alkis.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
