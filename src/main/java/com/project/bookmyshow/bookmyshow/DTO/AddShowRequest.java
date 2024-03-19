package com.project.bookmyshow.bookmyshow.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data

public class AddShowRequest
{
    private LocalTime showTime;
    private LocalDate showDate;

    private String movieName;
    private Integer theatreId;
}
