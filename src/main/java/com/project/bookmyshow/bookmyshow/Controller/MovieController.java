package com.project.bookmyshow.bookmyshow.Controller;

import com.project.bookmyshow.bookmyshow.DTO.UpdateMovieRequest;
import com.project.bookmyshow.bookmyshow.Entities.Movie;
import com.project.bookmyshow.bookmyshow.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController
{
    @Autowired
    private MovieService movieService;

    @PostMapping("/add")
    public String addMovie(@RequestBody Movie movie)
    {
        String rseult=movieService.addMovie(movie);
        return rseult;
    }

    @PutMapping("/updateMovieAttribute")
    public String updateMovieAttribute(@RequestBody UpdateMovieRequest updateMovieRequest)
    {
        String result=movieService.updateMovieAttribute(updateMovieRequest);
        return result;
    }
}
