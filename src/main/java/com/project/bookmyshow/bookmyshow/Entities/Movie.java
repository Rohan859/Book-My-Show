package com.project.bookmyshow.bookmyshow.Entities;

import com.project.bookmyshow.bookmyshow.Enum.Genre;
import com.project.bookmyshow.bookmyshow.Enum.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Movies")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movie
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    private LocalDate releaseDate;

    @Column(unique = true)
    private String movieName;

    private double duration;
    private double rating;

    @Enumerated(value = EnumType.STRING)
    private Language language;
}
