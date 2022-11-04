package application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import application.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
	
	@Query(
			"SELECT m FROM Movie m WHERE "  +
	"(:title is null or m.title = :title) and" +
	"(:type is null or m.type = :type) and" + 
	"(:year is null or year(m.date) = :year)"
	)
	List<Movie> findByTitleOrTypeOrDate(@Param("title") String title,@Param("type") String type,
			@Param("year") Integer year);
	
	

}
