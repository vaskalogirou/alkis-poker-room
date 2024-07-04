package com.poker.alkis.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class PlayerDto {
    
    private String id;
    
    @NotBlank(message = "A name is required")
    private String name;
    
    private String color;
    
    private boolean active;
    
}
