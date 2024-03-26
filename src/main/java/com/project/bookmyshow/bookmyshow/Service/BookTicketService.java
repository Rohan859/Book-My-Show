package com.project.bookmyshow.bookmyshow.Service;

import com.project.bookmyshow.bookmyshow.DTO.BookTicketRequest;
import com.project.bookmyshow.bookmyshow.Entities.*;
import com.project.bookmyshow.bookmyshow.Exceptions.SeatNotAvailableException;
import com.project.bookmyshow.bookmyshow.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookTicketService
{
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private BookTicketRepository bookTicketRepository;

    public String bookTicket(BookTicketRequest bookTicketRequest) throws SeatNotAvailableException
    {
        //1. calculate total amount
        Movie movie=movieRepository.findMovieByMovieName(bookTicketRequest.getMovieName());
        Theatre theatre=theatreRepository.findById(bookTicketRequest.getTheatreId()).get();

        //1.1 find show entity by date, time, movie and theatre
        Show show=showRepository.findShowByShowDateAndShowTimeAndMovieAndTheatre(bookTicketRequest.getShowDate(),
                                                                                bookTicketRequest.getShowTime(),
                                                                                 movie,theatre);

        Integer showId=show.getShowId();

        List<ShowSeat>showSeatList=showSeatRepository.findAllSeats(showId);



        //1.2 calculate the price and if all seats mentioned available or not

        int totalAmount=0;
        boolean isAllSeatAvailable=true; //assuming all the requested seats are available for booking

        for(String seatNo:bookTicketRequest.getRequestedSeatNos())
        {
            for(ShowSeat showSeat:showSeatList)
            {
                if(showSeat.getSeatNo().equals(seatNo))
                {
                    if(showSeat.isAvailable()==false)
                    {
                        //it means not all requested seats are available
                        isAllSeatAvailable=false;
                        break;
                    }
                    totalAmount+=showSeat.getPrice();
                }
            }
        }


        if(isAllSeatAvailable==false)
        {
            throw new SeatNotAvailableException("Not all requested seats are available");
        }


        //2. mark isAvailable is booked

        for(String seatNo:bookTicketRequest.getRequestedSeatNos())
        {
            for(ShowSeat showSeat:showSeatList)
            {
                if(showSeat.getSeatNo().equals(seatNo))
                {
                    showSeat.setAvailable(false);
                }
            }
        }

        //create user
        User user=userRepository.findUserByMobileNo(bookTicketRequest.getMobileNo());

        //generate ticket
        Ticket ticket=Ticket.builder()
                .movieName(bookTicketRequest.getMovieName())
                .showDate(bookTicketRequest.getShowDate())
                .showTime(bookTicketRequest.getShowTime())
                .theatreNameAndAddress(theatre.getTheatreName()+" "+theatre.getAddress())
                .totalAmountPaid(totalAmount)
                .seatNos(bookTicketRequest.getRequestedSeatNos())
                .user(user)
                .build();


        //3. save the ticket entity
        ticket=bookTicketRepository.save(ticket);

        //4. generate and return back actual ticket response

        return "Successfully ticket got generated "+ticket.toString();
    }



    public List<String> findAllMovieListByUser(Integer userId)
    {
        List<String>movieList=bookTicketRepository.findAllMovieListByUser(userId);
        return movieList;
    }
}
