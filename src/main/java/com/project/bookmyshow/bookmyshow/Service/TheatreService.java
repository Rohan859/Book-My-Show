package com.project.bookmyshow.bookmyshow.Service;

import com.project.bookmyshow.bookmyshow.DTO.AddTheatreRequest;
import com.project.bookmyshow.bookmyshow.DTO.AddTheatreSeatRequest;
import com.project.bookmyshow.bookmyshow.Entities.Theatre;
import com.project.bookmyshow.bookmyshow.Entities.TheatreSeat;
import com.project.bookmyshow.bookmyshow.Enum.SeatType;
import com.project.bookmyshow.bookmyshow.Repository.TheatreRepository;
import com.project.bookmyshow.bookmyshow.Repository.TheatreSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreService
{
    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private TheatreSeatRepository theatreSeatRepository;

    public String addTheatre(AddTheatreRequest addTheatreRequest)
    {
//        //convert request to entity
//        Theatre theatre=new Theatre();
//        theatre.setTheatreName(addTheatreRequest.getTheatreName());
//        theatre.setNoOfScreens(addTheatreRequest.getNoOfScreens());
//        theatre.setAddress(addTheatreRequest.getAddress());

        Theatre theatre=Theatre.builder().theatreName(addTheatreRequest.getTheatreName())
                .address(addTheatreRequest.getAddress())
                        .noOfScreens(addTheatreRequest.getNoOfScreens()).build();

        //save into db
        theatreRepository.save(theatre);

        return "Theare is created in db with id "+theatre.getTheatreId();
    }

    public String addTheatreSeats(AddTheatreSeatRequest addTheatreSeatRequest)
    {
        int noOfClassicSeats=addTheatreSeatRequest.getNoOfClassicSeats();
        int noOfPremiumSeats=addTheatreSeatRequest.getNoOfPremiumSeats();

        //get the theatre object
        Theatre myTheatre=theatreRepository.findById(addTheatreSeatRequest.getTheatreId()).get();


        //add into the list of theatre list

        List<TheatreSeat>theatreSeatList=new ArrayList<>();

        //i want each row will contain at most 5 seats
        //for classic seats
        int countClassicSeats=1;
        int row=1;
        char ch='A';

        while(countClassicSeats<=noOfClassicSeats)
        {
            //seatNo = 1A

            String seatNo=row+""+ch;
            ch++;

            TheatreSeat theatreSeat=TheatreSeat.builder()
                    .seatNo(seatNo)
                    .seatType(SeatType.CLASSIC)
                    .theatre(myTheatre)
                    .build();
            theatreSeatList.add(theatreSeat);

            countClassicSeats++;

            if(countClassicSeats%5==0)
            {
                ch='A';
                row++;
            }
        }

        //for premium seats

        int countPremiumSeats=1;
         row=row+1;
         ch='A';

        while(countPremiumSeats<=noOfPremiumSeats)
        {
            //seatNo = 1A

            String seatNo=row+""+ch;
            ch++;

            TheatreSeat theatreSeat=TheatreSeat.builder()
                    .seatNo(seatNo)
                    .theatre(myTheatre)
                    .seatType(SeatType.PREMIUM)
                    .build();
            theatreSeatList.add(theatreSeat);

            countPremiumSeats++;

            if(countPremiumSeats%5==0)
            {
                ch='A';
                row++;
            }
        }

        myTheatre.setTheatreSeatList(theatreSeatList);
        theatreSeatRepository.saveAll(theatreSeatList); // save ane
        theatreRepository.save(myTheatre);            //of them other will be automatically saved by casecading effect

        return "All seats are arranged for Classic and Premium";
    }
}
