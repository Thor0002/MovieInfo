package application.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import application.model.Movie;
import application.model.MovieRequest;
import application.repository.MovieRepository;

@SpringBootTest
public class MovieInfoServiceTest {
	
	@MockBean
	private Logger logger;
	
	@MockBean
	private MovieRequestValidator validator;
	
	@MockBean
	private MovieRepository movieRepository;
	
	@MockBean
	private MovieMapper mapper;
	
	@Autowired
	private MovieInfoService movieInfoService;
	
	@Test
	void movieTest() {
		MovieRequest request = new MovieRequest();
		Movie movie = new Movie();
		when(mapper.toMovie(any(MovieRequest.class))).thenReturn(movie);
		movieInfoService.movie(request);
		
		verify(validator).validateMovieRequest(request);
		verify(mapper).toMovie(eq(request));
		verify(movieRepository).save(eq(movie));
	}
	
	@Test
	void moviesTest() {
		List<MovieRequest> requests = new ArrayList<>();
		requests.add(new MovieRequest());
		when(mapper.toMovie(any(MovieRequest.class))).thenReturn(new Movie());
		movieInfoService.movies(requests);
		
		verify(validator).validateMovieRequests(eq(requests));
		verify(mapper, times(requests.size())).toMovie(any(MovieRequest.class));
		verify(movieRepository, times(requests.size())).save(any(Movie.class));
	}
	
	@Test
	void getTest() {
		String title = "title"; 
		String type = "type"; 
		Integer year = 2000;
		when(movieRepository.findByTitleOrTypeOrDate(any(String.class), any(String.class), any(Integer.class)))
		.thenReturn(new ArrayList<>());
		movieInfoService.get(title, type, year);
		
		verify(validator).validateGet(eq(title), eq(type), eq(year));
		verify(movieRepository).findByTitleOrTypeOrDate(eq(title), eq(type), eq(year));
	}

}
