package com.ubots.desafio.Controllers;


import com.ubots.desafio.DTO.MovieDTO;
import com.ubots.desafio.Services.MovieService;
import com.ubots.desafio.responses.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("movies")
    public List<MovieDTO> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("movie/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable Long id) {
        Optional<MovieDTO> movie = movieService.getMovieById(id);
        if (movie.isPresent()) {
            return ResponseEntity.ok(movie.get());
        } else {
            return ResponseEntity.status(404).body(new ErrorResponse(404, "Not Found", "Movie not found with id " + id));
        }
    }

    @PostMapping("movie/create_movie")
    public MovieDTO createMovie(@RequestBody MovieDTO movieDTO) {
        return movieService.createMovie(movieDTO);
    }

    @PutMapping("/movie/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable Long id, @RequestBody MovieDTO movieDTO) {
        Optional<MovieDTO> updatedMovie = movieService.updateMovie(id, movieDTO);
        if (updatedMovie.isPresent()) {
            return ResponseEntity.ok(updatedMovie.get());
        } else {
            return ResponseEntity.status(404).body(new ErrorResponse(404, "Not Found", "Movie not found with id " + id));
        }
    }

    @DeleteMapping("movie/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        if (movieService.deleteMovie(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(404).body(new ErrorResponse(404, "Not Found", "Movie not found with id " + id));
        }
    }

    @GetMapping("movie/recommend_unreviewed")
    public ResponseEntity<?> recommendUnreviewedMovie() {
        Optional<MovieDTO> movie = movieService.recommendUnreviewedMovie();
        if (movie.isPresent()) {
            return ResponseEntity.ok(movie.get());
        } else {
            return ResponseEntity.status(404).body(new ErrorResponse(404, "Not Found", "No unreviewed movies found"));
        }
    }

    @PostMapping("/review")
    public ResponseEntity<?> reviewMovie(@RequestParam Long id, @RequestParam int rating) {
        try {
            movieService.reviewMovie(id, rating);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ErrorResponse(404, "Not Found", e.getMessage()));
        }
    }
}