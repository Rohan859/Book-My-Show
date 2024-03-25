package com.project.bookmyshow.bookmyshow.Repository;

import com.project.bookmyshow.bookmyshow.Entities.Movie;
import com.project.bookmyshow.bookmyshow.Enum.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer>
{
    @Query(value = "select * from movies where movie_name = :movieName ",nativeQuery = true)
    Movie findMovieByMovieName(String movieName);


    @Query(nativeQuery = true,value = "select * from movies where language = :movie_language")
    List<Movie>findListOfMovieByLanguage(String movie_language);


    @Query(nativeQuery = true,value = "select * from movies where genre = :genre")
    List<Movie>findListOfMoviesByGenre(String genre);
}
