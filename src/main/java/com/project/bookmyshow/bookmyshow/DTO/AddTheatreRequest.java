package com.project.bookmyshow.bookmyshow.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AddTheatreRequest
{
    private String theatreName;
    private String address;

    private int noOfScreens;
}
