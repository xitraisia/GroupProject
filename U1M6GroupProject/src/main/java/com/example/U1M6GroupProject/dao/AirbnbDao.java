package com.example.U1M6GroupProject.dao;

import com.example.U1M6GroupProject.model.Airbnb;

import java.util.List;

public interface AirbnbDao {


    //create
    //delete
    //update
    //getAll
    //getbyId

    Airbnb addAirbnb(Airbnb airbnb);

    Airbnb getAirbnb(int id);

    List<Airbnb> getAllAirbnbs();

    void updateAirbnb(Airbnb airbnb);

    void deleteAirbnb(int id);

}
