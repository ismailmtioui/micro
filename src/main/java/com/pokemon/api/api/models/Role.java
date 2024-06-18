package com.pokemon.api.api.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name ="roles")

public class Role {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
   private String name;




}
