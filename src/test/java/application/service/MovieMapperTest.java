package application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import application.exception.IllegalRequestException;
import application.model.Movie;
import application.model.MovieRequest;

@SpringBootTest
public class MovieMapperTest {
	
	@Autowired
	private MovieMapper mapper;
	
	private final static String TITLE = "title";
	private final static String DESCRIPTION = "description";
	private final static String TYPE = "type";
	private final static String GENRE = "genre";
	private final static LocalDate DATE = LocalDate.now();
	

	@Test
	void toMovieOkTest() {
		MovieRequest request = new MovieRequest(TITLE, DESCRIPTION, TYPE, GENRE, DATE.toString());
		
		Movie movie = mapper.toMovie(request);
		assertEquals(movie.getTitle(), request.getTitle());
		assertEquals(movie.getDescription(), request.getDescription());
		assertEquals(movie.getType(), request.getType());
		assertEquals(movie.getGenre(), request.getGenre());
		assertEquals(movie.getDate(), DATE);
	}
	
	@Test
	void toMovieWithIllegalDate() {
		MovieRequest request = new MovieRequest();
		request.setDate("date");
		
		assertThrows(IllegalRequestException.class,() -> mapper.toMovie(request));
	}
}
