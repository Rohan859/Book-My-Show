package com.project.bookmyshow.bookmyshow.DTO;

import lombok.Data;

@Data
public class AddShowSeatRequest
{
    private Integer showId;
    private Integer priceForClassicSeat;
    private Integer priceForPremiumSeat;
}
