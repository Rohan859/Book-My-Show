package com.project.bookmyshow.bookmyshow.Repository;

import com.project.bookmyshow.bookmyshow.Entities.Movie;
import com.project.bookmyshow.bookmyshow.Entities.Ticket;
import com.project.bookmyshow.bookmyshow.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookTicketRepository extends JpaRepository<Ticket,String>
{

    @Query(nativeQuery = true,value = "select movie_name from ticket where user_user_id = :userId")
    public List<String> findAllMovieListByUser(Integer userId);


//    @Query(nativeQuery = true,value = "select movie_name from ticket where user_user_id = :userId")
//    public List<Movie> findTheUser(Integer userId);
}
