package com.project.bookmyshow.bookmyshow.Controller;

import com.project.bookmyshow.bookmyshow.DTO.AddTheatreRequest;
import com.project.bookmyshow.bookmyshow.Service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theatre")
public class TheatreController
{
    @Autowired
    private TheatreService theatreService;

    @PostMapping("/add")
    public String addTheatre(@RequestBody AddTheatreRequest addTheatreRequest)
    {
        String result=theatreService.addTheatre(addTheatreRequest);
        return result;
    }


}
