package com.project.bookmyshow.bookmyshow.Service;

import com.project.bookmyshow.bookmyshow.DTO.BookTicketRequest;
import com.project.bookmyshow.bookmyshow.Entities.*;
import com.project.bookmyshow.bookmyshow.Enum.Language;
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


    public List<String>recommendation(Integer userId)
    {
        List<String>movieList=bookTicketRepository.findAllMovieListByUser(userId);

        int freq[]=new int[10];
        //0 for hindi, 1 for english, 2 for bengali, 3 for tamil, 4 for telegu
        //5 for panjabi, 6 for bhojpuri, 7 for odi, 8 for marathi, 9 for malayalam



        for(String movieName:movieList)
        {
            Movie movie=movieRepository.findMovieByMovieName(movieName);

            if(movie.getLanguage()== Language.HINDI)
            {
                freq[0]++;
            }
            else if(movie.getLanguage()== Language.ENGLISH)
            {
                freq[1]++;
            }
            else if(movie.getLanguage()== Language.BENGALI)
            {
                freq[2]++;
            }
            else if(movie.getLanguage()== Language.TAMIL)
            {
                freq[3]++;
            }
            else if(movie.getLanguage()== Language.TELEGU)
            {
                freq[4]++;
            }
            else if(movie.getLanguage()== Language.PANGABI)
            {
                freq[5]++;
            }
            else if(movie.getLanguage()== Language.BHOJPURI)
            {
                freq[6]++;
            }
            else if(movie.getLanguage()== Language.ODI)
            {
                freq[7]++;
            }
            else if(movie.getLanguage()== Language.MARATHI)
            {
                freq[8]++;
            }
            else
            {
                freq[9]++;
            }
        }


        int max=Integer.MIN_VALUE;
        int index=-1;

        for(int i=0;i<10;i++)
        {
            if(max<freq[i])
            {
                max=freq[i];
                index=i;
            }
        }

        if(index==0)
        {
            List<String>movieNames=movieRepository.getListOfMovieByLanguage("HINDI");
            return movieNames;
        }
        else if(index==1)
        {
            List<String>movieNames=movieRepository.getListOfMovieByLanguage("ENGLISH");
            return movieNames;
        }
        else if(index==2)
        {
            List<String>movieNames=movieRepository.getListOfMovieByLanguage("BENGALI");
            return movieNames;
        }
        else if(index==3)
        {
            List<String>movieNames=movieRepository.getListOfMovieByLanguage("TAMIL");
            return movieNames;
        }
        else if(index==4)
        {
            List<String>movieNames=movieRepository.getListOfMovieByLanguage("TELEGU");
            return movieNames;
        }
        else if(index==5)
        {
            List<String>movieNames=movieRepository.getListOfMovieByLanguage("PANGABI");
            return movieNames;
        }
        else if(index==6)
        {
            List<String>movieNames=movieRepository.getListOfMovieByLanguage("BHOJPURI");
            return movieNames;
        }
        else if(index==7)
        {
            List<String>movieNames=movieRepository.getListOfMovieByLanguage("ODI");
            return movieNames;
        }
        else if(index==8)
        {
            List<String>movieNames=movieRepository.getListOfMovieByLanguage("MARATHI");
            return movieNames;
        }
        else
        {
            List<String>movieNames=movieRepository.getListOfMovieByLanguage("MALAYLAM");
            return movieNames;
        }



    }
}
