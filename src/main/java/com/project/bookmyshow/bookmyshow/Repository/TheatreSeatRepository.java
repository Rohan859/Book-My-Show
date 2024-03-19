package com.project.bookmyshow.bookmyshow.Repository;

import com.project.bookmyshow.bookmyshow.Entities.TheatreSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreSeatRepository extends JpaRepository<TheatreSeat,Integer>
{

}
