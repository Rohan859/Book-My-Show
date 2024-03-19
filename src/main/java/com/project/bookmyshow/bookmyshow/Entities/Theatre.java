package com.project.bookmyshow.bookmyshow.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "theatres")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Theatre
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer theatreId;

    private String theatreName;
    private String address;

    private int noOfScreens;

    @OneToMany(mappedBy = "theatre",cascade = CascadeType.ALL)
    List<TheatreSeat>theatreSeatList;

//    @JoinColumn
//    @OneToMany
//    private TheatreSeat theatreSeat;
}
