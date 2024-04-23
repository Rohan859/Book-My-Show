package com.project.bookmyshow.bookmyshow.Controller;

import com.project.bookmyshow.bookmyshow.DTO.BookTicketRequest;
import com.project.bookmyshow.bookmyshow.Entities.Movie;
import com.project.bookmyshow.bookmyshow.Exceptions.SeatNotAvailableException;
import com.project.bookmyshow.bookmyshow.Service.BookTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController
{
    @Autowired
    private BookTicketService bookTicketService;

    @PostMapping("/bookTicket")
    public ResponseEntity bookTicket(@RequestBody BookTicketRequest bookTicketRequest)
    {
        try
        {
            String result=bookTicketService.bookTicket(bookTicketRequest);
            return new ResponseEntity(result, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity("not booked "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getMovieListByUser")
    public List<String> findAllMovieListByUser(@RequestParam Integer userId)
    {
        List<String>movieList=bookTicketService.findAllMovieListByUser(userId);
        return movieList;
    }

    @GetMapping("/recommendation")
    public List<String>recommendation(@RequestParam Integer userId)
    {
        List<String>movieList=bookTicketService.recommendation(userId);
        return movieList;
    }
}
