package com.pokemon.api.api.controllers;
// Initialize number with 10
import com.pokemon.api.api.Dto.ReviewDto;
import com.pokemon.api.api.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/pokemon/{pokemonId}/reviews")
    public ResponseEntity<ReviewDto> createReview(@PathVariable(value = "pokemonId") int pokemonId, @RequestBody ReviewDto reviewDto) {
        ReviewDto createdReview = reviewService.createReview(pokemonId, reviewDto);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @GetMapping("/pokemon/{pokemonId}/reviews")
    public ResponseEntity<List<ReviewDto>> getReviewsByPokemonId(@PathVariable(value = "pokemonId") int pokemonId) {
        List<ReviewDto> reviews = reviewService.getReviewsByPokemonId(pokemonId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
    @GetMapping("/pokemons/{pokemonId}/reviews/{reviewId}")
    public ResponseEntity<ReviewDto> getReviewById(
            @PathVariable int pokemonId,
            @PathVariable int reviewId) {

        ReviewDto reviewDto = reviewService.getReviewById(reviewId, pokemonId);
        return ResponseEntity.ok(reviewDto);
    }
    @PutMapping("/pokemon/{pokemonId}/reviews/{id}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable(value = "pokemonId") int pokemonId, @PathVariable(value = "id") int reviewId,
                                                  @RequestBody ReviewDto reviewDto) {
        ReviewDto updatedReview = reviewService.updateReview(pokemonId, reviewId, reviewDto);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }
    @DeleteMapping("/pokemon/{pokemonId}/reviews/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable(value = "pokemonId") int pokemonId, @PathVariable(value = "id") int reviewId) {
        reviewService.deleteReview(pokemonId, reviewId);
        return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
    }

}
