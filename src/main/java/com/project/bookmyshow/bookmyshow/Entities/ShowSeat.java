package com.project.bookmyshow.bookmyshow.Entities;

import com.project.bookmyshow.bookmyshow.Enum.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "show_seat")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowSeat
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer showSeatId;

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private Integer price;

    private boolean isAvailable;

    @JoinColumn
    @ManyToOne
    private Show show;
}
