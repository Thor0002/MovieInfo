package application.model;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "movies", uniqueConstraints = 
{@UniqueConstraint(columnNames = {"title", "description", "type", "genre", "date"})})
public class Movie {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "genre")
	private String genre;
	
	@Column(name = "date")
	private LocalDate date;
	
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
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
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
