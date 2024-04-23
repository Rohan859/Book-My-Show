package com.project.bookmyshow.bookmyshow.Repository;

import com.project.bookmyshow.bookmyshow.Entities.Movie;
import com.project.bookmyshow.bookmyshow.Entities.Show;
import com.project.bookmyshow.bookmyshow.Entities.ShowSeat;
import com.project.bookmyshow.bookmyshow.Entities.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat,Integer>
{
    @Query(nativeQuery = true,value = "select * from show_seat where show_show_id = :showId")
    public List<ShowSeat>findAllSeats(Integer showId);


}
