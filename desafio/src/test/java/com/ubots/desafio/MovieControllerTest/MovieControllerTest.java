package com.ubots.desafio.MovieControllerTest;


import com.ubots.desafio.Controllers.MovieController;
import com.ubots.desafio.DTO.MovieDTO;
import com.ubots.desafio.Services.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = MovieController.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetMovieById() throws Exception {
        MovieDTO movie = new MovieDTO();
        movie.setId(1L);
        movie.setTitle("Test Movie");

        when(movieService.getMovieById(1L)).thenReturn(Optional.of(movie));

        mockMvc.perform(get("/api/movie/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Movie"));
    }

    @Test
    public void testRecommendUnreviewedMovie() throws Exception {
        MovieDTO movie = new MovieDTO();
        movie.setTitle("Test Movie");

        when(movieService.recommendUnreviewedMovie()).thenReturn(Optional.of(movie));

        mockMvc.perform(get("/api/movie/recommend_unreviewed"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Movie"));
    }

    @Test
    public void testReviewMovie() throws Exception {
        mockMvc.perform(post("/api/review")
                        .param("id", "1")
                        .param("rating", "5"))
                .andExpect(status().isOk());
    }


    @Test
    public void testGetMovieByIdNotFound() throws Exception {
        when(movieService.getMovieById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/movie/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Movie not found with id 1"));
    }

    @Test
    public void testRecommendUnreviewedMovieNotFound() throws Exception {
        when(movieService.recommendUnreviewedMovie()).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/movie/recommend_unreviewed"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("No unreviewed movies found"));
    }

}
