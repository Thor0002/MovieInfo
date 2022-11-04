package application.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import application.model.Movie;
import application.model.MovieRequest;
import application.service.MovieInfoService;

@SpringBootTest
public class MovieInfoControllerTest {

	@MockBean
	private MovieInfoService movieInfoService;
	
	@Autowired
	private MovieInfoController movieInfoController;
	
	@Test
	void movieTest() {
		MovieRequest request = new MovieRequest();
		ResponseEntity response = movieInfoController.movie(request);
		
		verify(movieInfoService).movie(eq(request));
		Assertions.assertEquals(response.getBody(), "Фильм сохранён");
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	void moviesTest() {
		List<MovieRequest> requests = new ArrayList<>();
		ResponseEntity response = movieInfoController.movies(requests);
		
		verify(movieInfoService).movies(eq(requests));
		Assertions.assertEquals(response.getBody(), "Фильмы сохранены");
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	void getTest() {
		String title = "title"; 
		String type = "type"; 
		Integer year = 2000;
		List<Movie> movies = new ArrayList<>();
		
		when(movieInfoService.get(any(String.class), any(String.class), any(Integer.class))).thenReturn(movies);
		ResponseEntity response = movieInfoController.get(title, type, year);
		
		verify(movieInfoService).get(eq(title), eq(type), eq(year));
		Assertions.assertEquals(response.getBody(), movies);
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
}
