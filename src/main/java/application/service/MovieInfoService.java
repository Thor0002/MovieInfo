package application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import application.exception.IllegalRequestException;
import application.model.Movie;
import application.model.MovieRequest;
import application.repository.MovieRepository;

@Service
public class MovieInfoService {

	Logger logger = LoggerFactory.getLogger(MovieInfoService.class);

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private MovieRequestValidator validator;

	@Autowired
	private MovieMapper mapper;

	/**
	 * Сервисный метод обработки запроса на добавление фильма. Проверяет запрос на
	 * валидность. После сохраняет фильм в базу.
	 * 
	 * @param request - запрос с фильмом.
	 */
	public void movie(MovieRequest request) throws IllegalRequestException {
		validator.validateMovieRequest(request);
		Movie movie = mapper.toMovie(request);
		movieRepository.save(movie);
		logger.info("Save movie: " + movie.toString());
	}

	/**
	 * Сервисный метод обработки запроса на добавление фильмом. Проверяет запрос на
	 * валидность. После сохраняет фильм в базу.
	 * 
	 * @param request - Список запросов с фильмами.
	 */
	public void movies(@RequestBody List<MovieRequest> requests) {
		validator.validateMovieRequests(requests);
		List<Movie> movies = requests.stream().map(mapper::toMovie).collect(Collectors.toList());
		movies.stream().forEach((m) -> {
			movieRepository.save(m);
			logger.info("Save movie: " + m.toString());
		});
	}

	/**
	 * Сервисный метод обработки запроса на получение фильмов. Возращает список с
	 * фильмами ли сохранить.
	 * 
	 * @param title - название фильма.
	 * @param type  - тип фильма.
	 * @param year  - год выхода.
	 */
	public List<Movie> get(String title, String type, Integer year) {
		validator.validateGet(title, type, year);
		logger.info("Get movie by request");
		return movieRepository.findByTitleOrTypeOrDate(title, type, year);
	}

}
