package com.project.bookmyshow.bookmyshow.Entities;

import com.project.bookmyshow.bookmyshow.Enum.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "theatre_seat")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TheatreSeat
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer theatreSeatId;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private String seatNo;


    @JoinColumn
    @ManyToOne
    private Theatre theatre;
}
