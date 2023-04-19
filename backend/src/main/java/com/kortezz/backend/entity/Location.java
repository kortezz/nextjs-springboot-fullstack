package com.kortezz.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "location")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String city;
    private String district;
}
