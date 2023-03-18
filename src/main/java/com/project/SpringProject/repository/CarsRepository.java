package com.project.SpringProject.repository;

import com.project.SpringProject.entity.Cars;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarsRepository extends JpaRepository<Cars,Long> {
}
