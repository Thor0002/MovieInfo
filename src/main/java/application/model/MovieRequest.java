package application.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Запрос на добавление фильма.
 * 
 */
public class MovieRequest implements Serializable {

	private static final long serialVersionUID = -4665970253132007547L;

	private String title;
	private String description;
	private String type;
	private String genre;
	private String date;

	public MovieRequest() {
	}

	public MovieRequest(String title, String description, String type, String genre, String date) {
		super();
		this.title = title;
		this.description = description;
		this.type = type;
		this.genre = genre;
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("( ");
		Optional.ofNullable(title).ifPresent((s) -> {str.append(s + " ");});
		Optional.ofNullable(description).ifPresent((s) -> {str.append(s + " ");});
		Optional.ofNullable(type).ifPresent((s) -> {str.append(s + " ");});
		Optional.ofNullable(genre).ifPresent((s) -> {str.append(s + " ");});
		Optional.ofNullable(date).ifPresent((s) -> {str.append(s + " ");});
		return str.toString();
	}
}
