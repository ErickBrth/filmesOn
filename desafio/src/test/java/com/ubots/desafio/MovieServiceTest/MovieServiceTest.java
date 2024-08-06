package com.ubots.desafio.MovieServiceTest;

import com.ubots.desafio.Models.Movie;
import com.ubots.desafio.Repository.MovieRepository;
import com.ubots.desafio.Services.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;public class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieRepository movieRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllMovies() {
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        movie.setDescription("Test Description");
        movie.setReviewed(false);
        when(movieRepository.findAll()).thenReturn(List.of(movie));

        var movies = movieService.getAllMovies();
        assertEquals(1, movies.size());
        assertEquals("Test Movie", movies.get(0).getTitle());
    }

    @Test
    public void testGetMovieById() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Test Movie");
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));

        var movieDTO = movieService.getMovieById(1L);
        assertTrue(movieDTO.isPresent());
        assertEquals("Test Movie", movieDTO.get().getTitle());
    }

    @Test
    public void testCreateMovie() {
        Movie movie = new Movie();
        movie.setTitle("New Movie");
        movie.setDescription("New Description");
        when(movieRepository.save(any(Movie.class))).thenReturn(movie);

        var movieDTO = movieService.createMovie(movieService.convertToDTO(movie));
        assertEquals("New Movie", movieDTO.getTitle());
    }

    @Test
    public void testUpdateMovie() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Updated Movie");
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        when(movieRepository.save(any(Movie.class))).thenReturn(movie);

        var updatedMovieDTO = movieService.updateMovie(1L, movieService.convertToDTO(movie));
        assertTrue(updatedMovieDTO.isPresent());
        assertEquals("Updated Movie", updatedMovieDTO.get().getTitle());
    }

    @Test
    public void testDeleteMovie() {
        when(movieRepository.existsById(1L)).thenReturn(true);
        doNothing().when(movieRepository).deleteById(1L);

        boolean isDeleted = movieService.deleteMovie(1L);
        assertTrue(isDeleted);
    }

    @Test
    public void testRecommendUnreviewedMovie() {
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        movie.setDescription("Test Description");
        movie.setReviewed(false);
        when(movieRepository.findFirstByReviewedFalse()).thenReturn(Optional.of(movie));

        var movieDTO = movieService.recommendUnreviewedMovie();
        assertTrue(movieDTO.isPresent());
        assertEquals("Test Movie", movieDTO.get().getTitle());
    }

    @Test
    public void testReviewMovie() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Test Movie");
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));

        movieService.reviewMovie(1L, 5);
        assertTrue(movie.isReviewed());
        assertEquals(5, movie.getRating());
    }
}