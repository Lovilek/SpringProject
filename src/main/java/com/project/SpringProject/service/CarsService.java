package com.project.SpringProject.service;

import com.project.SpringProject.entity.Cars;

import java.util.List;

public interface CarsService {
    List<Cars> findAllCars();
    Cars addCar(Cars car);
    Cars getCar(Long id);
    void deleteCar(Cars car);
    Cars saveCar(Cars car);
}
