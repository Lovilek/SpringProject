package com.project.SpringProject.impl;

import com.project.SpringProject.entity.Cars;
import com.project.SpringProject.repository.CarsRepository;
import com.project.SpringProject.service.CarsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class CarsServiceImpl implements CarsService {

   final CarsRepository carsRepository;


    @Override
    public List<Cars> findAllCars() {
        return carsRepository.findAll();
    }

    @Override
    public Cars addCar(Cars car) {
        return carsRepository.save(car);
    }

    @Override
    public Cars getCar(Long id) {
        return carsRepository.getOne(id);
    }

    @Override
    public void deleteCar(Cars car) {
     carsRepository.delete(car);
    }

    @Override
    public Cars saveCar(Cars car) {
        return carsRepository.save(car);
    }
}
