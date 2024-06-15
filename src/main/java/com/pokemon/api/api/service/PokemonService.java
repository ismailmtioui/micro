package com.pokemon.api.api.service;

import com.pokemon.api.api.Dto.PokemonDto;
import com.pokemon.api.api.Dto.PokemonResponse;
import com.pokemon.api.api.models.Pokemon;

import java.util.List;

public interface PokemonService {
    PokemonDto createPokemon(PokemonDto pokemonDto);
    PokemonResponse getAllPokemon(int pageNo, int pageSize);
    PokemonDto getPokemonById(int id);

    PokemonDto updatePokemon(PokemonDto pokemonDto, int id);
   void deletePokemon(int id);
}
