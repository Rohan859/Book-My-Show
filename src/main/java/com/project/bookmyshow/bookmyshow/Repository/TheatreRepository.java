package com.project.bookmyshow.bookmyshow.Repository;

import com.project.bookmyshow.bookmyshow.Entities.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre,Integer>
{

}
