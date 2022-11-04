package application.service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Component;

import application.exception.IllegalRequestException;
import application.model.Movie;
import application.model.MovieRequest;

@Component
public class MovieMapper {

	/**
	 * Мапит запрос на добавление фильма на валидность.
	 * 
	 * @param request - запрос с фильмом.
	 */
	public Movie toMovie(MovieRequest request) {
		Movie movie = new Movie();
		movie.setTitle(request.getTitle());
		movie.setDescription(request.getDescription());
		movie.setType(request.getType());
		movie.setGenre(request.getGenre());
		LocalDate date;
		try {
			date = LocalDate.parse(request.getDate());
		} catch (DateTimeParseException e) {
			String message = "Некорректно введена дата: " + e.getMessage();
			throw new IllegalRequestException(message);
		}
		movie.setDate(date);
		return movie;
	}
}
