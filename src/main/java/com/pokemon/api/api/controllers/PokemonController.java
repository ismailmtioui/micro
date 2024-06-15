package com.pokemon.api.api.controllers;

import com.pokemon.api.api.Dto.PokemonDto;
import com.pokemon.api.api.Dto.PokemonResponse;
import com.pokemon.api.api.models.Pokemon;
import com.pokemon.api.api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/")
public class PokemonController {
    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    private PokemonService pokemonService;
@GetMapping("Pokemon")
    public ResponseEntity<PokemonResponse> getPokemon(@RequestParam(value ="pageNo" , defaultValue = "0", required = false) int pageNo,
                                      @RequestParam(value ="pageSize" , defaultValue = "10", required = false) int pageSize) {


    return new ResponseEntity<>(pokemonService.getAllPokemon(pageNo, pageSize),HttpStatus.OK);
}
    @GetMapping("Pokemon/{id}")
public ResponseEntity<PokemonDto> pokemonDetails(@PathVariable int id) {
    return ResponseEntity.ok(pokemonService.getPokemonById(id));
    }
@PostMapping("Pokemon/create")
@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDto>createPokemon(@RequestBody PokemonDto pokemonDto) {

    return new ResponseEntity<>(pokemonService.createPokemon(pokemonDto), HttpStatus.CREATED);
}
    @PutMapping("Pokemon/{id}/update")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDto> updatePokemon(@RequestBody PokemonDto pokemonDto,@PathVariable("id") int pokemonid) {
       PokemonDto response = pokemonService.updatePokemon(pokemonDto , pokemonid);
       return new ResponseEntity<>(response, HttpStatus.OK);
}
    @DeleteMapping("Pokemon/{id}/delete")
   public ResponseEntity<String> deletePokemon(@PathVariable("id") int pokemonid) {
  pokemonService.deletePokemon(pokemonid);
  return new ResponseEntity<>("Pokemon deleted", HttpStatus.OK);
    }
}
