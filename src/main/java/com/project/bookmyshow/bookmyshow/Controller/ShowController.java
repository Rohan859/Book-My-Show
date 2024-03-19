package com.project.bookmyshow.bookmyshow.Controller;

import com.project.bookmyshow.bookmyshow.DTO.AddShowRequest;
import com.project.bookmyshow.bookmyshow.DTO.AddShowSeatRequest;
import com.project.bookmyshow.bookmyshow.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show")
public class ShowController
{
    @Autowired
    private ShowService showService;

    @PostMapping("/addShow")
    public String addShow(@RequestBody AddShowRequest addShowRequest)
    {
        String result=showService.addShow(addShowRequest);
        return result;
    }

    @PostMapping("/addShowSeats")
    public String addShowSeat(@RequestBody AddShowSeatRequest addShowSeatRequest)
    {
        String result = showService.addShowSeat(addShowSeatRequest);
        return result;
    }
}
