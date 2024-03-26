package com.project.bookmyshow.bookmyshow.Controller;

import com.project.bookmyshow.bookmyshow.DTO.UpdateMovieRequest;
import com.project.bookmyshow.bookmyshow.Entities.Movie;
import com.project.bookmyshow.bookmyshow.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController
{
    @Autowired
    private MovieService movieService;

    @PostMapping("/addMovie")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addMovie(@RequestBody Movie movie)
    {
        String rseult=movieService.addMovie(movie);
        return rseult;
    }

    @PutMapping("/updateMovieAttribute")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String updateMovieAttribute(@RequestBody UpdateMovieRequest updateMovieRequest)
    {
        String result=movieService.updateMovieAttribute(updateMovieRequest);
        return result;
    }

    @GetMapping("/getAllMovie")
    public List<Movie> getAllMovie()
    {
        List<Movie>movieList=movieService.getAllMovie();
        return movieList;
    }

    @GetMapping("/getMovieListByLanguage")
    public List<Movie>getMovieListByLanguage(@RequestParam String language)
    {
        List<Movie>movieList=movieService.getMovieListByLanguage(language);
        return movieList;
    }

    @GetMapping("/getMovieListByGenre")
    public List<Movie>getMovieListByGenre(@RequestParam String genre)
    {
        List<Movie>movieList=movieService.getMovieListByGenre(genre);
        return movieList;
    }
}
