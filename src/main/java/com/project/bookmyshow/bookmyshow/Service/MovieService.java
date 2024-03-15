package com.project.bookmyshow.bookmyshow.Service;

import com.project.bookmyshow.bookmyshow.DTO.UpdateMovieRequest;
import com.project.bookmyshow.bookmyshow.Entities.Movie;
import com.project.bookmyshow.bookmyshow.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService
{
    @Autowired
    MovieRepository movieRepository;
    public String addMovie(Movie movie)
    {
        movieRepository.save(movie);
        return "Successfully add "+movie.getMovieName()+" with id "+movie.getMovieId()+" into the db";
    }

    public String updateMovieAttribute(UpdateMovieRequest updateMovieRequest)
    {
        Integer movieId=updateMovieRequest.getMovieId();
        Movie movie=movieRepository.findById(movieId).get();

        movie.setDuration(updateMovieRequest.getDuration());
        movie.setRating(updateMovieRequest.getRating());

        movieRepository.save(movie);

        return "Successfully updated the attribue for movie rating and duration";
    }
}
