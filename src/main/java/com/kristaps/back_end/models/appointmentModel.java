package com.kristaps.back_end.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data

public class AppointmentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String AppointmentName;
    private String AppointmentDate;
    private String AppointmentTime;
    private String AppointmentLocation;
    private Integer MaxNumberofPeople;
    private Integer CurrentNumberofPeople;
    private String Description;
    private Boolean Registered;
}
