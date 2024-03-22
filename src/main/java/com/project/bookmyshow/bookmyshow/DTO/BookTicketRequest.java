package com.project.bookmyshow.bookmyshow.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class BookTicketRequest
{
    private String movieName;
    private LocalDate showDate;
    private LocalTime showTime;

    private List<String>requestedSeatNos;

    private String mobileNo;

    private Integer theatreId;


}
