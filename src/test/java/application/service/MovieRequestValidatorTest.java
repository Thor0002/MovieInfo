package application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import application.exception.IllegalRequestException;
import application.model.MovieRequest;

@SpringBootTest
public class MovieRequestValidatorTest {
	
	@Autowired
	private MovieRequestValidator validator;
	
	private final static String TITLE = "title";
	private final static String DESCRIPTION = "description";
	private final static String TYPE = "type";
	private final static String GENRE = "genre";
	private final static String DATE = "date";
	
	@Test
	void validateMovieRequestOkTest() {
		MovieRequest request = new MovieRequest(TITLE, DESCRIPTION, TYPE, GENRE, DATE);
		validator.validateMovieRequest(request);
	}
	
	@Test
	void validateMovieRequestWithNullTitleTest() {
		MovieRequest request = new MovieRequest(null, DESCRIPTION, TYPE, GENRE, DATE);
		assertThrows(IllegalRequestException.class,() -> validator.validateMovieRequest(request));
	}
	
	@Test
	void validateMovieRequestWithNullTypeTest() {
		MovieRequest request = new MovieRequest(TITLE, DESCRIPTION, null, GENRE, DATE);
		assertThrows(IllegalRequestException.class,() -> validator.validateMovieRequest(request));
	}
	
	@Test
	void validateMovieRequestsOkTest() {
		List<MovieRequest> requests = new ArrayList<>();
		requests.add(new MovieRequest(TITLE, DESCRIPTION, TYPE, GENRE, DATE));
		validator.validateMovieRequests(requests);
	}
	
	@Test
	void validateMovieRequestsWithNullTitleTest() {
		List<MovieRequest> requests = new ArrayList<>();
		requests.add(new MovieRequest(null, DESCRIPTION, TYPE, GENRE, DATE));
		assertThrows(IllegalRequestException.class,() -> validator.validateMovieRequests(requests));
	}
	
	@Test
	void validateMovieRequestsWithNullTypeTest() {
		List<MovieRequest> requests = new ArrayList<>();
		requests.add(new MovieRequest(TITLE, DESCRIPTION, null, GENRE, DATE));
		assertThrows(IllegalRequestException.class,() -> validator.validateMovieRequests(requests));
	}

	@Test
	void validateGetOkTest() {
		validator.validateGet(TITLE, TYPE, null);
	}
	
	@Test
	void validateGetWithNullTitleTest() {
		assertThrows(IllegalRequestException.class,() -> validator.validateGet(null, null, null));
	}
}
