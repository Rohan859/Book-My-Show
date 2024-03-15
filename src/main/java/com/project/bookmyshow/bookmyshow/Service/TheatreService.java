package com.project.bookmyshow.bookmyshow.Service;

import com.project.bookmyshow.bookmyshow.DTO.AddTheatreRequest;
import com.project.bookmyshow.bookmyshow.Entities.Theatre;
import com.project.bookmyshow.bookmyshow.Repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheatreService
{
    @Autowired
    private TheatreRepository theatreRepository;

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
}
