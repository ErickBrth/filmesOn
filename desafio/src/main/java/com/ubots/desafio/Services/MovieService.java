package com.ubots.desafio.Services;

import com.ubots.desafio.DTO.MovieDTO;
import com.ubots.desafio.Models.Movie;
import com.ubots.desafio.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<MovieDTO> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<MovieDTO> getMovieById(Long id) {
        return movieRepository.findById(id)
                .map(this::convertToDTO);
    }

    public MovieDTO createMovie(MovieDTO movieDTO) {
        Movie movie = convertToEntity(movieDTO);
        Movie savedMovie = movieRepository.save(movie);
        return convertToDTO(savedMovie);
    }

    public Optional<MovieDTO> updateMovie(Long id, MovieDTO movieDTO) {
        return movieRepository.findById(id).map(movie -> {
            movie.setTitle(movieDTO.getTitle());
            movie.setDescription(movieDTO.getDescription());
            movie.setReviewed(movieDTO.isReviewed());
            movie.setRating(movieDTO.getRating());
            return convertToDTO(movieRepository.save(movie));
        });
    }

    public boolean deleteMovie(Long id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<MovieDTO> recommendUnreviewedMovie() {
        return movieRepository.findFirstByReviewedFalse()
                .map(this::convertToDTO);
    }

    public void reviewMovie(Long id, int rating) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        movie.setRating(rating);
        movie.setReviewed(true);

        movieRepository.save(movie);
    }

    private MovieDTO convertToDTO(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setDescription(movie.getDescription());
        movieDTO.setReviewed(movie.isReviewed());
        movieDTO.setRating(movie.getRating());
        return movieDTO;
    }

    private Movie convertToEntity(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setId(movieDTO.getId());
        movie.setTitle(movieDTO.getTitle());
        movie.setDescription(movieDTO.getDescription());
        movie.setReviewed(movieDTO.isReviewed());
        movie.setRating(movieDTO.getRating());
        return movie;
    }
}
