package com.pokemon.api.api.repository;

import com.pokemon.api.api.Dto.ReviewDto;
import com.pokemon.api.api.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
   List<Review> findByPokemonId(int pokemonId) ;

}
