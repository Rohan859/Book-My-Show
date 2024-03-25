package com.project.bookmyshow.bookmyshow.Controller;

import com.project.bookmyshow.bookmyshow.DTO.AddShowRequest;
import com.project.bookmyshow.bookmyshow.DTO.AddShowSeatRequest;
import com.project.bookmyshow.bookmyshow.Entities.Show;
import com.project.bookmyshow.bookmyshow.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getShowByTheatre")
    public List<Show> getShowByTheatre(@RequestParam Integer theatreId)
    {
        List<Show>showList=showService.getShowByTheatre(theatreId);
        return showList;
    }
}
