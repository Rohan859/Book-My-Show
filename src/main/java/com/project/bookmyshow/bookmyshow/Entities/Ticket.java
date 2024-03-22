package com.project.bookmyshow.bookmyshow.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "ticket")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class Ticket
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ticketId;

    private String movieName;

    private LocalDate showDate;

    private LocalTime showTime;

    private String theatreNameAndAddress;

    private Integer totalAmountPaid;

    private List<String> seatNos;


    @JoinColumn
    @ManyToOne
    private User user;
}
