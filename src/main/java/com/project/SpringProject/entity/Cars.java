package com.project.SpringProject.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cars")
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "model" ,nullable = false)
    private String model;
    @Column(name = "value",nullable = false)
    private String value;
    @Column(name = "price",nullable = false)
    private String price;


}
