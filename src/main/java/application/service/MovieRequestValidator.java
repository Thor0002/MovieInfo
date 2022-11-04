package application.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import application.exception.IllegalRequestException;
import application.model.MovieRequest;

@Component
public class MovieRequestValidator {

	Logger logger = LoggerFactory.getLogger(MovieRequestValidator.class);

	/**
	 * Проверяет запрос на добавление фильма на валидность.
	 * 
	 * @param request - запрос с фильмом.
	 */
	public void validateMovieRequest(MovieRequest request) throws IllegalRequestException {
		logger.info("Validation of a request to add a movie: " + request.toString());
		boolean t = Stream.of(request.getTitle(), request.getDescription(), request.getType(), request.getGenre(),
				request.getDate()).allMatch((o) -> Objects.nonNull(o));
		if (!t) {
			String message = "Неккоректный запрос. Одно из полей не заполнено.";
			throw new IllegalRequestException(message);
		}
	}

	/**
	 * Проверяет запросы на добавление фильмов на валидность.
	 * 
	 * @param requests - запрос с фильмом.
	 */
	public void validateMovieRequests(List<MovieRequest> requests) throws IllegalRequestException {
		logger.info("Validation of a request to add a movies.");
		requests.stream().forEach((r) -> validateMovieRequest(r));
	}

	/**
	 * Проверяет запрос на получение фильмов на валидность.
	 * 
	 * @param title - название фильма.
	 * @param type  - тип фильма.
	 * @param year  - год выхода.
	 */
	public void validateGet(String title, String type, Integer date) {
		logger.info("Validation of a request to get a movies.");
		if (title == null && type == null && date == null) {
			String message = "Не заполнено не одно поле для нахождения фильмов.";
			throw new IllegalRequestException(message);
		}
	}
}
