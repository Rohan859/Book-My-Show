package com.project.bookmyshow.bookmyshow.Repository;

import com.project.bookmyshow.bookmyshow.Entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer>
{
    @Query(value = "select * from movies where movie_name = :movieName ",nativeQuery = true)
    Movie findMovieByMovieName(String movieName);
}
