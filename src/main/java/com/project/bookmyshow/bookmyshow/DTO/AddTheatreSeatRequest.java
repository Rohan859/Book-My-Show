package com.project.bookmyshow.bookmyshow.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AddTheatreSeatRequest
{
    private int noOfClassicSeats;
    private int noOfPremiumSeats;
    private int theatreId;
}
