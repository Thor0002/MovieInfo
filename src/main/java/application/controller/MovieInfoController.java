package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import application.exception.IllegalRequestException;
import application.model.Movie;
import application.model.MovieRequest;
import application.service.MovieInfoService;

import java.util.List;

@RestController
@RequestMapping("/movie-api")
public class MovieInfoController {

	@Autowired
	private MovieInfoService movieInfoService;

	/**
	 * REST метод обработки запроса на добавление фильма в виде json. Возращает HTTP
	 * ответ с информацие удалось ли сохранить.
	 * 
	 * @param request - запрос с фильмом полученный из json.
	 */
	@PostMapping(path = "/add/movie", consumes = "application/json")
	@ResponseBody
	public ResponseEntity movie(@RequestBody MovieRequest request) {
		movieInfoService.movie(request);
		String message = "Фильм сохранён";
		return new ResponseEntity(message, HttpStatus.OK);
	}

	/**
	 * REST метод обрабокти запроса на добавление фильмов в виде json. Возращает
	 * HTTP ответ с информацией удалось ли сохранить.
	 * 
	 * @param requests - Список запросов с фильмами полученный из json.
	 */
	@PostMapping(path = "/add/movies", consumes = "application/json")
	@ResponseBody
	public ResponseEntity movies(@RequestBody List<MovieRequest> requests) {
		movieInfoService.movies(requests);
		String message = "Фильмы сохранены";
		return new ResponseEntity(message, HttpStatus.OK);
	}

	/**
	 * REST метод обработки запроса на получение фильмов. Возращает HTTP ответ с
	 * фильмами.
	 * 
	 * @param title - название фильма.
	 * @param type  - тип фильма.
	 * @param year  - год выхода.
	 */
	@GetMapping("/get/movies")
	public ResponseEntity get(@RequestParam(required = false) String title, @RequestParam(required = false) String type,
			@RequestParam(required = false) Integer year) {
		List<Movie> movies = movieInfoService.get(title, type, year);
		return new ResponseEntity(movies, HttpStatus.OK);

	}
}
