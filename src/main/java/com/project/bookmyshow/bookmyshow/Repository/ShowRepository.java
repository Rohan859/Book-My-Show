package com.project.bookmyshow.bookmyshow.Repository;

import com.project.bookmyshow.bookmyshow.Entities.Movie;
import com.project.bookmyshow.bookmyshow.Entities.Show;
import com.project.bookmyshow.bookmyshow.Entities.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show,Integer>
{

    // @Query(nativeQuery = true,value = "select * from shows where show_date = :showDate and show_time = :showTime and ")
    public Show findShowByShowDateAndShowTimeAndMovieAndTheatre(LocalDate showDate,
                                                                LocalTime showTime,
                                                                Movie movie,
                                                                Theatre theatre);

    @Query(nativeQuery = true, value = "select * from shows where theatre_theatre_id = :theatreId")
    public List<Show>getShowByTheatre(Integer theatreId);
}
