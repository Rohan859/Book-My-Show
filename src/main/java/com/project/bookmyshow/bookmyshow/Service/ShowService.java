package com.project.bookmyshow.bookmyshow.Service;

import com.project.bookmyshow.bookmyshow.DTO.AddShowRequest;
import com.project.bookmyshow.bookmyshow.DTO.AddShowSeatRequest;
import com.project.bookmyshow.bookmyshow.Entities.*;
import com.project.bookmyshow.bookmyshow.Enum.SeatType;
import com.project.bookmyshow.bookmyshow.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService
{
    @Autowired
    ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;


    public String addShow(AddShowRequest addShowRequest)
    {

        Movie movie=movieRepository.findMovieByMovieName(addShowRequest.getMovieName());
        Theatre theatre=theatreRepository.findById(addShowRequest.getTheatreId()).get();

        Show show=Show.builder()
                .showDate(addShowRequest.getShowDate())
                .showTime(addShowRequest.getShowTime())
                .movie(movie)
                .theatre(theatre)
                .build();
        showRepository.save(show);

        return "Successfully saved the show with id "+show.getShowId();
    }

    public String addShowSeat(AddShowSeatRequest addShowSeatRequest)
    {
        Integer showId=addShowSeatRequest.getShowId();

        Show show=showRepository.findById(showId).get();

        Theatre theatre=show.getTheatre();


        List<TheatreSeat>theatreSeatList=theatre.getTheatreSeatList();

        //generate show seat list

        List<ShowSeat>showSeatList=new ArrayList<>();

        for(TheatreSeat theatreSeat:theatreSeatList)
        {
            ShowSeat showSeat=ShowSeat.builder()
                    .seatNo(theatreSeat.getSeatNo())
                    .isAvailable(true)
                    .show(show)
                    .seatType(theatreSeat.getSeatType())
                    .build();

            if(showSeat.getSeatType().equals(SeatType.CLASSIC))
            {
                showSeat.setPrice(addShowSeatRequest.getPriceForClassicSeat());
            }
            else
            {
                showSeat.setPrice(addShowSeatRequest.getPriceForPremiumSeat());
            }

            showSeatList.add(showSeat);
        }

        showSeatRepository.saveAll(showSeatList);

        return "All the show seats are generated";

    }
}
